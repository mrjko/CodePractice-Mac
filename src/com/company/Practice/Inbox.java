package com.company.Practice;

import java.util.*;

public class Inbox {
    List<Message> messages;
    String user;

    public Inbox(String id) {
        this.user = id;
        this.messages = new ArrayList<Message>();
    }

}
