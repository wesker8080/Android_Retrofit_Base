package com.elite.etl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;


/**
 *
 * @author wesker
 * @date 2018/6/14 13:34
 */
public class ProtectService extends Service {
	private static final String TAG = "MainService";
	private final String MAIN_SERVICE = "com.elite.etl.service.ProtectService";
	@Override
	public void onCreate() {
		startMainService();
	}

	private void startMainService() {
		boolean isRun = ServiceUtil.isServiceWork(ProtectService.this,
				MAIN_SERVICE);
		if (!isRun) {
			try {
				protectionService.startService();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onTrimMemory(int level) {
		startMainService();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		startMainService();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return (IBinder) protectionService;
	}
	private ProtectionService protectionService = new ProtectionService.Stub() {

		@Override
		public void stopService() {
			Intent intent = new Intent(getBaseContext(), MainService.class);
			getBaseContext().stopService(intent);
		}

		@Override
		public void startService() {
			Intent intent = new Intent(getBaseContext(), MainService.class);
			getBaseContext().startService(intent);
		}
	};
}
