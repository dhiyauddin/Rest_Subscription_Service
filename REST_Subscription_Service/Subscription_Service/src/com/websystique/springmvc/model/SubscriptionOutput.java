package com.websystique.springmvc.model;

public class SubscriptionOutput {

	private long id;

	private String SubscriptionType;
	private String Amount;
	private String InvoiceDate;

	public SubscriptionOutput() {
		id = 0;
	}

	public SubscriptionOutput(long id, String subscriptionType, String amount, String invoiceDate) {
		this.id = id;
		this.SubscriptionType = subscriptionType;
		this.Amount = amount;
		this.InvoiceDate = invoiceDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubscriptionType() {
		return SubscriptionType;
	}

	public void setSubscriptionType(String SubscriptionType) {
		this.SubscriptionType = SubscriptionType;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String Amount) {
		this.Amount = Amount;
	}

	public String getInvoiceDate() {
		return InvoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		InvoiceDate = invoiceDate;
	}

}
