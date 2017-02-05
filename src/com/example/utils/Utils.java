package com.example.utils;

import android.app.Activity;
import android.view.Display;

public class Utils {

	/**
	 * 获得屏幕的宽度
	 * @return
	 */
	public static int getScreenWidth(Activity activity){
		
		Display display = activity.getWindowManager().getDefaultDisplay();
		return display.getWidth();
	}
}
