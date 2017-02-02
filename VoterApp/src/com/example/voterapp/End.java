package com.example.voterapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class End extends Activity {
Button b1,b2,b3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end);
		b1=(Button)findViewById(R.id.bu1);
		b2=(Button)findViewById(R.id.bu2);
		b3=(Button)findViewById(R.id.bu3);
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in4=new Intent(End.this,MainActivity.class);
				startActivity(in4);
				finish();
				}
		});
		
         b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in5=new Intent(End.this,Admin.class);
				startActivity(in5);
				finish();
				}
		});

         b3.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		finish();
		}
});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.end, menu);
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
