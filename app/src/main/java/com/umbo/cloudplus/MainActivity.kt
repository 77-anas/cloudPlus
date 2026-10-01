package com.umbo.cloudplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.umbo.cloudplus.ui.theme.cloudPlusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            cloudPlusTheme {
                CloudPlusApp()
            }
        }
    }
}

internal data class ServiceStatus(
    val name: String,
    val detail: String,
    val healthy: Boolean,
)

private val sampleServices = listOf(
    ServiceStatus(name = "API gateway", detail = "4 instances · eu-west-1", healthy = true),
    ServiceStatus(name = "Object storage", detail = "1.2 TB used of 5 TB", healthy = true),
    ServiceStatus(name = "Search cluster", detail = "Reindexing, elevated latency", healthy = false),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CloudPlusApp() {
    var clickCount by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("cloudPlus") })
        },
    ) { innerPadding ->
        HomeScreen(
            clickCount = clickCount,
            onIncrement = { clickCount++ },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
internal fun HomeScreen(
    clickCount: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier,
    services: List<ServiceStatus> = sampleServices,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            HeaderCard(clickCount = clickCount, onIncrement = onIncrement)
        }
        items(services) { service ->
            ServiceCard(service)
        }
    }
}

@Composable
private fun HeaderCard(clickCount: Int, onIncrement: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.Cloud,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "Your cloud, running",
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                text = "Android + Jetpack Compose on AGP 9 built-in Kotlin",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = "Taps: $clickCount",
                style = MaterialTheme.typography.labelSmall,
            )
            TextButton(onClick = onIncrement) {
                Text("Increment")
            }
        }
    }
}

@Composable
private fun ServiceCard(service: ServiceStatus) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = service.name,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = service.detail,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = if (service.healthy) "Healthy" else "Degraded",
                style = MaterialTheme.typography.labelSmall,
                color = if (service.healthy) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.error
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    cloudPlusTheme {
        HomeScreen(clickCount = 0, onIncrement = {})
    }
}
