package com.netology.kotlinhomework5_1.PostTemplate

import com.netology.kotlinhomework5_1.PostTemplate.Post


class PostEvent (
    id: Long,
    author: String,
    content: String,
    created: String,

    type: PostType = PostType.EVENT, // Явно указали тип поста
    source: Post?,
    likedByMe: Boolean,
    CommentdByMe: Boolean,
    ShareByMe: Boolean,

    likedCounter: Int,
    commentCounter:Int,
    shareCounter: Int,

    // Добавлены дополнительные поля
    var locationByMe: Boolean = false,
    val address: String ="",
    val coordinates_lat: Double = 0.0,
    val coordinates_lng: Double = 0.0


): Post(
    id,
    author,
    content,
    created,

    type,
    source,
    likedByMe,
    CommentdByMe,
    ShareByMe,

    likedCounter,
    commentCounter,
    shareCounter
)
