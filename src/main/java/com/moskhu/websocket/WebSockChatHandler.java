package com.moskhu.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

@Slf4j
@Component
public class WebSockChatHandler extends TextWebSocketHandler {

    Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
    Map<Integer, WebSocketSession> orderMap = new HashMap<>();
    WebSocketSession seller;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        //log.info("payload {}", payload);

        String[] str = payload.split(" ");
        if(str[0].equals("주문")) {
            orderMap.put(Integer.parseInt(str[1]), session);
            seller.sendMessage(message);
        }
        else if(str[0].equals("판매자_등록"))
            seller = session;
        else if(str[0].equals("판매자_주문취소")) {
            orderMap.get(Integer.parseInt(str[1])).sendMessage(message);
        }
        else if(str[0].equals("판매자_조리완료"))
            orderMap.get(Integer.parseInt(str[1])).sendMessage(message);
        else{}
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
        sessions.remove(session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception{
        sessions.remove(session);
    }
}