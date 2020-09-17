package com.vaadin.root.dto;

import javax.ejb.Stateful;

import com.vaadin.server.VaadinSession;

@Stateful
public class CartSingleton {
	
	private VaadinSession cartsession = VaadinSession.getCurrent();
	
	private CheckoutCart checkoutcart;

    private static CartSingleton instance;

    private CartSingleton(){
        checkoutcart = new CheckoutCart();
    }

    public static CartSingleton getInstance(){
        if (instance == null){
            instance = new CartSingleton();
            instance.cartsession.setAttribute("cartinstance", new CartSingleton());

        }
        return (CartSingleton)instance.cartsession.getAttribute("cartinstance");
    }

    public CheckoutCart getCheckoutCart(){
        return checkoutcart;
    }
}
