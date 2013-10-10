package storage.types

import java.util.UUID
import java.util.Date

/**
 * Created with IntelliJ IDEA.
 * User: swishy
 * Date: 10/5/13
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */

public class Session(
    // The unique Id for this session.
    public val sessionId : UUID,

    // The date the session was created
    public val creationTime : Date,

    // The current expiry time of the session
    public var expiryTime : Date,

    // The UUID of the user associated with the session
    public val userId : UUID
)
{}