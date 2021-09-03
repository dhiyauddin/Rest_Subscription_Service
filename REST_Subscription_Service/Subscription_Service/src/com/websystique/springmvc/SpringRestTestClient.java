package com.websystique.springmvc;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.websystique.springmvc.model.Subscription;

public class SpringRestTestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8080/Subscription_Service-0.0.1-SNAPSHOT";

	/* GET */
	@SuppressWarnings("unchecked")
	private static void listAllSubscriptions() {
		System.out.println("Testing listAllSubscription API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> SubscriptionsMap = restTemplate
				.getForObject(REST_SERVICE_URI + "/Subscription/", List.class);

		if (SubscriptionsMap != null) {
			for (LinkedHashMap<String, Object> map : SubscriptionsMap) {
				System.out.println("Subscription : id=" + map.get("id") + ", Name=" + map.get("name") + ", Level="
						+ map.get("level"));
				;
			}
		} else {
			System.out.println("No Subscription exist----------");
		}
	}

	/* GET */
	private static void getSubscription() {
		System.out.println("Testing getSubscription API----------");
		RestTemplate restTemplate = new RestTemplate();
		Subscription subscription = restTemplate.getForObject(REST_SERVICE_URI + "/SubscriptionById/3",
				Subscription.class);
		System.out.println("Subscription Input : id=" + subscription.getId() + ", Subscription_Type="
				+ subscription.getSubscriptionType() + ", " + "Amount=" + subscription.getAmount() + ", Day="
				+ subscription.getDay() + ", StartDate=" + subscription.getStartDate() + ", EndDate="
				+ subscription.getEndDate());

		dateCountInvoice(subscription);

	}

	/* POST */
	private static void createSubscription() {
		System.out.println("Testing create Subscription API----------");
		RestTemplate restTemplate = new RestTemplate();
		Subscription subscription = new Subscription(0, "Daily", "10", "Friday", "03/09/2021", "03/09/2021");
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/SubscriptionCreate/", subscription,
				Subscription.class);
		System.out.println("Location : " + uri.toASCIIString());
	}

	/* PUT */
	private static void updateSubscription() {
		System.out.println("Testing update Subscription API----------");
		RestTemplate restTemplate = new RestTemplate();
		Subscription subscription = new Subscription(1, "Daily", "10", "Friday", "03/09//2021", "03/09//2021");
		restTemplate.put(REST_SERVICE_URI + "/SubscriptionUpdate/1", subscription);
		System.out.println(subscription);
	}

	/* DELETE */
	private static void deleteSubscription() {
		System.out.println("Testing delete Subscription API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/SubscriptionDeleteById/3");
	}

	/* DELETE */
	private static void deleteAllSubscriptions() {
		System.out.println("Testing all delete Subscriptions API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/SubscriptionDeleteAll/");
	}

	/* GET */
	@SuppressWarnings("unchecked")
	private static void listAllSubscriptionsBySubscriptionType() {
		System.out.println("Testing listAllSubscriptionsByLevel API-----------");

		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> SubscriptionsMap = restTemplate
				.getForObject(REST_SERVICE_URI + "/Subscription/", List.class);

		if (SubscriptionsMap != null) {
			for (LinkedHashMap<String, Object> map : SubscriptionsMap) {
				System.out.println("Subscription : id=" + map.get("id") + ", Day=" + map.get("day") + ", StartDate="
						+ map.get("startDate") + ", EndDate=" + map.get("endDate") + ", Amount=" + map.get("amount")
						+ ", SubscriptionType=" + map.get("subscriptionType"));
			}
		} else {
			System.out.println("No Subscription exist----------");
		}
	}

	private static void dateCountInvoice(Subscription subscription) {

		String startDate = subscription.getStartDate();
		String endDate = subscription.getEndDate();
		String subcriptionType = subscription.getSubscriptionType();
		String amount = subscription.getAmount();

		int amountInt = 0;
		int totalAmount = 0;
		int initDay = 0;
		int endDay = 2;
		int initMonth = 3;
		int endMonth = 5;
		int initYear = 6;
		int count = 0;
		int startDayInt = 0;
		int endDayInt = 0;
		int startMonthInt = 0;
		int endMonthInt = 0;
		int yearInt = 0;
		int flag = 0;
		int reminder = 0;

		amountInt = Integer.parseInt(amount);
		startDayInt = Integer.parseInt(startDate.substring(initDay, endDay));
		endDayInt = Integer.parseInt(endDate.substring(initDay, endDay));
		startMonthInt = Integer.parseInt(startDate.substring(initMonth, endMonth));
		endMonthInt = Integer.parseInt(endDate.substring(initMonth, endMonth));
		yearInt = Integer.parseInt(startDate.substring(6));

		if ("Monthly".equalsIgnoreCase(subcriptionType)) {
			count = endMonthInt - startMonthInt;
			totalAmount = amountInt * count;
			System.out.print("Subscription Output : " + "Amount=" + totalAmount + ", Subscription_Type="
					+ subscription.getSubscriptionType() + ", Invoice Date=[" + subscription.getStartDate());
			for (int i = 1; i < count; i++) {
				System.out.print("," + startDayInt + "/" + (startMonthInt + i) + "/" + yearInt);

			}
			System.out.println("]");
		} else if ("Weekly".equalsIgnoreCase(subcriptionType)) {
			flag = endDayInt - startDayInt;
			reminder = flag % 7;
			if (reminder == 0) {
				count = flag / 7;
			}
			totalAmount = amountInt * count;
			String changeDayString = "";
			System.out.print("Subscription Output : " + "Amount=" + totalAmount + ", Subscription_Type="
					+ subscription.getSubscriptionType() + ", Invoice Date=[");
			int changeDay = startDayInt;
			for (int i = 1; i <= count; i++) {
				if (changeDay < 10) {
					changeDayString = "0" + String.valueOf(changeDay);
				} else {
					changeDayString = String.valueOf(changeDay);
				}
				if (i <= 1)
					System.out.print(changeDayString + "/" + startMonthInt + "/" + yearInt);
				else
					System.out.print("," + changeDayString + "/" + startMonthInt + "/" + yearInt);
				changeDay = changeDay + 7;
			}
			System.out.println("]");
		} else {
			count = 0;
			totalAmount = amountInt;
			System.out.println("Subscription Output : " + "Amount=" + totalAmount + ", Subscription_Type="
					+ subscription.getSubscriptionType() + ", Invoice Date=[" + subscription.getStartDate() + "]");
		}
	}

	public static void main(String args[]) {
		// listAllSubscriptions();
		getSubscription();
		// createSubscription();
		// listAllSubscriptions();
		// updateSubscription();
		// listAllSubscriptions();
		// deleteSubscription();
		// listAllSubscriptions();
		// deleteAllSubscriptions();
		// listAllSubscriptions();

		// listAllSubscriptionsBySubscriptionType();
	}
}