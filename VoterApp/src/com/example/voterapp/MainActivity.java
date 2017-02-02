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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	String[] str=new String[100];
	EditText e1,e2;
	Button b1,b2;
	String se1,se2,sw1="";
	TextView t1;
	int y=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try{
        	
           	FileInputStream fis = openFileInput("voters.txt");
           	InputStreamReader isr= new InputStreamReader(fis);
           	char[] buffer= new char[10];
           	int l;
           	String s="";
           	while((l=isr.read(buffer))>0)
           	{
           		String s1 = String.copyValueOf(buffer,0,l);
           		s += s1;
           		
           		buffer = new char[10];
           	}
           	
           	 str=s.split(",");
           	
           	 
           }
           catch(IOException ioe){
           	 
           	}
        t1=(TextView)findViewById(R.id.tv1);
       e1=(EditText)findViewById(R.id.et1);
       e2=(EditText)findViewById(R.id.et2);
       b1=(Button)findViewById(R.id.bu1);
       b2=(Button)findViewById(R.id.bu2);
       
       
       
       
       b1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			 
			se1=e1.getText().toString();
		       se2=e2.getText().toString();
		       
			for(int i=0;i<str.length;i=i+3)
			{
				if(se1.equals(str[i])&&se2.equals(str[i+1]))
				{
					if(str[i+2].equals("0"))
					{
						y=1;
						str[i+2]="1";
						int len1=str.length;
						for(int k=0;k<len1;k++)
						{
							sw1 += str[k];
							if((k+1)<len1)
								sw1 += ",";
						}
						try{
							 FileOutputStream fOut1 =openFileOutput("voters.txt", MODE_WORLD_READABLE);
							 OutputStreamWriter osw1 = new OutputStreamWriter(fOut1);
							 osw1.write(sw1);
						        osw1.flush();
						        osw1.close();
	                         }
						catch(IOException iio){
							
						
			           }
					Intent in1=new Intent(MainActivity.this,Vote.class);
					startActivity(in1);
					finish();
					}
					else{
						y=2;
						Toast.makeText(getBaseContext(), "You have already voted", Toast.LENGTH_LONG).show();
					}
				}
				else
					continue;
					
			}
			if(y==0)
			Toast.makeText(getBaseContext(), "Invalid login", Toast.LENGTH_LONG).show();
			
		}
	});
       
       b2.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent in2=new Intent(MainActivity.this,Admin.class);
			startActivity(in2);
			}
	});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
