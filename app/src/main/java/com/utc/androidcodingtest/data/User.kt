package com.utc.androidcodingtest.data

import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("id")
    var id:String?=null
    @SerializedName("first_name")
    var firstName: String? = null
    @SerializedName("last_name")
    var lastName: String? = null
    @SerializedName("avatar")
    var avatarURL: String? = null

    fun fullName(): String{
        return "$firstName $lastName"
    }
}