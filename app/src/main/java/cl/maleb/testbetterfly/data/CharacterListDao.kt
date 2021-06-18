package cl.maleb.testbetterfly.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.maleb.testbetterfly.api.list.ResultData
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterList(resultData: List<ResultData>)

    @Query("SELECT * FROM resultDataTable")
    fun getCharacterList(): Flow<List<ResultData>>

    @Query("DELETE FROM resultDataTable")
    suspend fun deleteCharacterList()

}