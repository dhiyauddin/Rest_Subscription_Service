package com.websystique.springmvc.model;

public class Subscription {

	private long id;

	private String SubscriptionType;
	private String Amount;
	private String Day;
	private String StartDate;
	private String EndDate;

	public Subscription() {
		id = 0;
	}

	public Subscription(long id, String SubscriptionType, String Amount, String Day, String StartDate, String EndDate) {
		this.id = id;
		this.SubscriptionType = SubscriptionType;
		this.Amount = Amount;
		this.Day = Day;
		this.StartDate = StartDate;
		this.EndDate = EndDate;
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

	public String getDay() {
		return Day;
	}

	public void setDay(String day) {
		Day = day;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	
	
}
