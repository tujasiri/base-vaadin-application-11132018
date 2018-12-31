package com.vaadin.root;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.root.dao.AbstractDao;
import com.vaadin.root.dao.DataService;
import com.vaadin.root.dao.DefaultDao_;
import com.vaadin.root.dao.DefaultDataService;
import com.vaadin.root.dao.EntityManagerInstance;
import com.vaadin.root.model.MemberData;
import com.vaadin.root.model.MerchTable;
import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.SessionDestroyListener;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("standardtheme")
@Widgetset("com.vaadin.DefaultWidgetSet")
public class StandardUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = StandardUI.class)
    public static class Servlet extends VaadinServlet implements SessionInitListener, SessionDestroyListener {

		@Override
		public void sessionDestroy(SessionDestroyEvent event) {
		}

		@Override
		protected void servletInitialized() throws ServletException {
			super.servletInitialized();
	        getService().addSessionInitListener((SessionInitListener) this);
	        getService().addSessionDestroyListener((SessionDestroyListener) this);
		}

		@Override
		public void sessionInit(SessionInitEvent event) throws ServiceException {
			
			BootstrapListener bsl = new BootstrapListener(){

				@Override
				public void modifyBootstrapFragment(BootstrapFragmentResponse response) {
				}

				@Override
				public void modifyBootstrapPage(BootstrapPageResponse response) {
					
					Element jqCssLink1 = new Element(Tag.valueOf("link"),"");
					Element jqCssLink2 = new Element(Tag.valueOf("link"),"");
					Element jqCssLink3 = new Element(Tag.valueOf("link"),"");
					
    				jqCssLink1.attr("rel","stylesheet")
		    				.attr("type","text/css")
		    				.attr("href","./VAADIN/themes/standardtheme/css/jquery-ui.css");
					
    				jqCssLink2.attr("rel","stylesheet")
		    				.attr("type","text/css")
		    				.attr("href","./VAADIN/themes/standardtheme/css/jquery-ui.structure.css");
					
    				jqCssLink3.attr("rel","stylesheet")
		    				.attr("type","text/css")
		    				.attr("href","./VAADIN/themes/standardtheme/css/jquery-ui.theme.css");
    				
    				//***************CUSTOM STYLE TAG ****//
					Element customStyle = new Element(Tag.valueOf("style"),"");
					customStyle.attr("type","text/css");
					
    				//***************HIDE ELEMENTS ON LOAD****//
					customStyle.text(".hideonload{display:none;}");
    				
    				//***************RIGHT JUSTIFY ELEMENTS****//
//					customStyle.appendText(" .rightjustify{display: flex; justify-content: flex-end;}");
//					customStyle.appendText(" .rightjustify{"
//							+ " display: block !important;"
//							+ " margin-left: auto !important;"
//							+ " margin-right: 0 !important;"
//							+ " width: 200px !important;"
//							+ " height: 200px !important;"
//							+ " border: 14px solid red !important;}");
    				
    				//***************LEFT JUSTIFY ELEMENTS****//
					customStyle.appendText(" .leftjustify{margin-right:auto; margin-left:0;}");
					
    				//***************PARENT OF JUSTIFIED HEADER ELEMENT****//
//					customStyle.appendText(" .outerdiv {"
//							+ " width: 100% !important;"
//							+ " height: 200px !important;"
//							+ " border: 1px solid rgba(0, 0, 0, 0.1) !important; }"); 
					
    				Element head = response.getDocument().head();
    				
    				head.appendChild(jqCssLink1);
    				head.appendChild(jqCssLink2);
    				head.appendChild(jqCssLink3);
    				head.appendChild(customStyle);
					
				}};
				
				event.getSession().addBootstrapListener(bsl);
			
		}
    	
    }

    @Override
    protected void init(VaadinRequest request) {
    	
        setContent(new StandardMainScreen());
    }

}