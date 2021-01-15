//import io.ktor.client.*
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.button
import react.dom.div
import react.dom.input

external interface WelcomeProps : RProps {
    var name: String
}

data class WelcomeState(val name: String) : RState

@JsExport
class Welcome(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>(props) {
//
//    private val httpClient: HttpClient = HttpClient() {
//        install( JsonFeature ) {
//            serializer = KotlinxSerializer()
//        }
//    }

    init {
        state = WelcomeState(props.name)
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
        div {
            button {
                +"Click Me!"
            }
        }
    }
}
