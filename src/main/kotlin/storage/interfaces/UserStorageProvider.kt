package com.st8vrt.mitsumame.storage.interfaces

import com.st8vrt.mitsumame.storage.types.User
import java.util.UUID

/**
 * Created by swishy on 30/10/13.
 */
public trait UserStorageProvider {

    fun storeUser(user: User)

    fun getUser(userId: UUID) : User?

    fun deleteUser(userId: UUID)
}