package com.genaku.interactor

import com.genaku.domain.StartUseCase
import com.genaku.domain.interfaces.interactors.IStartInteractor
import org.jetbrains.anko.doAsync

/**
 * Created by Gena Kuchergin on 21.07.2018.
 * © 2018 Gena Kuchergin. All Rights Reserved.
 */
class StartInteractor(private val useCase: StartUseCase) : IStartInteractor {

    override fun doOnStart() {
        doAsync {
            useCase.doOnStart()
        }
    }

}