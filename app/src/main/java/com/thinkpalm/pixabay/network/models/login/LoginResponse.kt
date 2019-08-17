package com.thinkpalm.pixabay.network.models.login

import com.google.gson.annotations.SerializedName

/**
 * Created by Jemsheer K D on 17 August, 2019.
 * Package com.thinkpalm.pixabay.network.models.login
 * Project Orchid
 */
class LoginResponse {
    @SerializedName("data")
    var data: LoginData = LoginData()
}


class LoginData {
    @SerializedName("user_id")
    var userID: String = ""
    @SerializedName("email")
    var email: String = ""
    @SerializedName("first_name")
    var firstName: String = ""
    @SerializedName("last_name")
    var lastName: String = ""
    @SerializedName("auth_token")
    var token: String = ""
    @SerializedName("is_email_verified")
    var isEmailVerified: Boolean = false
    @SerializedName("full_name")
    var fullName: String = ""
    @SerializedName("user_name")
    var userName: String = ""

    var isLoggedIn = false
}
