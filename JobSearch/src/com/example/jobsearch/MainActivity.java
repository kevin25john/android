package com.example.jobsearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Thread t = new Thread()
		{
			@Override
			public void run() {

				try
				{
					sleep(3*1000);
					Intent intent = new Intent(getBaseContext(), Login.class);
					startActivity(intent);
					finish();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		};
		t.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
