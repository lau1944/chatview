package com.vau.chatview

import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vau.chatview.databinding.MyChatBinding
import com.vau.chatview.databinding.OtherChatBinding
import java.lang.Exception

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val myChatViewType: Int = 1
    private val otherChatViewType: Int = 0

    private lateinit var otherChatBinding: OtherChatBinding
    private lateinit var myChatBinding: MyChatBinding
    private var observers: List<NewChatObserver>? = ArrayList()
    private var list: List<ChatMessage>? = ArrayList()

    class MyChatViewHolder(private val binding: MyChatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ChatMessage) {
            binding.message.text = item.message
            binding.time.text = item.date
        }
    }

    class OtherChatViewHolder(private val binding: OtherChatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(view: View, item: ChatMessage) = with(view){
            binding.message.text = item.message
            binding.name.text = item.userName
            binding.time.text = item.date
            Glide.with(this).load(item.userIcon).into(binding.userImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == otherChatViewType) {
            otherChatBinding = OtherChatBinding.inflate(LayoutInflater.from(parent.context))
            OtherChatViewHolder(otherChatBinding)
        } else {
            myChatBinding = MyChatBinding.inflate(LayoutInflater.from(parent.context))
            MyChatViewHolder(myChatBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            myChatViewType -> {
                (holder as MyChatViewHolder).onBind(getChatItem(position))
            }
            otherChatViewType -> {
                (holder as OtherChatViewHolder).onBind(holder.itemView, getChatItem(position))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val chatMessage = getChatItem(position)
        return if (chatMessage.isMine) myChatViewType else otherChatViewType
    }

    fun submitList(submitList: List<ChatMessage>) {
        list = submitList
    }

    fun addMessageToList(message: ChatMessage) {
        (list as ArrayList).add(message)
        notifyObserver(message)
    }

    fun addAll(list: List<ChatMessage>) {
        (list as ArrayList).addAll(list)
        notifyObserver(list)
    }

    fun notifyObserver(data: Any) {
        observers?.forEach { it ->
            it.update(data)
        }
    }

    fun addObserver(observer: NewChatObserver?) {
        if (observer != null)
            (observers as ArrayList).add(observer)
    }

    fun removeObserver(observer: NewChatObserver?) {
        if (observer != null)
            (observers as ArrayList).remove(observer)
    }

    fun getChatItem(index: Int) : ChatMessage {
        if (list != null) {
            return list!![index]
        } else {
            throw Exception("Please submit the list first before get item")
        }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}