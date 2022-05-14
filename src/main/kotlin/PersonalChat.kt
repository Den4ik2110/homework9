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
            "1" -> {
                println("Введите текст сообщения \nДля отмены оставьте поле пустым и нажмите ENTER")
                val text = readLine() ?: return
                if (text.isEmpty()) break
                var maxId = chat.message.maxOfOrNull { it.id }
                chat.message.add(Message(maxId?.plus(1) ?: 1, text))
                println("Сообщение отправлено!")

                val loginSearchRecipient = userList[searchRecipient(chat)].login
                val searchUser = userList[searchSender(chat.recipient)]
                if (searchChat(loginSearchRecipient, searchUser) < 0) createNewChat(loginSearchRecipient, searchUser)
                val indexSearchChat = searchChat(loginSearchRecipient, searchUser)
                maxId = searchUser.chatList[indexSearchChat].message.maxOfOrNull { it.id }
                searchUser.chatList[indexSearchChat].message.add(Message(maxId?.plus(1) ?: 1, text, false))

            }
            "2" -> chat.message.forEach { println(it) }
            "3" -> {
                chat.message.filter { !it.isRead }
                    .forEach { println(it) }
                for (message in chat.message) {
                    message.isRead = true
                }
            }
            "4" -> {
                println("Напишите id сообщения")
                val idInput = readLine()?.toInt() ?: return
                for (message in chat.message) {
                    if (message.id == idInput) {
                        chat.message.remove(message)
                        println("Сообщение удалено!")
                        break
                    } else {
                        println("Сообщение с таким id не найдено")
                    }
                }
            }
            "0" -> return
        }
    }
}