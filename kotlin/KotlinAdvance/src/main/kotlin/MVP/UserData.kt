package MVP

// Модель (Model)
data class UserData(var name: String, var age: Int)

// Представление (View)
interface UserView {
    fun showUserInfo(name: String, age: Int)
}

// Презентер (Presenter)
class UserPresenter(private val view: UserView) {
    private var userData: UserData = UserData("John Doe", 30)

    fun onUserUpdateButtonClicked(newName: String, newAge: Int) {
        userData.name = newName
        userData.age = newAge
        view.showUserInfo(userData.name, userData.age)
    }
}


// Пример использования
class MainActivity : UserView {
    private var presenter: UserPresenter = UserPresenter(this)

    fun updateUser() {
        presenter.onUserUpdateButtonClicked("MAX", 100)
    }

    override fun showUserInfo(name: String, age: Int) {
        println("Name: $name, Age: $age")
    }
}

fun main() {
    val mainActivity = MainActivity()
    mainActivity.showUserInfo("NULL", 0)
    mainActivity.updateUser()
}