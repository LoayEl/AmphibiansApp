package com.example.amphibiansapp



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibiansapp.data.AmphibianRepository
import com.example.amphibiansapp.data.AmphibiansViewModel
import com.example.amphibiansapp.data.AmphibiansViewModelFactory
import com.example.amphibiansapp.model.Amphibian
import com.example.amphibiansapp.ui.theme.AmphibiansAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmphibiansAppTheme {
                AmphibiansApp()
            }
        }
    }
}

@Composable
fun AmphibiansApp(viewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModelFactory(AmphibianRepository()))) {
    val layoutDir = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues().calculateStartPadding(layoutDir),
                end = WindowInsets.safeDrawing.asPaddingValues().calculateEndPadding(layoutDir)
            )
    ) {
        AmphibianList(viewModel.amphibians.value ?: emptyList())
    }
}

@Composable
fun AmphibianList(amphibians: List<Amphibian>) {
    LazyColumn(
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
    ) {
        items(amphibians) { amph ->
            AmphibianCard(amph)
        }
    }
}

@Composable
fun AmphibianCard(
    amphibian: Amphibian,
    // If previewPainter is non‐null, we'll use it; otherwise fall back to network
    previewPainter: Painter? = null
) {
    val painter = previewPainter
        ?: rememberAsyncImagePainter(amphibian.imageUrl)

    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            Text(
                text = amphibian.name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = amphibian.type,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Image(
                painter = painter,
                contentDescription = amphibian.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp)
            )
            Text(
                text = amphibian.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewAmphibianList() {
    // 1) A sample list matching your JSON
    val sampleList = listOf(
        Amphibian(
            name = "Great Basin Spadefoot",
            type = "Toad",
            description = "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
            imageUrl = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
        ),
        Amphibian(
            name = "Roraima Bush Toad",
            type = "Toad",
            description = "This toad is typically found in South America. Specifically on Mount Roraima at the boarders of Venezuala, Brazil, and Guyana, hence the name. The Roraiam Bush Toad is typically black with yellow spots or marbling along the throat and belly.",
            imageUrl = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/roraima-bush-toad.png"
        ),
        Amphibian(
            name = "Pacific Chorus Frog",
            type = "Frog",
            description = "Also known as the Pacific Treefrog, it is the most common frog on the Pacific Coast of North America. These frogs can vary in color between green and brown and can be identified by a brown stripe that runs from their nostril, through the eye, to the ear.",
            imageUrl = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/pacific-chorus-frog.png"
        ),
        Amphibian(
            name = "Blue Jeans Frog",
            type = "Frog",
            description = "Sometimes called the Strawberry Poison-Dart Frog, this little amphibian is identifiable by its bright red body and blueish-purple arms and legs. The Blue Jeans Frog is not toxic to humans like some of its close relatives, but it can be harmful to some predators.",
            imageUrl = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/blue-jeans-frog.png"
        ),
        Amphibian(
            name = "California Giant Salamander",
            type = "Salamander",
            description = "As the name implies, this salamander can be found in Northern California, as well as other parts of the Pacific Northwest. They prefer temperate areas with plenty of moisture and they typically can grow up to 30 cm.",
            imageUrl = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/california-giant-salamander.png"
        ),
        Amphibian(
            name = "Tiger Salamander",
            type = "Salamander",
            description = "Tiger Salamanders are typically found on the Atlantic Coast of North America. They are named for their coloring which is typically a brown body broken up by yellowish-greenish spots. While they like moist conditions, they don't spend very much time in bodies of water. Instead they prefer to burrow into loose soil.",
            imageUrl = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/tiger-salamander.png"
        )
    )

    // 2) A local placeholder painter for preview
    val placeholder = painterResource(R.drawable.ic_launcher_foreground)

    AmphibiansAppTheme {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(sampleList) { amphibian ->
                // pass previewPainter so we see the drawable instead of a broken network image
                AmphibianCard(amphibian = amphibian, previewPainter = placeholder)
            }
        }
    }
}

