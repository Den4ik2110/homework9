import org.junit.Test

import org.junit.Assert.*

class AuxiliaryFunctionsKtTest {

    @Test
    fun checkLoginTrue() {
        // arrange
        val loginTest = "loginTest"

        // act
        userList.clear()
        val result = checkLogin(login = loginTest)

        // assert
        assertEquals(true, result)
    }

    @Test
    fun checkLoginFalse() {
        // arrange
        val loginTest = "loginTest"

        // act
        userList.clear()
        userList = mutableListOf(User("loginTest", "123", 1, mutableListOf()))
        val result = checkLogin(login = loginTest)

        // assert
        assertEquals(false, result)
    }

    @Test
    fun searchRecipientTestYes() {
        // arrange
        val chat = Chat("Den", 1)
        val user = User("loginTest", "123", 1, mutableListOf(chat))

        // act
        userList.clear()
        userList = mutableListOf(user)
        val result = searchRecipient(chat)

        // assert
        assertEquals(0, result)
    }

    @Test
    fun searchRecipientTestNo() {
        // arrange
        val chat = Chat("Den", 1)

        // act
        userList.clear()
        val result = searchRecipient(chat)

        // assert
        assertEquals(-1, result)
    }

    @Test
    fun deleteChatExists() {
        // arrange
        val chat = Chat("Den", 1)
        val user = User("loginTest", "123", 1, mutableListOf(chat))
        val idInputTest = 1

        // act
        userList.clear()
        userList = mutableListOf(user)
        deleteChat(user, idInputTest)
        val result = user.chatList.isEmpty()

        // assert
        assertEquals(true, result)
    }

    @Test
    fun deleteChatNoExists() {
        // arrange
        val chat = Chat("Den", 1)
        val user = User("loginTest", "123", 1, mutableListOf(chat))
        val idInputTest = 2

        // act
        userList.clear()
        userList = mutableListOf(user)
        deleteChat(user, idInputTest)
        val result = user.chatList.isEmpty()

        // assert
        assertEquals(false, result)
    }

    @Test
    fun createNewChatDouble() {
        // arrange
        val chat = Chat("loginTestTwo", 1)
        val userOne = User("loginTestOne", "123", 1, mutableListOf(chat))
        val userTwo = User("loginTestTwo", "321", 2, mutableListOf())
        val loginTest = "loginTestTwo"

        // act
        userList.clear()
        userList = mutableListOf(userOne, userTwo)

        val result = createNewChat(loginTest, userOne)

        // assert
        assertEquals(false, result)
    }

    @Test
    fun createNewChatYes() {
        // arrange
        val chat = Chat("loginTest", 1)
        val userOne = User("loginTestOne", "123", 1, mutableListOf(chat))
        val userTwo = User("loginTestTwo", "321", 2, mutableListOf())
        val loginTest = "loginTestTwo"

        // act
        userList.clear()
        userList = mutableListOf(userOne, userTwo)

        val result = createNewChat(loginTest, userOne)

        // assert
        assertEquals(true, result)
    }

    @Test
    fun createNewChatNo() {
        // arrange
        val userOne = User("loginTestOne", "123", 1, mutableListOf())
        val userTwo = User("loginTestTwo", "321", 2, mutableListOf())
        val loginTest = "loginTest"

        // act
        userList.clear()
        userList = mutableListOf(userOne, userTwo)
        val result = createNewChat(loginTest, userOne)

        // assert
        assertEquals(false, result)
    }

    @Test
    fun createNewChatMeNo() {
        // arrange
        val userOne = User("loginTestOne", "123", 1, mutableListOf())
        val userTwo = User("loginTestTwo", "321", 2, mutableListOf())
        val loginTest = "loginTestOne"

        // act
        userList.clear()
        userList = mutableListOf(userOne, userTwo)
        val result = createNewChat(loginTest, userOne)

        // assert
        assertEquals(false, result)
    }
}