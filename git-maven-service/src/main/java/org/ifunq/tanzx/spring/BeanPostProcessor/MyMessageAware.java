package org.ifunq.tanzx.spring.BeanPostProcessor;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import java.util.Locale;

/**
 * 消息
 *
 * @author tanzx [tanzongxi@ifunq.com]
 * @date 2017-12-11 15:11
 **/
public class MyMessageAware implements MessageSourceAware {
    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void sayMessage(String messageName){
        String message = messageSource.getMessage(messageName, null, null);
        System.out.println(message);
    }
    public void sayMessage(String messageNamem, Object[] args, Locale locale){
        String message = messageSource.getMessage(messageNamem, args, locale);
        System.out.println(message);
    }
}
