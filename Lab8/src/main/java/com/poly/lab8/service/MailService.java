package com.poly.lab8.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

public interface MailService {

    void send(Mail mail);

    default void send(String to, String subject, String body) {
        Mail mail = Mail.builder ()
                .to (to)
                .subject (subject)
                .body (body)
                .build ();
        this.send (mail);
    }

    void push(Mail mail);

    default void push(String to, String subject, String body) {
        this.push (Mail.builder ()
                .to (to)
                .subject (subject)
                .body (body)
                .build ());
    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    class Mail {
        @Builder.Default
        private String from = "WebShop <web-shop@gmail.com>";
        private String to, cc, bcc, subject, body, filenames;
    }
}
