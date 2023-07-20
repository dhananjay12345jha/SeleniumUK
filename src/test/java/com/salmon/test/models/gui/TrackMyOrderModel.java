package com.salmon.test.models.gui;

import lombok.Data;

/**
 * Created by hbkpreddy on 24/01/2018
 */

@Data
public class TrackMyOrderModel {
	public String getOrderNumber()
	{
		return orderNumber;
	}

	public void setOrderNumber(final String orderNumber)
	{
		this.orderNumber = orderNumber;
	}

	public String getOrderStatus()
	{
		return orderStatus;
	}

	public void setOrderStatus(final String orderStatus)
	{
		this.orderStatus = orderStatus;
	}

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

	private String orderNumber;
    private String orderStatus;
    private String deliveryAddress;
    private String deliveryDetails;
}
