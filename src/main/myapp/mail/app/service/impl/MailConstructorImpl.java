package mail.app.service.impl;

import mail.app.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import mail.app.service.MailConstructor;
import mail.app.service.XlsxReader;

@Service
@PropertySource("classpath:application.properties")
public class MailConstructorImpl implements MailConstructor {
    private static final String REPLACE_NAME_INDEX = "[Name]";
    private static final String REPLACE_DISTRICT_INDEX = "[District]";
    public TxtFileReaderImpl txtFileReader;
    public XlsxReader xlsxReader;

    @Autowired
    public MailConstructorImpl(TxtFileReaderImpl txtFileReader, XlsxReader xlsxReader) {
        this.txtFileReader = txtFileReader;
        this.xlsxReader = xlsxReader;
    }

    @Value("${mail.txtTitlePath}")
    private String txtTitlePath;

    @Value("${mail.txtBodyPath}")
    private String txtBodyPath;

    @Override
    public Mail getMail(int numberRow) {
        Mail mail = new Mail();
        mail.setBody(replaceName(txtFileReader.getString(txtBodyPath), xlsxReader.getName(numberRow)));
        mail.setBody(replaceDistrictNumber(mail.getBody(), xlsxReader.getDistrict(numberRow)));

        mail.setTitle(replaceDistrictNumber(txtFileReader.getString(txtTitlePath),
                xlsxReader.getDistrict(numberRow)));

        mail.setLetterRecipientMail(xlsxReader.getPrimaryMail(numberRow) + ", "
                + xlsxReader.getSecondaryMail(numberRow));
        mail.setLetterRecipientName(xlsxReader.getName(numberRow));
        return mail;
    }

    public String replaceName(String str, String name) {
        return str.replace(REPLACE_NAME_INDEX, name);
    }

    public String replaceDistrictNumber(String str, String district) {
        return str.replace(REPLACE_DISTRICT_INDEX, district);
    }
}
