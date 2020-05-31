package com.vaadin.root.framework.views;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vaadin.dialogs.ConfirmDialog;

//import org.processbase.vaadin.addon.validator.CardNumberValidator;

//import com.google.gwt.user.datepicker.client.DatePicker;
import com.stripe.Stripe;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Source.Card;
import com.stripe.model.Token;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.LocalDateTimeToDateConverter;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.root.dao.DefaultDataService;
import com.vaadin.root.dto.CartSingleton;
import com.vaadin.root.dto.OrderFormDetails;
import com.vaadin.root.framework.MerchItemDetailLayout;
import com.vaadin.root.framework.MerchLayout;
import com.vaadin.root.framework.SpacerLabel;
import com.vaadin.root.framework.StandardHeaderLayout;
import com.vaadin.root.framework.StandardSideLayout;
import com.vaadin.root.framework.grids.ShoppingCartGrid;
import com.vaadin.root.model.MerchTable;
import com.vaadin.root.utils.UIUtils;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class CheckoutView extends VerticalLayout implements View {
	
	private VerticalLayout standardMainLayout = new VerticalLayout();
	private HorizontalLayout subLayout = new HorizontalLayout();
	private HorizontalLayout hLayout = new HorizontalLayout();
	private Panel standardMainPanel= new Panel();
	private StandardHeaderLayout headerLayout;
//		this.businessInfo = DefaultDataService.getInstance().getBusinessInfoDao().findById(2L);
	private StandardSideLayout sideLayout = new StandardSideLayout();
	private ShoppingCartGrid grid = new ShoppingCartGrid();
	private Button chargeButton = new Button("Charge");

	
	public CheckoutView(){
		super();
//		this.headerLayout = new StandardHeaderLayout( DefaultDataService.getInstance().getBusinessInfoDao().findById(2L));
		this.headerLayout = UIUtils.getStandardHeaderLayout(2L);
		setProperties();
		buildPage();
		addStyleName("scrollable");
		
		
		for(int i=10; i>0;i--) {
				System.out.println("i==>"+i);
		}
		
		
	}
	
	private void buildPage(){
		
		VerticalLayout vl = new VerticalLayout();
		HorizontalLayout hl = new HorizontalLayout();
		
//		standardMainLayout.setSizeFull();
		standardMainLayout.setSizeUndefined();
		
		standardMainLayout.setMargin(true);
		standardMainLayout.setSpacing(true);
		
//		standardMainPanel.setSizeFull();
		standardMainPanel.setHeight("500px");
		standardMainPanel.setWidth("800px");
	
		Label testLabel = new Label("Hello World!");
		
		GridLayout gridLayout = new GridLayout(4,3);
		
		gridLayout.setSizeFull();
		gridLayout.setMargin(true);
		gridLayout.setSpacing(true);
		
//		vl.setSizeFull();
		vl.setHeight("1000px");
		vl.setWidth("1000px");
		vl.setMargin(true);
		vl.setSpacing(true);
		
//		for(int x=0;x<1000;x++)
//			vl.addComponent(new Label("Label "+x));
//		
//		for(int y=0;y<1000;y++)
//			hl.addComponent(new Label("Label "+y));
		
		List<MerchTable> mtList = new ArrayList<MerchTable>();
		List<MerchLayout> merchLayoutList = new ArrayList<MerchLayout>();
		
		for(int i=0;i<12;i++)
			mtList.add(DefaultDataService.getInstance().getMerchDao().findOneRecord(12));
		
		mtList.stream().forEach(x->{
		 merchLayoutList.add(new MerchLayout(x));
		});
		
		int idx=0;
		
		for(int i=0;i<3;i++){
			gridLayout.setRowExpandRatio(i,1.0f);
			for(int j=0;j<4;j++){
				gridLayout.setColumnExpandRatio(j,1.0f);
				MerchLayout ml = merchLayoutList.get(idx++);
				ml.setHeightUndefined();
//				ml.setWidth(10.0f,Unit.PERCENTAGE);
				ml.addStyleName("merchLayout");
				gridLayout.addComponent(ml,j,i);
//				gridLayout.addComponent(merchLayoutList.get(idx++),j,i);
			}
		}
		
		gridLayout.setSizeFull();
		this.subLayout.setSizeFull();
		
//		this.subLayout.addComponents(gridLayout, this.sideLayout);
		this.headerLayout.addStyleName("headerLayoutStyle");
		
		
//		standardMainLayout.addComponents(this.headerLayout, hl, vl);
//		standardMainLayout.addComponents(this.headerLayout, gridLayout);
		standardMainLayout.addComponents(this.headerLayout);
		SpacerLabel spacer = new SpacerLabel();
		spacer.addStyleName("testborder");
		standardMainLayout.addComponents(spacer);
		
//		standardMainLayout.addComponents(this.headerLayout, this.subLayout);
//		standardMainLayout.setExpandRatio(this.subLayout, 1);
//		standardMainLayout.setExpandRatio(this.subLayout, 1);
//		standardMainLayout.setExpandRatio(this.headerLayout, 1);
		
//	standardMainLayout.setExpandRatio(gridLayout, 1);
		
//		standardMainLayout.setComponentAlignment(gridLayout, Alignment.MIDDLE_CENTER);
		this.grid.setSizeFull();
		this.grid.setCaptionAsHtml(true);
		this.grid.setCaption("<h1>Order Summary</ht>");
		
		standardMainLayout.addComponents(this.grid,this.buildOrderForm());
		
		standardMainPanel.setContent(standardMainLayout);
		
//		addComponent(standardMainLayout);
//		this.headerLayout.setHeight(5.0f, Unit.PERCENTAGE);
//		addComponents(this.headerLayout, standardMainPanel);
//		addComponents(this.hLayout, standardMainPanel);
//		addComponents(standardMainPanel);
		
		
		standardMainLayout.addStyleName("scrollable");;
		
//		hLayout.addComponent(new Label("HEADER PANEL"));
//		hLayout.setSizeFull();
//		hLayout.addStyleName("testborder");

		
		addComponents(standardMainLayout);
		setExpandRatio(standardMainLayout,1.0f);
		
		
		
		try {
	
//			byte[] bytearray = DefaultDataService.getInstance().getMerchDao().findOneRecord(12).getMtImage();
//			
//			Image img = UIUtils.byteArrayToImage(bytearray);
//			
//			img.setHeight(20.0f, Unit.PERCENTAGE);
//			img.setWidth(15.0f, Unit.PERCENTAGE);
//			
//			addComponents(img);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	private Image byteArrayToImage(final byte[]byteArray){
		StreamSource streamSource = new StreamResource.StreamSource(){

			@Override
			public InputStream getStream() {
				// TODO Auto-generated method stub
				return new ByteArrayInputStream(byteArray);
			}
		}; 
		
		return new Image(null, new StreamResource(streamSource,""));
		
	}

	private void setProperties(){
//		setSizeFull();
		setHeight("100%");
		addStyleName("");
		
	}

	public VerticalLayout getStandardMainLayout() {
		return standardMainLayout;
	}

	public void setStandardMainLayout(VerticalLayout standardMainLayout) {
		this.standardMainLayout = standardMainLayout;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	private void makeCharge(OrderFormDetails ofd){

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Stripe.apiKey = "sk_test_N8a1Y5MGiiuGg8n8SjouqAS3";
		Card card = new Card();
		Customer customer = new Customer();
		Token token = new Token();

		Map<String, Object> cardParams = new HashMap<>();
		Map<String, Object> customerParams = new HashMap<>();
		Map<String, Object> tokenParams = new HashMap<>();
		
		
		//convert expiration date 
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		String expdate = sdf.format(ofd.getExpirationDate());
		
		
		System.out.println("expdate==>"+expdate);
		System.out.println("expdate yy ==>"+expdate.substring(6));
		System.out.println("expdate mm ==>"+expdate.substring(0,2));
		
//		DateTimeFormatter TWO_YEAR_FORMATTER = DateTimeFormatter.ofPattern("yy");
//	    Year expyear = TWO_YEAR_FORMATTER.parse(expdate.substring(6,7), Year::from);
//				
//		System.out.println("expyear==>"+expyear);
		//create customer object if not exist
		customerParams.put("email", ofd.getEmailAddress());
		
		try {
			System.out.println("here create customer ==>");
			//if customer does not exist... *retrieve*
			customer = Customer.create(customerParams);
		} catch (CardException e) {
			  // Since it's a decline, CardException will be caught
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (RateLimitException e) {
		  // Too many requests made to the API too quickly
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (InvalidRequestException e) {
		  // Invalid parameters were supplied to Stripe's API
//			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (AuthenticationException e) {
		  // Authentication with Stripe's API failed
		  // (maybe you changed API keys recently)
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (ApiConnectionException e) {
		  // Network communication with Stripe failed
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (StripeException e) {
		  // Display a very generic error to the user, and maybe send
		  // yourself an email
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
		  // Something else happened, completely unrelated to Stripe
			alertUser(e.getMessage());
			e.printStackTrace();
		}
		
		//create card object if not exist
		cardParams.put("number", ofd.getCardNumber());
		cardParams.put("exp_month", expdate.substring(0,2));
//		cardParams.put("exp_year", "20"+expdate.substring(6));
//		System.out.println("exp_year==>"+expdate.substring(6));
		cardParams.put("exp_year", String.format("20%s",expdate.substring(6)));
		cardParams.put("cvc", ofd.getCvvCode());
		
		//create token
		tokenParams.put("card", cardParams);
		
		try {
			token = Token.create(tokenParams);
		} catch (CardException e) {
			  // Since it's a decline, CardException will be caught
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (RateLimitException e) {
		  // Too many requests made to the API too quickly
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (InvalidRequestException e) {
		  // Invalid parameters were supplied to Stripe's API
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (AuthenticationException e) {
		  // Authentication with Stripe's API failed
		  // (maybe you changed API keys recently)
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (ApiConnectionException e) {
		  // Network communication with Stripe failed
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (StripeException e) {
		  // Display a very generic error to the user, and maybe send
		  // yourself an email
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
		  // Something else happened, completely unrelated to Stripe
			alertUser(e.getMessage());
			e.printStackTrace();
		}
		

		// `source` is obtained with Stripe.js; see https://stripe.com/docs/payments/accept-a-payment-charges#web-create-token
		Map<String, Object> params = new HashMap<>();
		System.out.println("amount ==>"+ Math.round(calculateTotalAndShipping()));
		params.put("amount", Math.round(calculateTotalAndShipping())*100);
		params.put("currency", "usd");
//		params.put("source", "tok_mastercard");
		params.put("source", token.getId());
		params.put("description", String.format("Truth Universal Music Application Charge :: %s",timestamp));

		try {
			Charge charge = Charge.create(params);
			System.out.println("STATUS==>"+charge.getStatus());
			System.out.println("INVOICE==>"+charge.getInvoice());
			alertUser("SUCCESS!  Invoice will be emailed to the address you provided.  Thank you for rocking with us!");
		} catch (CardException e) {
			  // Since it's a decline, CardException will be caught
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (RateLimitException e) {
		  // Too many requests made to the API too quickly
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (InvalidRequestException e) {
		  // Invalid parameters were supplied to Stripe's API
//			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (AuthenticationException e) {
		  // Authentication with Stripe's API failed
		  // (maybe you changed API keys recently)
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (ApiConnectionException e) {
		  // Network communication with Stripe failed
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (StripeException e) {
		  // Display a very generic error to the user, and maybe send
		  // yourself an email
			alertUser(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
		  // Something else happened, completely unrelated to Stripe
			alertUser(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	private double calculateTotalAndShipping(){
		return (CartSingleton.getInstance().getCheckoutCart().calculateTotal()+
		CartSingleton.getInstance().getCheckoutCart().calculateShipping());
	}
	
	private FormLayout buildOrderForm() {
		FormLayout orderForm = new FormLayout();
		Binder<OrderFormDetails>binder = new Binder<>();
		OrderFormDetails orderFormObj = new OrderFormDetails();
		Button chargeButton = new Button("Charge");
		

		chargeButton.addClickListener(cButton->{
			
			try {
				UIUtils.SendMessage();
				
				System.out.println("before writing bean==>"+orderFormObj.getFirstName());
				binder.writeBean(orderFormObj);
				System.out.println("after writing bean==>"+orderFormObj.getFirstName());
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			System.out.println("orderFormObj"+orderFormObj.toString());

			//try catch!!
			
			if(binder.validate().isOk())
				this.makeCharge(orderFormObj);
			else
				Notification.show("VALIDATION FAILED");

//			this.grid.refresh();
		});				

		orderForm.addStyleName("sectionborder");
		orderForm.setSpacing(true);

		FormLayout custInfoLayout = new FormLayout();
		custInfoLayout.addStyleName("sectionborder");
	
		TextField firstName = new TextField();
		firstName.setValueChangeMode(ValueChangeMode.EAGER);
		firstName.setCaption("First Name");
		TextField lastName = new TextField();
		lastName.setValueChangeMode(ValueChangeMode.EAGER);
		lastName.setCaption("Last Name");
		TextField addressLine1 = new TextField();
		addressLine1.setValueChangeMode(ValueChangeMode.EAGER);
		addressLine1.setCaption("Billing Address");
		TextField addressLine2 = new TextField();
		addressLine2.setValueChangeMode(ValueChangeMode.EAGER);
		addressLine2.setCaption("Billing Address (Suite, Apt., Box, etc.)");
		TextField city = new TextField();
		city.setValueChangeMode(ValueChangeMode.EAGER);
		city.setCaption("City");
		TextField state = new TextField();
		state.setValueChangeMode(ValueChangeMode.EAGER);
		state.setCaption("State");
		TextField zip = new TextField();
		zip.setValueChangeMode(ValueChangeMode.EAGER);
		zip.setCaption("Zip");
		TextField phone = new TextField();
		phone.setValueChangeMode(ValueChangeMode.EAGER);
		phone.setCaption("Phone");
		TextField email = new TextField();
		email.setValueChangeMode(ValueChangeMode.EAGER);
		email.setCaption("E-Mail");
		
		custInfoLayout.addComponents(firstName, lastName, addressLine1, addressLine2, city, state, zip, phone, email);
		
		
		
		
		//card info
		
//		VerticalLayout cardInfoLayout = new VerticalLayout();
		FormLayout cardInfoLayout = new FormLayout();
		
		TextField cardNumber = new TextField();
		cardNumber.setValueChangeMode(ValueChangeMode.EAGER);
		cardNumber.setCaption("Card Number");
		
		//add image next to cardNumber textfield to indicate card type -- Visa, MasterCard, etc
		
		TextField cvvCode = new TextField();
		cvvCode.setValueChangeMode(ValueChangeMode.EAGER);
		cvvCode.setCaption("CVV");

		DateField expDate = new DateField();		
		((Component) expDate).setCaption("Exp. Date");
		
		cardInfoLayout.addComponents(cardNumber,cvvCode,expDate);
		cardInfoLayout.setSizeFull();
		cardInfoLayout.addStyleName("sectionborder");
		cardInfoLayout.setSpacing(true);
		
		//customer form section 
		orderForm.addComponent(custInfoLayout);

		//card form section 
		orderForm.addComponent(cardInfoLayout);

		//buttons
		orderForm.addComponent(chargeButton);
		

		orderForm.setCaptionAsHtml(true);
		orderForm.setCaption("<h1>Order Details</ht>");
		custInfoLayout.setCaption("Customer Info");
		cardInfoLayout.setCaption("Card Info");
		
		binder.forField(firstName)
				.asRequired("Required")
				.withValidator(fname->!(fname == null||fname.isEmpty()),"Field invalid")
				.bind(OrderFormDetails::getFirstName,OrderFormDetails::setFirstName);
		
		binder.forField(lastName)
				.asRequired("Required")
				.withValidator(lname->!(lname == null||lname.isEmpty()),"Field invalid")
				.bind(OrderFormDetails::getLastName,OrderFormDetails::setLastName);
		//address1
		binder.forField(addressLine1)
				.asRequired("Required")
				.withValidator(address1->!(address1 == null||address1.isEmpty()),"Field invalid")
				.bind(OrderFormDetails::getAddressLine1,OrderFormDetails::setAddressLine1);
		//address2
		binder.forField(addressLine2)
				.bind(OrderFormDetails::getAddressLine1,OrderFormDetails::setAddressLine1);
		
		binder.forField(phone)
				.bind(OrderFormDetails::getPhoneNumber,OrderFormDetails::setPhoneNumber);
	
		binder.forField(city)
				.asRequired("Required")
				.withValidator(c->!(c == null||c.isEmpty()),"Field invalid")
				.bind(OrderFormDetails::getCity,OrderFormDetails::setCity);
	
		binder.forField(state)
				.asRequired("Required")
				.withValidator(s->!(s == null||s.isEmpty()),"Field invalid")
				.bind(OrderFormDetails::getState,OrderFormDetails::setState);
	
		binder.forField(zip)
				.asRequired("Required")
				.withValidator(z->!(z == null||z.isEmpty()),"Field invalid")
				.bind(OrderFormDetails::getZipCode,OrderFormDetails::setZipCode);
		
		binder.forField(email)
				.asRequired("Required")
				.withValidator(new EmailValidator("Invalid email address"))
				.bind(OrderFormDetails::getEmailAddress,OrderFormDetails::setEmailAddress);
		
		binder.forField(cvvCode)
				.asRequired("Required")
//				.withValidator(new EmailValidator("Invalid card number"))
				.bind(OrderFormDetails::getCvvCode,OrderFormDetails::setCvvCode);
		
		binder.forField(cardNumber)
				.asRequired("Required")
//				.withValidator(new CardNumberValidator("Invalid card number"))
				.withValidator(cardnum -> cardnum.length() == 16,"Invalid card number")
				.bind(OrderFormDetails::getCardNumber,OrderFormDetails::setCardNumber);
		
		binder.forField(expDate)
				.asRequired("Required")
//				.bind(OrderFormDetails::getExpirationDate,OrderFormDetails::setExpirationDate);
		        .withConverter(new LocalDateToDateConverter(ZoneId.systemDefault()))
				.bind(OrderFormDetails::getExpirationDate,OrderFormDetails::setExpirationDate);
		
		/****set dummy values*****/
		firstName.setValue("Tajiri");
		lastName.setValue("Ujasiri");
		addressLine1.setValue("5000 Elysian Fields Ave");
		addressLine2.setValue("Suite 0");
		city.setValue("New Orleans");
		state.setValue("LA");
		zip.setValue("70122");
		phone.setValue("5044815959");
		email.setValue("truthuniversal@yahoo.com");
		cardNumber.setValue("4242424242424242");
		cvvCode.setValue("424");
		expDate.setValue(LocalDate.of(20, 5, 1));
		/*************************/
		
	
		

		return orderForm;
		
	}
	
	private void alertUser(String errMsg){
		ConfirmDialog.show(UI.getCurrent(), "Confirmation",
//				"ERROR: "+errMsg.matches("^(.+?).") , "OK","Cancel", 
				"ERROR: "+errMsg , "OK","Cancel", 
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
	
}
