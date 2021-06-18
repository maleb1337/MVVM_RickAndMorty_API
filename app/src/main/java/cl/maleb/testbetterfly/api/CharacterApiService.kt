package cl.maleb.testbetterfly.api

import cl.maleb.testbetterfly.api.detail.CharacterDetailData
import cl.maleb.testbetterfly.api.list.CharacterListData
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApiService {

    companion object {
        const val GET_CHARACTER_LIST = "character/"
        const val GET_CHARACTER_DETAIL = "character/{characterId}"
    }

    @GET(GET_CHARACTER_LIST)
    suspend fun getCharacterList(): CharacterListData

    @GET(GET_CHARACTER_DETAIL)
    suspend fun getCharacterDetail(
        @Path("characterId") characterIdentifier: String
    ): CharacterDetailData

}