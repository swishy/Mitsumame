package com.st8vrt.mitsumame.storage.providers

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.slf4j.LoggerFactory
import java.util.UUID
import com.st8vrt.mitsumame.storage.types.User
import com.st8vrt.mitsumame.storage.interfaces.UserStorageProvider

/**
 * Created by swishy on 30/10/13.
 */
public class HibernateUserStorageProvider : UserStorageProvider {

    private var log = LoggerFactory.getLogger(HibernateUserStorageProvider::class.java)

    val factory = Configuration().configure()?.buildSessionFactory();

    override fun getUser(userId: UUID): User? {
        var session = factory?.openSession()
        var user = session?.get(User::class.java, userId) as User;
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
            session?.save(user)
        }
        catch(exception : Exception)
        {
            log?.debug("Error saving user ${user.userName}")
        }
    }
}