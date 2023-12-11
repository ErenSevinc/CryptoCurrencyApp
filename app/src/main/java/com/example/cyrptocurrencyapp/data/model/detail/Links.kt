package com.example.example

import com.google.gson.annotations.SerializedName


data class Links(

    @SerializedName("homepage") var homepage: ArrayList<String> = arrayListOf(),
    @SerializedName("blockchain_site") var blockchainSite: ArrayList<String> = arrayListOf(),
    @SerializedName("official_forum_url") var officialForumUrl: ArrayList<String> = arrayListOf(),
    @SerializedName("chat_url") var chatUrl: ArrayList<String> = arrayListOf(),
    @SerializedName("announcement_url") var announcementUrl: ArrayList<String> = arrayListOf(),
    @SerializedName("twitter_screen_name") var twitterScreenName: String? = null,
    @SerializedName("facebook_username") var facebookUsername: String? = null,
    @SerializedName("bitcointalk_thread_identifier") var bitcointalkThreadIdentifier: String? = null,
    @SerializedName("telegram_channel_identifier") var telegramChannelIdentifier: String? = null,
    @SerializedName("subreddit_url") var subredditUrl: String? = null,
    @SerializedName("repos_url") var reposUrl: ReposUrl? = ReposUrl()

)