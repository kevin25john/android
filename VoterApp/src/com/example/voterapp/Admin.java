package com.example.voterapp;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Admin extends Activity {
EditText e1,e2;
Button b1;
String s1,s2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin);
		b1=(Button)findViewById(R.id.bu1);
		 e1=(EditText)findViewById(R.id.et1);
	       e2=(EditText)findViewById(R.id.et2);
	       b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				s1=e1.getText().toString();
				s2=e2.getText().toString();
				if(s1.equals("Vodka")&&s2.equals("whiskey"))
				{
					Intent in7=new Intent(Admin.this,Count.class);
					startActivity(in7);
					
				}
				else
					Toast.makeText(getBaseContext(), "Invalid login", Toast.LENGTH_LONG).show();
				
			}
		});
	}

	private void setContentView(int admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin, menu);
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
