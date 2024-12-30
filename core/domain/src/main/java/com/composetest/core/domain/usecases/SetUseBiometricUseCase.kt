package com.composetest.core.domain.usecases

import com.composetest.core.domain.managers.BiometricRepository
import javax.inject.Inject

class SetUseBiometricUseCase @Inject constructor(
    private val repository: BiometricRepository
) {

    suspend operator fun invoke(useBiometric: Boolean) {
        repository.setUseBiometric(useBiometric)
    }
}