package com.vaadin.root.utils;

import com.stripe.Stripe;

public class UIConstants {

	//ENUMS
	private enum Size{S,M,XL,XXL,XXXL}
	private enum Gender{M,F};
	
	//CONSTANTS
	public static String STRIPE_API_KEY = "sk_test_N8a1Y5MGiiuGg8n8SjouqAS3";
//	public static String STRIPE_API_KEY = "pk_live_LXbT7GmRv8NFS6oD1KOKZ9UL";


	//TRANSACTION MESSAGES
	
	//SUCCESS
	public static String PURCHASE_TRANSACTION_SUCCESS = "Your invoice will be sent to the e-mail address you provided.  "
			+ "Thank you for rocking with us!";
	//FAILURE
	public static String PURCHASE_TRANSACTION_FAILURE_CARD = "Your card is invalid.";
	public static String PURCHASE_TRANSACTION_FAILURE_EXP = "Your card's expiration date is invalid.";
	public static String PURCHASE_TRANSACTION_FAILURE_GENERIC = "An error occurred during your transaction. Please retry your transaction."
			+ "If the problem persists, please contact support@truthuniversal.com. Please make note of the order number.";
	
	//MESSAGES
	public static String PURCHASE_TRANSACTION_MSG_NONZERO = "There are no items in the cart.";
	public static String PURCHASE_TRANSACTION_MSG_QUANTITY = "A quantity must be specified in order to add an item to your cart.";
}
