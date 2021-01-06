package domain.model

import kotlinx.serialization.SerialInfo
import kotlinx.serialization.Serializable

/**
 * A Like by a single user.
 */
@Serializable
data class Like(
    val postId: PostId,
    val user: String,       // TODO strongly type users.
    val date: String        // TODO include Klock
)
