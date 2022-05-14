class Chat(
    val recipient: String,
    var id: Int,
    var message: MutableList<Message> = mutableListOf(),
) {
    override fun toString(): String {
        return "id: $id Получатель: $recipient "
    }
}