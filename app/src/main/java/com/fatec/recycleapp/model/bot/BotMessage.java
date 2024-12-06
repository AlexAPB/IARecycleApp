package com.fatec.recycleapp.model.bot;

public class BotMessage {
    private Object msg;
    private BotMessageType type;
    private BotConnection connection;

    public BotMessage() {

    }

    public BotMessage(Object msg, BotMessageType type, BotConnection connection) {
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

    public BotMessageType getType() {
        return type;
    }

    public void setType(BotMessageType type) {
        this.type = type;
    }

    public void setConnection(BotConnection connection) {
        this.connection = connection;
    }

    public BotConnection getConnection() {
        return connection;
    }
}
