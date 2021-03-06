package com.promeets.model.service.entity;

import com.promeets.model.entity.Chat;
import com.promeets.model.entity.User;
import com.promeets.model.entity.UserMessage;
import com.promeets.model.entity.UserMessagePK;

import java.util.List;

/**
 * Created by Vladimir on 12.04.2016.
 */
public interface UserMessageService extends BaseService<UserMessage, UserMessagePK> {

    UserMessage getUserMessageByUserIdAndMessageId(long userId, long messageId);

    List<UserMessage> getUserMessagesByUserIdAndChatId(long userId, long chatId, int page);

    List<UserMessage> getNewUserMessagesByUserId(long userId);

    List<UserMessage> getNewUserMessagesByUserIdAndChatId(long userId, long chatId);

    void deleteUserMessagesByChatId(long chatId);

    UserMessage getLastUserMessageByUserIdAndChatId(long userId, long chatId);

    void createUserMessagesByUserAndChat(User user, Chat chat);
}
