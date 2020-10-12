package com.c0320g1.vaccine.config;

import com.c0320g1.vaccine.model.InjectionHistory;
import com.c0320g1.vaccine.service.EmailService;
import com.c0320g1.vaccine.service.InjectionHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private EmailService emailService;

    @Autowired
    private InjectionHistoryService injectionHistoryService;

    @Scheduled(cron = "0 30 8 ? * *")
    public void remind() {
        List<InjectionHistory> historyList = this.injectionHistoryService.findAll();
        int todayInYear = LocalDateTime.now().getDayOfYear();
        historyList.forEach(injectionHistory -> {
            LocalDateTime injectionDate = injectionHistory.getInjectionDate();
            if (injectionDate.getDayOfYear() - todayInYear == 1) {
                String email = injectionHistory.getAccount().getEmail();
                String text = "Bạn có lịch tiêm chủng vào ngày " + injectionDate.getDayOfMonth() + " tháng " +
                        injectionDate.getMonthValue() + " tại trung tâm tiêm chủng vắc xin C0320G1-Center - Địa chỉ : " +
                        "295 Nguyễn Tất Thành, Đà nẵng. Khi đi vui lòng mang theo chứng minh thư, Xin cảm ơn!";
                try {
                    this.emailService.sendSimpleHTMLMessage(email,"Thư nhắc tiêm chủng",text);
                    logger.info("Đã gửi mail báo");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
