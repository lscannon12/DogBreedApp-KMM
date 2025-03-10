package org.example.project
import io.ktor.client.*
import io.ktor.client.engine.android.*

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Android) {
    config(this)
}