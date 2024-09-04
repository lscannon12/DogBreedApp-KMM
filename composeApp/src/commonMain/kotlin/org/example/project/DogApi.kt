package org.example.project

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

class DogApi {
    private val client = httpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
                isLenient = true
            })
        }
    }

    suspend fun getBreeds(): Flow<List<DogBreed>> = flow {
        try {
            val url = "https://api.thedogapi.com/v1/breeds"
            val response: List<DogBreed> = client.get(url).body()
            emit(response)
        } catch (e: Exception) {
            println("Error fetching dog breeds: ${e.message}")
            e.printStackTrace()
            emit(emptyList())
        }
    }
}