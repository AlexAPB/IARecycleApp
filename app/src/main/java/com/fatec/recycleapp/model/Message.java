package com.fatec.recycleapp.model;

public class Message {
    private Object msg;
    private MessageType type;
    private MessageConnection connection;

    public Message() {

    }

    public Message(Object msg, MessageType type, MessageConnection connection) {
        this.msg = msg;
        this.type = type;
        this.connection = connection;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public void setConnection(MessageConnection connection) {
        this.connection = connection;
    }

    public MessageConnection getConnection() {
        return connection;
    }
}
