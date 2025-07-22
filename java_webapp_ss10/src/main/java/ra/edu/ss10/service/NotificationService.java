package ra.edu.ss10.service;

import ra.edu.ss10.model.entity.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getByAccount(Long id);
}
