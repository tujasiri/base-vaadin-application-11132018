package com.vaadin.root.dto;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.root.model.*;

public class CheckoutCart {

    public CheckoutCart(){
        int test =0;

    }

    //ArrayList<MerchItem> itemsArray;
    private List<MerchTable> itemsArray = new ArrayList<MerchTable>();

    public List<MerchTable> itemsInCart(){
        return this.itemsArray;
    }

    public Boolean containsMerchItem(MerchTable merchItem){
        return false;
    }

    public void groupItemsByType(){
        //group items by type and compact itemsArray
    }

    public int countIndividualItem(MerchTable merchItem){
        int itemCount = 0;
        return itemCount;
    }

    public int removeIndividualItem(MerchTable merchItem){
        int itemCount = 0;
        return itemCount;
    }

    public void addItemToCart(MerchTable merchItem){
        itemsArray.add(merchItem);
        /*
        for(MerchItem tmpMerchItem :itemsArray){
            System.out.println(String.format("In cart ==>%s", tmpMerchItem.getMt_item_desc_long()));

        }
        */
    }

    public void removeItemFromCart(int positon){
        itemsInCart().remove(positon);
    }

    public void emptyCart(){

    }

    public double calculateTotal(){

        double itemTotal = 0.0;

        if(itemsInCart().isEmpty()){
            return 0.0;
        }

        for(int i=0;i<itemsInCart().size();i++){
            itemTotal += itemsInCart().get(i).getMtItemPrice();
        }
        return itemTotal;

    }

    public float calculateShipping(){

        float itemTotal = 0;
        return itemTotal;

    }

}
