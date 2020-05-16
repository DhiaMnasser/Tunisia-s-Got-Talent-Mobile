///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.Entities.Achat;
//
//
//import java.util.Properties;
//
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.event.MailEvent;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
///**
// *
// * @author 
// */
//public class SendEmailTLS {
//    public static void sendCommandeConfrimationMail(String recepient,String link)throws Exception{
//        System.out.println("preparing to send email");
//        Properties properties= new Properties() ;
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.googlemail.com");
//        properties.put("mail.smtp.port", "587");
//         
//     String myAccountEmail = "esprit.bitdev@gmail.com";
//     String password="espritbitdev6";
//     Session session =  Session.getInstance(properties, new Authenticator(){
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(myAccountEmail, password);
//            }
//         
//     });
//     Message message = prepareMessage(session, myAccountEmail, recepient, link);
//        Transport.send(message);
//        System.out.println("message sent successfully");
//    }
//
//    private static Message prepareMessage(Session session,String myAccountEmail,String recepient,String code) {
//        Message message= new MimeMessage(session);
//        try {
//            message.setFrom(new InternetAddress(myAccountEmail));
//            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
//            message.setSubject("your account confirmation code");
//            message.setText(code);
//            return message;
//        } catch (Exception ex) {
//
//        }
//        return null;
//    }
//}