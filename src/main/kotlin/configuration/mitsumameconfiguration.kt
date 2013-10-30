package com.st8vrt.mitsumame.configuration

import com.st8vrt.mitsumame.storage.providers.InMemoryStorageProvider
import com.st8vrt.mitsumame.storage.interfaces.SessionStorageProvider
import com.st8vrt.mitsumame.storage.interfaces.DeviceStorageProvider
import com.st8vrt.mitsumame.storage.interfaces.UserStorageProvider

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 10/5/13
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */

// Static instance to reference.
public var mitsumameConfiguration : MitsumameConfiguration = MitsumameConfiguration()

public class MitsumameConfiguration
{
    // Can be assigned to custom providers.
    public var sessionStorageProvider : SessionStorageProvider = InMemoryStorageProvider()

    public var deviceStorageProvider : DeviceStorageProvider = InMemoryStorageProvider()

    public var userStorageProvider : UserStorageProvider = InMemoryStorageProvider()

    // Default to 2 hours.
    public var sessionDuration : Long = 7200000
}