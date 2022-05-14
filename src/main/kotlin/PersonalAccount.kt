fun personalAccount(user: User) {

    println("Добро пожаловать, ${user.login}")

    while (true) {
        val idChat = user.chatList.size
        if (idChat == 0) {
            println(
                "---------------------- \n" +
                        "1 - Начать новый чат \n" +
                        "0 - Выйти из аккаунта \n" +
                        "----------------------"
            )

            when (readLine()) {
                "1" -> {
                    println("С кем хотите начать переписку?")
                    val login = readLine() ?: return
                    createNewChat(login, user)
                    personalChat(user.chatList.last())
                }
                "0" -> return
            }
        } else {
            println(
                "---------------------- \n" +
                        "1 - Начать новый чат \n" +
                        "2 - Показать список чатов \n" +
                        "3 - Показать список чатов с непрочитанными сообщениями \n" +
                        "4 - Открыть чат \n" +
                        "5 - Удалить чат \n" +
                        "0 - Выйти из аккаунта \n" +
                        "----------------------"
            )

            when (readLine()) {
                "1" -> {
                    println("С кем хотите начать переписку?")
                    val login = readLine() ?: return
                    createNewChat(login, user)
                    personalChat(user.chatList.last())
                }
                "2" -> user.chatList.forEach { println(it) }
                "3" -> {
                    user.chatList.filter(::filterChat)
                        .forEach { println(it) }
                }
                "4" -> {
                    println("С кем хотите продолжить переписку?")
                    val login = readLine() ?: return
                    for (chat in user.chatList) {
                        if (chat.recipient == login) {
                            personalChat(chat)
                        } else {
                            println("Чат с данным пользователем еще не создан")
                        }
                    }
                }
                "5" -> {
                    println("Напишите id чата")
                    val idInput = readLine()?.toInt() ?: return
                    for (chat in user.chatList) {
                        if (chat.id == idInput) {
                            user.chatList.remove(chat)
                            println("Чат со всеми сообщениями удален!")
                            break
                        } else {
                            println("Чата с таким id не найдено")
                        }
                    }
                }
                "0" -> return
            }
        }
    }
}