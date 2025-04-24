package com.testing.mockito.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	public void notifyProductCreated(String productName) {
        System.out.println("Notification sent for product: " + productName);
	}
}
