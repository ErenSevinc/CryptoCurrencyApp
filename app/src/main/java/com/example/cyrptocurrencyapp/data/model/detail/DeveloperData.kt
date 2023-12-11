package com.example.example

import com.google.gson.annotations.SerializedName


data class DeveloperData(

    @SerializedName("forks") var forks: Double? = null,
    @SerializedName("stars") var stars: Double? = null,
    @SerializedName("subscribers") var subscribers: Double? = null,
    @SerializedName("total_issues") var totalIssues: Double? = null,
    @SerializedName("closed_issues") var closedIssues: Double? = null,
    @SerializedName("pull_requests_merged") var pullRequestsMerged: Double? = null,
    @SerializedName("pull_request_contributors") var pullRequestContributors: Double? = null,
    @SerializedName("code_additions_deletions_4_weeks") var codeAdditionsDeletions4Weeks: CodeAdditionsDeletions4Weeks? = CodeAdditionsDeletions4Weeks(),
    @SerializedName("commit_count_4_weeks") var commitCount4Weeks: Double? = null,
    @SerializedName("last_4_weeks_commit_activity_series") var last4WeeksCommitActivitySeries: ArrayList<Double> = arrayListOf()

)