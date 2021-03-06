import domain.model.Like
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals

class ApiTest {

    /**
     * An automated test of the GET Like API endpoint.
     */
    @Test
    fun testGetLike() {
        withTestApplication({ module(testing = true)}) {

            handleRequest(HttpMethod.Get, "/api/like/42").apply {
                println("response, status=${response.status()}, content=${response.content}")
                assertEquals( 200, response.status()?.value )
                val content =  response.content?.let { Json.decodeFromString( Like.serializer(), it) }
                println("response (object): $content")
                assertEquals( 42, content?.postId?.value )
            }

        }
    }

    @Test
    fun testGetLikeBadId() {
        withTestApplication({ module(testing = true)}) {

            handleRequest(HttpMethod.Get, "/api/like/bad").apply {
                println("response, status=${response.status()}, content=${response.content}")
                assertEquals( 400, response.status()?.value )
            }

        }
    }
}