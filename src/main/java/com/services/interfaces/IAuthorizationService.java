package com.services.interfaces;

import com.DTO.TokenVerifyResult;
import com.logic.Detective;

public interface IAuthorizationService {
    /**
     * @param user Пользователь, для которого надо получить токен
     * @param hoursExpire Количество часов, которое действителен токен. Если равно нулю или отрицательному значению, не используется
     * @return Сгенерированный токен, если не было ошибок. Null, если были ошибки (будут добавлены в лог)
     */
    String getToken(Detective user, long hoursExpire);

    /**
     * @param token Токен, который надо проверить
     * @return Объект, содержащий ответ на вопрос, корректен и действителен ли токен, и, в случае корректности, имя пользователя
     */
    TokenVerifyResult checkToken(String token);
}
