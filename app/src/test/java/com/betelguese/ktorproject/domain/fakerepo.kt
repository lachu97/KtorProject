package com.betelguese.ktorproject.domain

import com.betelguese.ktorproject.data.Comments
import com.betelguese.ktorproject.data.NetworkService

class fakerepo : NetworkService {
    override suspend fun getcomments(): List<Comments> {
//        val comments = listOf<Comments>(
//            Comments("Some Body","vindo@gmail.com",2,"Vinod",56),
//            Comments("Body 4","Neeraj@gmail.com",26,"Neeraj",59),
//            Comments("Some 78","saravana@gmail.com",782,"Saravana",506),
//            Comments("Some 79","raghu@gmail.com",289,"Raghunath",5478),
//        )
        return listOf<Comments>(
            Comments("Some Body", "vindo@gmail.com", 2, "Vinod", 56),
            Comments("Body 4", "Neeraj@gmail.com", 26, "Neeraj", 59),
            Comments("Some 78", "saravana@gmail.com", 782, "Saravana", 506),
            Comments("Some 79", "raghu@gmail.com", 289, "Raghunath", 5478),
        )
    }
}