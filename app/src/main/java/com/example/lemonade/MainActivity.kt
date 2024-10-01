package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Lemonade() {

    var currentStep by remember { mutableStateOf(R.string.lemon_tree) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade"
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFF1E27A)
                )
            )
        }
    ) { innerPadding ->

        Surface (
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            color = MaterialTheme.colorScheme.background
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (currentStep) {

                    R.string.lemon_tree -> {
                        ShowView(
                            nameImageId = R.drawable.lemon_tree,
                            descriptionImageId = R.string.lemon_tree,
                            nextStepId = R.string.lemon,
                            textImageId = R.string.lemon_tree_text,
                            onStepChange = {
                                newStep -> currentStep = newStep
                            }
                        )
                    }

                    R.string.lemon -> {
                        ShowView(
                            nameImageId = R.drawable.lemon_squeeze,
                            descriptionImageId = R.string.lemon,
                            nextStepId = R.string.glass_of_lemonade,
                            textImageId = R.string.lemon_text,
                            onStepChange = {
                                    newStep -> currentStep = newStep
                            }
                        )
                    }

                    R.string.glass_of_lemonade -> {
                        ShowView(
                            nameImageId = R.drawable.lemon_drink,
                            descriptionImageId = R.string.glass_of_lemonade,
                            nextStepId = R.string.empty_glass,
                            textImageId = R.string.glass_of_lemonade_text,
                            onStepChange = {
                                    newStep -> currentStep = newStep
                            }
                        )
                    }

                    R.string.empty_glass -> {
                        ShowView(
                            nameImageId = R.drawable.lemon_restart,
                            descriptionImageId = R.string.empty_glass,
                            nextStepId = R.string.lemon_tree,
                            textImageId = R.string.empty_glass_text,
                            onStepChange = {
                                    newStep -> currentStep = newStep
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowView(
    nameImageId: Int,
    descriptionImageId: Int,
    nextStepId: Int,
    textImageId: Int,
    onStepChange: (Int) -> Unit
) {
    Image(
        painter = painterResource(nameImageId),
        contentDescription = stringResource(descriptionImageId),
        modifier = Modifier
            .clickable {
                onStepChange(nextStepId)
            }
            .background(Color(0xFFBEEBBE), RoundedCornerShape(20.dp))
            .width(200.dp)
            .height(200.dp)
    )

    Spacer(
        modifier = Modifier.height(20.dp)
    )

    Text (
        text = stringResource(textImageId)
    )
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        Lemonade()
    }
}