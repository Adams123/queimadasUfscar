package com.ufscar.queimadas_front.ui.home

import com.ufscar.queimadas_front.data.repository.AuthRepository
import com.ufscar.queimadas_front.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel(repository) {
}