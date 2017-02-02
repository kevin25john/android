package com.electronic.electronicsalesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class RegionNLocation extends Activity {

	Spinner regionSpinner, locationSpinner;
	Button button;
	int regionIndex;
	String selectedRegion, selectedLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_region_nlocation);
		regionSpinner = (Spinner)findViewById(R.id.region);
		locationSpinner = (Spinner)findViewById(R.id.location);
		button = (Button)findViewById(R.id.continueWithLoc);
		
		final String []defaultArr = (String[])getResources().getStringArray(R.array.defaultArr);
		ArrayAdapter<String> defaultAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, defaultArr);
		regionSpinner.setAdapter(defaultAdapter);
		
		final String []regionArr = (String[])getResources().getStringArray(R.array.regionArray);
		ArrayAdapter<String> regionAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, regionArr);
		regionSpinner.setAdapter(regionAdapter);

		final String []mumbaiWesternArr = (String[])getResources().getStringArray(R.array.MumbaiWesternArray);
		final ArrayAdapter<String> mumbaiWesternAdapter = new ArrayAdapter<String>(getApplicationContext(), 
				android.R.layout.simple_spinner_item, mumbaiWesternArr);
		
		final String []mumbaiCentralArr = (String[])getResources().getStringArray(R.array.MumbaiCentralArray);
		final ArrayAdapter<String> mumbaiCentralAdapter = new ArrayAdapter<String>(getApplicationContext(), 
				android.R.layout.simple_spinner_item, mumbaiCentralArr);
		
		final String []mumbaiSouthArr = (String[])getResources().getStringArray(R.array.MumbaiSouthArray);
		final ArrayAdapter<String> mumbaiSouthAdapter = new ArrayAdapter<String>(getApplicationContext(), 
				android.R.layout.simple_spinner_item, mumbaiSouthArr);
		
		final String []naviMumbaiArr = (String[])getResources().getStringArray(R.array.NaviMumbaiArr);
		final ArrayAdapter<String> naviMumbaiAdapter = new ArrayAdapter<String>(getApplicationContext(), 
				android.R.layout.simple_spinner_item, naviMumbaiArr);
		
		regionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) 
			{
				Toast.makeText(getApplicationContext(), regionArr[position], Toast.LENGTH_LONG).show();
				regionIndex=position;
				selectedRegion = regionArr[position];
				if(position == 0)
				{
					locationSpinner.setAdapter(mumbaiWesternAdapter);
				}
				else if(position == 1)
				{
					locationSpinner.setAdapter(mumbaiCentralAdapter);
				}
				else if(position == 2)
				{
					locationSpinner.setAdapter(mumbaiSouthAdapter);
				}
				else if(position == 3)
				{
					locationSpinner.setAdapter(naviMumbaiAdapter);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		locationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int pos, long id) {

				if(regionIndex == 0)
					selectedLocation = mumbaiWesternArr[pos];
				else if(regionIndex == 1)
					selectedLocation = mumbaiCentralArr[pos];
				else if(regionIndex == 2)
					selectedLocation = mumbaiSouthArr[pos];
				else
					selectedLocation = naviMumbaiArr[pos];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), OrderDetails.class);
				Bundle bundle = new Bundle();
				bundle.putString("region", selectedRegion);
				bundle.putString("location", selectedLocation);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.region_nlocation, menu);
		return true;
	}

}
