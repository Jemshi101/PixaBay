package com.thinkpalm.pixabay.network.models.login

import com.google.gson.annotations.SerializedName

class LoginRequest {

	@SerializedName("email")
	var email: String = ""
	@SerializedName("Password")
	var password: String = ""

}
