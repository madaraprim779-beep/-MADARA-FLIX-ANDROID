package com.madara.flix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Dark = Color(0xFF080914)
private val Panel = Color(0xFF17182B)
private val Purple = Color(0xFF8B5CF6)
private val Blue = Color(0xFF596CFF)

data class Film(
    val title: String,
    val category: String,
    val emoji: String,
    val description: String,
    val colors: List<Color>
)

private val films = listOf(
    Film(
        "Les Ombres du futur",
        "Film",
        "🌌",
        "Une aventure de science-fiction.",
        listOf(Color(0xFF35145C), Color(0xFF9B397C))
    ),
    Film(
        "Mission Horizon",
        "Film",
        "🚀",
        "Une aventure aux frontières de l'inconnu.",
        listOf(Color(0xFF103C67), Color(0xFF2683A5))
    ),
    Film(
        "Le Dernier Royaume",
        "Série",
        "🏰",
        "Une histoire fantastique en plusieurs épisodes.",
        listOf(Color(0xFF67291D), Color(0xFFD17B35))
    ),
    Film(
        "Nature Sauvage",
        "Original",
        "🌿",
        "Une idée de documentaire original.",
        listOf(Color(0xFF12453B), Color(0xFF42A58A))
    ),
    Film(
        "Nébuleuse",
        "Série",
        "🪐",
        "Une histoire imaginaire au-delà des étoiles.",
        listOf(Color(0xFF292052), Color(0xFF7258D8))
    ),
    Film(
        "Au-delà du silence",
        "Original",
        "🎬",
        "Une création fictive en préparation.",
        listOf(Color(0xFF4B1832), Color(0xFFB84268))
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = Purple,
                    background = Dark,
                    surface = Panel,
                    onBackground = Color.White,
                    onSurface = Color.White
                )
            ) {
                MadaraFlixApp()
            }
        }
    }
}

@Composable
fun MadaraFlixApp() {
    var search by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Tout") }
    var selectedFilm by remember { mutableStateOf<Film?>(null) }

    val categories = listOf("Tout", "Film", "Série", "Original")

    val filteredFilms = films.filter { film ->
        val matchesCategory =
            category == "Tout" || film.category == category

        val matchesSearch =
            film.title.contains(search, ignoreCase = true) ||
            film.category.contains(search, ignoreCase = true)

        matchesCategory && matchesSearch
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Panel)
                    .padding(horizontal = 18.dp, vertical = 17.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "MADARA ",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
                Text(
                    text = "FLIX",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFFA78BFA)
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 30.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xFF17102D),
                                        Color(0xFF25205B),
                                        Dark
                                    )
                                )
                            )
                            .padding(23.dp)
                    ) {
                        Text(
                            text = "TON UNIVERS CINÉMA",
                            color = Color(0xFFC4B5FD),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(16.dp))

                        Text(
                            text = "Le divertissement\nsans limites.",
                            fontSize = 34.sp,
                            lineHeight = 40.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )

                        Spacer(Modifier.height(10.dp))

                        Text(
                            text = "Films, séries et créations originales " +
                                "dans un univers conçu pour tes écrans.",
                            color = Color(0xFFD0D0E2),
                            fontSize = 14.sp,
                            lineHeight = 21.sp
                        )

                        Spacer(Modifier.height(12.dp))

                        Text(
                            text = "MADARA FLIX • VERSION DE DÉMONSTRATION",
                            color = Color(0xFFA78BFA),
                            fontSize = 10.sp
                        )
                    }
                }

                item {
                    OutlinedTextField(
                        value = search,
                        onValueChange = { search = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        singleLine = true,
                        label = { Text("Rechercher un titre") },
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Purple,
                            unfocusedBorderColor = Color(0xFF42435B),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedLabelColor = Purple,
                            unfocusedLabelColor = Color.LightGray,
                            cursorColor = Purple
                        )
                    )
                }

                item {
                    Text(
                        text = "Découvrir",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(
                            start = 18.dp,
                            top = 8.dp,
                            bottom = 12.dp
                        )
                    )
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(7.dp)
                    ) {
                        categories.forEach { item ->
                            FilterChip(
                                selected = category == item,
                                onClick = { category = item },
                                label = { Text(item) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = Purple,
                                    selectedLabelColor = Color.White,
                                    labelColor = Color.LightGray
                                )
                            )
                        }
                    }
                }

                if (filteredFilms.isEmpty()) {
                    item {
                        Text(
                            text = "Aucun titre trouvé.",
                            color = Color.LightGray,
                            modifier = Modifier.padding(24.dp)
                        )
                    }
                } else {
                    items(filteredFilms) { film ->
                        FilmCard(
                            film = film,
                            onClick = { selectedFilm = film }
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        HorizontalDivider(color = Color(0xFF303148))
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = "MADARA FLIX",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFA78BFA)
                        )
                        Text(
                            text = "Une plateforme indépendante en construction.",
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        selectedFilm?.let { film ->
            AlertDialog(
                onDismissRequest = { selectedFilm = null },
                containerColor = Panel,
                title = {
                    Text(
                        text = "${film.emoji} ${film.title}",
                        color = Color.White
                    )
                },
                text = {
                    Text(
                        text = film.description +
                            "\n\nCette fiche est une démonstration. " +
                            "La vidéo sera intégrée ultérieurement.",
                        color = Color.LightGray
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = { selectedFilm = null }
                    ) {
                        Text("Fermer", color = Purple)
                    }
                }
            )
        }
    }
}

@Composable
fun FilmCard(
    film: Film,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 7.dp)
            .background(Panel, RoundedCornerShape(15.dp))
            .clickable(onClick = onClick)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(105.dp)
                .height(125.dp)
                .background(
                    Brush.linearGradient(film.colors),
                    RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = film.emoji,
                fontSize = 42.sp
            )
        }

        Spacer(Modifier.width(14.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = film.category.uppercase(),
                color = Color(0xFFA78BFA),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = film.title,
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(7.dp))

            Text(
                text = film.description,
                color = Color.LightGray,
                fontSize = 12.sp,
                lineHeight = 17.sp
            )

            Spacer(Modifier.height(9.dp))

            Text(
                text = "DÉCOUVRIR →",
                color = Color(0xFF9BA8FF),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
