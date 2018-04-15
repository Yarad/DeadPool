package com.events;

import com.DAO.interfaces.IDAODetective;
import com.logic.Detective;
import com.services.interfaces.IEMailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.logic.ProjectConstants.reportTitleDateTimeFormatter;

@Component
public class ScheduledEvents {
    static Logger log = Logger.getLogger(ScheduledEvents.class.getName());

    @Autowired
    private IDAODetective daoDetective;

    @Autowired
    private IEMailService eMailService;

    @Scheduled(cron = "0 45 2 * * *")
    public void reportCurrentTime() {
        log.info("Метод reportCurrentTime был вызван в " + LocalDateTime.now().format(reportTitleDateTimeFormatter));
        List<Detective> allDetectives = daoDetective.getAllDetectives();
        List<Detective> detectives = allDetectives.stream()
                .filter(det -> det.getBirthDay() != null && LocalDate.now().getMonth() == det.getBirthDay().getMonth()
                        && LocalDate.now().getDayOfMonth() == det.getBirthDay().getDayOfMonth())
                .collect(Collectors.toList());
        for(Detective det : detectives) {
            eMailService.sendTextEMail(det.getEmail(), "Позравляем с днём рождения!",
                    "Уважаемый " + det.getName() + " " + det.getSurname() + " !\n" +
                    "Администрация приложения \"Deadpool\" от всей души поздравляет Вас с днём рождения!" +
                    "Желаем крепкого здоровья и реализации всех жизненых планов!\n" + "\n" +
                    "Поздравляю\n" +
                    "с днём рожденья,\n" +
                    "желаю счастья в личной жизни.\n" +
                    "Пух!");
        }
    }
}
