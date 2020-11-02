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
    "</div>"+
	/*360 div*/
	"<div id=\"main_image_container\" class=\"main_image_container\">"+
		 "<img id=\"bg_smooth\" src=\"VAADIN/themes/standardtheme/images/A_360_05.jpg\">"+
		  "<span id=\"progress_bar\"></span>"+
		  "<div class=\"controls\">"+
			"<div id=\"slider\" class=\"slider ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content\"><span tabindex=\"0\" class=\"ui-slider-handle ui-corner-all ui-state-default\" style=\"left: 0%;\"></span>"+
			"<button class=\"play_btn ui-button ui-corner-all ui-widget\">" +
				"<span class=\"ui-button-icon ui-icon ui-icon-play\"></span>" +
				"<span class=\"ui-button-icon-space\"></span>" +
			"</button>"+
			"<button class=\"pause_btn ui-button ui-corner-all ui-widget\">" +
				"<span class=\"ui-button-icon ui-icon ui-icon-pause\"></span>" +
				"<span class=\"ui-button-icon-space\"> </span>" +
			"</button>"+
		 "</div>"+
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
		
		/************************************/
$.fn.rotate360 = function () {
	
	//window.alert("rotate");

	var options1 = {
		//yourDirectory1: "images", //dir of images to 'rotate' through
		yourDirectory1: "VAADIN/themes/standardtheme/images", //dir of images to 'rotate' through
		transition_speed: 110,
	}

	var interval_controller = null;

	var self = this;
	var current_image_being_preloaded;
	var image_preloader_controller = 0;


	$("#slider").slider({ //jQuery UI slider widget, here using this method:
		//$(selector, context).slider (options)
		//(see api.jqueryui.com/slider/#method-option)
		max: 36,
		min: 1,
		value: 1,
		animate: true
	});

	function change_image(number) {
		var prev_image = Number(number) - 1;
		prev_image = prev_image == 0 ? 1 : prev_image;
		prev_image = prev_image < 10 ? "0" + prev_image : prev_image;
		//$("#bg_smooth").attr("src", options1.yourDirectory1.toLowerCase() + '/a_360_' + prev_image + '.jpg');
		//$(this).css("background-image", "url(" + options1.yourDirectory1.toLowerCase() + "/a_360_" + number + ".jpg)");
		$("#bg_smooth").attr("src", options1.yourDirectory1 + '/A_360_' + prev_image + '.jpg');
		$(this).css("background-image", "url(" + options1.yourDirectory1 + "/A_360_" + number + ".jpg)");
	}

	function autoplaySliderAndImages() {
		//window.alert("in autoplaySliderAndImages");
		interval_controller = setTimeout(function () { //setTimeout() method calls a function or evaluates an expression after a specified number of milliseconds
			var value = $("#slider").slider("option", "value"); //gets value of slider (see api.jqueryui.com/slider/#option-value)
			var max_value = $("#slider").slider("option", "max"); //gets max of slider (see api.jqueryui.com/slider/#option-value)
			if (value < max_value) { //up until slider has reached end,
				value = (value + 1) < 10 ? "0" + (value + 1) : value + 1; //increase value by 1
				$("#slider").slider("option", "value", Number(value)); //move handle to current value
				change_image(value); //display image corresponding to current value
				autoplaySliderAndImages(); //go back to beginning of autoplaySliderAndImages() function,
				//i.e., repeat previous steps until value = max_value
			}
		}, options1.transition_speed); //options1.transition_speed determines the number of milliseconds that elapse before function executes
		var valueT = $("#slider").slider("option", "value");
		var max_valueT = $("#slider").slider("option", "max");
		if (valueT == max_valueT) {
			clearTimeout(interval_controller);
			$("#slider").slider("option", "value", 1); //if slider has reached end, interval_controller will not execute,
			//and slider value will be set to 1 ('autoplaying' stops, and slider goes back to left side)
		}
	}

	change_image("01"); //this fires change_image function (see above), passing "01" (or whatever is in quotes) as argument via the nmbr parameter
	//this function call determines the picture that is displayed 1st, before any sliding/autoplaying is done

	$('body').on("slide", "#slider", function (event, ui) { //'slide' event defined in jquery-ui.js (from jqueryui site)
		var value = ui.value;
		change_image(value < 10 ? "0" + value : value);
	});

	/**play button */
	$(".play_btn").button({
		icon: "ui-icon-play"
	});
	$('body').on("click", ".play_btn", function () { //fires autoplaySliderAndImages() when .play_btn clicked
		autoplaySliderAndImages();
	})
	/**play button */


	/**pause button */
	$(".pause_btn").button({
		icon: "ui-icon-pause"
	});
	$('body').on("click", ".pause_btn", function () {
		clearTimeout(interval_controller);
	});
	/**pause button */
}

		/*************************************/
		/*
		$("#slider").slider({ //jQuery UI slider widget, here using this method:
			//$(selector, context).slider (options)
			//(see api.jqueryui.com/slider/#method-option)
			max: 36,
			min: 1,
			value: 1,
			animate: true
		});	
		*/
		
		$( "#main_image_container" ).dialog({
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
	    
		$("#main_image_container").rotate360();
		 
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
	      //$( "#dialog" ).dialog( "open" );
		  alert("JS INVOKED");
		$("#main_image_container").dialog("open");
		$("#main_image_container").show();
	      
	 });
};

TimerComponent.prototype.openmenu= function() {
	$( function() {
		$( "#menudiv" ).show();
	    $( "#menu" ).menu();
	});
	
};

TimerComponent.prototype.getdimensions= function() {
//	$( function() {
//	});
};





