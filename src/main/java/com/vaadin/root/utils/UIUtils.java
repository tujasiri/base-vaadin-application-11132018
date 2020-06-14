package com.vaadin.root.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
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
import javax.servlet.http.HttpServletRequest;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.root.dao.DefaultDataService;
import com.vaadin.root.dto.CartSingleton;
import com.vaadin.root.dto.CheckoutCart;
import com.vaadin.root.framework.StandardFooterLayout;
import com.vaadin.root.framework.StandardHeaderLayout;
import com.vaadin.root.framework.grids.ShoppingCartGrid;
import com.vaadin.root.model.BusinessInfo;
import com.vaadin.root.model.MerchTable;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;

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
		
		ShoppingCartGrid grid = new ShoppingCartGrid();
		
		BusinessInfo businessInfo = DefaultDataService.getInstance().getBusinessInfoDao().findById(2L);
		
		Image headerBanner = UIUtils.byteArrayToImage(businessInfo.getBiHeader());
		String encodedimg = Base64.getEncoder().encodeToString(businessInfo.getBiLogo());
//		String cid = String.format("%s",UUID.randomUUID());
//		String cid = "http://a.vimeocdn.com/si/email/Vimeo-logo-1a2e3b.png";
//		String cid = "http://166.62.122.123:8080/website/images/banner.png";
		String cid = "http://166.62.122.123:8080/website/VAADIN/images/banner.png";

		 // Recipient's email ID needs to be mentioned.
//        String to = "truthuniversal@gmail.com";
        String to = "truthuniversal@yahoo.com";

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
//        session.setDebug(true);
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Create multipart message objects
//			MimeMultipart multipart = new MimeMultipart("related");
			MimeMultipart multipart = new MimeMultipart("alternative");
//			MimeBodyPart headerBodyPart = new MimeBodyPart();
//			MimeBodyPart messageBodyPart = new MimeBodyPart();

			BodyPart headerBodyPart = new MimeBodyPart();
			BodyPart pdfAttachmentPart = new MimeBodyPart();
			BodyPart messageBodyPart = new MimeBodyPart();

			//Convert images to be added inline
//			DataSource fds = new FileDataSource("/home/manisha/javamail-mini-logo.png");
			DataSource imageds = new ByteArrayDataSource(businessInfo.getBiHeader(),"image/png");
			
			
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
            message.setSubject("Truth Universal Music ==> Order #000000007");

            // Now set the actual message
//            message.setText("<h>This is the actual message</h>");
//            message.setContent(hdr+"<b>This is the actual message</b>","text/html");
          
			messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setContent("<img src=\"cid:"+cid+"\"><br><br><br><b>This is the actual message, with a banner above.</b>","text/html");
			String msgHtml="<img src=\""+cid+"\"><br><br><br><b>This is the actual message, with a banner above.</b>";
//			String msgHtml="";
			msgHtml+=buildContentHtml();
			
			//create invoicePDF 
			String base64EncodedPdf = UIUtils.createOrderPDF(msgHtml);
			
			System.out.println("msgHtml==>"+msgHtml);
			
            messageBodyPart.setContent(msgHtml.toString(),"text/hmtl;charset=utf-8");

//			multipart.addBodyPart(headerBodyPart);
//			multipart.addBodyPart(messageBodyPart);


			DataSource pdfds = new ByteArrayDataSource(base64EncodedPdf ,"application/pdf");
			pdfAttachmentPart.setDataHandler(new DataHandler(pdfds));
			pdfAttachmentPart.setFileName("order_ordernumber.pdf");
			pdfAttachmentPart.setDisposition(MimeBodyPart.ATTACHMENT);

			multipart.addBodyPart(pdfAttachmentPart);
            message.setContent(multipart);
            
            msgHtml+="<div>View/Download order invoice.<a href=\"http://166.62.122.123/invoices/truthuniversal_order.pdf\"></div>";            
		

            message.setText(msgHtml.toString(), "utf-8", "html");

            System.out.println("OS==>"+UIUtils.getOS().toString());
            System.out.println("Substring ==>"+UIUtils.getOS().toString().contains("Windows"));
            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException | IOException mex) {
            mex.printStackTrace();
        }

		
	}
	
	private static	String buildContentHtml() {
		String rowStyle="";
		String htmlTable="<table style=\"border-collapse: collapse;width:100%;\"><tr>"
				+ "<td><b>ITEM</b></td>"
				+ "<td><b>DESCRIPTION</b></td>"
				+ "<td><b>COST</b></td>"
				+ "</tr>";
//		String htmlTable="";
//			htmlTable+= "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">" +
//					"<html>\n" + 

		List<MerchTable> items = CartSingleton.getInstance().getCheckoutCart().itemsInCart();
		
		int idx = 1;
		
		for(MerchTable item:items){
			
			rowStyle=getRowStyle(idx++);
			
			htmlTable+= 
				"<tr style=\""+rowStyle+"\">"+
					"<td style=\"text-align: left;padding: 8px;\">"+item.getMtItemNum()+"</td>" +
					"<td style=\"text-align: left;padding: 8px;\">"+item.getMtItemDescLong()+"</td>" +
					"<td style=\"text-align: left;padding: 8px;\">"+item.getMtItemPrice()+"</td>" +
					"</tr>";
		}

		rowStyle=getRowStyle(idx++);
		//add tax row
		htmlTable+= 
		"<tr style=\""+rowStyle+"\">"+
			"<td style=\"text-align: left;padding: 8px;\">TAX: </td>" +
			"<td style=\"text-align: left;padding: 8px;\"></td>" +
			"<td style=\"text-align: left;padding: 8px;\">"+"0.0"+
			"</td>" +
			"</tr>";

		rowStyle=getRowStyle(idx++);
		//add shipping row
		htmlTable+= 
		"<tr style=\""+rowStyle+"\">"+
			"<td style=\"text-align: left;padding: 8px;\">SHIPPING: </td>" +
			"<td style=\"text-align: left;padding: 8px;\"></td>" +
			"<td style=\"text-align: left;padding: 8px;\">"+CartSingleton.getInstance().getCheckoutCart().calculateShipping()+
			"</td>" +
			"</tr>";
		

		rowStyle=getRowStyle(idx++);
		//add total row
		htmlTable+= 
		"<tr style=\""+rowStyle+"\">"+
			"<td style=\"text-align: left;padding: 8px;\">TOTAL: </td>" +
			"<td style=\"text-align: left;padding: 8px;\"></td>" +
			"<td style=\"text-align: left;padding: 8px;\"></td>" +
			"<td style=\"text-align: left;padding: 8px;\">"+CartSingleton.getInstance().getCheckoutCart().calculateTotal()+
			"</td>" +
			"</tr>";
		

		htmlTable+="</table>";


		return htmlTable;
	}
	
	private static String getRowStyle(int rowIndex){
		return rowIndex % 2 == 0 ? "background-color: #f2f2f2;" : "background-color: #ffffff;";
		
	}
	
	public static String createOrderPDF(String htmlString) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String encodedPdf = "";

    	try {
			HtmlConverter.convertToPdf(htmlString, new FileOutputStream(UIUtils.getDocumentRoot()+
					 "\\truthuniversal-order.pdf"));
			HtmlConverter.convertToPdf(htmlString, baos);
			encodedPdf = Base64.getEncoder().encodeToString(baos.toByteArray());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return encodedPdf;
	}
	
	private static String getOS(){
		return System.getProperty("os.name");
	}
	
	private static String getDocumentRoot() {
		String docRoot="";
		
		String serverOS = UIUtils.getOS();
		
		if(serverOS.contains("Windows"))
			docRoot="C:\\Users\\tujasiri2\\AppData\\Local\\Packages\\"
					+ "CanonicalGroupLimited.UbuntuonWindows_79rhkp1fndgsc\\"
					+ "LocalState\\rootfs\\var\\www\\html\\invoices";

		if(serverOS.contains("Linux"))
			docRoot="/var/www/html/invoices";

		return docRoot;
	}
	
}
