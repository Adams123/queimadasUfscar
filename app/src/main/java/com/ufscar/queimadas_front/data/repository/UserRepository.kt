package com.ufscar.queimadas_front.data.repository

import com.ufscar.queimadas_front.data.network.UserApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi
): BaseRepository(api)