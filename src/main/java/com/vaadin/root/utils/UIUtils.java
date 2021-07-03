package com.vaadin.root.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.mail
import java.util.Properties;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
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

import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.root.dao.DataService;
import com.vaadin.root.dao.DefaultDao;
import com.vaadin.root.dao.DefaultDataService;
import com.vaadin.root.dto.CartSingleton;
import com.vaadin.root.dto.CheckoutCart;
import com.vaadin.root.dto.ImageData;
import com.vaadin.root.framework.StandardFooterLayout;
import com.vaadin.root.framework.StandardHeaderLayout;
import com.vaadin.root.framework.grids.ShoppingCartGrid;
import com.vaadin.root.model.BusinessInfo;
import com.vaadin.root.model.MerchTable;
import com.vaadin.root.model.OrderSummary;
import com.vaadin.root.model.ViewerImage;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;

public class UIUtils {
	
	private static DefaultDao dao = new DefaultDao();
	private static DataService ds;
	
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
	
	//public static void SendMessage() {
	public static void SendMessage() {
		
		ShoppingCartGrid grid = new ShoppingCartGrid();
		
		BusinessInfo businessInfo = DefaultDataService.getInstance().getBusinessInfoDao().findById(2L);
		
		Image headerBanner = UIUtils.byteArrayToImage(businessInfo.getBiHeader());
		String encodedimg = Base64.getEncoder().encodeToString(businessInfo.getBiLogo());
//		String cid = String.format("%s",UUID.randomUUID());
//		String cid = "http://166.62.122.123:8080/website/VAADIN/images/generic_tum_banner.png";
		String cid = "http://"+UIUtils.getIpAddress()+":8080/website/VAADIN/images/generic_tum_banner.png";

		System.out.println("IP ==>"+UIUtils.getIpAddress());

		 // Recipient's email ID needs to be mentioned.
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
            String subj = String.format("Truth Universal Music, LLC, Order #%d", CartSingleton.getInstance().getCheckoutCart().getOrder().getOrId());

            message.setSubject(subj);

            // Now set the actual message
//            message.setText("<h>This is the actual message</h>");
//            message.setContent(hdr+"<b>This is the actual message</b>","text/html");
          
			messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setContent("<img src=\"cid:"+cid+"\"><br><br><br><b>This is the actual message, with a banner above.</b>","text/html");

			//add header banner to email
			String msgHtml="<img style=\"width:633px;height:96px;padding:8px;\" src=\""+cid+"\"><br><br><br><b>PEACE & Thank you for supporting Truth Universal Music, LLC!  Here are you order details:</b></b>";
			
			//add teh remaining content to email message
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
//            message.setContent(multipart);
		
//            msgHtml+=String.format("<div><a href=\"http://166.62.122.123/invoices/%s\">View/Download order invoice.</a></div>",getInvoiceFileName());            
            msgHtml+=String.format("<div><a href=\"http://%s/invoices/%s\">View/Download order invoice.</a></div>",getIpAddress( ),getInvoiceFileName());            

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

		List<OrderSummary> items = CartSingleton.getInstance().getCheckoutCart().getOrderSummary();
		
		int idx = 1;
		
		for(OrderSummary item:items){
			
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
					 "/"+getInvoiceFileName()));
			
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
	
	private static String getInvoiceFileName(){
		return String.format("truthuniversal_order_%d.pdf", CartSingleton.getInstance().getCheckoutCart().getOrder().getOrId());
	}
	
	public static void alertUser(String errMsg){
		ConfirmDialog.show(UI.getCurrent(), "Confirmation",
//				"ERROR: "+errMsg.matches("^(.+?).") , "OK","Cancel", 
				 errMsg , "OK","Cancel", 
		new ConfirmDialog.Listener() {

			@Override
			public void onClose(ConfirmDialog dialog) {
				if (dialog.isConfirmed()) {
					dialog.close();
				} else {
					dialog.close();
				}
			}
		});
		
		
	}			

	public static String getIpAddress(){
		try {
			return InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "127.0.0.1";

	}
	
	public static Map<String,String> getDummyImageMap(){
		Map dummyImageMap = new HashMap<String,String>();
		
		return dummyImageMap;
		
	}

	public static List<ImageData> getDummyImageList(){
		List<ImageData>dummyImageList = new ArrayList<ImageData>();
		ImageData imagedata = new ImageData();
		
		String path = new java.io.File("").getAbsolutePath();
		System.out.println("here after path");
		
		try (InputStream input = new FileInputStream("/mnt/c/Temp/base64/base64imagearray.properties")) {         
		
	        Properties prop = new Properties();
	        prop.load(input);
	        
	        
	        for(int i=0;i<prop.size();i++) {
	        	
	        	String sValue = "";
				imagedata = new ImageData();
				
	        	sValue = prop.getProperty(Integer.toString(i+1));
	        	System.out.println("sValue==>"+sValue);
//	        	System.out.println("sValue==>"+sValue.replace("\"",""));
				imagedata.setImagedata(sValue);
				dummyImageList.add(imagedata);
	        }
		
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return dummyImageList;
		
	}
	
	public static List<ViewerImage> getImageList(int merchItemIndex){
		List<ViewerImage> imageList = new ArrayList<ViewerImage>();
		
		imageList = DefaultDataService.getInstance().getViewerImagesDao().findById(merchItemIndex);

		return imageList;
	}
	
	// convert byte[] to BufferedImage
    public static BufferedImage toBufferedImage(byte[] bytes)
        throws IOException {

        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;
    }
    
//    private static void resize(BufferedImage bufferedImage) throws IOException {
    public static byte[] resizeImage(byte[] byteArray) throws IOException {
    	
		int IMG_WIDTH=72;
		int IMG_HEIGHT=72;
    	
    	BufferedImage bufferedImage = toBufferedImage(byteArray);
    	
    	BufferedImage newResizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g = newResizedImage.createGraphics();

		//Graphics2D g = bufferedImage.createGraphics();
		
		//g.setBackground(Color.WHITE);
		//g.setPaint(Color.WHITE);
		
		// background transparent
		g.setComposite(AlphaComposite.Src);
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
		
		/* try addRenderingHints()
		// VALUE_RENDER_DEFAULT = good tradeoff of performance vs quality
		// VALUE_RENDER_SPEED   = prefer speed
		// VALUE_RENDER_QUALITY = prefer quality
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
		           RenderingHints.VALUE_RENDER_QUALITY);
		
		// controls how image pixels are filtered or resampled
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		           RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		// antialiasing, on
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		           RenderingHints.VALUE_ANTIALIAS_ON);*/
		/*
		Map<RenderingHints.Key,Object> hints = new HashMap<>();
		hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.addRenderingHints(hints);
		*/
		
		// puts the original image into the newResizedImage
		g.drawImage(bufferedImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(bufferedImage, "jpeg", baos);
        ImageIO.write(newResizedImage, "png", baos);
        
        byte[] bytes = baos.toByteArray();
        
        return bytes;

} 
    
	
        
	
}
