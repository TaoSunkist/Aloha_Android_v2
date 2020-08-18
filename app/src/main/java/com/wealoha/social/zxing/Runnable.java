package com.wealoha.social.zxing;

import android.os.AsyncTask;
import android.os.Build;

/**
 * 兼容低版本的子线程开启任务
 * 
 * @author hugo
 * 
 */
public class Runnable {

	public static void execAsync(AsyncTask<Object, Object, Object> task) {
		if (Build.VERSION.SDK_INT >= 11) {
			task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} else {
			task.execute();
		}

	}

}
