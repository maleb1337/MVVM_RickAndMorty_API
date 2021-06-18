package cl.maleb.testbetterfly.utils

import androidx.room.TypeConverter
import cl.maleb.testbetterfly.api.detail.LocationDetailData
import cl.maleb.testbetterfly.api.detail.OriginDetailData
import cl.maleb.testbetterfly.api.list.LocationListData
import cl.maleb.testbetterfly.api.list.OriginListData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun restoreListString(listOfString: String?): List<String?>? {
        return Gson().fromJson(listOfString, object : TypeToken<List<String?>?>() {}.type)
    }

    @TypeConverter
    fun saveListString(listOfString: List<String?>?): String? {
        return Gson().toJson(listOfString)
    }

    @TypeConverter
    fun restoreLocationDetailData(objectToRestore: String?): LocationDetailData? {
        return Gson().fromJson(objectToRestore, object : TypeToken<LocationDetailData?>() {}.type)
    }

    @TypeConverter
    fun saveLocationDetailData(objectToSave: LocationDetailData?): String? {
        return Gson().toJson(objectToSave)
    }

    @TypeConverter
    fun restoreLocationListData(objectToRestore: String?): LocationListData? {
        return Gson().fromJson(objectToRestore, object : TypeToken<LocationListData?>() {}.type)
    }

    @TypeConverter
    fun saveLocationListData(objectToSave: LocationListData?): String? {
        return Gson().toJson(objectToSave)
    }

    @TypeConverter
    fun restoreOriginDetailData(objectToRestore: String?): OriginDetailData? {
        return Gson().fromJson(objectToRestore, object : TypeToken<OriginDetailData?>() {}.type)
    }

    @TypeConverter
    fun saveOriginDetailData(objectToSave: OriginDetailData?): String? {
        return Gson().toJson(objectToSave)
    }

    @TypeConverter
    fun restoreOriginData(objectToRestore: String?): OriginListData? {
        return Gson().fromJson(objectToRestore, object : TypeToken<OriginListData?>() {}.type)
    }

    @TypeConverter
    fun saveOriginData(objectToSave: OriginListData?): String? {
        return Gson().toJson(objectToSave)
    }


}