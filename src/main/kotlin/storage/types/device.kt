package com.st8vrt.mitsumame.storage.types

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 10/8/13
 * Time: 9:30 PM
 * To change this template use File | Settings | File Templates.
 */

[Entity]
public class Device(
        [Id]
        val deviceId : UUID,
        val deviceType : String
)
{}
