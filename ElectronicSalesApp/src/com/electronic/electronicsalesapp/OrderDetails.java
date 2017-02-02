package com.electronic.electronicsalesapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.electronic.electronicsalesapp.bean.Product;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class OrderDetails extends Activity {

	Spinner vendorSpinner, productList1Spinner, productList2Spinner, productList3Spinner;
	EditText editTextQty1, editTextQty2, editTextQty3;
	Button odrPlacedBtn, odrCancelledBtn;
	String vendorName, product1, product2, product3, qty1, qty2, qty3;	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_details);
		
		vendorSpinner = (Spinner)findViewById(R.id.vendors);
		productList1Spinner = (Spinner)findViewById(R.id.productList1);
		productList2Spinner = (Spinner)findViewById(R.id.productList2);
		productList3Spinner = (Spinner)findViewById(R.id.productList3);
		
		editTextQty1 = (EditText)findViewById(R.id.product1Qty);
		editTextQty2 = (EditText)findViewById(R.id.product2Qty);
		editTextQty3 = (EditText)findViewById(R.id.product3Qty);
		
		odrPlacedBtn = (Button)findViewById(R.id.orderPlaced);
		odrCancelledBtn = (Button)findViewById(R.id.orderCancelled);
		
		final String westernVendorsArr[] = (String[])getResources().getStringArray(R.array.MumbaiWesternArray);
		ArrayAdapter<String> westernAdaper = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, westernVendorsArr);
		
		final String centralVendorsArr[] = (String[])getResources().getStringArray(R.array.MumbaiWesternArray);
		ArrayAdapter<String> centralAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, centralVendorsArr);
		
		final String southVendorsArr[] = (String[])getResources().getStringArray(R.array.MumbaiWesternArray);
		ArrayAdapter<String> southAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, southVendorsArr);
		
		final String naviMumbaiVendorsArr[] = (String[])getResources().getStringArray(R.array.MumbaiWesternArray);
		ArrayAdapter<String> naviMumbaiAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, naviMumbaiVendorsArr);
		
		Bundle bundle = getIntent().getExtras();
		final String area = bundle.getString("region");
		Toast.makeText(getApplicationContext(), bundle.getString("region")+" "+ bundle.getString("location"), Toast.LENGTH_LONG).show();
		
		if(area.contains("Western"))
			vendorSpinner.setAdapter(westernAdaper);
		else if(area.contains("Central"))
			vendorSpinner.setAdapter(centralAdapter);
		else if(area.contains("South"))
			vendorSpinner.setAdapter(southAdapter);
		else if(area.contains("Navi"))
			vendorSpinner.setAdapter(naviMumbaiAdapter);
			
		vendorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				if(area.contains("western"))
					vendorName = westernVendorsArr[position];
				else if(area.contains("central"))
					vendorName = centralVendorsArr[position];
				else if(area.contains("south"))
					vendorName = southVendorsArr[position];
				else if(area.contains("navi"))
					vendorName = naviMumbaiVendorsArr[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		final String productList1Arr[] = (String[])getResources().getStringArray(R.array.productList1);
		ArrayAdapter<String> productList1Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, productList1Arr);
		productList1Spinner.setAdapter(productList1Adapter);
		
		final String productList2Arr[] = (String[])getResources().getStringArray(R.array.productList2);
		ArrayAdapter<String> productList2Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, productList2Arr);
		productList2Spinner.setAdapter(productList2Adapter);
		
		final String productList3Arr[] = (String[])getResources().getStringArray(R.array.productList3);
		ArrayAdapter<String> productList3Adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, productList3Arr);
		productList3Spinner.setAdapter(productList3Adapter);
		
		productList1Spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// TODO Auto-generated method stub
				product1 = productList1Arr[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		productList2Spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// TODO Auto-generated method stub
				product2 = productList2Arr[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		productList3Spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// TODO Auto-generated method stub
				product3 = productList3Arr[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		odrPlacedBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String strQty1 = editTextQty1.getText().toString();
				String strQty2 = editTextQty2.getText().toString();
				String strQty3 = editTextQty3.getText().toString();
				double total = 0, pro1Cost = 0, pro2Cost = 0, pro3Cost = 0;
				List<Product> productList = new ArrayList<Product>();
				
				if(strQty1 != null && !strQty1.equals("") && product1 != null && !product1.equalsIgnoreCase("select"))
				{
					productList = setProductList(product1, strQty1, productList);
					/*String rs = product1.split("Rs.")[1];
					String price = rs.substring(0, rs.length()-1).trim();
					pro1Cost = Double.valueOf(price) * Double.valueOf(strQty1);
					Product product = new Product();
					product.setProductName(product1);
					product.setProductQty(Integer.valueOf(strQty1));
					product.setPerProductPrice(Double.valueOf(price));
					product.setProductCost(pro1Cost);
					productList.add(product);*/
				}
				if(strQty2 != null && !strQty2.equals("") && product2 != null && !product2.equalsIgnoreCase("select"))
				{
					productList = setProductList(product2, strQty2, productList);
					/*String rs = product2.split("Rs.")[1];
					String price = rs.substring(0, rs.length()-1).trim();
					pro2Cost = Double.valueOf(price) * Double.valueOf(strQty1);
					Product product = new Product();
					product.setProductName(product1);
					product.setProductQty(Integer.valueOf(strQty1));
					product.setPerProductPrice(Double.valueOf(price));
					product.setProductCost(pro2Cost);
					productList.add(product);*/
				}
				if(strQty3 != null && !strQty3.equals("") && product3 != null && !product3.equalsIgnoreCase("select"))
				{
					productList = setProductList(product3, strQty3, productList);
					
					/*String rs = product3.split("Rs.")[1];
					String price = rs.substring(0, rs.length()-1).trim();
					pro3Cost = Double.valueOf(price) * Double.valueOf(strQty1);
					Product product = new Product();
					product.setProductName(product1);
					product.setProductQty(Integer.valueOf(strQty1));
					product.setPerProductPrice(Double.valueOf(price));
					product.setProductCost(pro3Cost);
					productList.add(product);*/
				}
				
				//total = pro1Cost + pro2Cost + pro3Cost;
				for(Product pro: productList)
				{
					total += pro.getProductCost();
				}
				double totalWithDiscount = total - (total/10);
				Toast.makeText(getApplicationContext(), "product: "+total +" "+totalWithDiscount , Toast.LENGTH_LONG).show();	
		
				Intent intent = new Intent(getApplicationContext(), OrderPlaced.class);
				Bundle extras = new Bundle();
				extras.putString("total", String.valueOf(total));
				extras.putString("totalWithDiscount", String.valueOf(totalWithDiscount));
				extras.putString("vendor", vendorName);
				extras.putSerializable("productList", (Serializable)productList);
				extras.putString("region", area);
				intent.putExtras(extras);
				startActivity(intent);
			}
		});
		
		odrCancelledBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				String strQty1 = editTextQty1.getText().toString();
				String strQty2 = editTextQty2.getText().toString();
				String strQty3 = editTextQty3.getText().toString();
				double total = 0, pro1Cost = 0, pro2Cost = 0, pro3Cost = 0;
				
				List<Product> productList = new ArrayList<Product>();
				
				if(strQty1 != null && !strQty1.equals("") && product1 != null && !product1.equalsIgnoreCase("select"))
				{
					productList = setProductList(product1, strQty1, productList);
				}
				if(strQty2 != null && !strQty2.equals("") && product2 != null && !product2.equalsIgnoreCase("select"))
				{
					productList = setProductList(product2, strQty2, productList);
				}
				if(strQty3 != null && !strQty3.equals("") && product3 != null && !product3.equalsIgnoreCase("select"))
				{
					productList = setProductList(product3, strQty3, productList);
				}
				for(Product pro: productList)
				{
					total += pro.getProductCost();
				}
				
				double totalWithDiscount = total - (total/10);
				Toast.makeText(getApplicationContext(), "product: "+total +" "+totalWithDiscount , Toast.LENGTH_LONG).show();	
		
				Intent intent = new Intent(getApplicationContext(), OrderCancelled.class);
				Bundle extras = new Bundle();
				extras.putString("total", String.valueOf(total));
				extras.putString("totalWithDiscount", String.valueOf(totalWithDiscount));
				extras.putString("vendor", vendorName);
				extras.putSerializable("productList", (Serializable)productList);
				extras.putString("region", area);
				intent.putExtras(extras);
				startActivity(intent);
				
				//Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();	
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_details, menu);
		return true;
	}
	
	private List<Product> setProductList(String productName, String strQty, List<Product> productList)
	{
		String rs = productName.split("Rs.")[1];
		String price = rs.substring(0, rs.length()-1).trim();
		double prodCost = Double.valueOf(price) * Double.valueOf(strQty);
		Product product = new Product();
		product.setProductName(product1);
		product.setProductQty(Integer.valueOf(strQty));
		product.setPerProductPrice(Double.valueOf(price));
		product.setProductCost(prodCost);
		productList.add(product);
		return productList;
	}
}
