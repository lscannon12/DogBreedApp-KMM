package org.example.project

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun DogBreedsScreen() {
    var dogBreeds by remember { mutableStateOf(emptyList<DogBreed>()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            DogApi().getBreeds().collect { breeds: List<DogBreed> ->
                dogBreeds = breeds
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Dog Breeds :)",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        DogBreedsList(dogBreeds)
    }
}

@Composable
fun DogBreedsList(breeds: List<DogBreed>) {
    LazyColumn {
        items(breeds) { breed ->
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    breed.name,
                    style = MaterialTheme.typography.h6
                )
                breed.temperament?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}
