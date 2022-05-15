fun personalChat(chat: Chat) {

    println("Чат с ${chat.recipient}")

    while (true) {
        val idMessage = chat.message.size
        if (idMessage == 0) {
            println(
                "---------------------- \n" +
                        "1 - Написать новое сообщение \n" +
                        "0 - Выйти из чата \n" +
                        "----------------------"
            )
        } else {
            println(
                "---------------------- \n" +
                        "1 - Написать новое сообщение \n" +
                        "2 - Показать список сообщений \n" +
                        "3 - Показать список непрочитанных сообщений \n" +
                        "4 - Удалить сообщение \n" +
                        "0 - Выйти из чата \n" +
                        "----------------------"
            )
        }

        when (readLine()) {
            "1" -> newMessage(chat)
            "2" -> chat.message.forEach { println(it) }
            "3" -> {
                chat.message.filter { !it.isRead }
                    .forEach { println(it) }
                for (message in chat.message) {
                    message.isRead = true
                }
            }
            "4" -> deleteMessage(chat)
            "0" -> return
        }
    }
}