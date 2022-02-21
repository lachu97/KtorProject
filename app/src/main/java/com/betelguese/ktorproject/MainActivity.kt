package com.betelguese.ktorproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betelguese.ktorproject.data.myrepo
import com.betelguese.ktorproject.domain.comment
import com.betelguese.ktorproject.domain.repos
import com.betelguese.ktorproject.ui.theme.KtorProjectTheme
import com.betelguese.ktorproject.views.AnimatedShimmer
import com.betelguese.ktorproject.views.Myviewmodel
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val httpclient = HttpClient(CIO) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
            install(Logging) {
                level = LogLevel.BODY
            }
        }
        val repository = myrepo(httpclient)
        val repos1 = repos(repository)
        val myviewm = Myviewmodel(repos1)
        setContent {
            KtorProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val state = myviewm.newvalue.value

                        state.let {
                            Column(modifier = Modifier.fillMaxSize()) {
                                LazyColumn(modifier = Modifier.fillMaxWidth().padding(5.dp)) {
                                    itemsIndexed(it.comment) { _, comment ->
                                        CommentList(
                                            comment = comment
                                        )
                                    }
                                }
                            if (it.isloading) {
                                AnimatedShimmer()
                            }
                            else if(it.error.isNullOrEmpty()) {
                                Text(text = state.error ?: "Some Error ", fontSize = 23.sp, color = Color.Red)
                            }

                        }


                    }
                }
            }
        }
    }

}

@Composable
fun CommentList(comment: comment) {
    Card(
        elevation = 4.dp,
        backgroundColor = Color.LightGray,
        modifier = Modifier.padding(5.dp).fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            comment.name.let {
                Text(
                    text = it ?: "Random Name",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(modifier = Modifier.padding(2.dp))
            comment.body.let {
                Text(text = it ?: "Random Body", fontSize = 17.sp)
            }
            Spacer(modifier = Modifier.padding(2.dp))
            comment.email.let {
                Text(text = it ?: "Random Email", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            }

        }

    }
}
