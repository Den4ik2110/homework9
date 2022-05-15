
fun newChat(user: User) {
    println("С кем хотите начать переписку?")
    val login = readLine() ?: return
    val resultMethod = createNewChat(login, user)
    if (user.chatList.isNotEmpty() && resultMethod) personalChat(user.chatList.last())
}

fun continueChat(user: User) {
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

fun deleteChat(user: User, idInput: Int) {
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

fun newMessage(chat: Chat) {
    println("Введите текст сообщения \nДля отмены оставьте поле пустым и нажмите ENTER")
    val text = readLine() ?: return
    if (text.isEmpty()) return
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

fun deleteMessage(chat: Chat) {
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