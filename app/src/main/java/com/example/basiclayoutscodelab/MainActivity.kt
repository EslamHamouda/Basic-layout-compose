package com.example.basiclayoutscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiclayoutscodelab.ui.theme.BasicLayoutsCodelabTheme
import com.example.basiclayoutscodelab.ui.theme.shapes
import com.example.basiclayoutscodelab.ui.theme.typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { BasicLayoutsCodelabThemeApp() }
    }
}

// Step: Search bar - Modifiers
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    TextField(
        value = "",
        onValueChange = {},
        placeholder = {
            Text(
                stringResource(R.string.placeholder_search)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = null
                )
                      },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colors.surface
        )
        )

}

// Step: Align your body - Alignment
@Composable
fun AlignYourBodyElement(
    modifier: Modifier = Modifier,
    @DrawableRes image:Int,
    @StringRes text:Int
) {
    // Implement composable here
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = modifier
                .size(88.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = text),
            modifier = Modifier.paddingFromBaseline(24.dp, 8.dp),
            style = typography.h3
        )
    }
}

// Step: Favorite collection card - Material Surface
@Composable
fun FavoriteCollectionCard(
    modifier: Modifier = Modifier,
    @DrawableRes image:Int,
    @StringRes text:Int
) {
    // Implement composable here
    Surface(modifier = modifier, shape = shapes.small) {
        Row(Modifier.width(192.dp),verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(56.dp),
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = stringResource(id = text),
                style = typography.h3
            )
        }
    }
}

// Step: Align your body row - Arrangements
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    LazyRow(modifier = modifier, contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)){
        items(alignYourBodyData){
            AlignYourBodyElement(image = it.drawable, text = it.text)
        }
    }
}

// Step: Favorite collections grid - LazyGrid
@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    LazyHorizontalGrid(rows = GridCells.Fixed(2) ,
        modifier = modifier.height(120.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)){
        items(favoriteCollectionsData){
            FavoriteCollectionCard(modifier = modifier.height(56.dp) ,image = it.drawable, text = it.text)
        }
    }
}

// Step: Home section - Slot APIs
@Composable
fun HomeSection(
    @StringRes title:Int,
    content: @Composable ()->Unit,
    modifier: Modifier = Modifier
) {
    // Implement composable here
    Column(modifier) {
        Text(
            text = stringResource(id = title),
            style = typography.h2,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp))
        content()
    }
}

// Step: Home screen - Scrolling
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // Implement composable here
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body, {AlignYourBodyRow()})
        HomeSection(title = R.string.favorite_collections, {FavoriteCollectionsGrid()})
        Spacer(Modifier.height(16.dp))
    }
}

// Step: Bottom navigation - Material
@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    // Implement composable here
    BottomNavigation(modifier, backgroundColor = colors.background) {
        BottomNavigationItem(icon = {Icon(imageVector = Icons.Default.Spa,
            contentDescription = null)},
            label = { Text(text = stringResource(id = R.string.bottom_navigation_home))},
            selected = true,
            onClick = { /*TODO*/ })

        BottomNavigationItem(icon = {Icon(imageVector = Icons.Default.AccountCircle,
            contentDescription = null)},
            label = { Text(text = stringResource(id = R.string.bottom_navigation_profile))},
            selected = false,
            onClick = { /*TODO*/ })
    }
}

// Step: MySoothe App - Scaffold
@Composable
fun BasicLayoutsCodelabThemeApp() {
    // Implement composable here
    Scaffold(
        bottomBar = { SootheBottomNavigation() }
    ) { padding ->
        HomeScreen(Modifier.padding(padding))
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun SearchBarPreview() {
    BasicLayoutsCodelabTheme { SearchBar(Modifier.padding(8.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyElementPreview() {
    BasicLayoutsCodelabTheme {
        AlignYourBodyElement(
            modifier = Modifier.padding(8.dp),
            R.drawable.ab1_inversions,
            R.string.ab1_inversions
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionCardPreview() {
    BasicLayoutsCodelabTheme {
        FavoriteCollectionCard(
            modifier = Modifier.padding(8.dp),
            R.drawable.fc2_nature_meditations,
            R.string.fc2_nature_meditations
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionsGridPreview() {
    BasicLayoutsCodelabTheme { FavoriteCollectionsGrid() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyRowPreview() {
    BasicLayoutsCodelabTheme { AlignYourBodyRow() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeSectionPreview() {
    BasicLayoutsCodelabTheme { HomeSection(title = R.string.align_your_body, {AlignYourBodyRow()}) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ScreenContentPreview() {
    BasicLayoutsCodelabTheme { HomeScreen() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun BottomNavigationPreview() {
    BasicLayoutsCodelabTheme { SootheBottomNavigation(Modifier.padding(top = 24.dp)) }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun BasicLayoutsCodelabPreview() {
    BasicLayoutsCodelabThemeApp()
}
