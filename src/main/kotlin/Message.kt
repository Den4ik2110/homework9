class Message(
    var id: Int,
    var text: String,
    var isRead: Boolean = true
) {
    override fun toString(): String {
        return "id: $id Сообщение: $text "
    }
}