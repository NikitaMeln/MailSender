package mail.app.config;

import java.util.Properties;
import mail.app.service.MailService;
import mail.app.service.impl.MailServiceImpl;
import mail.app.storage.DataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "mail.app")
public class AppConfig {
    private final Environment environment;

    @Autowired
    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public void getDataStorage() {
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        mailSender.setUsername(DataStorage.getMail());
        mailSender.setPassword(DataStorage.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("spring.mail.properties.mail.smtp.starttls.enable", true);
        return mailSender;
    }

    @Primary
    @Bean
    public MailService getMailService() {
        return new MailServiceImpl(getJavaMailSender());
    }
}
