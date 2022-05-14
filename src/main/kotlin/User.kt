class User(
    var login: String,
    var password: String,
    var id: Int,
    var chatList: MutableList<Chat> = mutableListOf()
)