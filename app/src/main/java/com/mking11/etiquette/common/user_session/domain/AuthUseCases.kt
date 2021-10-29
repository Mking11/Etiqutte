package com.mking11.etiquette.common.user_session.domain

import com.mking11.etiquette.common.user_session.domain.use_case.SignInAnonymous
import com.mking11.etiquette.common.user_session.domain.use_case.SignOut
import kotlinx.coroutines.ExperimentalCoroutinesApi

data class AuthUseCases @ExperimentalCoroutinesApi constructor(
    val signInAnonymous: SignInAnonymous,
    val signOut: SignOut
)