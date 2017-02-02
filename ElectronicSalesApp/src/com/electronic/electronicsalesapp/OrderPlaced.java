package com.electronic.electronicsalesapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.electronic.electronicsalesapp.bean.Product;

public class OrderPlaced extends Activity {

	TextView totalAmt, netAmt, date, discount;
	RadioGroup radioGroup;
	RadioButton cashRadio, creditRadio;
	Button saveOrder;

	Location l;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_placed);
		
		totalAmt = (TextView)findViewById(R.id.totalAmt);
		netAmt = (TextView)findViewById(R.id.netAmt);
		date = (TextView)findViewById(R.id.date);
		discount = (TextView)findViewById(R.id.discount);
		saveOrder = (Button)findViewById(R.id.saveOrder);
		
		
		radioGroup = (RadioGroup)findViewById(R.id.paymentRadio);
		cashRadio = (RadioButton)findViewById(R.id.cash);
		creditRadio = (RadioButton)findViewById(R.id.credit);
		
		Bundle bundle = getIntent().getExtras();
		final String total = bundle.getString("total");
		final String totalWithDiscount = bundle.getString("totalWithDiscount");
		final String vendor = bundle.getString("vendor");
		final String region = bundle.getString("region");
		
		final List<Product> productList = (ArrayList<Product>) bundle.getSerializable("productList");
		
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		final String todaysDate = dateformat.format(new Date());
		
		date.setText("Date: "+todaysDate);
		totalAmt.setText("Total Amount: "+total);
		netAmt.setText("Net Amount: "+totalWithDiscount);
	
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup grup, int checekdId) {

				RadioButton radioBtn = (RadioButton)radioGroup.findViewById(checekdId);
				
				switch(radioBtn.getId())
				{
					case R.id.cash :
						netAmt.setText("Net Amount: "+totalWithDiscount);
						discount.setText("10% discount on cash");
						break;
					case R.id.credit:
						netAmt.setText("");
						discount.setText("");
						break;
				}

			}
		});
		
		
		saveOrder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ServiceToSaveLocation serviceToSaveLocation = new ServiceToSaveLocation(getApplicationContext());
				l = serviceToSaveLocation.getLocation();
				
				if(isExternalStorageAvailable())
				{
					try {
						SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
						String todayDate = format.format(new Date());
						File file = new File("/sdcard/OrderPlaced"+todayDate+".txt");
						file.createNewFile();
						FileOutputStream fos = new FileOutputStream(file);
						OutputStreamWriter osw = new OutputStreamWriter(fos);
						if(l != null)
							osw.append("Latitude: " + String.valueOf(l.getLatitude()) + ", " + "Longitude: " + String.valueOf(l.getLongitude()));
						osw.append("Region: "+region);
						osw.append("\nDate: "+todaysDate);
						osw.append(vendor);
						for(Product product : productList)
						{
							osw.append("\n product -> "+product.getProductName());
							osw.append("\n Quantity -> "+product.getProductQty());
							osw.append("\n Cost -> "+product.getPerProductPrice());
						}
						osw.append("\n Total cost -> "+total);					
						
						osw.close();
						fos.close();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				
				Intent intent = new Intent(getApplicationContext(), Logout.class);
				startActivity(intent);
				finish();
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_placed, menu);
		return true;
	}

	public boolean isExternalStorageAvailable()
	{
		String state = Environment.getExternalStorageState();
		
		if(Environment.MEDIA_MOUNTED.equals(state))
			return true;
		return false;
	}
}
