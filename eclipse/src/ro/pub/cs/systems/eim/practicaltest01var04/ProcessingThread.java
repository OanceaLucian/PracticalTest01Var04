package ro.pub.cs.systems.eim.practicaltest01var04;

import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread {
	 
	  private Context context = null;
	  private boolean isRunning = true;
	 
	  private Random random = new Random();
	 
	  
	 
	  public ProcessingThread(Context context) {
	    this.context = context;
	 
	   
	  }
	 
	  @Override
	  public void run() {
	    Log.d("[ProcessingThread]", "Thread has started!");
	    while (isRunning) {
	      sendMessage();
	      sleep();
	    }
	    Log.d("[ProcessingThread]", "Thread has stopped!");
	  }
	 
	  private void sendMessage() {
	    Intent intent = new Intent();
	    intent.setAction("guess");
	    int rr = random.nextInt(10);
	    Log.d("Number" ,String.valueOf(rr));
	    intent.putExtra("message",rr );
	    context.sendBroadcast(intent);
	  }
	 
	  private void sleep() {
	    try {
	      Thread.sleep(1500);
	    } catch (InterruptedException interruptedException) {
	      interruptedException.printStackTrace();
	    }
	  }
	 
	  public void stopThread() {
	    isRunning = false;
	  }
	}