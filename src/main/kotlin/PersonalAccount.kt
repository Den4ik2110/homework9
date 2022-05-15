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
                "1" -> newChat(user)
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
                "1" -> newChat(user)
                "2" -> user.chatList.forEach { println(it) }
                "3" -> {
                    user.chatList.filter(::filterChat)
                        .forEach { println(it) }
                }
                "4" -> continueChat(user)
                "5" -> {
                    println("Напишите id чата")
                    val idInput = readLine()?.toInt() ?: break
                    deleteChat(user, idInput)
                }
                "0" -> return
            }
        }
    }
}