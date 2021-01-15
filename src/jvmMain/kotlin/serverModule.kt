import domain.model.Like
import domain.model.PostId
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.datetime.Clock
import kotlinx.html.HTML
import kotlinx.serialization.json.Json

fun Application.serverModule(testing: Boolean = false ) {
    install( ContentNegotiation) {
        json(
            Json {
                isLenient = true
            }
        )
    }

    install( DefaultHeaders )

    routing {

        // Serve up a basic index page
        get("/") {
            call.respondHtml(HttpStatusCode.OK, HTML::index)
        }

        route( "/api") {

            // Return a specific Like
            get("/like/{id}" ) {
                // get and validate the ID
                val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText(
                    text = "Missing or bad Like ID",
                    status = HttpStatusCode.BadRequest
                )

                call.respond(
                    // return some fake data
                    Like(
                        postId = PostId(id),
                        user = "Test",
                        date = Clock.System.now()
                    )
                )
            }

        }

        // Serve the web pack build for the index page.
        static("/static") {
            resources()
        }
    }
}