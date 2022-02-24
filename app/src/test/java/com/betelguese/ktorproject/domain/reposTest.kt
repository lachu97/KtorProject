package com.betelguese.ktorproject.domain

import com.betelguese.ktorproject.data.Comments
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class reposTest {
    lateinit var items: List<Comments>
    lateinit var body: Comments

    @Before
    fun setup() {

        val fakerepo = fakerepo()
        runBlocking {
            items = fakerepo.getcomments()
            body = Comments("Some Body", "vindo@gmail.com", 2, "Vinod", 56)

        }
    }

    @Test
    fun `getcomments successfully`() {
        assertNotNull(items)
    }

    @Test
    fun `checkforbody successfully`() {
        val id = items.size
        assertEquals(body, items.get(Random.nextInt(id)))
    }
}