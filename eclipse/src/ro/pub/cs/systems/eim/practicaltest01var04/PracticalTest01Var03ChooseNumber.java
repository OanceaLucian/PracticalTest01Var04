package ro.pub.cs.systems.eim.practicaltest01var04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var03ChooseNumber extends Activity {
	private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
	EditText number = null;
	Button play = null;
	private ButtonClickListener buttonClickListener = new ButtonClickListener();

	private class ButtonClickListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.play:

				String text = number.getText().toString();
				try {
					int num = Integer.parseInt(text);
					Intent intent = new Intent(getApplicationContext(),
							PracticalTest01Var02PlayActivity.class);
					intent.putExtra("magicnumber",num);
					startActivityForResult(intent,
							SECONDARY_ACTIVITY_REQUEST_CODE);

				} catch (NumberFormatException e) {

				}

			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var03_choose_number);
		number = (EditText) findViewById(R.id.number);
		play = (Button) findViewById(R.id.play);
		play.setOnClickListener(buttonClickListener);

	}

	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_var03_choose_number,
				menu);
		return true;
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
