package com.vaadin.root.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.vaadin.root.dao.DataService;
import com.vaadin.root.dao.DefaultDao;
import com.vaadin.root.dao.DefaultDataService;
import com.vaadin.root.framework.listeners.UpdateListener;
import com.vaadin.root.model.*;

public class CheckoutCart implements Serializable{
	
	private static final long serialVersionUID = -810996462757623659L;

	private	DefaultDao dao = new DefaultDao();
    private OrderIdentification order = new OrderIdentification();
    private Order orderRecord = new Order();
    private Date orderDate = new Date(System.currentTimeMillis());

    private List<MerchTable> itemsArray = new ArrayList<MerchTable>();
    private UpdateListener updateListener;

    

	public CheckoutCart(){
		this.order = dao.updateOrCreateEntity(this.order, null);
    }

    public OrderIdentification getOrder() {
		return order;
	}

	public void setOrder(OrderIdentification order) {
		this.order = order;
	}

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
        itemsArray.remove(merchItem);
        int itemCount = 0;
        this.getUpdateListener().updateObject();
        return itemCount;
    }

    public void addItemToCart(MerchTable merchTableItem){
		System.out.println("ADD ITEM TO CARRRT");
		
		System.out.println("ORDER==>"+order.getOrId());
        merchTableItem.setMtOrderId(order.getOrId());
        
        
		orderRecord.setOrIcId(merchTableItem.getMtIcId());
		orderRecord.setOrId(merchTableItem.getMtOrderId());
		orderRecord.setOrMtItemNum(merchTableItem.getMtItemNum());
		orderRecord.setOrDate(orderDate);
        orderRecord = dao.updateOrCreateEntity(orderRecord,null);
		merchTableItem.setMtOrIdSeq(orderRecord.getOrSeq());
		//orderRecord.setOrCbId();
		//populate dynamic date set
		//orderRecord.setOrDate(orDate);
        
        itemsArray.add(merchTableItem);
        
        this.getUpdateListener().updateObject();
    }

    public void addItemsCart(List<MerchTable> merchTableItems){
		
		merchTableItems.stream().forEach(x->{
		System.out.println("addItemsCart method ORDER=======>"+order.getOrId());
			x.setMtOrderId(order.getOrId());

			orderRecord.setOrIcId(x.getMtIcId());
			orderRecord.setOrId(x.getMtOrderId());
			orderRecord.setOrMtItemNum(x.getMtItemNum());
			orderRecord.setOrDate(orderDate);
			orderRecord = dao.updateOrCreateEntity(orderRecord,null);

			x.setMtOrIdSeq(orderRecord.getOrSeq());
			
		});

		merchTableItems.stream().forEach(x->{
		    System.out.println("item==>"+x.toString());
		});

        itemsArray.addAll(merchTableItems);
        this.getUpdateListener().updateObject();
    }
    
    public List<OrderSummary> getOrderSummary(){
    	return DefaultDataService.getInstance().getOrderSummaryDao().findOrderRecord(this.getOrder().getOrId());
    }
    
    public void removeOrderItemFromCart(long orseq){
    	//Delete item record from the Order table and it will reflect in the OrderSummary View 
    	//Following logic needed because entity must be managed to call remove
    	EntityManager em = dao.getDaoEntityManager();
    	em.getTransaction().begin();
    	Order orderItem = em.find(Order.class, orseq);
    	orderItem = em.merge(orderItem);
    	em.remove(orderItem);
    	em.getTransaction().commit();

    	//remove item from MerchTable array reference 
        itemsInCart().removeIf(x->x.getMtOrSeq() == orseq);
        this.getUpdateListener().updateObject();
    }

    public void emptyCart(){
    	//empties shopping cart and creates new order record so old items are not linked
    	this.itemsArray.clear();
		this.order = dao.updateOrCreateEntity(this.order, null);

        this.getUpdateListener().updateObject();
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

	@Override
	public String toString() {
		return this.itemsArray.toString();	
	}
	
	public UpdateListener getUpdateListener() {
		return updateListener;
	}

	public void setUpdateListener(UpdateListener updateListener) {
		this.updateListener = updateListener;
	}
    

}
