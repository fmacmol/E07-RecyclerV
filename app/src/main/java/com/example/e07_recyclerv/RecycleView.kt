package com.example.e07_recyclerv

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e07_recyclerv.model.SuperHero

@Composable
fun SimpleRecycleView(modifier: Modifier){
    val  myList = listOf("perro", "gato", "canario")
    LazyColumn {
        item { Text(text = "Cabezera") }
        items(myList){
            Text(text = "Animal: $it")
        }
        item{ Text(text = "Pie")}
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroStickView(modifier: Modifier){
    val  contex = LocalContext.current
    val superhero: Map<String, List<SuperHero>> = getSuperHeroes().groupBy { it.publisher }

    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
        superhero.forEach{(publisher, mySuperHero) ->
            stickyHeader {
                Text(
                    text = publisher,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Green),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
            items(mySuperHero){ superhero ->
                ItemHero(superhero = superhero){
                    Toast.makeText(contex, it.realName, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun ItemHero(superhero: SuperHero, onItemSelected: (SuperHero) -> Unit) {
    Card(
        border = BorderStroke(1.dp, Color.Yellow),
        elevation = CardDefaults.run {
            cardElevation(
                defaultElevation = 6.dp
            )
        },
        modifier = Modifier
            .fillMaxWidth().padding(1.dp)
            .clickable { onItemSelected(superhero) }
    ) {
        Column {
            Image(
                painter = painterResource(id = superhero.photo),
                contentDescription = "SuperHero Avatar",
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = superhero.superHeroName,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = superhero.realName,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superhero.publisher,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp),
                fontSize = 10.sp
            )
        }
    }
}

@Composable
fun SuperHeroGridView(modifier: Modifier){
    val context = LocalContext.current
    val  myList = getSuperHeroes()

    LazyVerticalGrid(
        modifier = modifier.background(Color.DarkGray),
        columns = GridCells.Fixed(2),
        content = {
            items(myList) { superhero ->
                ItemHero(superhero = superhero) {
                    Toast.makeText(context, superhero.realName, Toast.LENGTH_SHORT).show()
                }
            }
        },
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun getSuperHeroes(): List<SuperHero> {
    return listOf(
        SuperHero("Spiderman", "Petter Parcker", "Marvel", R.drawable.spiderman),
        SuperHero("Wolverine", "James Howlett", "Marvel", R.drawable.logan),
        SuperHero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
        SuperHero("Thor", "Thor Odison", "Marvel", R.drawable.thor),
        SuperHero("Flash", "Jay Garrick", "DC", R.drawable.flash),
        SuperHero("Green Lantern", "Alan Scott", "DC", R.drawable.green_lantern),
        SuperHero("Wonder Woman", "Princess Diana", "DC", R.drawable.wonder_woman)
    )
}

