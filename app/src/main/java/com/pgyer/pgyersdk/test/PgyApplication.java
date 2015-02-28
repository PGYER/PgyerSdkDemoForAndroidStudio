package com.pgyer.pgyersdk.test;


import android.app.Application;

import com.pgyersdk.crash.PgyCrashManager;

public class PgyApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		PgyCrashManager.register(this,Constants.APPID);
	}
}
