package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Subscription;

public interface SubscriptionService {
	
	Subscription findById(long id);

	Subscription findByName(String name);

	void saveSubscription(Subscription Subscription);

	void updateSubscription(Subscription  Subscription);

	void deleteSubscriptionById(long id);

	List<Subscription> findAllSubscriptions();
	
	List<Subscription> findAllSubscriptionsByLevel(String level);

	void deleteAllSubscriptions();

	public boolean isSubscriptionExist(Subscription Subscription);
}
