package org.example.project

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogBreed(
    val id: Int,
    val name: String,
    val bred_for: String? = null,
    @SerialName("breed_group") val breedGroup: String? = null,
    @SerialName("life_span") val lifeSpan: String? = null,
    val temperament: String? = null,
    val origin: String? = null,
    @SerialName("reference_image_id") val referenceImageId: String? = null,
    val weight: Weight? = null,
    val height: Height? = null
)

@Serializable
data class Weight(
    val imperial: String,
    val metric: String
)

@Serializable
data class Height(
    val imperial: String,
    val metric: String
)