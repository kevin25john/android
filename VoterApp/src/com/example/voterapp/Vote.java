package com.example.voterapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class Vote extends Activity {
String[] str1=new String[100];
String[] str2=new String[100];
RadioGroup r1;
RadioButton rb1,rb2,rb3,rb4;
Button b1;
int j,x;
String sw="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vote);
		try{
        	FileInputStream fis1 = openFileInput("voters.txt");
        	InputStreamReader isr1= new InputStreamReader(fis1);
        	char[] buffer= new char[10];
        	int l1;
        	String ss="";
        	while((l1=isr1.read(buffer))>0)
        	{
        		String s1 = String.copyValueOf(buffer, 0, l1);
        		ss += s1;
        		buffer = new char[10];
        	}
        	 str1=ss.split(",");
        }
        catch(IOException ioef){
        	 
        	}
		try{
        	FileInputStream fis2 = openFileInput("votes.txt");
        	InputStreamReader isr2= new InputStreamReader(fis2);
        	char[] buffer= new char[10];
        	int l2;
        	String sss="";
        	while((l2=isr2.read(buffer))>0)
        	{
        		String s1 = String.copyValueOf(buffer, 0, l2);
        		sss += s1;
        		buffer = new char[10];
        	}
        	 str2=sss.split(",");
        }
        catch(IOException ioeg){
        	 
        	}
		
		r1=(RadioGroup)findViewById(R.id.rgp);
		rb1=(RadioButton)findViewById(R.id.rbu1);
		rb2=(RadioButton)findViewById(R.id.rbu2);
		rb3=(RadioButton)findViewById(R.id.rbu3);
		rb4=(RadioButton)findViewById(R.id.rbu4);
		b1=(Button)findViewById(R.id.bu1);
		r1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(rb1.isChecked())
					j=0;
				else if(rb2.isChecked())
					j=1;
				else if(rb3.isChecked())
					j=2;
				else if(rb4.isChecked())
					j=3;
				
			}
		});
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
					x=Integer.parseInt(str2[j]);
					x=x+1;
					Integer x1=new Integer(x);
					String s2=x1.toString();
					str2[j]=s2;
					
					int len=str2.length;
					for(int i=0;i<len;i++)
					{
						sw += str2[i];
						if((i+1)<len)
							sw += ",";
					}
					
					try{
						 FileOutputStream fOut =openFileOutput("votes.txt", MODE_WORLD_READABLE);
						 OutputStreamWriter osw = new OutputStreamWriter(fOut);
						 osw.write(sw);
					        osw.flush();
					        osw.close();
                         }
					catch(IOException iio){
						
					
		           }
					
					Intent in3=new Intent(Vote.this,End.class);
					startActivity(in3);
					finish();
				}
				
			
		});
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vote, menu);
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
