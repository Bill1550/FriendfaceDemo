package serialization

import domain.model.Like
import domain.model.PostId
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotSame

class BasicSerializationTest {

    private val jsonPretty = Json {
        prettyPrint = true
    }

    private val jsonApi = Json {
        prettyPrint = false
        encodeDefaults = false
        ignoreUnknownKeys = true
    }

    /**
     * Round trip test of serialization of a Like object.
     */
    @Test
    fun serializeLike() {

        val likeObj = Like(
            postId = PostId(123),
            user = "Fred",
            date = "2015-01-01T01:00:00.000Z"
        )

        val likeJson = """{"postId":{"value":123},"user":"Fred","date":"2015-01-01T01:00:00.000Z"}"""

        println( jsonPretty.encodeToString(likeObj))

        val likeSerialized = jsonApi.encodeToString(likeObj)

        assertEquals( likeJson, likeSerialized )

        val likeDecoded = jsonApi.decodeFromString<Like>(likeSerialized)

        println("Like decoded: $likeDecoded")
        assertEquals( likeObj, likeDecoded)
        assertNotSame(likeObj, likeDecoded)
    }
}