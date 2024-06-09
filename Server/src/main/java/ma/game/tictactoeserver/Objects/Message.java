package ma.game.tictactoeserver.Objects;

import ma.game.tictactoeserver.Interfaces.IMessages;

import java.io.Serializable;

public class Message implements Serializable, IMessages {
    private String sender;
    private String receiver;
    private String content;
    private String timeStamp;

    public Message(String sender, String receiver, String content, String timeStamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
