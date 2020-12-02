package com.vau.chatview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ChatAdapter
    private var list = ArrayList<ChatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i: Int in 0..5) {
            list.add(
                ChatMessage(false, "hello", "jimmy",
                "icon", "2020-12-31")
            )
            list.add(ChatMessage(true, "hi", "me",
                "icon", "2020-12-31"))
        }
        recyclerView = findViewById(R.id.demo_recycler)
        adapter = ChatAdapter()
        adapter.submitList(list)
        recyclerView.adapter = adapter
        adapter.addObserver(object: NewChatObserver{
            override fun update(newMessage: ChatMessage) {
                Log.d("DemoActivity", newMessage.message.toString())
            }
        })

        adapter.addMessageToList(ChatMessage(false, "new message", "jimmy",
            "icon", "2020-12-31"))
    }
}