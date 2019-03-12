package com.company.Practice;

import java.util.*;

public class EmailClient {
    List<Contact> contacts;
    Inbox inbox;
    String id;
    Outbox outbox;

    public EmailClient(String id) {
        this.id = id;
        contacts = new ArrayList<Contact>();
        this.inbox = new Inbox(id);
        this.outbox = new Outbox(id);
    }

    public void send(Contact c, String msg) {
        if (contacts.contains(c)) {

        }
    }

    public void recv(Contact c) {

    }

    public Inbox getInbox() {
        return this.inbox;
    }

    public Outbox getOutbox() {
        return this.outbox;
    }

}
