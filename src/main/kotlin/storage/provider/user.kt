package storage.provider

import java.util.HashMap
import storage.UserStorageProvider
import storage.types.User
import java.util.UUID

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 10/10/13
 * Time: 7:33 PM
 * To change this template use File | Settings | File Templates.
 */

private var currentUsers : HashMap<UUID, User> =  HashMap<UUID, User>()

public class InMemoryUserStorageProvider : UserStorageProvider
{
    override fun deleteUser(userId: UUID) {
        throw UnsupportedOperationException()
    }
    override fun storeUser(user: User) {
        currentUsers.put(user.userId, user)
    }

    override fun getUser(userId: UUID): User? {
        return currentUsers.get(userId)
    }

}