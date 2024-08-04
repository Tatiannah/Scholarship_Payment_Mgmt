package com.xadmin.paye.bean;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailUtil {

    public static void sendEmail(String recipient, String subject, String body) throws MessagingException {
        // Configuration des propriétés SMTP pour Gmail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Authentification
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tatyral1704@gmail.com", "ytkffzymwhijqfix"); // Mot de passe d'application
            }
        };

        // Création de la session
        Session session = Session.getInstance(props, auth);

        try {
            // Création du message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tatyral1704@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setContent(body, "text/html");

            // Envoi du message
            Transport.send(message);
            System.out.println("Email envoyé avec succès!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'envoi de l'email: " + e.getMessage());
            throw e; // Lancer une exception pour pouvoir gérer l'erreur ailleurs
        }
    }
}
