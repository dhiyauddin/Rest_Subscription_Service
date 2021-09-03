package com.websystique.springmvc.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springmvc.model.Subscription;
import com.websystique.springmvc.model.SubscriptionOutput;
import com.websystique.springmvc.service.SubscriptionService;

@RestController
public class SubscriptionRestController {

	@Autowired
	SubscriptionService SubscriptionService; // Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All
	// Subscriptions--------------------------------------------------------

	@RequestMapping(value = "/Subscription/", method = RequestMethod.GET)
	public ResponseEntity<List<Subscription>> listAllSubscriptions() {
		List<Subscription> subscriptions = SubscriptionService.findAllSubscriptions();
		if (subscriptions.isEmpty()) {
			return new ResponseEntity<List<Subscription>>(HttpStatus.NO_CONTENT);// You many decide to return
																					// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Subscription>>(subscriptions, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Subscription--------------------------------------------------------

	@RequestMapping(value = "/SubscriptionById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Subscription> getSubscription(@PathVariable("id") long id) {
		System.out.println("Fetching Subscription with id " + id);
		Subscription subscription = SubscriptionService.findById(id);
		if (subscription == null) {
			System.out.println("Subscription with id " + id + " not found");
			return new ResponseEntity<Subscription>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);
	}

	// -------------------Retrieve Single Output
	// Subscription--------------------------------------------------------

	@RequestMapping(value = "/SubscriptionOutputById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SubscriptionOutput> getSubscriptionOutputById(@PathVariable("id") long id) {
		System.out.println("Fetching Subscription with id " + id);
		Subscription subscription = SubscriptionService.findById(id);
		if (subscription == null) {
			System.out.println("Subscription with id " + id + " not found");
			return new ResponseEntity<SubscriptionOutput>(HttpStatus.NOT_FOUND);
		}
		SubscriptionOutput subscriptionOutput = getOutputById(subscription);

		return new ResponseEntity<SubscriptionOutput>(subscriptionOutput, HttpStatus.OK);
	}

	// -------------------Create a
	// Subscription--------------------------------------------------------

	@RequestMapping(value = "/SubscriptionCreate/", method = RequestMethod.POST)
	public ResponseEntity<Void> createSubscription(@RequestBody Subscription subscription,
			UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Subscription " + subscription.getSubscriptionType());

		if (SubscriptionService.isSubscriptionExist(subscription)) {
			System.out.println("A Subscription with name " + subscription.getAmount() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		SubscriptionService.saveSubscription(subscription);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/Subscription/{id}").buildAndExpand(subscription.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Subscription
	// --------------------------------------------------------

	@RequestMapping(value = "/SubscriptionUpdate/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Subscription> updateSubscription(@PathVariable("id") long id,
			@RequestBody Subscription subscription) {
		System.out.println("Updating Subscription " + id);

		Subscription currentSubscription = SubscriptionService.findById(id);

		if (currentSubscription == null) {
			System.out.println("Subscription with id " + id + " not found");
			return new ResponseEntity<Subscription>(HttpStatus.NOT_FOUND);
		}

		currentSubscription.setSubscriptionType(subscription.getSubscriptionType());
		currentSubscription.setAmount(subscription.getAmount());

		SubscriptionService.updateSubscription(currentSubscription);
		return new ResponseEntity<Subscription>(currentSubscription, HttpStatus.OK);
	}

	// ------------------- Delete a Subscription
	// --------------------------------------------------------

	@RequestMapping(value = "/SubscriptionDeleteById/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Subscription> deleteSubscription(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Subscription with id " + id);

		Subscription subscription = SubscriptionService.findById(id);
		if (subscription == null) {
			System.out.println("Unable to delete. Subscription with id " + id + " not found");
			return new ResponseEntity<Subscription>(HttpStatus.NOT_FOUND);
		}

		SubscriptionService.deleteSubscriptionById(id);
		return new ResponseEntity<Subscription>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Subscription
	// --------------------------------------------------------

	@RequestMapping(value = "/SubscriptionDeleteAll/", method = RequestMethod.DELETE)
	public ResponseEntity<Subscription> deleteAllSubscriptions() {
		System.out.println("Deleting All Subscriptions");

		SubscriptionService.deleteAllSubscriptions();
		return new ResponseEntity<Subscription>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/SubscriptionByLevel/{level}", method = RequestMethod.GET)
	public ResponseEntity<List<Subscription>> listAllSubscriptionsByLevel(@PathVariable("level") String level) {
		System.out.println("Fetching Subscription with level " + level);
		List<Subscription> subscriptions = SubscriptionService.findAllSubscriptionsByLevel(level);
		if (subscriptions.isEmpty()) {
			return new ResponseEntity<List<Subscription>>(HttpStatus.NO_CONTENT);// You many decide to return //
																					// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Subscription>>(subscriptions, HttpStatus.OK);
	}

	public SubscriptionOutput getOutputById(Subscription subscription) {

		String startDate = subscription.getStartDate();
		String endDate = subscription.getEndDate();
		String subcriptionType = subscription.getSubscriptionType();
		String amount = subscription.getAmount();
		String invoiceDate = "";

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
		yearInt = Integer.parseInt(startDate.substring(initYear));

		if ("Monthly".equalsIgnoreCase(subcriptionType)) {
			count = endMonthInt - startMonthInt;
			totalAmount = amountInt * count;
			System.out.print("Subscription Output : " + "Amount=" + totalAmount + ", Subscription_Type="
					+ subscription.getSubscriptionType() + ", Invoice Date=[" + subscription.getStartDate());
			String invoiceDateS = "";
			invoiceDate = subscription.getStartDate();
			for (int i = 1; i < count; i++) {
				System.out.print("," + startDayInt + "/" + (startMonthInt + i) + "/" + yearInt);
				invoiceDateS = "," + startDayInt + "/" + (startMonthInt + i) + "/" + yearInt;
				invoiceDate = invoiceDate + invoiceDateS;
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
			String invoiceDateS = "";
			for (int i = 1; i <= count; i++) {
				if (changeDay < 10) {
					changeDayString = "0" + String.valueOf(changeDay);
				} else {
					changeDayString = String.valueOf(changeDay);
				}
				if (i <= 1) {
					System.out.print(changeDayString + "/" + startMonthInt + "/" + yearInt);
					invoiceDateS = changeDayString + "/" + startMonthInt + "/" + yearInt;
				} else {
					System.out.print("," + changeDayString + "/" + startMonthInt + "/" + yearInt);
					invoiceDateS = "," + changeDayString + "/" + startMonthInt + "/" + yearInt;
				}
				changeDay = changeDay + 7;
				invoiceDate = invoiceDate + invoiceDateS;
			}
			System.out.println("]");
		} else {
			count = 0;
			totalAmount = amountInt;
			System.out.println("Subscription Output : " + "Amount=" + totalAmount + ", Subscription_Type="
					+ subscription.getSubscriptionType() + ", Invoice Date=[" + subscription.getStartDate() + "]");
			invoiceDate = subscription.getStartDate();
		}

		SubscriptionOutput subscriptionOuput = new SubscriptionOutput();
		subscriptionOuput.setId(subscription.getId());
		subscriptionOuput.setAmount(String.valueOf(totalAmount));
		subscriptionOuput.setSubscriptionType(subscription.getSubscriptionType());
		subscriptionOuput.setInvoiceDate(invoiceDate);

		return subscriptionOuput;

	}

}
