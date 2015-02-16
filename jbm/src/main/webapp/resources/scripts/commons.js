function pad(num, size) {
	var s = num + "";
	while (s.length < size)
		s = "0" + s;
	return s;
}

function getTimeFormat(hour, minute, ampm) {
	var hourStr = pad(hour, 2);
	var minuteStr = pad(minute, 2);
	return hourStr + ":" + minuteStr + " " + ampm;
}

function getTimeFormatWithoutMin(hour, ampm) {
	var minute = "0";
	if(Math.floor(hour) != hour) {
		hour = Math.floor(hour);
		minute = "30";
	}
	var hourStr = pad(hour, 2);
	var minuteStr = pad(minute, 2);
	return hourStr + ":" + minuteStr + " " + ampm;
}

function getStartDateArr() {
	var dateArr = new Array();
	var index = 0;
	for (hour = 0; hour < 24; hour++) {
		if (hour == 0) {
			dateArr[index++] = getTimeFormat(hour+12, 0, "AM");
			dateArr[index++] = getTimeFormat(hour+	12, 30, "AM");
		} else if (hour < 12) {
			dateArr[index++] = getTimeFormat(hour, 0, "AM");
			dateArr[index++] = getTimeFormat(hour, 30, "AM");
		} else if (hour == 12) {
			dateArr[index++] = getTimeFormat(hour, 0, "PM");
			dateArr[index++] = getTimeFormat(hour, 30, "PM");
		} else {
			dateArr[index++] = getTimeFormat(hour - 12, 0, "PM");
			dateArr[index++] = getTimeFormat(hour - 12, 30, "PM");
		}
	}
	return dateArr;
}

/*function getEndDateArr(startHr, startMin, diff) {
	var dateArr = new Array();
	var index = 0;
	var endHr = startHr + diff + 12;
	for (hour = (startHr + diff); hour < endHr; hour++) {
		if (hour < 12) {
			if (startMin == 0) {
				dateArr[index++] = getTimeFormat(hour, 0, "AM");
				dateArr[index++] = getTimeFormat(hour, 30, "AM");
			} else {
				dateArr[index++] = getTimeFormat(hour, 30, "AM");
				dateArr[index++] = getTimeFormat(hour + 1, 0, "AM");
			}

		} else if (hour == 12) {
			if (startMin == 0) {
				dateArr[index++] = getTimeFormat(hour, 0, "PM");
				dateArr[index++] = getTimeFormat(hour, 30, "PM");
			} else {
				dateArr[index++] = getTimeFormat(hour, 30, "PM");
				dateArr[index++] = getTimeFormat(hour-11, 0, "PM");
			}
		} else if (hour > 12 && hour <= 23) {
			if (startMin == 0) {
				dateArr[index++] = getTimeFormat(hour - 12, 0, "PM");
				dateArr[index++] = getTimeFormat(hour - 12, 30, "PM");
			} else {
				dateArr[index++] = getTimeFormat(hour - 12, 30, "PM");
				dateArr[index++] = getTimeFormat(hour - 11, 0, "PM");
			}
		} else if (hour == 24) {
			if (startMin == 0) {
				dateArr[index++] = getTimeFormat(hour - 12, 0, "AM");
				dateArr[index++] = getTimeFormat(hour - 12, 30, "AM");
			} else {
				dateArr[index++] = getTimeFormat(hour - 12, 30, "AM");
				dateArr[index++] = getTimeFormat(hour - 23, 0, "AM");
			}
		} 
	}
	return dateArr;
}*/

function getEndDateArr(startHr, startMin, diff, frmAmPm) {
	var dateArr = new Array();
	var index = 0;
	
	var hour = parseFloat(startHr) + parseFloat(diff);
	
	if(startMin != "0") {
		hour = hour + .5;
	}
	if(frmAmPm == "PM" && startHr != 12 && startHr != 12.5) {
		hour = hour + 12;
	}
	var endHr = hour + 12;

	
	while (hour < endHr) {
		var toHr = "";
		var ampm = "";
		if(hour < 12) {
			toHr = hour;
			ampm = "AM";
		}
		if(hour == 12 || hour == 12.5) {
			toHr = hour;
			ampm = "PM";
		}
		
		if(hour >= 13 && hour < 24) {
			toHr = hour-12;
			ampm = "PM";
		}
		
		if(hour == 24 || hour == 24.5) {
			toHr = hour - 12;
			ampm = "AM";
		}
		
		if(hour >= 25 && hour < 36) {
			toHr = hour - 24;
			ampm = "AM";
		}
		
		
		
		dateArr[index++] = getTimeFormatWithoutMin(toHr, ampm);
		
		hour = hour+0.5;
	}
	return dateArr;
}

function zeroPad(num, places) {
	var zero = places - num.toString().length + 1;
	return Array(+(zero > 0 && zero)).join("0") + num;
}

function calculateTimeDifference(startTime, endTime) {
	
	var timeDiff = 0;
	
	var startHr = parseInt(startTime.substr(0, 2));
	var startMin = parseInt(startTime.substr(3, 2));
	var startAmPm = startTime.substr(6, 2);

	var endHr = parseInt(endTime.substr(0, 2));
	var endMin = parseInt(endTime.substr(3, 2));
	var endAmPm = endTime.substr(6, 2);

	if(startHr == 12) {		
		if(startAmPm == "AM") {
			startHr = startHr - 12;
		}
	} else if(startHr > 12) {
		startHr = startHr + 12;
	}
	
	
	if(startMin == 30) {
		startHr = startHr + .5;
	}
	
	if(endHr == 12) {		
		if(endAmPm == "AM") {
			endHr = endHr - 12;
		}
	} else if(endHr < 12) {
		if(endAmPm == "PM") {
			endHr = endHr + 12;
		}
	} else {
		endHr = endHr + 12;
	}
	
	if(endMin == 30) {
		endHr = endHr + .5;
	}
	
	if(startAmPm == "PM" && endAmPm == "AM") {
		endHr = endHr + 12;
		timeDiff = endHr - startHr;
	} else {
		timeDiff = endHr - startHr;		
	}
	return timeDiff;
		
}

function addTime(startTime, hours) {
	
	var startHr = parseInt(startTime.substr(0, 2));
	var startMin = parseInt(startTime.substr(3, 2));
	var startAmPm = startTime.substr(6, 2);

	var endHr = 0;
	var endMin = 0;
	var endAmPm = "";
		
	if(startHr == 12) {		
		if(startAmPm == "AM") {
			startHr = startHr - 12;
		} else {
			
		}
	} else if(startHr < 12) {
		if(startAmPm == "PM") {
			startHr = startHr + 12;
		}
	} 
	
	if(startMin == 30) {
		startHr = startHr + .5;
	}
	
	endHr = startHr+hours;
	//alert(startHr +" : "+endHr);
	if(parseInt(endHr) < 12) {
		endAmPm = "AM";
	} else if(endHr == 12 || endHr ==12.5 ) {
		if(startAmPm == "AM") {
			endAmPm = "PM";
		} else {
			endAmPm = "AM";
		}		
	} else if(endHr == 24 || endHr == 24.5)  {
		endHr = endHr - 12;
		endAmPm = "AM";
	} else if(endHr > 24)  {
		endHr = endHr - 24;
		endAmPm = "AM";
	}
	else {
		endHr = endHr -12;
		endAmPm = "PM";
	}
	
	if(Math.floor(endHr) != endHr) {
		endHr = Math.floor(endHr);
		endMin = "30";
	}
	
	var endTime = pad(endHr,2)+":"+pad(endMin,2)+" "+endAmPm;
	
	
	return endTime;
}

