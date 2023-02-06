package com.comunique.comunique.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.comunique.comunique.model.Email;

@Service
public class EmailService {
	
	 public String sendEmail(Email email) {
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(props,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(email.getUsername(), email.getPassword());
	                    }
	                });

	        try {
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(email.getUsername()));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
	            message.setSubject(email.getSubject());
	            message.setContent(email.getBody(), "text/html");

	            Transport.send(message);

	            return "E-mail enviado com sucesso";
	        } catch (AddressException e) {
	           return "Endereço de e-mail inválido: " + email.getTo();
	        } catch (MessagingException e) {
	           return "Ocorreu um erro ao enviar o e-mail: " + e.getMessage();
	        }
	
	    }
}
