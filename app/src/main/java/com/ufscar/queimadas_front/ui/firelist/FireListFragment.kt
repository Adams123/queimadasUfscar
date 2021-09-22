package com.ufscar.queimadas_front.ui.firelist

import android.location.Geocoder
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ufscar.queimadas_front.R
import com.ufscar.queimadas_front.data.UserPreferences
import com.ufscar.queimadas_front.data.network.Resource
import com.ufscar.queimadas_front.databinding.FragmentFireListBinding
import com.ufscar.queimadas_front.utils.handleApiError
import com.ufscar.queimadas_front.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class FireListFragment : Fragment(R.layout.fragment_fire_list) {

    @Inject
    lateinit var userPreferences: UserPreferences

    private lateinit var binding: FragmentFireListBinding
    private val viewModel by viewModels<FireListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFireListBinding.bind(view)

        val adapter = LocationAdapter(requireContext(), R.layout.fragment_fire_list)
        binding.theListView.adapter = adapter

        val geocoder = Geocoder(requireContext(), Locale.forLanguageTag("pt-BR"))

        viewModel.locationsResponse.observe(viewLifecycleOwner, { response ->
            binding.fireListLoader.visible(response is Resource.Loading)
            when(response){
                is Resource.Success -> {
                    response.value.forEach{
                        val address = geocoder.getFromLocation(it.latitude, it.longitude, 1);
                        it.locationName = address[0].getAddressLine(0)
                        adapter.add(it)
                    }
                    binding.fireListLoader.visible(false)
                    adapter.notifyDataSetChanged()
                }
                is Resource.Loading -> {
                    binding.fireListLoader.visible(true)
                }
                is Resource.Failure -> {
                    handleApiError(response) { fetchFireList() }
                }
            }
        })

        fetchFireList()
    }


    private fun fetchFireList(){
        viewModel.fetchLocations()
    }
}