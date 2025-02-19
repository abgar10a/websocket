package com.chat.websocket.client;

import com.chat.websocket.Message;

import java.util.concurrent.ExecutionException;

public class ClientGUI {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyStompClient myStompClient = new MyStompClient("Abgar");
        myStompClient.sendMessage(new Message("Abgar", "Hello brats"));
    }
}
