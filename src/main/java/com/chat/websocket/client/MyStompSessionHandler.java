package com.chat.websocket.client;

import com.chat.websocket.Message;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

@AllArgsConstructor
public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    private String username;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("client connected");
        session.subscribe("/topic/messages", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Message.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                try {
                    if (payload instanceof Message) {
                        Message message = (Message) payload;
                        System.out.println("Message from user " + message.getUser() + " : " + message.getMessage());
                    } else {
                        System.out.println("Received message of unexpected type:  " + payload.getClass().getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        exception.printStackTrace();
    }

}
