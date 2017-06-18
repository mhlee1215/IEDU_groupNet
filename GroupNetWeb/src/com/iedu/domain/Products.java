package com.iedu.domain;

/**
 * @author mhlee
 * Please check here for model info.
 * http://www.mysqltutorial.org/mysql-sample-database.aspx
 *
 */

public class Products {
	String productCode;
	String productName;
	String productLine;
	
	//Getter and setter must be added
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	@Override
	public String toString() {
		return "Products [productCode=" + productCode + ", productName=" + productName + ", productLine=" + productLine
				+ "]";
	}
	
	//... can add more
}
