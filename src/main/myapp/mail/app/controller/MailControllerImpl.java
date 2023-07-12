package mail.app.controller;

import mail.app.model.Mail;
import mail.app.service.MailConstructor;
import mail.app.service.MailService;
import mail.app.service.XlsxReader;
import mail.app.storage.DataStorage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "mail.app")
public class MailControllerImpl implements MailController {
    private static final String START_STRING = "start";
    private static final int FIRST_START_INDEX = 0;
    private static final int TIME_MULTIPLIER = 10000;
    private final XlsxReader xlsxReader;
    private final MailConstructor mailConstructor;
    private final MailService mailService;

    public MailControllerImpl(XlsxReader xlsxReader,
                              MailConstructor mailConstructor,
                              MailService mailService) {
        this.xlsxReader = xlsxReader;
        this.mailConstructor = mailConstructor;
        this.mailService = mailService;
    }


    @Override
    public void starCommand() {
        String userCommand = DataStorage.getCommand();
        int numberRow = FIRST_START_INDEX;
        int tableSize = xlsxReader.getSizeofTable();
        if (userCommand.equals(START_STRING)) {
            while (numberRow <= tableSize) {
                sendMail(numberRow);
                numberRow++;
            }
        }
        if (userCommand.contains("@")) {
            numberRow = xlsxReader.getNumberLineByMail(userCommand) - 1;
            while (numberRow <= tableSize) {
                sendMail(numberRow);
                numberRow++;
            }
        } else {
            System.out.print("Wrong input command or mail: " + "'" + userCommand + "'"
                    + ", or this mail not found. Please restart app.");
        }
    }
    private Mail getFormLatter(int numberRow){
        return mailConstructor.getMail(numberRow);
    }

    private void sendMail(int numberRow) {
        Mail mail = getFormLatter(numberRow);
        mailService.updateMailSenderCredentials();
        mailService.sendEmail(mail);
        System.out.print("Sent mail to: " + mail.getLetterRecipientMail() + "\n");
        try {
            Thread.sleep( 10 * TIME_MULTIPLIER);
        } catch (InterruptedException e) {
            throw new RuntimeException("Time out error");
        }
    }
}
