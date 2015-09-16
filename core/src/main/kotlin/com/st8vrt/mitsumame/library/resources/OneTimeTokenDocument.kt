package com.st8vrt.mitsumame.library.resources

import java.util.UUID

/**
 * Created by swishy on 30/10/13.
 */
public class OneTimeTokenDocument(
        // A unique token
        val Token : UUID,

        // Always true currently
        val MitsumameEncryption : Boolean = true,

        // Specifies the number of iterations to perform when strengthening keys so client/server are synced
        val KeyStrengtheningIterations : Int
)
{}