package com.salmon.test.models.gui;

import lombok.Data;

@Data
public class CheckoutModel {
	public String getProductCode()
	{
		return productCode;
	}

	public void setProductCode(final String productCode)
	{
		this.productCode = productCode;
	}

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(final String productName)
	{
		this.productName = productName;
	}

	public String getCollectionType()
	{
		return collectionType;
	}

	public void setCollectionType(final String collectionType)
	{
		this.collectionType = collectionType;
	}

	String productCode;
    String productName;
    String collectionType;

}
