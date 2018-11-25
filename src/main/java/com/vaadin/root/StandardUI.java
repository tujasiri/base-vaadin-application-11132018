package com.vaadin.root;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.root.model.MemberData;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class StandardUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = StandardUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        // create root layout
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
        // add timer component
        final StandardComponent timer = new StandardComponent();
        layout.addComponent(timer);
        // add buttons
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        // add start button
        Button start = new Button("start");
        start.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                timer.start();
                timer.alertMe();
                
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("application-unit");
      		   
           		EntityManager entityManager = emf.createEntityManager();
           		   
           		entityManager.createNamedQuery("MemberData.findAll",MemberData.class).getResultList().stream().forEach(x->{
           			System.out.println("id ==>"+x.getMdId());
           		}); 
                
                
            }
        });
        buttons.addComponent(start);
        // add stop button
        Button stop = new Button("stop");
        stop.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                timer.stop();
            }
        });
        buttons.addComponent(stop);
        // add reset button
        Button reset = new Button("reset");
        reset.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                timer.reset();
            }
        });
        buttons.addComponent(reset);
        layout.addComponent(buttons);
    }

}