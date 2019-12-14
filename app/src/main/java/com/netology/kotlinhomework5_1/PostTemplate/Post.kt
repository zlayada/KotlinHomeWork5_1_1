package com.netology.kotlinhomework5_1.PostTemplate


open class Post (

    // Данные поста введенные автором
    val id: Long,
    val author: String,
    val content: String,
    val created: String, // дата - пока строка

    // Начальные данные поста
    val type: PostType = PostType.POST,
    val source: Post? = null,
    var likedByMe: Boolean = false,
    var CommentdByMe: Boolean = false,
    var ShareByMe: Boolean = false,

    // Счетчики интереса к посту
    var likedCounter: Int = 0,
    var commentCounter:Int = 0,
    var shareCounter: Int = 0

)
