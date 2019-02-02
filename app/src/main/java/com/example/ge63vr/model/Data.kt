package com.example.ge63vr.model
data class Data(
        val date: String,
        val stories: List<StoriesBean>,
        val top_stories: List<TopStoriesBean>
)

data class StoriesBean(
        val ga_prefix: String,
        val id: Int,
        val images: List<String>,
        val multipic: Boolean,
        val title: String,
        val type: Int
)

data class TopStoriesBean(
        val ga_prefix: String,
        val id: Int,
        val image: String,
        val title: String,
        val type: Int
)