import domain.model.Like
import domain.model.PostId
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.input

external interface WelcomeProps : RProps {
    var name: String
}

data class WelcomeState(val name: String) : RState

@JsExport
class Welcome(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>(props) {

    private val httpClient: HttpClient = HttpClient() {
        install( JsonFeature ) {
            serializer = KotlinxSerializer()
        }
    }

    init {
        state = WelcomeState(props.name)

        MainScope().launch {
            delay(1000)
            console.log("delayed fetch")
            val like = getLike( PostId(42))
            console.log("got a like: $like")
        }
    }

    override fun RBuilder.render() {
        div {
            +"Hello, ${state.name}"
        }
        input {
            attrs {
                type = InputType.text
                value = state.name
                onChangeFunction = { event ->
                    setState(
                        WelcomeState(name = (event.target as HTMLInputElement).value)
                    )
                }
            }
        }
    }

    private suspend fun getLike( id: PostId): Like? {
        return httpClient.get( "http://localhost:8080/api/like/${id.value}" ) {
            header( HttpHeaders.ContentEncoding, "application/json")
        }
    }
}
