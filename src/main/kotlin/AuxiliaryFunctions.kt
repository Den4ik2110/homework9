fun checkLogin(login: String): Boolean {
    for (user in userList) {
        if (user.login == login) return false
    }
    return true
}

fun getUserByLoginAndPassword(login: String, password: String): User {
    for (user in userList) {
        if (user.login == login && user.password == password) return user
    }
    throw UserNotFoundRuntimeExceptions("Пользователь не найден")
}

fun dropMeAndRepeat(login: String, user: User): Boolean {
    if (user.chatList.isEmpty()) return user.login != login
    for (users in userList) {
        for (chat in users.chatList) {
            if (chat.recipient == login || user.login == login) return false
        }
    }
    return true
}

fun createNewChat(login: String, user: User): Boolean {
    for (users in userList) {
        if (users.login == login && dropMeAndRepeat(login, user)) {
            val maxId = user.chatList.maxOfOrNull { it.id }
            val chat = Chat(login, maxId?.plus(1) ?: 1)
            user.chatList.add(chat)
            println("Новый чат создан!")
            return true
        }
    }
    println("Пользователя с таким именем не существует, или пытаетесь создать второй чат с тем же пользователем, или с самим собой")
    return false
}

fun searchRecipient(chat: Chat): Int {
    for (users in userList) {
        for (chats in users.chatList) {
            if (chats == chat) return userList.indexOf(users)
        }
    }
    return -1
}

fun searchSender(login: String): Int {
    for (users in userList) {
        if (users.login == login) return userList.indexOf(users)
    }
    return -1
}

fun searchChat(login: String, user: User): Int {
    for (chat in user.chatList) {
        if (chat.recipient == login) return user.chatList.indexOf(chat)
    }
    return -1
}

fun filterChat(chat: Chat): Boolean {
    val result = chat.message.filter { !it.isRead }
    return result.isNotEmpty()
}