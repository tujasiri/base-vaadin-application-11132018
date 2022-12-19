package com.vaadin.root;

import java.io.Serializable;
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
import com.vaadin.navigator.Navigator;
import com.vaadin.root.dao.AbstractDao;
import com.vaadin.root.dao.DataService;
import com.vaadin.root.dao.DefaultDao_;
import com.vaadin.root.dao.DefaultDataService;
import com.vaadin.root.dao.EntityManagerInstance;
import com.vaadin.root.dto.CheckoutCart;
import com.vaadin.root.framework.AboutView_OLD;
import com.vaadin.root.framework.DefaultView_OLD;
import com.vaadin.root.framework.MusicView;
import com.vaadin.root.framework.views.AboutView;
import com.vaadin.root.framework.views.CheckoutView;
import com.vaadin.root.framework.views.HomeView;
import com.vaadin.root.framework.views.OrderCompleteView;
import com.vaadin.root.framework.views.UploadImagesView;
import com.vaadin.root.model.MemberData;
import com.vaadin.root.model.MerchTableOLD_;
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
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("standardtheme")
@Widgetset("com.vaadin.DefaultWidgetSet")
public class StandardUI extends UI {	
	
//	private static final long serialVersionUID = -8009964627576236599L;

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
					Element jqCssLink4 = new Element(Tag.valueOf("link"),"");
					
					Element jqScript = new Element(Tag.valueOf("script"),"");
					Element jqScript2 = new Element(Tag.valueOf("script"),"");
					Element jqScript3 = new Element(Tag.valueOf("script"),"");
	/*				
    				jqCssLink1.attr("rel","stylesheet")
		    				.attr("type","text/css")
		    				.attr("href","./VAADIN/themes/standardtheme/css/jquery-ui.css");
	*/
					
    				jqCssLink1.attr("rel","stylesheet")
		    				.attr("type","text/css")
		    				.attr("href","https://code.jquery.com/ui/1.12.1/themes/flick/jquery-ui.css");
					
    				jqCssLink2.attr("rel","stylesheet")
		    				.attr("type","text/css")
		    				.attr("href","./VAADIN/themes/standardtheme/css/jquery-ui.structure.css");
					
    				jqCssLink3.attr("rel","stylesheet")
		    				.attr("type","text/css")
		    				.attr("href","./VAADIN/themes/standardtheme/css/jquery-ui.theme.css");
					
    				jqCssLink4.attr("rel","stylesheet")
		    				.attr("type","text/css")
		    				.attr("href","./VAADIN/themes/standardtheme/css/rotate360.css");
					
    				jqScript.attr("src","./VAADIN/themes/standardtheme/js/jquery.rotate360.js")
		    				.attr("type","text/javascript");

    				jqScript2.attr("src","https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js")
		    				.attr("type","text/javascript");
    				
    				jqScript3.attr("src","https://code.jquery.com/jquery-3.3.1.min.js") 
		    				.attr("type","text/javascript")
							.attr("integrity","sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT")
							.attr("crossorigin","anonymous");
						
    				//***************CUSTOM STYLE TAG ****//
					Element customStyle = new Element(Tag.valueOf("style"),"");
					customStyle.attr("type","text/css");
    				//***************CUSTOM STYLE TAG ****//
					
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
    				head.appendChild(jqCssLink4);
    				head.appendChild(jqScript3);
    				head.appendChild(jqScript2);
    				head.appendChild(jqScript);
    				head.appendChild(customStyle);

				}};
				
				event.getSession().addBootstrapListener(bsl);
        VaadinSession.getCurrent().setAttribute("shoppingcart", new CheckoutCart());
			
		}
    	
    }

    @Override
    protected void init(VaadinRequest request) {
//        StandardMainScreen standardMainScreen = new StandardMainScreen();
//        setContent(standardMainScreen);
        
        HomeView homeScreen = new HomeView();
        setContent(homeScreen);
        
//        final Navigator nav = new Navigator(this, standardMainScreen.getStandardMainLayout());
//        final Navigator nav = new Navigator(this, standardMainScreen);
        final Navigator nav = new Navigator(this, homeScreen);
//        nav.addView("", StandardMainScreen.class );
        nav.addView("", HomeView.class);
//      nav.addView("about", AboutView_OLD.class);
        nav.addView("about", AboutView.class);
        nav.addView("checkout", CheckoutView.class);
        nav.addView("checkout_complete", OrderCompleteView.class);
        nav.addView("upload_images", UploadImagesView.class);
        nav.addView("video_view", MusicView.class);
        nav.addView("home", HomeView.class);
        nav.addView("merch", StandardMainScreen.class);


    	
    }

}