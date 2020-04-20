package com.vaadin.root.framework.views;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.processbase.vaadin.addon.validator.CardNumberValidator;

//import com.google.gwt.user.datepicker.client.DatePicker;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Source.Card;
import com.stripe.model.Token;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
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
		
		standardMainLayout.addComponents(this.grid,this.buildOrderForm(),this.chargeButton);
		
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

		// `source` is obtained with Stripe.js; see https://stripe.com/docs/payments/accept-a-payment-charges#web-create-token
		Map<String, Object> params = new HashMap<>();
		System.out.println("amount ==>"+ Math.round(calculateTotalAndShipping()));
		params.put("amount", Math.round(calculateTotalAndShipping())*100);
		params.put("currency", "usd");
		params.put("source", "tok_mastercard");
		params.put("description", String.format("Truth Universal Music Application Charge :: %s",timestamp));

		try {
			Charge charge = Charge.create(params);
			System.out.println("STATUS"+charge.getStatus());
		} catch (StripeException e) {
			// TODO Auto-generated catch block
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
				System.out.println("before writing bean==>"+orderFormObj.getFirstName());
				binder.writeBean(orderFormObj);
				System.out.println("after writing bean==>"+orderFormObj.getFirstName());
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			
			Notification.show("CLICKED!");
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
				.bind(OrderFormDetails::getExpirationDate,OrderFormDetails::setExpirationDate);
		
		
		
	
		

		return orderForm;
		
	}
	
}
