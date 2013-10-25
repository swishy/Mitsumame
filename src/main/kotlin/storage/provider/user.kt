package storage.provider

import java.util.HashMap
import storage.UserStorageProvider
import storage.types.User
import java.util.UUID
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.slf4j.LoggerFactory

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

public class HibernateUserStorageProvider : UserStorageProvider
{
    private var log = LoggerFactory.getLogger(javaClass<HibernateUserStorageProvider>())

    val factory = Configuration().configure()?.buildSessionFactory();

    override fun getUser(userId: UUID): User? {
        var session = factory?.openSession()
        var user = session?.get(javaClass<User>(), userId) as User;
        return user;
    }

    override fun deleteUser(userId: UUID) {
        var user = getUser(userId)
        var session = factory?.openSession()
        session?.delete(user)
    }

    override fun storeUser(user: User) {
        var session = factory?.openSession()
        try
        {
            var id = session?.save(user) as Long
            user.userId = id
        }
        catch(exception : Exception)
                {
                    log?.debug("Error saving user ${user.userName}")
                }
    }

}