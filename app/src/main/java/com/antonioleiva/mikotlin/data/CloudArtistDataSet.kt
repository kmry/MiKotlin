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

package com.antonioleiva.mikotlin.data

import com.antonioleiva.mikotlin.data.lastfm.LastFmService
import com.antonioleiva.mikotlin.data.mapper.ArtistMapper
import com.antonioleiva.mikotlin.domain.entity.Artist
import com.antonioleiva.mikotlin.repository.dataset.ArtistDataSet



public class CloudArtistDataSet(val language: String, val lastFmService: LastFmService) : ArtistDataSet {

    override fun requestRecommendedArtists(): List<Artist> {
        // Search for coldplay similar artists.
        val result = lastFmService.requestSimilar("79239441-bfd5-4981-a70c-55c3f15c1287")
        //mbid result of:
        //curl 'http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist=Madonna&api_key=XXX' > xmls

        return ArtistMapper().transform(result.similarArtists.artists)
    }

    override fun requestArtist(id: String): Artist {
        val result = lastFmService.requestArtistInfo(id, language)
        return ArtistMapper().transform(result.artist)
    }
}