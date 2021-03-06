package com.betelguese.ktorproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.betelguese.ktorproject.domain.comment
import com.betelguese.ktorproject.ui.theme.KtorProjectTheme
import com.betelguese.ktorproject.ui.theme.MyBlack
import com.betelguese.ktorproject.views.AnimatedShimmer
import com.betelguese.ktorproject.views.myViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val myviewm by viewModels<myViewModel>()
    @Inject
    lateinit var somerandomstring: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        val httpclient = HttpClient(CIO) {
//            install(JsonFeature) {
//                serializer = GsonSerializer()
//            }
//            install(Logging) {
//                level = LogLevel.BODY
//            }
//        }
//        val repository = myrepo(httpclient)
//        val repos1 = repos(repository)
//        val myviewm = Myviewmodel(repos1)
        setContent {
            KtorProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MyBlack
                ) {
                    val state = myviewm.newvalue.value

                    state.let {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Spacer(modifier = Modifier.padding(1.dp))
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                            ) {
                                itemsIndexed(it.comment) { _, cmt ->
                                    CommentList(
                                        comment = cmt
                                    )
                                }
                            }
                            if (it.isloading) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    repeat(6) {
                                        AnimatedShimmer()
                                    }
                                }

                            } else if (it.error.isNullOrEmpty()) {
                                Text(
                                    text = state.error ?: "Some Error ",
                                    fontSize = 23.sp,
                                    color = Color.Red
                                )
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
    var select by remember {
        mutableStateOf(false)
    }
    Card(
        elevation = 8.dp,
        backgroundColor = Color.LightGray.copy(
            alpha = 0.9f,

        ),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
        ,
        border = BorderStroke(2.dp, color = Color.Black.copy(
            alpha = 0.5f
        )),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            comment.name.let {
                Text(
                    text = it ?: "Random Name",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.SansSerif,

                    modifier = Modifier
                        .padding(5.dp)
                )
            }
            Spacer(modifier = Modifier.padding(2.dp))
            comment.body.let {
                Text(
                    text = it ?: "Random Body",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier
                        .padding(5.dp)

                )
            }
            Spacer(modifier = Modifier.padding(2.dp))
            comment.email.let {
                Text(
                    text = it ?: "Random Email",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp)
                )
            }
        }
    }
}

@Composable
fun mycompose() {

}
