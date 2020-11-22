package com.vau.chatview

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vau.chatview.databinding.MyChatBinding
import com.vau.chatview.databinding.OtherChatBinding

class ChatAdapter(private val list: List<Any>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    lateinit var otherChatBinding: OtherChatBinding
    lateinit var myChatBinding: MyChatBinding

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ChatViewHolder {
        return if (viewType == 0) {
            val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.other_chat, parent, false)
            otherChatBinding = OtherChatBinding.inflate(LayoutInflater.from(parent.context))
            ChatViewHolder(layoutInflater)
        } else {
            val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.my_chat, parent, false)
            myChatBinding = MyChatBinding.inflate(LayoutInflater.from(parent.context))
            ChatViewHolder(layoutInflater)
        }
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) = with(holder.itemView) {
        if (holder.itemViewType == 0) {

        } else {

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}