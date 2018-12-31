package com.vaadin.root.jscomponent;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.Notification;

import elemental.json.JsonArray;

@JavaScript({ "Timer.js", "TimerComponentConnector.js", "jquery-1.12.4.js", "jquery-ui.js" })
public class TimerComponent extends AbstractJavaScriptComponent {

    private static final long serialVersionUID = -4318074441748905804L;

    /**
     * Creates a new instance of timer component
     * 
     */
    public TimerComponent() {
        registerFunctions();
    }

    /**
     * Registers functions that can be called from the client side
     * 
     */
    private void registerFunctions() {
        this.addFunction("timeout", new JavaScriptFunction() {
            private static final long serialVersionUID = -6857196720604898163L;

            @Override
            public void call(JsonArray arguments) {
                Notification.show("Time is up!!");
            }
        });
    }

    /**
     * Starts the timer on the client side by changing state
     * 
     */
    public void alertme() {
        this.callFunction("alertme");
    }
    
    public void start() {
        getState().started = true;
    }

    /**
     * Resets the timer on the client side
     * 
     */
    public void reset() {
        // invoke clientRPC
        this.callFunction("reset");
    }

    @Override
    protected TimerComponentState getState() {
        return (TimerComponentState) super.getState();
    }

    /**
     * Stops the timer on the client side by changing state
     * 
     */
    public void stop() {
        getState().started = false;
    }
    
    public void alertyou() {
        this.callFunction("alertyou");
    }
    
    public void jquerytest() {
        this.callFunction("jquerytest");
    }
    
    public void openmenu() {
        this.callFunction("openmenu");
    }



}
