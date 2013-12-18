package com.st8vrt.mitsumame.storage.types

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 10/10/13
 * Time: 7:53 PM
 * To change this template use File | Settings | File Templates.
 */

[Entity]
public class User(
        val userName : String,
        val authToken : String
)
{
    [Id]
    var userId : UUID = UUID.randomUUID() as UUID
}