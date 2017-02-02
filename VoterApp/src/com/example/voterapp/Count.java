package com.example.voterapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Count extends Activity {
TextView t1,t2,t3,t4;
Button b1;
String[] str3=new String[100];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.count);
		try{
        	FileInputStream fis5 = openFileInput("votes.txt");
        	InputStreamReader isr5= new InputStreamReader(fis5);
        	char[] buffer= new char[10];
        	int l5;
        	String sss="";
        	while((l5=isr5.read(buffer))>0)
        	{
        		String s1 = String.copyValueOf(buffer, 0, l5);
        		sss += s1;
        		buffer = new char[10];
        	}
        	 str3=sss.split(",");
        }
        catch(IOException ioeg){
        	 
        	}
		t1=(TextView)findViewById(R.id.tv1);
		t2=(TextView)findViewById(R.id.tv2);
		t3=(TextView)findViewById(R.id.tv3);
		t4=(TextView)findViewById(R.id.tv4);
		b1=(Button)findViewById(R.id.bu1);
		t1.setText("Congress: "+str3[0]);
		t2.setText("BJP: "+str3[1]);
		t3.setText("Shiv Sena: "+str3[2]);
		t4.setText("MNS: "+str3[3]);
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			  }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.count, menu);
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
