package com.parttimereader.retrofit_example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parttimereader.retrofit_example.R
import com.parttimereader.retrofit_example.ui.theme.Retrofit_exampleTheme


@Composable
fun HomeScreen(
    movieUiState: MovieUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (movieUiState){
        is MovieUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MovieUiState.Success -> ResultScreen(
            movieUiState.photos, modifier.padding(top = contentPadding.calculateTopPadding())
        )
        is MovieUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())

    }
}

@Composable
fun LoadingScreen(modifier: Modifier=Modifier){
    Image(
        modifier = Modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading),

    )
}

@Composable
fun ErrorScreen(modifier: Modifier) {
    Column (
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = null,
        )
        Text(
            text = stringResource(R.string.loading_failed),
            color = Color.Red,
            modifier = Modifier.padding(16.dp),
        )
    }
}



/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
        Retrofit_exampleTheme{
        ResultScreen(stringResource(R.string.placeholder_result))
    }
}
