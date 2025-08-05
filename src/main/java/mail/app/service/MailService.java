package mail.app.service;

import mail.app.model.Mail;

public interface MailService {

    void updateMailSenderCredentials();
    void sendEmail(Mail mail);
}
