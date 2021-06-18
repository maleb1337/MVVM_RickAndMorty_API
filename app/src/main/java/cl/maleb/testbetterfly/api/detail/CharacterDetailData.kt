package cl.maleb.testbetterfly.api.detail

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characterDetailDataTable")
data class CharacterDetailData(
    val created: String? = null,
    val episode: List<String>? = null,
    val gender: String? = null,
    @PrimaryKey val id: Int,
    val image: String? = null,
    val location: LocationDetailData? = null,
    val name: String? = null,
    val origin: OriginDetailData? = null,
    val species: String? = null,
    val status: String? = null,
    val type: String? = null,
    val url: String? = null
)