//function rotate360() {
jQuery.fn.rotate360 = function () {
	
	//window.alert("rotate");
	console.log("rotate called");

	var options1 = {
		yourDirectory1: "images", //dir of images to 'rotate' through
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
		$("#bg_smooth").attr("src", options1.yourDirectory1.toLowerCase() + '/a_360_' + prev_image + '.jpg');
		$(this).css("background-image", "url(" + options1.yourDirectory1.toLowerCase() + "/a_360_" + number + ".jpg)");
	}

	function autoplaySliderAndImages() {
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