package cl.maleb.testbetterfly.data.repository

import cl.maleb.testbetterfly.api.CharacterApiService
import cl.maleb.testbetterfly.data.AppDatabase
import cl.maleb.testbetterfly.utils.networkBoundResource
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val apiService: CharacterApiService,
    private val appDatabase: AppDatabase
) {

    private val characterListDao = appDatabase.characterListDao()
    private val characterDetailDao = appDatabase.characterDetailDao()

    fun getCharacterList() = networkBoundResource(
        databaseQuery = {
            characterListDao.getCharacterList()
        },
        networkCall = {
            apiService.getCharacterList()
        },
        saveCallResult = {
            it.results?.let { characterList -> characterListDao.insertCharacterList(characterList) }
        }
    )

    fun getCharacterDetail(characterIdentifier: String) = networkBoundResource(
        databaseQuery = {
            characterDetailDao.getCharacterDetail()
        },
        networkCall = {
            apiService.getCharacterDetail(characterIdentifier)
        },
        saveCallResult = {
            characterDetailDao.insertCharacterDetail(it)
        }
    )

}