package com.design.template.domain.model

data class SampleItem(
    val id: Long = 0,
    val title: String,
    val description: String,
    val status: String,
    val createdAt: Long = System.currentTimeMillis()
)
