package com.vaadin.root.jscomponent;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.Notification;
import javax.xml.*;

//import elemental.json.JsonArray;
import elemental.json.*;

//@JavaScript({ "Timer.js", "TimerComponentConnector.js", "jquery-1.12.4.js", "jquery-ui.js" })
@JavaScript({ "Timer.js", "TimerComponentConnector.js", "jquery-1.12.4.js", "jquery-ui.js", "jquery.rotate360.js" })
public class TimerComponent extends AbstractJavaScriptComponent {

    private static final long serialVersionUID = -4318074441748905804L;
    private Long screenWidth;
    private Long screenHeight;

    /**
     * Creates a new instance of timer component
     * 
     */
    public TimerComponent() {
        registerFunctions();
    }

    public Long getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(Long screenWidth) {
		this.screenWidth = screenWidth;
	}

	public Long getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(Long screenHeight) {
		this.screenHeight = screenHeight;
	}

	/**
     * Registers functions that can be called from the client side
     * 
     */
    private void registerFunctions() {
//        this.addFunction("timeout", new JavaScriptFunction() {
//            private static final long serialVersionUID = -6857196720604898163L;
//
//            @Override
//            public void call(JsonArray arguments) {
//                Notification.show("Time is up!!");
//            }
//        });
        
        this.addFunction("senddimensions", new JavaScriptFunction() {
            private static final long serialVersionUID = -6857196720604898164L;

            @Override
            public void call(JsonArray arguments) {
                Notification.show(arguments.asString());
//            	String jsonString = arguments.asString();
//                Notification.show(jsonString);
//                Notification.show("===>"+arguments.getObject(0).get("height").asNumber());
                System.out.println("height===>"+Json.parse(arguments.get(0).asString()).getNumber("height"));
                System.out.println("width===>"+Json.parse(arguments.get(0).asString()).getNumber("width"));
               
               setScreenHeight(Double.doubleToLongBits(Json.parse(arguments.get(0).asString()).getNumber("height")));
               setScreenWidth(Double.doubleToLongBits(Json.parse(arguments.get(0).asString()).getNumber("width")));
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
    
    public void getdimensions() {
        this.callFunction("getdimensions");
    }




}
