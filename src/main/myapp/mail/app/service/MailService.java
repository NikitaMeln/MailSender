package mail.app.service;

import mail.app.model.Mail;

public interface MailService {

    public void updateMailSenderCredentials();
    public void sendEmail(Mail mail);
}
