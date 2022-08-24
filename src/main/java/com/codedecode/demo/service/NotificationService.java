package com.codedecode.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Notification;
import com.codedecode.demo.repository.NotificationRepository;

@Service
@Transactional
public class NotificationService {


	private final NotificationRepository notificationRepository;

	@Autowired
	public NotificationService(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	public Notification save(Notification notification) {
		return notificationRepository.save(notification);
	}
	
	public void deleteAllNotification() {
		 notificationRepository.deleteAll();
	}
	
	public void deleteNotification(Long nofiticationId) {
		notificationRepository.deleteById(nofiticationId);
	}
}
