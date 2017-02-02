/**
 * 
 */
package com.electronic.electronicsalesapp.bean;

import java.io.Serializable;

/**
 * @author Geeta
 *
 */
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productName;
	private double perProductPrice;
	private int productQty;
	private double productCost;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPerProductPrice() {
		return perProductPrice;
	}
	public void setPerProductPrice(double perProductPrice) {
		this.perProductPrice = perProductPrice;
	}
	public int getProductQty() {
		return productQty;
	}
	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}
	public double getProductCost() {
		return productCost;
	}
	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}

	
	
	
}
