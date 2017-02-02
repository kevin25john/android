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
import android.widget.TextView;
import android.widget.Toast;

public class JobSearchByTitle extends Activity {

	EditText jobTitleEditText;
	TextView jobDescTextView;
	Button button;
	//String result1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_job_search_by_title);
		
		jobTitleEditText =(EditText)findViewById(R.id.jobsTitle);
		jobDescTextView =(TextView)findViewById(R.id.seachedJobDesc); 
		button = (Button)findViewById(R.id.submitJobTle);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				//test();
				new JobSearchResponce().execute();
				//Toast.makeText(getApplicationContext(), result1+" aaa", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	/*private void test()
	{
		new JobSearchResponce(this).execute();
	}*/
	
	private class JobSearchResponce extends AsyncTask<String, String, String>
	{
		/*private Activity activity; 
		
		public JobSearchResponce(Activity activity) {
			this.activity = activity;
		}*/
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			//super.onPostExecute(result);
			//result1 = result;
			if(result != null && !result.equals(""))
				jobDescTextView.setText(result);
			else
				jobDescTextView.setText("Not Found");
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			
			String jobTitle = jobTitleEditText.getText().toString();
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("jobTitle", jobTitle));
			
			
			HttpClient httpClient=new DefaultHttpClient();  
            HttpPost httpPost = new HttpPost("http://10.0.2.2:8081/JobSearch/jobSearchByTitle");  
            
            HttpResponse res = null;
            try {
            	httpPost.setEntity(new UrlEncodedFormEntity(params));  
				res = httpClient.execute(httpPost);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
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
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.job_search_by_title, menu);
		return true;
	}

}
