package com.thinkpalm.pixabay.core;


import com.thinkpalm.pixabay.network.models.login.LoginData;
import com.thinkpalm.pixabay.network.models.login.LoginResponse;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * Created by Jemsheer K D on 12 March, 2019.
 * Package com.thinkpalm.pixabay.core
 * Project Pixabay
 */
public class Config {

	// Is DEBUG Build
	public static final Boolean isDebug = true;

	public static final Boolean isDemo = true;
	// Splash screen timer
	public static int SPLASH_TIME_OUT = 2000;

	private static final String TAG = "Config";
	private static Config instance;

	private String locale = Locale.getDefault().getLanguage();
	private LoginData userData;

	private Config() {
	}

	public static Config getInstance() {
		if (instance == null)
			instance = new Config();

		return instance;
	}

	public static void clear() {
		instance = null;
		instance = new Config();
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public LoginData getUserData() {
		return userData;
	}

	public void setUserData(LoginData userData) {
		this.userData = userData;
	}

	public void saveLogin(@NotNull LoginResponse loginResponse) {
		userData = loginResponse.getData();
	}
}
