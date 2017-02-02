package com.example.jobsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

	EditText userEditText, passEdiitText;
	Button loginBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		userEditText = (EditText)findViewById(R.id.userName);
		passEdiitText = (EditText)findViewById(R.id.userPassword);		
		loginBtn = (Button)findViewById(R.id.loginBtn);
		
		loginBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				new ValidateLoogin().execute();
				
			}
		});
		
	}

	
	private class ValidateLoogin extends AsyncTask<String, String, String>
	{

		@Override
		protected String doInBackground(String... arg0) {
			
			String user = userEditText.getText().toString();
			String pass = passEdiitText.getText().toString();
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("user", user));
			params.add(new BasicNameValuePair("pwd", pass));
			
			HttpClient httpClient=new DefaultHttpClient();  
            HttpPost httpPost=new HttpPost("http://10.0.2.2:8081/JobSearch/validateLogin");  
            
            HttpResponse res = null;
            try {
            	httpPost.setEntity(new UrlEncodedFormEntity(params));  
				res = httpClient.execute(httpPost);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            
            InputStream is=null;   
            String returRes="";  
            try {  
                is=res.getEntity().getContent();  
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));  
                String line="";  
                StringBuffer sb=new StringBuffer();  
                while ((line=bufferedReader.readLine())!=null)  
                {  
                	sb.append(line);
                }  
                returRes=sb.toString();
                return returRes;
            } catch (Exception e)  
            {  
            	e.printStackTrace();
            }  
            
            return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if(result.contains("1"))
            {
            	Intent intent = new Intent(getApplicationContext(), JobSearchByTitle.class);
            	startActivity(intent);
            	finish();
            }
            else 
            {
            	Toast.makeText(getApplicationContext(), "Invalid Login Id or Password.", Toast.LENGTH_SHORT).show();
            }
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}


