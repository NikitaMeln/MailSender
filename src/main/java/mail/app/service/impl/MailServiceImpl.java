package mail.app.service.impl;

import mail.app.model.Mail;
import mail.app.storage.DataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import mail.app.service.MailService;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void updateMailSenderCredentials() {
        String userEmail = DataStorage.getMail();
        String userPassword = DataStorage.getPassword();
        JavaMailSenderImpl mailSenderImpl = (JavaMailSenderImpl) javaMailSender;
        mailSenderImpl.setUsername(userEmail);
        mailSenderImpl.setPassword(userPassword);
    }

    @Override
    public void sendEmail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getLetterRecipientMail());
        message.setSubject(mail.getTitle());
        message.setText(mail.getBody());
        javaMailSender.send(message);
    }
}
