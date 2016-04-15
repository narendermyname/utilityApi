/**
 * 
 */
package com.naren.email.notify;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author nstanwar
 *
 */
public class EmailNotification {

	private static final String password="9829068334";
	static Properties properties = new Properties();
	static
	{
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
	}
	/**
	 * 
	 */
	public EmailNotification() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		for(int i=0;i<200;i++)
		{
			//Using JSDN Mail Server
			//sendMail();
			//sendMailByGmail("narendermyname@gmail.com", "nstane88@gmail.com", "Hello From Nstane....", "!Greeting....:");
			//sendMailByGmail("sjha@jamcracker.com", "nstane88@gmail.com", "Hello From Nstane....", "!Greeting....:");
			
		}
		//Using Gmail
		//sendMailByGmail("narendermyname@gmail.com", "nstane88@gmail.com", "Hello From Nstane....", "!Greeting....:",true);
		//Send Email Throught Spring
		ApplicationContext context = 
	            new ClassPathXmlApplicationContext("Spring-Mail.xml");
	 
	    	SendMail mm = (SendMail) context.getBean("mailMail");
	        mm.sendMail("Narender Singh", "THis is Seimple Mail.");
	        System.out.println("Mail Sent Success Fully.....");
	}

	/**
	 * Send Email Throught Simple Java Mail API and JSDN
	 */
	public static void sendMail()
	{
		String to = "shivaputr@gmail.com";
		String from = "pp@pp.com";
		// String host = "localhost";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host",  "172.17.11.201");
		Session session = Session.getDefaultInstance(properties);
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));
			message.setSubject("This is the Subject Line!");
			message.setText("This is actual message");
			Transport.send(message);
			System.out.println("Sent message successfully....");
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	/**
	 * Send Email Throught Simple Java Mail API and  Gmail
	 * @param to
	 * @param from
	 * @param body
	 * @param subject
	 * @param attachment
	 */
	public static void sendMailByGmail(String to,final String from,String body,String subject,boolean attachment){
		try
		{
			Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator() {
				protected PasswordAuthentication 
				getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Email Sent Successfully..");
	}
}
