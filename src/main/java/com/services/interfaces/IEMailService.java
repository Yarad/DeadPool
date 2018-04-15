package com.services.interfaces;

import org.springframework.scheduling.annotation.Async;

public interface IEMailService {
    @Async
    void sendTextEMail(String addressTo, String subject, String body);

    @Async
    void sendTextEMailWithFooter(String addressTo, String subject, String body);

    @Async
    void sendTextEMailToAllDetectivesExceptId(long detectiveId, String subject, String body);
}
