package top.adxd.tikonlinejudge.message.api;

import java.io.Serializable;

/*
 * @author wait-light
 * @date 2021/10/28.
 */
public class Email implements Serializable {
    public static final String QUEUE = "email";
    public String to;
    public String subject;
    public String content;

    public Email(String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
