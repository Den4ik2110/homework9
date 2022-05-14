var userList = mutableListOf<User>()

fun main() {

    while (true) {
        println(
            "---------------------- \n" +
                    "1 - Создать нового пользователя \n" +
                    "2 - Войти в существующий аккаунт \n" +
                    "0 - Закрыть программу \n" +
                    "----------------------"
        )

        when (readLine()) {
            "1" -> {
                while (true) {
                    println("Придумайте логин (Не менее 3 символов) \nДля отмены ввода оставьте поле пустым и нажмите ENTER")
                    val loginNew = readLine() ?: return
                    if (loginNew.length < 3) break
                    if (checkLogin(loginNew)) {
                        println("Придумайте пароль")
                        val passwordNew = readLine() ?: return
                        val maxId = userList.maxOfOrNull { it.id }
                        userList.add(User(loginNew, passwordNew, maxId?.plus(1) ?: 1))
                        println("Новый пользователь создан")
                    } else {
                        println("Пользователь с таким логином уже существует \n" + "")
                        continue
                    }
                    break
                }
            }

            "2" -> {
                println("Введите логин \nДля отмены ввода оставьте поле пустым и нажмите ENTER")
                val loginInput = readLine() ?: break
                if (loginInput.isEmpty()) continue
                println("Введите пароль")
                val passwordInput = readLine() ?: break
                val userAccount = getUserByLoginAndPassword(loginInput, passwordInput)
                println("Успешная авторизация")
                personalAccount(userAccount)
                continue
            }

            "0" -> return

            else -> {
                println("Неизвестная команда")
                continue
            }
        }
    }
}