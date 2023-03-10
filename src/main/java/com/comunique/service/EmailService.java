package com.comunique.service;

import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.comunique.model.Email;

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
			} catch (AuthenticationFailedException e) {
				return "Falha na autenticação: " + e.getMessage();
			} catch (SendFailedException e) {
				return "Erro no envio: " + e.getMessage();
			}  catch (MessagingException e) {
				return "Ocorreu um erro ao enviar o e-mail: " + e.getMessage();
			}
	
	    }
}
