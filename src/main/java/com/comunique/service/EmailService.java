package com.comunique.service;

import java.util.Properties;

import jakarta.mail.Address;
import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.SendFailedException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
import com.comunique.config.SystemConfigs;
import com.comunique.emailsPages.CadastroUsuario;
import com.comunique.model.Email;

public interface EmailService {
	public static String sendEmail(Email email) {
		if (SystemConfigs.sendEmail) {
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
					new jakarta.mail.Authenticator() {
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
			} catch (MessagingException e) {
				return "Ocorreu um erro ao enviar o e-mail: " + e.getMessage();
			}
		} else {
			return "Email desativado";
		}
	}

	public static boolean isValidEmail(String RemententeEmail, String id) {

		try {
			CadastroUsuario cadastroUsuario = new CadastroUsuario(id);
			String subject = "Verificação de e-mail";
			String body = cadastroUsuario.body;
			String username = SystemConfigs.Email;
			String password = SystemConfigs.senhaEmail;

			Email emailObj = new Email(RemententeEmail, subject, body, username, password);

			String emailResponse = sendEmail(emailObj);

			if (emailResponse.equals("E-mail enviado com sucesso")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
