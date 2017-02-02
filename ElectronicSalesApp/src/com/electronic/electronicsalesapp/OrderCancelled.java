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
import android.widget.EditText;
import android.widget.Toast;

import com.electronic.electronicsalesapp.bean.Product;

public class OrderCancelled extends Activity {

	EditText odrCancelledReason;
	Button saveCancellReason, logout;

	Location l;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_cancelled);
		
		odrCancelledReason = (EditText)findViewById(R.id.orderCancelReason);
		saveCancellReason = (Button)findViewById(R.id.saveOdrCanReason);
		logout = (Button)findViewById(R.id.logoutBtn);
		
		Bundle bundle = getIntent().getExtras();
		final String total = bundle.getString("total");
		//final String totalWithDiscount = bundle.getString("totalWithDiscount");
		final String vendor = bundle.getString("vendor");
		final String region = bundle.getString("region");
		
		final List<Product> productList = (ArrayList<Product>) bundle.getSerializable("productList");
		
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
		final String todaysDate = dateformat.format(new Date());
		
		saveCancellReason.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(odrCancelledReason.getText().toString() != null && !odrCancelledReason.getText().toString().equals(""))
				{
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
							osw.append("\n order Cancelled Reason -> "+odrCancelledReason.getText().toString());
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
						
						Toast.makeText(getApplicationContext(), "Reason is saved.", Toast.LENGTH_LONG).show();
					}
				}
				else
					Toast.makeText(getApplicationContext(), "Please enter the order cancellation reason.", Toast.LENGTH_LONG).show();
				
			}
		});
		
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(odrCancelledReason.getText().toString() != null && !odrCancelledReason.getText().toString().equals(""))
				{
					Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(intent);
				}
				else
					Toast.makeText(getApplicationContext(), "Please enter the order cancellation reason.", Toast.LENGTH_LONG).show();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_cancelled, menu);
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
