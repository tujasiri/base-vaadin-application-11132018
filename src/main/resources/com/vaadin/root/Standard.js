// JavaScript standardcomp component 
var standardcomp;

function StandardComponent(element) {
	this._time = 0;
	this._elem = element;
	this._updateTime();
};
// Starts the standardcomp
StandardComponent.prototype.start = function() {
	if (!this._standardcomp) {
		var self = this;
		this._standardcomp = setInterval(function() {
			return self._tick();
		}, 1000);
		window.alert("here");
	}
};
// Stops the standardcomp
StandardComponent.prototype.stop = function() {
	if (this._standardcomp) {
		clearInterval(this._standardcomp);
		this._standardcomp = null;
	}
};
// Resets the standardcomp to 0
StandardComponent.prototype.reset = function() {
	this._time = 0;
	this._updateTime();
};
// empty for hooking external tick listener
StandardComponent.prototype.onTick = function() {
	// no op
};
// handle tick internally
StandardComponent.prototype._tick = function() {
	this._time++;
	this._updateTime();
	this.onTick(this._time);
};
// handle time label update internally
StandardComponent.prototype._updateTime = function() {
	this._elem.innerHTML = "Time elapsed: " + this._time + " seconds";
};

StandardComponent.prototype.alertme = function() {
	this.alert("alertme here");
	window.alert("window lertme here");
	
};
