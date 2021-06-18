package cl.maleb.testbetterfly.ui.list

sealed class CharacterListEvent {
    data class NavigateToDetailScreen(val characterIdentifier: String) : CharacterListEvent()
}
