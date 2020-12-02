package com.vau.chatview

data class ChatMessage (
    val isMine: Boolean,
    val message: String? = "",
    val userId: String? = "",
    val userName: String? = "",
    val userIcon: String? = "",
    val date: String? = ""){
}