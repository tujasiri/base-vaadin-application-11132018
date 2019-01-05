// JavaScript timer component 
var timer;

function TimerComponent(element) {
	this._time = 0;
	this._elem = element;
	this._updateTime();
	
	this._elem.innerHTML = 
	/*dialog div*/	
	"<div id=\"dialog\" class=\"dialog hideonload zeroheightwidth\" title=\"Basic dialog\"> " +
	"<p>This is the default dialog which is useful for displaying information. " +
	"The dialog window can be moved, resized and closed with the 'x' icon.</p>" +
	"</div>"+
	/*menu div*/
	"<div id=\"menudiv\" class=\"hideonload zeroheightwidth\">"+
		"<ul id=\"menu\" class=\"menu\">"+
	    "<li class=\"ui-state-disabled\"><div>Toys (n/a)</div></li>"+
	    "<li><div>Books</div></li>"+
	    "<li><div>Clothing</div></li>"+
	    "<li><div>Electronics</div></li>"+
	    "</ul>"+
    "</div>";
//    "</div>";
	/*
     " <li class=\"ui-state-disabled\"><div>Home Entertainment</div></li>"+
      "<li><div>Car Hifi</div></li>"+
      "<li><div>Utilities</div></li>"+
   " </ul>"+
  "</li>"+
  "<li><div>Movies</div></li>"+
  "<li><div>Music</div>"+
   " <ul>"+
    "  <li><div>Rock</div>"+
     "   <ul>"+
      "    <li><div>Alternative</div></li>"+
       "   <li><div>Classic</div></li>"+
        "</ul>"+
      "</li>"+
      "<li><div>Jazz</div>"+
       " <ul>"+
        "  <li><div>Freejazz</div></li>"+
         " <li><div>Big Band</div></li>"+
          "<li><div>Modern</div></li>"+
        "</ul>"+
      "</li>"+
      "<li><div>Pop</div></li>"+
    "</ul>"+
  "</li>"+
  "<li class=\"ui-state-disabled\"><div>Specials (n/a)</div></li>"+
"</ul>"+
"</div>" ;
*/
	
	$(document).ready( function() {
	    $( "#dialog" ).dialog({
	      autoOpen: false,
	      show: {
	        effect: "blind",
	        duration: 1000
	      },
	      hide: {
	        effect: "explode",
	        duration: 1000
	      },
	      modal: true
	    });
	    
		 
//		 $( "#menu" ).hide();
//		 $( "#menudiv" ).hide();
//		 $( "#menux" ).css({
//			 "display":"none",
//			 "width":"auto",
//			 "position":"absolute"
//			 });
//		 
//		 $( "#menu" ).menu({
		 $( ".menu" ).menu({
		    	collapseAll: {
		    		all: true
		    	},
		    	modal: true,
		    	show: {
			        effect: "blind",
			        duration: 1000
			    }
		});
	    
	
	});
	
	$( ".v-menubar" ).click(function() {
		  if(!$(".v-menubar-submenu").is(":animated"))
			  $(".v-menubar-submenu").finish().effect("bounce",400);
		  
	});

	$( ".v-menubar-popup").mouseover(function() {
		console.log("helloski");
		  if(!$(".v-menubar-submenu").is(":animated"))
			  $(".v-menubar-submenu").finish().effect("bounce",400);
	});
	
	$( ".v-menubar-menuitem").mouseover(function() {
		console.log("helloski");
		  if(!$(".v-menubar-submenu").is(":animated"))
			  $(".v-menubar-submenu").finish().effect("bounce",400);
	});
	    
};
// Starts the timer
TimerComponent.prototype.start = function() {
	if (!this._timer) {
		var self = this;
		this._timer = setInterval(function() {
			return self._tick();
		}, 1000);
		window.alert("here");
	}
};
// Stops the timer
TimerComponent.prototype.stop = function() {
	if (this._timer) {
		clearInterval(this._timer);
		this._timer = null;
	}
};
// Resets the timer to 0
TimerComponent.prototype.reset = function() {
	this._time = 0;
	this._updateTime();
};
// empty for hooking external tick listener
TimerComponent.prototype.onTick = function() {
	// no op
};
// handle tick internally
TimerComponent.prototype._tick = function() {
	this._time++;
	this._updateTime();
	this.onTick(this._time);
};
// handle time label update internally
TimerComponent.prototype._updateTime = function() {
//	this._elem.innerHTML = "Time elapsed: " + this._time + " seconds";
	
};

TimerComponent.prototype.alertme = function() {
	console.log("alert called!");
	window.alert("alert alert alert!");
};

TimerComponent.prototype.jquerytest = function() {
	$( function() {
	      $( "#dialog" ).dialog( "open" );
	 } );
};

TimerComponent.prototype.openmenu= function() {
	$( function() {
		$( "#menudiv" ).show();
	    $( "#menu" ).menu();
	});
};





