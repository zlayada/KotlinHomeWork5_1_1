package com.netology.kotlinhomework5_1

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netology.kotlinhomework5_1.PostTemplate.Post
import kotlinx.android.synthetic.main.post_card.view.*


// Адаптер
class PostAdapter(val list: List<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.post_card, parent, false)
        return PostViewHolder(this, view)

    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as PostViewHolder) {
            bind(list[position])
        }
    }
}

// ОБрабатываем кнопки карточки поста
class PostViewHolder(val adapter: PostAdapter, view: View) : RecyclerView.ViewHolder(view) {
    init {
        with(itemView) {

            // Кнопка "Лайков"
            imageBtn_like.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]

                    if (item.likedByMe == false) {
                        imageBtn_like.setImageResource(R.drawable.ic_favorite_red_24dp)
                        textView_counte_like.setTextColor(Color.RED)
                        item.likedCounter += 1
                        item.likedByMe = true
                        textView_counte_like.text = item.likedCounter.toString()

                    } else {
                        imageBtn_like.setImageResource(R.drawable.ic_favorite_black_24dp)
                        textView_counte_like.setTextColor(Color.GRAY)
                        item.likedCounter -= 1
                        item.likedByMe = false
                        textView_counte_like.text = zeroСheck(item.likedCounter)
                    }

                    adapter.notifyItemChanged(adapterPosition)
                }
            }

            // Кнопка "Комментариев"
            imageBtn_commit.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]

                    if (item.CommentdByMe == false) {
                        imageBtn_commit.setImageResource(R.drawable.ic_mode_comment_red_24dp)
                        textView_counte_commit.setTextColor(Color.RED)
                        item.commentCounter += 1
                        item.CommentdByMe = true
                        textView_counte_commit.text = item.commentCounter.toString()
                    } else {
                        imageBtn_commit.setImageResource(R.drawable.ic_mode_comment_black_24dp)
                        textView_counte_commit.setTextColor(Color.GRAY)
                        item.commentCounter -= 1
                        item.CommentdByMe = false
                        textView_counte_commit.text = zeroСheck(item.commentCounter)
                    }

                    adapter.notifyItemChanged(adapterPosition)
                }
            }

            // Кнопка "Поделиться"
            imageBtn_share.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val item = adapter.list[adapterPosition]

                    if (item.ShareByMe == false) {
                        imageBtn_share.setImageResource(R.drawable.ic_share_red_24dp)
                        textView_counte_share.setTextColor(Color.RED)
                        item.shareCounter += 1
                        item.ShareByMe = true
                        textView_counte_share.text = item.shareCounter.toString()
                        sharePost(item.author, item.created, item.content)

                    } else {
                        imageBtn_share.setImageResource(R.drawable.ic_share_black_24dp)
                        textView_counte_share.setTextColor(Color.GRAY)
                        item.shareCounter -= 1
                        item.ShareByMe = false
                        textView_counte_share.text = zeroСheck(item.shareCounter)
                    }
                }
            }
        }
    }

    // Визуализация данных из постов (для цикленного перечисления)
    fun bind(post: Post) {

        with(itemView) {
            textView_author.text = post.author
            textView_text_note.text = post.content
            textView_data.text = post.created

            textView_counte_share.text = zeroСheck(post.shareCounter)
            textView_counte_commit.text = zeroСheck(post.commentCounter)
            textView_counte_like.text = zeroСheck(post.likedCounter)

            // Визуализация лайков
            if (post.likedByMe) {
                imageBtn_like.setImageResource(R.drawable.ic_favorite_red_24dp)
                textView_counte_like.setTextColor(Color.RED)

            } else {
                imageBtn_like.setImageResource(R.drawable.ic_favorite_black_24dp)
                textView_counte_like.setTextColor(Color.GRAY)
            }

            // Визуализация комментариев
            if (post.CommentdByMe) {
                imageBtn_commit.setImageResource(R.drawable.ic_mode_comment_red_24dp)
                textView_counte_commit.setTextColor(Color.RED)
            } else {
                imageBtn_commit.setImageResource(R.drawable.ic_mode_comment_black_24dp)
                textView_counte_commit.setTextColor(Color.GRAY)
            }

            // Визуализация "поделиться"
            if (post.ShareByMe) {
                imageBtn_share.setImageResource(R.drawable.ic_share_red_24dp)
                textView_counte_share.setTextColor(Color.RED)
            } else {
                imageBtn_share.setImageResource(R.drawable.ic_share_black_24dp)
                textView_counte_share.setTextColor(Color.GRAY)
            }

        }
    }


    // Проверка на "0" счетчиков для визуализации в TextView
    fun zeroСheck(counter: Int): String {

        val counterText: String

        if (counter == 0) {
            counterText = ""
        } else {
            counterText = counter.toString()
        }

        return counterText
    }

    // Проверка на "0" счетчиков для визуализации в TextView
    fun sharePost(author: String, created: String, content: String): Unit {

        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT, """
                                ${author} (${created})
    
                                ${content}
                            """.trimIndent()
            )
            type = "text/plain"
        }
        itemView.context.startActivity(intent)
    }
}


