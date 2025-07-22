package ra.edu.ss10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss10.model.entity.Notification;
import ra.edu.ss10.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationServiceImp implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public List<Notification> getByAccount(Long id) {
        return notificationRepository.findByAccountId(id);
    }
}
