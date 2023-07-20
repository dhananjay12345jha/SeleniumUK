package com.salmon.test.models.gui;

import lombok.Data;

/**
 * Created by hbkpreddy on 26/01/2018
 */

@Data
public class OrderConfirmationModel {
	public String getDeliveryAddress()
	{
		return deliveryAddress;
	}

	public void setDeliveryAddress(final String deliveryAddress)
	{
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryDetails()
	{
		return deliveryDetails;
	}

	public void setDeliveryDetails(final String deliveryDetails)
	{
		this.deliveryDetails = deliveryDetails;
	}

	public String getItemQuantity()
	{
		return itemQuantity;
	}

	public void setItemQuantity(final String itemQuantity)
	{
		this.itemQuantity = itemQuantity;
	}

	public String getItemPrice()
	{
		return itemPrice;
	}

	public void setItemPrice(final String itemPrice)
	{
		this.itemPrice = itemPrice;
	}

	public String getItemTotalPrice()
	{
		return itemTotalPrice;
	}

	public void setItemTotalPrice(final String itemTotalPrice)
	{
		this.itemTotalPrice = itemTotalPrice;
	}

	public String getCardInfo()
	{
		return cardInfo;
	}

	public void setCardInfo(final String cardInfo)
	{
		this.cardInfo = cardInfo;
	}

	public String getProductName()
	{
		return productName;
	}

	public void setProductName(final String productName)
	{
		this.productName = productName;
	}

	public String getProductImage()
	{
		return productImage;
	}

	public void setProductImage(final String productImage)
	{
		this.productImage = productImage;
	}

	public String getOrderNumber()
	{
		return orderNumber;
	}

	public void setOrderNumber(final String orderNumber)
	{
		this.orderNumber = orderNumber;
	}

	private String deliveryAddress;
    private String deliveryDetails;
    private String itemQuantity;
    private String itemPrice;
    private String itemTotalPrice;
    private String cardInfo;
    private String productName;
    private String productImage;
    private String orderNumber;
}
