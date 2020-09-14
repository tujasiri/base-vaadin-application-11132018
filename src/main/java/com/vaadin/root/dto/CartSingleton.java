package com.vaadin.root.dto;

import javax.ejb.Stateful;

@Stateful
public class CartSingleton {
	
	private CheckoutCart checkoutcart;

    private static CartSingleton instance;

    private CartSingleton(){
        checkoutcart = new CheckoutCart();
    }

    public static CartSingleton getInstance(){
        if (instance == null){
            instance = new CartSingleton();
        }
        return instance;
    }

    public CheckoutCart getCheckoutCart(){
        return checkoutcart;
    }
}
