import kotlin.properties.Delegates
import kotlin.reflect.KProperty


fun main(args: Array<String>) {
    //TODO #LAZY
    val lazyProperty: DelegateObject by lazy {
        // Вычисление значения только при первом обращении
        DelegateObject()
    }
    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }
    println(lazyValue)
    println(lazyValue)

    //TODO #observableProperty
    var observableProperty: String by Delegates.observable("Initial Value") { _, old, new ->
        // Действия при изменении значения свойства
        println("Значение свойства изменено с $old на $new")
    }
    observableProperty = "New value"

    //TODO #PropertyHolder
    class PropertyHolder {
        private val properties = mutableMapOf<String, Any>()

        operator fun <T> get(key: String): T? {
            return properties[key] as? T
        }

        operator fun set(key: String, value: Any) {
            properties[key] = value
        }
    }

    val holder = PropertyHolder()
    holder["property1"] = "Value 1"
    holder["property2"] = 42

    val value1: String? = holder["property1"]
    val value2: Int? = holder["property2"]


    class User(val map: Map<String, Any?>) {
        val name: String by map
        val age: Int     by map
    }

    val user = User(mapOf(
        "name" to "John Doe",
        "age"  to 25
    ))

    //TODO #Delegate

    val delegate by DelegateObject()
    println(delegate)


    val objects:String by DelegateObject()
    println(objects)

}


class Example {
    var property: String by DelegateObject()
}

class DelegateObject {
    private var _value: String = "FROM DelegateObject"

    val value: LoggerDelegate
        get() = LoggerDelegate()

    operator fun getValue(thisRef: Example?, property: KProperty<*>): String {
        println("GET DelegateObject")
        return _value + "666"
    }

    operator fun setValue(thisRef: Example?, property: KProperty<*>, value: String) {
        println("SET DelegateObject")
        this._value = value + "777"
    }
}



//interface Messenger{
//    fun send(message: String)
//}
//class InstantMessenger(val programName: String) : Messenger{
//    override fun send(message: String) = println("Send message: `$message`")
//}
//interface PhotoDevice{
//    fun takePhoto()
//}
//class PhotoCamera: PhotoDevice{
//    override fun takePhoto() = println("Take a photo")
//}
//class SmartPhone(val name: String, m: Messenger, p: PhotoDevice)
//    : Messenger by  m, PhotoDevice by p
//



class Person{
    var name: String by LoggerDelegate()
}
class LoggerDelegate {
    operator fun getValue(thisRef: Person, property: KProperty<*>): String {
        println("Запрошено свойство: ${property.name}")
        return "Tom"
    }

    operator fun setValue(thisRef: Person, property: KProperty<*>, value: String): String {
        println("Изменено свойство на $value")
        return "Tom"
    }

    operator fun getValue(nothing: Nothing?, property: KProperty<*>): String {
        println("val state by delegateObject.value")
        return "LoggerDelegate"
    }
}



