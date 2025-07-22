package ra.edu.ss10.utils;

import ra.edu.ss10.model.entity.Account;
import ra.edu.ss10.model.entity.Notification;

import java.time.LocalDateTime;

public class NotificationUtil {
    public static Notification buildNotification(Account account, String content) {
        Notification noti = new Notification();
        noti.setAccount(account);
        noti.setContent(content);
        noti.setStatus("chưa đọc");
        noti.setCreatedAt(LocalDateTime.now());
        return noti;
    }
}
