package com.vaadin.root.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
//import java.mail
import java.util.Properties;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.root.dao.DefaultDataService;
import com.vaadin.root.framework.StandardFooterLayout;
import com.vaadin.root.framework.StandardHeaderLayout;
import com.vaadin.root.model.BusinessInfo;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;

public class UIUtils {
	
	public static Image byteArrayToImage(final byte[]byteArray){
		StreamSource streamSource = new StreamResource.StreamSource(){

			@Override
			public InputStream getStream() {
				return new ByteArrayInputStream(byteArray);
			}
		}; 
		
		return new Image(null, new StreamResource(streamSource,""));
		
	}
	
	public static Button createShoppingButton(){
		Button shoppingButton = new Button("Add to Cart",VaadinIcons.CART);
		return shoppingButton; 
	}
	
	public static Button createViewButton(){
		Button viewButton = new Button("View Item Details", VaadinIcons.GLASSES);
		
		return viewButton; 
	}
	
	public static StandardHeaderLayout getStandardHeaderLayout(Long businessId){
		return new StandardHeaderLayout( DefaultDataService.getInstance().getBusinessInfoDao().findById(businessId));
	}
	
	public static StandardFooterLayout getStandardFooterLayout(Long businessId){
		return new StandardFooterLayout( DefaultDataService.getInstance().getBusinessInfoDao().findById(businessId));
	}
	
	public static void SendMessage() {
		
		BusinessInfo businessInfo = DefaultDataService.getInstance().getBusinessInfoDao().findById(2L);
		
		Image headerBanner = UIUtils.byteArrayToImage(businessInfo.getBiHeader());
		String encodedimg = Base64.getEncoder().encodeToString(businessInfo.getBiLogo());
		String cid = String.format("%s",UUID.randomUUID());


		 // Recipient's email ID needs to be mentioned.
        String to = "truthuniversal@gmail.com";
//        String to = "truthuniversal@yahoo.com";

        // Sender's email ID needs to be mentioned
        String from = "beats4truth@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("beats4truth@gmail.com", "kucxhaqnewykwdgs");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Create multipart message objects
//			MimeMultipart multipart = new MimeMultipart("related");
			MimeMultipart multipart = new MimeMultipart("mixed");
//			MimeBodyPart headerBodyPart = new MimeBodyPart();
//			MimeBodyPart messageBodyPart = new MimeBodyPart();

			BodyPart headerBodyPart = new MimeBodyPart();
			BodyPart messageBodyPart = new MimeBodyPart();
			

			//Convert images to be added inline
//			DataSource fds = new FileDataSource("/home/manisha/javamail-mini-logo.png");
			DataSource imageds = new ByteArrayDataSource(businessInfo.getBiHeader(),"image/png");
			
			headerBodyPart.setDataHandler(new DataHandler(imageds));
			headerBodyPart.setHeader("Content-ID", "<"+cid+">");	
			headerBodyPart.setDisposition(MimeBodyPart.INLINE);
//			headerBodyPart.setFileName("banner.png");
			headerBodyPart.setFileName(cid);
			
//			multipart.addBodyPart(messageBodyPart);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
//            message.setText("<h>This is the actual message</h>");
//            message.setContent(hdr+"<b>This is the actual message</b>","text/html");
           
			messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<img src=\"cid:"+cid+"\"><br><br><br><b>This is the actual message, with a banner above.</b>","text/html");

			multipart.addBodyPart(headerBodyPart);
			multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            
            System.out.println("multipart ==>"+multipart.toString());

            
            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

		
	}
	
}
