package com.yasir.ktor

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yasir.ktor.data.client.KtorClient
import com.yasir.ktor.model.Posts
import com.yasir.ktor.ui.theme.KtorTheme
import com.yasir.ktor.view.viewmodel.PostsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = getViewModel<PostsViewModel>()
            val posts by viewModel.posts.collectAsStateWithLifecycle()
            // Trigger fetching posts once
            LaunchedEffect(Unit) {
                viewModel.fetchPosts()
            }
            KtorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(
                        list = posts,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier, list: List<Posts>) {
    LazyColumn(modifier.fillMaxSize()) {
        items(list) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxSize()
            ) {
                Text(text = it.id.toString())
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it.title, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it.body, style = MaterialTheme.typography.bodyMedium)
            }
        }

    }
}
