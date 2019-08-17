package com.thinkpalm.pixabay.network.models

import java.io.Serializable

/**
 * Created by Jemsheer K D on 19 December, 2018.
 * Package com.thinkpalm.pixabay.webservice.models
 * Project RoyalArabianTours
 */
class APIResponseBean<T>() : Serializable {
	var status: Boolean = false
	var responseBody: T? = null
	var type: String = ""

	constructor(status: Boolean = false,
	            responseBody: T? = null
	) : this() {
		this.status = status
		this.responseBody = responseBody
		this.type = ""
	}

	constructor(status: Boolean = false,
	            responseBody: T? = null,
	            type: String = ""
	) : this() {
		this.status = status
		this.responseBody = responseBody
		this.type = type
	}

	fun isErrorResponse(): Boolean {
		return (status && responseBody != null)
	}
}
