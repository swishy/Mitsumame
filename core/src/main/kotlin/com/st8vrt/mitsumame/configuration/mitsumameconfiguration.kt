package com.st8vrt.mitsumame.configuration

import com.st8vrt.mitsumame.authentication.Foo
import com.st8vrt.mitsumame.authentication.IMitsumamePlugin
import com.st8vrt.mitsumame.storage.providers.InMemoryStorageProvider
import com.st8vrt.mitsumame.storage.interfaces.SessionStorageProvider
import com.st8vrt.mitsumame.storage.interfaces.DeviceStorageProvider
import com.st8vrt.mitsumame.storage.interfaces.UserStorageProvider
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import uy.kohesive.injekt.api.injectLazy
import uy.kohesive.injekt.injectLazy

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 10/5/13
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */

// Static instance to reference.
public var mitsumameConfiguration : MitsumameConfiguration = MitsumameConfiguration()

public class MitsumameConfiguration()
{
    // Can be assigned to custom providers.
    // Make these injected using injekt / config declared libraries.
    public var sessionStorageProvider : SessionStorageProvider =  Injekt.get()

    public var deviceStorageProvider : DeviceStorageProvider = Injekt.get()

    public var userStorageProvider : UserStorageProvider = Injekt.get()

    public var authHandler : IMitsumamePlugin = Foo()

    // Default to 2 hours.
    public var sessionDuration : Long = 7200000

    // Default to 1024 iterations during key strengthening.
    public var keyIterations : Int = 1024
}