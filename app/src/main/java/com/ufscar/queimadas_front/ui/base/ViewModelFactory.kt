package com.ufscar.queimadas_front.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ufscar.queimadas_front.data.repository.AuthRepository
import com.ufscar.queimadas_front.data.repository.BaseRepository
import com.ufscar.queimadas_front.data.repository.MapsRepository
import com.ufscar.queimadas_front.data.repository.UserRepository
import com.ufscar.queimadas_front.ui.UserViewModel
import com.ufscar.queimadas_front.ui.auth.login.LoginViewModel
import com.ufscar.queimadas_front.ui.auth.registration.RegistrationViewModel
import com.ufscar.queimadas_front.ui.firelist.FireListViewModel
import com.ufscar.queimadas_front.ui.home.MapsViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(UserViewModel::class.java) -> UserViewModel(repository as UserRepository) as T
            modelClass.isAssignableFrom(RegistrationViewModel::class.java) -> RegistrationViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> MapsViewModel(repository as MapsRepository) as T
            modelClass.isAssignableFrom(FireListViewModel::class.java) -> FireListViewModel(repository as MapsRepository) as T
            else -> throw IllegalArgumentException("ViewModel class not found")
        }
    }
}