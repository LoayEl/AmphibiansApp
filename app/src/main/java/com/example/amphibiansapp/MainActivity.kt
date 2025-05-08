package com.example.amphibiansapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.tooling.preview.Preview
import com.example.amphibiansapp.data.AmphibianRepository
import com.example.amphibiansapp.data.AmphibiansViewModel
import com.example.amphibiansapp.data.AmphibiansViewModelFactory
import com.example.amphibiansapp.model.Amphibian
import com.example.amphibiansapp.ui.theme.AmphibiansAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState


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
fun AmphibiansApp(
    viewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModelFactory(AmphibianRepository()))
) {
    val amphibians by viewModel.amphibians.observeAsState(emptyList())

    Surface(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (amphibians.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            AmphibianList(amphibians)
        }
    }
}

@Composable
fun AmphibianList(amphibians: List<Amphibian>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(amphibians) { amphibian ->
            AmphibianCard(amphibian)
        }
    }
}

@Composable
fun AmphibianCard(amphibian: Amphibian) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            // Name and Type
            Text(
                text = "${amphibian.name} (${amphibian.type})",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Image
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(amphibian.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = amphibian.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Description
            Text(
                text = amphibian.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
