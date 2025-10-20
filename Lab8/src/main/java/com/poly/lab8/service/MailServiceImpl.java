package com.poly.lab8.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.*;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

    List<Mail> queue = new ArrayList<>();

    private boolean isNullOrEmpty(String text) {
        return (text == null || text.trim().length() == 0);
    }

    @Override
    public void send(Mail mail) {
        try {
            // Validate required fields
            if (isNullOrEmpty(mail.getTo())) {
                throw new IllegalArgumentException("To address must not be null or empty");
            }
            if (isNullOrEmpty(mail.getSubject())) {
                throw new IllegalArgumentException("Subject must not be null or empty");
            }
            if (isNullOrEmpty(mail.getBody())) {
                throw new IllegalArgumentException("Body must not be null or empty");
            }

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            // 2.1 Ghi thông tin người gửi
            helper.setFrom(mail.getFrom());
            helper.setReplyTo(mail.getFrom());

            // 2.2 Ghi thông tin người nhận
            helper.setTo(mail.getTo());
            if (!isNullOrEmpty(mail.getCc())) helper.setCc(mail.getCc());
            if (!isNullOrEmpty(mail.getBcc())) helper.setBcc(mail.getBcc());

            // 2.3 Tiêu đề & nội dung
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(), true);

            // 2.4 Đính kèm file
            String filenames = mail.getFilenames();
            if (!isNullOrEmpty(filenames)) {
                for (String filename : filenames.split("[,;]+")) {
                    File file = new File(filename.trim());
                    helper.addAttachment(file.getName(), file);
                }
            }

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void push(Mail mail) {
        queue.add(mail);
    }

    @Scheduled(fixedDelay = 500)
    public void processQueue() {
        while (!queue.isEmpty()) {
            Mail mail = queue.remove(0);
            try {
                this.send(mail);
            } catch (Exception e) {
                System.err.println("Failed to send email to: " + mail.getTo());
                e.printStackTrace();
            }
        }
    }

}
