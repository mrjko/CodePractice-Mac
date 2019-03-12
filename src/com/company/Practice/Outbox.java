package com.company.Practice;

import java.util.*;

public class Outbox {
    List<Message> messages;
    String user;

    public Outbox(String id) {
        this.user = id;
        this.messages = new ArrayList<Message>();
    }

}
