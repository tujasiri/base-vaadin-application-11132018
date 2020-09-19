package com.vaadin.root.dto;

import javax.ejb.Stateful;

import com.vaadin.root.utils.UIUtils;
import com.vaadin.server.VaadinSession;

@Stateful
public class CartSingleton {
	
    private static CartSingleton instance = new CartSingleton();

    private CartSingleton(){
    }

    public static CartSingleton getInstance(){
		System.out.println("IP ==>"+UIUtils.getIpAddress());
        return instance;
    }

    public CheckoutCart getCheckoutCart(){
    	return (CheckoutCart)VaadinSession.getCurrent().getAttribute("shoppingcart");
    }
}
