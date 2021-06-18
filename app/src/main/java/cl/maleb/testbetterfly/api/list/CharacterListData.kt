package cl.maleb.testbetterfly.api.list

data class CharacterListData(
    val info: InfoData? = null,
    val results: List<ResultData>? = null
)