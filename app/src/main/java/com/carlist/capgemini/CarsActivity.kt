package com.carlist.capgemini

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlist.capgemini.service.ApiUtils
import com.carlist.capgemini.ui.theme.MyApplicationTheme
import com.carlist.capgemini.views.CarListScreen

class CarsActivity : ComponentActivity() {
    private val carViewModel = ApiUtils.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                CarListScreen (
                    modifier = Modifier.padding(top = 42.dp),
                    uiState = carViewModel.uiState.collectAsState().value,
                    onRefresh = { carViewModel.fetchCars(model) }
                )
            }
        }
    }

    companion object{
        const val model = "audi"
    }
}
