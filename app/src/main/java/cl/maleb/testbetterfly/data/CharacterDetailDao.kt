package cl.maleb.testbetterfly.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.maleb.testbetterfly.api.detail.CharacterDetailData
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterDetail(characterDetailData: CharacterDetailData)

    @Query("SELECT * FROM characterDetailDataTable")
    fun getCharacterDetail(): Flow<CharacterDetailData>

    @Query("DELETE FROM characterDetailDataTable")
    suspend fun deleteCharacter()

}