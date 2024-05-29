package Сompleteness

import org.example.Models.User

sealed class ViewState {
    data object Loading : ViewState()
    data class Success(val data: List<User>) : ViewState()
    data class Error(val message: String) : ViewState()
}

//Use sealed classes when you have a fixed set of related states or types
// that require exhaustive handling, such as UI states or network request states.



sealed interface Loggable {
    fun log()
}

data object FileLogger : Loggable {
    override fun log() {
        // Implementation for file logging
    }
}

data object DatabaseLogger : Loggable {
    override fun log() {
        // Implementation for database logging
    }
}

// Usage example:
fun logAll(loggables: List<Loggable>) {
    loggables.forEach { it.log() }
}

//Используйте запечатанные интерфейсы, если вы хотите определить закрытый набор типов,
// которые имеют общее поведение, но в остальном не связаны друг с другом, например,
// разные типы средств журналирования.