package com.nowcoder.service;

import com.nowcoder.dao.MessageDao;
import com.nowcoder.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;

    public int addMessage(Message message){
        return messageDao.addMessage(message);
    }

    public List<Message> getConversationList(int userId,int offset,int limit){
        return messageDao.getConversationList(userId,offset,limit);
    }

    public List<Message> getConversationDetail(String conversationId,int offset,int limit){
        return messageDao.getConversationDetail(conversationId,offset,limit);
    }

    public int getUnreadCount(int userId,String conversationId){
        return messageDao.getConversationUnReadCount(userId, conversationId);
    }
}
