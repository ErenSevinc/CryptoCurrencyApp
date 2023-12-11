package com.example.example

import com.google.gson.annotations.SerializedName


data class CommunityData(

    @SerializedName("facebook_likes") var facebookLikes: String? = null,
    @SerializedName("twitter_followers") var twitterFollowers: Double? = null,
    @SerializedName("reddit_average_posts_48h") var redditAveragePosts48h: Double? = null,
    @SerializedName("reddit_average_comments_48h") var redditAverageComments48h: Double? = null,
    @SerializedName("reddit_subscribers") var redditSubscribers: Double? = null,
    @SerializedName("reddit_accounts_active_48h") var redditAccountsActive48h: Double? = null,
    @SerializedName("telegram_channel_user_count") var telegramChannelUserCount: String? = null

)