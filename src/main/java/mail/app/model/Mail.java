package mail.app.model;

import java.util.Objects;

public class Mail {
    private String letterRecipientMail;
    private String letterRecipientName;
    private String title;
    private String body;

    public Mail() {
    }

    public Mail(String letterRecipientMail, String title, String body) {
        this.letterRecipientMail = letterRecipientMail;
        this.title = title;
        this.body = body;
    }

    public String getLetterRecipientMail() {
        return letterRecipientMail;
    }

    public void setLetterRecipientMail(String letterRecipientMail) {
        this.letterRecipientMail = letterRecipientMail;
    }

    public String getLetterRecipientName() {
        return letterRecipientName;
    }

    public void setLetterRecipientName(String letterRecipientName) {
        this.letterRecipientName = letterRecipientName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return letterRecipientMail.equals(mail.letterRecipientMail)
                && letterRecipientName.equals(mail.letterRecipientName)
                && title.equals(mail.title)
                && body.equals(mail.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(letterRecipientMail, letterRecipientName, title, body);
    }

    @Override
    public String toString() {
        return "Mail [letterRecipientMail = " + letterRecipientMail + "\n" + ", letterRecipientName = "
                + letterRecipientName + "\n" + ", title = " + title + "\n" + ", body = " + body + "]";
    }

}
