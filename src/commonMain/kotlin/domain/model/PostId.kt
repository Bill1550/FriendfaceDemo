package domain.model

import kotlinx.serialization.Serializable

/**
 * Strongly typed post ID
 */
@Serializable
data class PostId(
    val value: Int
)
