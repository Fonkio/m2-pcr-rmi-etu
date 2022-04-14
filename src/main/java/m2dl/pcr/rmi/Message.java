package m2dl.pcr.rmi;

import java.io.Serializable;

public class Message implements Serializable {
    private String expediteur;
    private String message;
    private Long timeMillis;

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(Long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public Message(String expediteur, String message) {
        this.expediteur = expediteur;
        this.message = message;
        this.timeMillis = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Message{" +
                "expediteur='" + expediteur + '\'' +
                ", message='" + message + '\'' +
                ", timeMillis=" + timeMillis +
                '}';
    }
}
