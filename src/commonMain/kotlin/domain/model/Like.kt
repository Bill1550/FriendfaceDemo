package domain.model

import domain.model.serialization.InstantSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialInfo
import kotlinx.serialization.Serializable

/**
 * A Like by a single user.
 */
@Serializable
data class Like(
    val postId: PostId,

    val user: String,       // TODO strongly type users.

    @Serializable( with = InstantSerializer::class )
    val date: Instant
)
