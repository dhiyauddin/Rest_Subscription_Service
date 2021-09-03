package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.websystique.springmvc.model.Subscription;

@Service("SubscriptionService")
@Transactional
public class SubscriptionImpl implements SubscriptionService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<Subscription> Subscriptions;

	static {
		Subscriptions = populateDummySubscriptions();
	}

	public List<Subscription> findAllSubscriptions() {
		return Subscriptions;
	}

	public Subscription findById(long id) {
		for (Subscription user : Subscriptions) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public Subscription findByName(String name) {
		for (Subscription user : Subscriptions) {
			if (user.getSubscriptionType().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}

	public void saveSubscription(Subscription user) {
		user.setId(counter.incrementAndGet());
		Subscriptions.add(user);
	}

	public void updateSubscription(Subscription user) {
		int index = Subscriptions.indexOf(user);
		Subscriptions.set(index, user);
	}

	public void deleteSubscriptionById(long id) {

		for (Iterator<Subscription> iterator = Subscriptions.iterator(); iterator.hasNext();) {
			Subscription user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
	}

	public boolean isSubscriptionExist(Subscription user) {
		return findByName(user.getSubscriptionType()) != null;
	}

	private static List<Subscription> populateDummySubscriptions() {
		List<Subscription> Subscriptions = new ArrayList<Subscription>();

		// Daily
		Subscriptions.add(new Subscription(counter.incrementAndGet(), "Daily", "10", "Monday", "03/09/2021", "03/09/2021"));

		// Weekly
		Subscriptions.add(new Subscription(counter.incrementAndGet(), "Weekly", "10", "Friday", "03/09/2021", "24/09/2021"));

		// Monthly
		Subscriptions.add(new Subscription(counter.incrementAndGet(), "Monthly", "10", "18", "18/09/2021", "17/12/2021"));
		return Subscriptions;
	}

	public void deleteAllSubscriptions() {
		Subscriptions.clear();
	}

	@Override
	public List<Subscription> findAllSubscriptionsByLevel(String level) {
		List<Subscription> tList = new ArrayList<Subscription>();

		for (Subscription Subscription : Subscriptions) {
			
			if (Subscription.getAmount().equalsIgnoreCase(level)) {
				tList.add(Subscription);
			}
		}
		return tList;
	}

}
