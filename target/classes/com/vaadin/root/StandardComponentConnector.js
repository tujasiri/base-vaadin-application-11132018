window.com_vaadin_root_StandardComponent = function() {
	// Create the component
	var standardcomp = new StandardComponent(this.getElement());
	// Handle state changes from server
	this.onStateChange = function() {
		if (this.getState().started) {
			standardcomp.start();
		} else {
			standardcomp.stop();
		}
	};

	// called from server
	this.reset = function() {
		standardcomp.reset();
	};
	
	this.alertme = function(){
		standardcomp.alertme();
	};

	self = this;

	// handle standardcomp ticks
	standardcomp.onTick = function(time) {
		if (time > 0 && time % 10 == 0) {
			standardcomp.stop();
			self.timeout();
		}
	};

};
