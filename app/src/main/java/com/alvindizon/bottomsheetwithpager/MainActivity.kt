package com.alvindizon.bottomsheetwithpager

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alvindizon.bottomsheetwithpager.ui.theme.BottomSheetWithPagerTheme

data class TestItem(
    val title: String,
    val message: String
)

val pages = listOf(
    TestItem(
        title = "Screen 1",
        message = "Hello from Screen 1"
    ),
    TestItem(
        title = "Screen 2",
        message = "Hello from Screen 2"
    ),
    TestItem(
        title = "Screen 3",
        message = "Hello from Screen 3"
    ),
    TestItem(
        title = "Screen 4",
        message = "Hello from Screen 4"
    )
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomSheetWithPagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TestScreen(onNavigationIconClick = {
                        Log.d("debug", "onNavigationIconClick")
                    })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(modifier: Modifier = Modifier, onNavigationIconClick: () -> Unit) {
    val modalBottomSheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = true, confirmValueChange = { false })
    ModalBottomSheet(
        onDismissRequest = onNavigationIconClick,
        sheetState = modalBottomSheetState,
        containerColor = Color.White,
        dragHandle = null
    ) {
        TestScreen2(
            onNavigationIconClick = onNavigationIconClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TestScreen2(onNavigationIconClick: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { pages.size })
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        HorizontalPager(state = pagerState, beyondBoundsPageCount = pages.size) { pageIndex ->
            Column(
                modifier = Modifier.background(color = Color.Blue),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                    text = pages[pageIndex].title,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                    text = pages[pageIndex].message,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
        TopAppBar(
            modifier = Modifier.align(Alignment.TopCenter),
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
            title = {},
            actions = {
                IconButton(onClick = onNavigationIconClick) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close"
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomSheetWithPagerTheme {
        TestScreen(onNavigationIconClick = {})
    }
}