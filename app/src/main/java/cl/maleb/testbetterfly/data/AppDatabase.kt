package cl.maleb.testbetterfly.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cl.maleb.testbetterfly.api.detail.CharacterDetailData
import cl.maleb.testbetterfly.api.list.ResultData
import cl.maleb.testbetterfly.utils.Converters

@Database(
    entities = [ResultData::class, CharacterDetailData::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterListDao(): CharacterListDao

    abstract fun characterDetailDao(): CharacterDetailDao

}