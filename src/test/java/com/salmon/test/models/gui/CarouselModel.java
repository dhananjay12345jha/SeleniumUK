package com.salmon.test.models.gui;

import lombok.Data;

@Data
public class CarouselModel {
	public String getProductName()
	{
		return productName;
	}

	public void setProductName(final String productName)
	{
		this.productName = productName;
	}

	String productName;
}
