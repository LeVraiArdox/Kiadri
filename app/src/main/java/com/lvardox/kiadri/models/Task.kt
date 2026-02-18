package com.lvardox.kiadri.models

data class Task(
    val id: String = java.util.UUID.randomUUID().toString(),
    val title: String,
    val isCompleted: Boolean = false,
    val photoUri: String? = null,
    val completedAt: Long? = null
)