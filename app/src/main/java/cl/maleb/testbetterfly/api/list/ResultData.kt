package cl.maleb.testbetterfly.api.list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resultDataTable")
data class ResultData(
    val created: String? = null,
    val episode: List<String>? = null,
    val gender: String? = null,
    @PrimaryKey val id: Int,
    val image: String? = null,
    val location: LocationListData? = null,
    val name: String? = null,
    val origin: OriginListData? = null,
    val species: String? = null,
    val status: String? = null,
    val type: String? = null,
    val url: String? = null
)