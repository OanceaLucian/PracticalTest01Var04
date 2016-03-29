package ro.pub.cs.systems.eim.practicaltest01var04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var04Service extends Service {
	private ProcessingThread processingThread = null;
	public PracticalTest01Var04Service() {
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		processingThread = new ProcessingThread(this);
	    processingThread.start();
		return Service.START_REDELIVER_INTENT;
	}
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		return null;
	}
}
