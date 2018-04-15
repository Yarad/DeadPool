package com.services;

import com.DAO.interfaces.IDAODetective;
import com.logic.Detective;
import com.logic.Man;
import com.services.interfaces.IEMailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.logic.ProjectConstants.reportTitleDateTimeFormatter;
import static java.lang.Thread.sleep;

@Service
public class EMailService implements IEMailService {
    static Logger log = Logger.getLogger(EMailService.class.getName());

    private static String textMailFooter = "\n" + "-----------\n" + "\n" +
            "Это письмо автоматически сгенерировано системой \"Deadpool\".\n" +
            "Пожалуйста, не отвечайте на него!\n" + "\n" +
            "Если у вас есть какие-то вопросы - пожалуйста, напишите нам удобным для вас способов, указанным в разделе \"Контакты\" нашего приложения!";

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public IDAODetective daoDetective;

    @Async
    @Override
    public void sendTextEMail(String addressTo, String subject, String body) {
        log.info("Метод sendTextEMail был вызван в " + LocalDateTime.now().format(reportTitleDateTimeFormatter));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(addressTo);
        message.setSubject(subject);
        /*try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        message.setText(body);
        emailSender.send(message);
        log.info("Text email message was sent to " + addressTo);
    }

    @Override
    public void sendTextEMailWithFooter(String addressTo, String subject, String body) {
        log.info("Метод sendTextEMailWithFooter был вызван в " + LocalDateTime.now().format(reportTitleDateTimeFormatter));
        sendTextEMail(addressTo, subject, body + textMailFooter);
    }

    @Async
    @Override
    public void sendTextEMailToAllDetectivesExceptId(long detectiveId, String subject, String body) {
        log.info("Метод sendTextEMailToAllDetectivesExceptId был вызван в " + LocalDateTime.now().format(reportTitleDateTimeFormatter));
        List<Detective> allDetectives = daoDetective.getAllDetectives();
        List<String> detectives = allDetectives.stream()
                .filter(det -> det.getManId() != detectiveId)
                .map(det -> det.getEmail())
                .collect(Collectors.toList());
        for(String mail : detectives) {
            sendTextEMailWithFooter(mail, subject, body);
        }
    }
}
