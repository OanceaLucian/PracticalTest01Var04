package ro.pub.cs.systems.eim.practicaltest01var04;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var02PlayActivity extends Activity {

	EditText guess = null;
	EditText score = null;
	Button generate = null;
	Button check = null;
	Button back = null;
	Timer t = new Timer();
	private Random random = new Random();
	private int generateNum;
	private int main_number = 0;
	private int sscore = 0;
	private int service_status ;
	private ButtonClickListener buttonClickListener = new ButtonClickListener();
	
	/*public class MyReceiver extends BroadcastReceiver {
		public MyReceiver() {
		}

		/*@Override
		public void onReceive(Context context, Intent intent) {
			int num = intent.getStringArrayExtra("message");
		}
	}*/

	private class ButtonClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.generate:
				generateNum = random.nextInt(10);
				guess.setText(String.valueOf(generateNum));
				/*guess.postDelayed(new Runnable() {
					public void run() {
						guess.setText("");
						;
					}
				}, 2000);*/
				break;
			case R.id.back:
				finish();
				break;
			case R.id.check:
				if (main_number == generateNum)
					sscore += 1;
				else
					sscore -= 1;
				score.setText(String.valueOf(sscore));
				break;
			default:
				break;

			}
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var02_play);
		guess = (EditText) findViewById(R.id.guess);
		score = (EditText) findViewById(R.id.score);
		generate = (Button) findViewById(R.id.generate);
		check = (Button) findViewById(R.id.check);
		back = (Button) findViewById(R.id.back);
		generate.setOnClickListener(buttonClickListener);
		back.setOnClickListener(buttonClickListener);
		check.setOnClickListener(buttonClickListener);
		Intent intent = getIntent();
		if (intent != null && intent.getExtras().containsKey("magicnumber")) {
			main_number = intent.getIntExtra("magicnumber", -1);

		}else{
		main_number = 0;
		}
		score.setText(String.valueOf(sscore));
		Intent second_intent = new Intent(getApplicationContext(),PracticalTest01Var04Service.class);
		getApplicationContext().startService(second_intent);
	      service_status = 1;
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("generate", guess.getText().toString());
	    outState.putString("score", score.getText().toString());
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		guess.setText(savedInstanceState.getString("generate"));
		generateNum = Integer.parseInt(guess.getText().toString());
		score.setText(savedInstanceState.getString("score"));
		sscore = Integer.parseInt(score.getText().toString());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_var02_play, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		Intent intent = new Intent(this,PracticalTest01Var04Service.class);
		stopService(intent);
		super.onDestroy();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
