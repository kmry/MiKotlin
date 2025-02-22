/*
 * Copyright (C) 2015 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.antonioleiva.mikotlin.ui.presenter

import com.antonioleiva.mikotlin.domain.interactor.GetRecommendedArtistsInteractor
import com.antonioleiva.mikotlin.domain.interactor.base.Bus
import com.antonioleiva.mikotlin.domain.interactor.base.InteractorExecutor
import com.antonioleiva.mikotlin.domain.interactor.event.ArtistsEvent
import com.antonioleiva.mikotlin.ui.entity.ImageTitle
import com.antonioleiva.mikotlin.ui.entity.mapper.ImageTitleDataMapper
import com.antonioleiva.mikotlin.ui.view.MainView

class MainPresenter(
        override val view: MainView,
        override val bus: Bus,
        val recommendedArtistsInteractor: GetRecommendedArtistsInteractor,
        val interactorExecutor: InteractorExecutor,
        val mapper: ImageTitleDataMapper) : Presenter<MainView> {

    override fun onResume() {
        super.onResume()
        interactorExecutor.execute(recommendedArtistsInteractor)
    }

    fun onEvent(event: ArtistsEvent) {
        view.showArtists(mapper.transformArtists(event.artists))
    }

    fun onArtistClicked(item: ImageTitle) {
        view.navigateToDetail(item.id)
    }
}