import kotlinx.html.*

fun HTML.index() {

    head {
        title("Hello from Ktor!")
    }
    body {
        div {
            +"Hello from Ktor - React component loaded below:"
        }
        div {
            id = "root"
        }
        script(src = "/static/output.js") {}
    }
}