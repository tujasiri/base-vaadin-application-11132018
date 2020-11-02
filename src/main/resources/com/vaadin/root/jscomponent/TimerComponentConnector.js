window.com_vaadin_root_jscomponent_TimerComponent = function() {
	// Create the component
	var timer = new TimerComponent(this.getElement());
	// Handle state changes from server
	this.onStateChange = function() {
		if (this.getState().started) {
			timer.start();
		} else {
			timer.stop();
		}
	};

	// called from server
	this.reset = function() {
		timer.reset();
	};
	
	this.alertme = function(){
		timer.alertme();
	};
	
	this.jquerytest = function(){
		timer.jquerytest();
	};
	
	this.openmenu= function(){
		timer.openmenu();
	};
	
	self = this;
	
	this.getdimensions= function(){
		
		timer.getdimensions();
		
		
		var screenHeight = window.innerHeight;
		var screenWidth = window.innerWidth;
		
		var json = {
				height:screenHeight,
				width:screenWidth
		};
		
		self.senddimensions(JSON.stringify(json));
	};
	

	// handle timer ticks
	timer.onTick = function(time) {
		if (time > 0 && time % 10 == 0) {
			timer.stop();
			self.timeout();
		}
	};
	//***************************************

	//***************************************

};
