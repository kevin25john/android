package com.electronic.electronicsalesapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText userNameEditText, userPassEditText;
	Button loginBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*EditText userNameEditText;
		EditText userPassEditText;
		Button loginBtn;*/
		
		/*EditText userNameEditText = (EditText)findViewById(R.id.userName);
		EditText userPassEditText = (EditText)findViewById(R.id.userPassword);
		Button loginBtn = (Button)findViewById(R.id.loginBtn);*/
		
		try
		{
			userNameEditText = (EditText)findViewById(R.id.userName);
			userPassEditText = (EditText)findViewById(R.id.userPassword);
			loginBtn = (Button)findViewById(R.id.loginBtn);
			
			loginBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					String userName = userNameEditText.getText().toString();
					String password = userPassEditText.getText().toString(); 
						
					if(userName != null && password != null && !userName.equals("") && !password.equals("") )
					{
						if(userName.equals("a") && password.equals("a"))
						{
							Intent intent = new Intent(getApplicationContext(), RegionNLocation.class);
							startActivity(intent);
						}
						else
							Toast.makeText(getApplicationContext(), "Invalid Userid or Password", Toast.LENGTH_LONG).show();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Please enter username and password.", Toast.LENGTH_LONG).show();
					}
				}
			});
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
