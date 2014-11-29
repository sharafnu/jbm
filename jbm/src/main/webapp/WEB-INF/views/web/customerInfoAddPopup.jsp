<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
      #map-canvas {
      	width:700px;
      	height:640px;
        margin: 0px;
        padding: 0px
      }
      .controls {
        margin-top: 16px;
        border: 1px solid transparent;
        border-radius: 2px 0 0 2px;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        height: 32px;
        outline: none;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
      }

      #pac-input {
        background-color: #fff;
        padding: 0 11px 0 13px;
        width: 350px;
        font-family: Roboto;
        font-size: 15px;
        font-weight: 300;
        text-overflow: ellipsis;
      }

      #pac-input:focus {
        border-color: #4d90fe;
        margin-left: -1px;
        padding-left: 14px;  /* Regular padding-left + 1. */
        width: 401px;
      }

      .pac-container {
        font-family: Roboto;
		    background-color: #FFF;
		    z-index: 18000;
		    position: fixed;
		    display: inline-block;
		    float: left;
		}

      #type-selector {
        color: #fff;
        background-color: #4d90fe;
        padding: 5px 11px 0px 11px;
      }

      #type-selector label {
        font-family: Roboto;
        font-size: 13px;
        font-weight: 300;
      }
}

    </style>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places"></script>
<script>
// This example adds a search box to a map, using the Google Place Autocomplete
// feature. People can enter geographical searches. The search box will return a
// pick list containing a mix of places and predicted search terms.

var residenceAddressMarker = null;
var officeAddressMarker = null;

var map = null;
try {
var infowindow = new google.maps.InfoWindow(
		  { 
		    size: new google.maps.Size(200,100)
		  });

} catch(ex) {
	
}
// A function to create the marker and set up the event window function 
function placeMarker(event) {
	var residenceAddressSelected = $('#residenceAddressRadio').jqxRadioButton('checked'); 
	var officeAddressSelected = $('#officeAddressRadio').jqxRadioButton('checked');

	if(residenceAddressSelected) {
		//alert(event.latLng);
	  	if ( residenceAddressMarker ) {
	  		residenceAddressMarker.setPosition(event.latLng);
	  	} else {
	  		residenceAddressMarker = new google.maps.Marker({
				position: event.latLng,
				map: map,
				icon: homeIcon
	    	});
	  	}
	  google.maps.event.addListener(residenceAddressMarker, 'click', function() {
	  		infowindow.setContent("<b>Residence</b><br>Building : "+ $("#residenceBuildingName").val()+"<br>Flat No: "+ $("#residenceFlatNo").val()+"<br>Landmark: "+ $("#residenceRemarks").val()); 
			infowindow.open(map,residenceAddressMarker);
		});
		google.maps.event.trigger(residenceAddressMarker, 'click');
		$("#residenceLatitude").val(event.latLng.lat());
		$("#residenceLongitude").val(event.latLng.lng());
	} else if(officeAddressSelected) {
		//alert(event.latLng);
	  	if ( officeAddressMarker ) {
	  		officeAddressMarker.setPosition(event.latLng);
	  	} else {
	  		officeAddressMarker = new google.maps.Marker({
				position: event.latLng,
				map: map,
				icon: officeIcon
	    	});
	  	}
	  	google.maps.event.addListener(officeAddressMarker, 'click', function() {
	  		infowindow.setContent("<b>Office</b><br>Building : "+ $("#officeBuildingName").val()+"<br>Flat No: "+ $("#officeFlatNo").val()+"<br>Landmark: "+ $("#officeRemarks").val()); 
			infowindow.open(map,officeAddressMarker);
		});
		google.maps.event.trigger(officeAddressMarker, 'click');
		$("#officeLatitude").val(event.latLng.lat());
		$("#officeLongitude").val(event.latLng.lng());
	}
	
}

var homeIcon = '<c:url value="/resources/images/residence-icon.png" />';
var officeIcon = '<c:url value="/resources/images/office-building-icon.png" />';
var service;



function initialize() {

  try {	
  var markers = [];
  map = new google.maps.Map(document.getElementById('map-canvas'), {
    mapTypeId: google.maps.MapTypeId.ROADMAP
  });

  service = new google.maps.places.PlacesService(map);
  
  var defaultBounds = new google.maps.LatLngBounds(
      new google.maps.LatLng(25.27114, 55.30748),
      new google.maps.LatLng(25.26114, 55.39748));
  
  map.setZoom(8);
  map.fitBounds(defaultBounds);
  
  // Create the search box and link it to the UI element.
  var input = /** @type {HTMLInputElement} */(
      document.getElementById('pac-input'));
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

  var searchBox = new google.maps.places.SearchBox(
    /** @type {HTMLInputElement} */(input));

  // [START region_getplaces]
  // Listen for the event fired when the user selects an item from the
  // pick list. Retrieve the matching places for that item.
  google.maps.event.addListener(searchBox, 'places_changed', function() {
    var places = searchBox.getPlaces();

    if (places.length == 0) {
      return;
    }
    for (var i = 0, marker; marker = markers[i]; i++) {
      marker.setMap(null);
    }

    // For each place, get the icon, place name, and location.
    markers = [];
    var bounds = new google.maps.LatLngBounds();
    for (var i = 0, place; place = places[i]; i++) {
      /* var image = {
        url: place.icon,
        size: new google.maps.Size(71, 71),
        origin: new google.maps.Point(0, 0),
        anchor: new google.maps.Point(0, 0),
        scaledSize: new google.maps.Size(35, 35)
      }; */
      createMarker(place);
      // Create a marker for each place.
      /* var marker = new google.maps.Marker({
        map: map,
        icon: image,
        title: place.name,
        position: place.geometry.location
      });

      google.maps.event.addListener(marker, 'click', function() {
	  		infowindow.setContent(marker.getTitle()); 
			infowindow.open(map,marker);
	  }); */
      
      markers.push(marker);

      bounds.extend(place.geometry.location);
    }
    
    map.fitBounds(bounds);
    map.setZoom(16);
  });
  // [END region_getplaces]

  // Bias the SearchBox results towards places that are within the bounds of the
  // current map's viewport.
  google.maps.event.addListener(map, 'bounds_changed', function() {
    var bounds = map.getBounds();
    searchBox.setBounds(bounds);
  });
  
 google.maps.event.addListener(map, 'click', function(event) {
	  placeMarker(event);
	});
  
 /* google.maps.event.addListener(map, 'click', function() {
      infowindow.close();
      }); 

  google.maps.event.addListener(map, 'click', function(event) {
	  alert(event.latLng);
	});*/
} catch(exc)
{
}
}

//google.maps.event.addDomListener(window, 'load', initialize);


function createMarker(place) {
	var image = {
	        url: place.icon,
	        size: new google.maps.Size(71, 71),
	        origin: new google.maps.Point(0, 0),
	        anchor: new google.maps.Point(0, 0),
	        scaledSize: new google.maps.Size(35, 35)
	      };
	
    var placeLoc = place.geometry.location;
    var marker = new google.maps.Marker({
      map: map,
      icon: image,
      position: place.geometry.location
    });

    var request = { reference: place.reference };
    service.getDetails(request, function(place, status) {
        if (status == google.maps.places.PlacesServiceStatus.OK) {
          var contentStr = '<h5>'+place.name+'</h5><p>'+place.formatted_address;
          //if (!!place.formatted_phone_number) contentStr += '<br>'+place.formatted_phone_number;
         // if (!!place.website) contentStr += '<br><a target="_blank" href="'+place.website+'">'+place.website+'</a>';
          contentStr += '<br>'+place.types+'</p>';
          infowindow.setContent(place.formatted_address);
          infowindow.open(map,marker);
          
        	var residenceAddressSelected = $('#residenceAddressRadio').jqxRadioButton('checked'); 
    		var officeAddressSelected = $('#officeAddressRadio').jqxRadioButton('checked');
    		if(residenceAddressSelected) {
    			$("#residenceBuildingName").val(place.name);
    		 	$("#residenceRemarks").val(place.formatted_address);
    		 	$("#residenceLatitude").val(place.geometry.location.lat());
    			$("#residenceLongitude").val(place.geometry.location.lng());
    		}  else if(officeAddressSelected) {
    			$("#officeBuildingName").val(place.name);
    		 	$("#officeRemarks").val(place.formatted_address);
    		 	$("#officeLatitude").val(place.geometry.location.lat());
    			$("#officeLongitude").val(place.geometry.location.lng());
    		}
        } else { 
          //var contentStr = "<h5>No Result, status="+status+"</h5>";
          //infowindow.setContent(contentStr);
          //infowindow.open(map,marker);
        }
      });
  }

function callback(results, status) {
    if (status == google.maps.places.PlacesServiceStatus.OK) {
      for (var i = 0; i < results.length; i++) {
        createMarker(results[i]);
      }
    }
  }
  
$(document).ready(function() {
	//google.maps.event.addDomListener(window, 'load', initialize);
	
	var $selector = '.gm-style-iw';
	//var $selector = '.gm-title';
	//var $selector1 = '.gm-addr';
	
	$(document).on('click', $selector, function(e){
	  //var $html      = $(this).html();
		//alert($(this).text());
		var buildingName = $(this).find(".gm-title").text();
		var address = $(this).find(".gm-addr").text();
	  
		var residenceAddressSelected = $('#residenceAddressRadio').jqxRadioButton('checked'); 
		var officeAddressSelected = $('#officeAddressRadio').jqxRadioButton('checked');

		if(residenceAddressSelected) {
			//alert(event.latLng);
		 	$("#residenceBuildingName").val(buildingName);
		 	$("#residenceRemarks").val(address);
		} else if(officeAddressSelected) {
			//alert(event.latLng);		  	
			$("#officeBuildingName").val(buildingName);
		 	$("#officeRemarks").val(address);
		}
		
		//alert($html.find(".gm-title"));
		
		//alert($html.find('.gm-title'));
	   //your code
	});
		
	$('#addCustomerPopupWindow').jqxWindow({
        showCollapseButton: false, maxHeight: 700, maxWidth: 1100, minHeight: 700, minWidth: 1100, height: 700, width: 1100,
        autoOpen: false,
        initContent: function () {
           // $('#tab').jqxTabs({ height: '100%', width:  '100%' });
            $('#addCustomerPopupWindow').jqxWindow('focus');
            //google.maps.event.trigger(map, 'resize');
            initialize();
        }
    });
	
	$('#addCustomerPopupWindow').on('open', function (event) {
			$("#pac-input").val("");
			google.maps.event.trigger(map, 'resize');
			infowindow.close();
			var defaultBounds = new google.maps.LatLngBounds(
				      new google.maps.LatLng(25.27114, 55.30748),
				      new google.maps.LatLng(25.26114, 55.39748));
				  
				  map.setZoom(8);
				  map.fitBounds(defaultBounds);
		//}
	});
	
	//$('#jqxWindow').jqxWindow({ modalZIndex: 99999});
	$("#createCustomerExpander").jqxExpander({   width: '380px', showArrow:false });
	$("#customerResidenceAddressExpander").jqxExpander({   width: '380px', showArrow:false });
	$("#customerOfficeAddressExpander").jqxExpander({   width: '380px', showArrow:false });
	
	$('#addCustomerPopupWindow').on('close', function (event) { 
		infowindow.close();
	});
	
	setupCustomerAddPopupForm();
	
	
});


function setupCustomerAddPopupForm() {
	
	 
	$("#residenceAddressRadio").jqxRadioButton({ width: 250, height: 25, theme:'fresh'});
	$("#officeAddressRadio").jqxRadioButton({ width: 250, height: 25, theme:'fresh'});
	var areaListUrl = "areaListJSON.html";
	var areaListSource = {
		datatype : "json",
		datafields : [ {
			name : 'areaName',
			type : 'string'
		}, {
			name : 'comboBoxText',
			type : 'string'
		}, {
			name : 'areaId',
			type : 'int'
		} ],
		id : 'areaId',
		url : areaListUrl
	};
	var areaListDataAdapter = new $.jqx.dataAdapter(areaListSource);

	$("#formAreaIdResidence").jqxComboBox({
		selectedIndex : -1,
		source : areaListDataAdapter,
		displayMember : "comboBoxText",
		valueMember : "areaId",
		width : 210,
		height : 20,
		renderSelectedItem: function(index, item) {
			var item = areaListDataAdapter.records[index];
			return item.areaName;   
        }
	});
	
	$("#formAreaIdOffice").jqxComboBox({
		selectedIndex : -1,
		source : areaListDataAdapter,
		displayMember : "comboBoxText",
		valueMember : "areaId",
		width : 210,
		height : 20,
		renderSelectedItem: function(index, item) {
			var item = areaListDataAdapter.records[index];
			return item.areaName;   
        }
	});
	
	// Create jqxInput.
	$("#firstName").jqxInput({
		width : '230px',
		height : '20px'
	});
	$("#lastName").jqxInput({
		width : '230px',
		height : '20px'
	});
	
	$("#mobile1Prefix").jqxInput({
		width : '40px',
		height : '20px',
		disabled: true
	});
	$("#mobile1").jqxInput({
		width : '170px',
		height : '20px'
	});
	
	$("#mobile2").jqxInput({
		width : '170px',
		height : '20px'
	});
	$("#mobile2Prefix").jqxInput({
		width : '40px',
		height : '20px',
		disabled: true
	});
	$("#landlinePrefix").jqxInput({
		width : '40px',
		height : '20px',
		disabled: true
	});
	$("#landline").jqxInput({
		width : '170px',
		height : '20px'
	});
	$("#email").jqxInput({
		width : '230px',
		height : '20px'
	});
	$("#preferenceCallElement").jqxCheckBox({
		width : 80,
		height : 25
	});
	$("#preferenceEmailElement").jqxCheckBox({
		width : 80,
		height : 25
	});
	$("#preferenceSmsElement").jqxCheckBox({
		width : 80,
		height : 25
	});
	
	$("#residenceBuildingName").jqxInput({
		width : '230px',
		height : '20px'
	});
	
	$("#residenceFlatNo").jqxInput({
		width : '230px',
		height : '20px'
	});
	
	$("#residenceRemarks").jqxInput({
		width : '230px',
		height : '20px'
	});
	
	$("#officeBuildingName").jqxInput({
		width : '230px',
		height : '20px'
	});
	
	$("#officeFlatNo").jqxInput({
		width : '230px',
		height : '20px'
	});
	$("#officeRemarks").jqxInput({
		width : '230px',
		height : '20px'
	});
	
	var createCustomerButton = $("#createCustomerButton").jqxButton({
		theme : theme
	});
	
	createCustomerButton.click(function (event) {
    	
		var residenceAreaId = -1;
		var officeAreaId = -1;
		
		var callPref = 0;
    	var emailPref = 0;
    	var smsPref = 0;
    	
		var residenceAreaItem = $("#formAreaIdResidence").jqxComboBox('getSelectedItem');
    	var officeAreaItem = $("#formAreaIdOffice").jqxComboBox('getSelectedItem');
    	
    	if(residenceAreaItem  != null) {
    		residenceAreaId = residenceAreaItem.value;
		}
    	
    	if(officeAreaItem  != null) {
    		officeAreaId = officeAreaItem.value;
		}
    	
    	var residenceLatitude = $("#residenceLatitude").val();
    	var residenceLongitude = $("#residenceLongitude").val();
    	var officeLatitude = $("#officeLatitude").val();
    	var officeLongitude = $("#officeLongitude").val();
    	
    	var callChecked = $('#preferenceCallElement').jqxCheckBox('checked');
		var emailChecked = $('#preferenceEmailElement').jqxCheckBox('checked');
		var smsChecked = $('#preferenceSmsElement').jqxCheckBox('checked');
		
		if(callChecked) {
			callPref  = 1;	
		}
		if(emailChecked) {
			emailPref  = 1;
		}
		if(smsChecked) {
			smsPref  = 1;
		}
		
		//Do customer mandatory checks
	
		if($("#firstName").val() == null || $("#firstName").val() == "") {
       		alert("Please enter customer first name !");
       		return;
       	}
		
		if($("#mobile1").val() == null || $("#mobile1").val() == "") {
       		alert("Please enter customer mobile number 1!");
       		return;
       	}
		
		//Check whether either of the addess is selected
		if(residenceAreaId == -1 && officeAreaId == -1) {
	    	if((residenceLatitude == null || residenceLatitude == "") && (officeLatitude == null || officeLongitude == "")) {
	    		alert("Please choose address from map or select area from drop down !");
				return;
	    	}				
		}
		
		if(residenceAreaId != -1) {
			if($("#residenceBuildingName").val() == null || $("#residenceBuildingName").val() == "") {
				alert("Please enter resident building name !");
				return;
			}
			/* if($("#residenceFlatNo").val() == null || $("#residenceFlatNo").val() == "") {
				alert("Please select resident flat number !");
				retrun;
			} */
		}
		
		if(officeAreaId != -1) {
			if($("#officeBuildingName").val() == null || $("#officeBuildingName").val() == "") {
				alert("Please enter office building name !");
				return;
			}
			/* if($("#officeFlatNo").val() == null || $("#officeFlatNo").val() == "") {
				alert("Please select office flat number !");
				retrun;
			} */
		}
		
		$.ajax({
       	  type: "POST",
       	  url: "saveCustomerAndAddress.html",
       	  data: { 	firstName:$("#firstName").val(), lastName:$("#lastName").val(),  
       				mobile1: $("#mobile1").val(), mobile2: $("#mobile2").val(),
       			 	landline: $("#landline").val(),  email: $("#email").val(),
       				preferenceCall: callPref, preferenceEmail: emailPref,
       				preferenceSms: smsPref, residenceAreaId :residenceAreaId,
       				residenceBuildingName:$("#residenceBuildingName").val(), residenceFlatNo:$("#residenceFlatNo").val(),
       				officeAreaId:officeAreaId, officeBuildingName:$("#officeBuildingName").val(), officeFlatNo:$("#officeFlatNo").val(), 
       				residenceRemarks: $("#residenceRemarks").val(), officeRemarks:$("#officeRemarks").val(),
       				residenceLatitude: $("#residenceLatitude").val(), residenceLongitude: $("#residenceLongitude").val(), 
       				officeLatitude: $("#officeLatitude").val(), officeLongitude: $("#officeLongitude").val(),}
       	}).done(function( actionStatus ) {
       		if(actionStatus.statusType == "Success") {
       		 	//alert( "Data Saved: " + msg );
           	 	loadCustomerComboWithSelectedId(280, actionStatus.generatedId);
           	 	alert(actionStatus.statusMessage);
           	 	resetAndClosePopupForm();
       		} else {
       			alert(actionStatus.statusMessage);
       		}       		       	   
       	});
        //Clear form values
       
    });
}



function resetAndClosePopupForm() {
	$('#addCustomerPopupWindow').jqxWindow('hide');	
}

function setupFormValidations() {
	$('#customerAddForm').jqxValidator({
		//_margin: 30,
		rules: [
			{ input: '#firstName', 	message: 'First name is required!', 	action: 'blur', rule: 'required' },
			{ input: '#lastName', 	message: 'Last name is required!', 		action: 'blur', rule: 'required' },
			
			{ input: '#mobile1', 	message: 'Mobile No. 1 is required!', 	action: 'blur', rule: 'required' },
			{ input: '#mobile1', 	message: 'Mobile No. 1 length should be 9 !', 	action: 'blur', rule: 'length=9,9' },
			{ input: '#mobile1', 	message: 'Mobile No. 1 should be a number !', 	action: 'blur', rule: 'number' },
			{ input: '#mobile1', 	message: 'Mobile No. 1 cannot contain zero!', 	action: 'blur', rule: function(input) {
				var val = $("#mobile1").val();
				if(val.substring(0, 1) == "0") {
					return false;
				}
					return true;
				} 
			},
			{ input: '#mobile1', 	message: 'Duplicate Mobile No.!', 	action: 'blur', rule: function(input, commit) {
				var val = $("#mobile1").val();
				
				if(val == ""){
					return;
				}				
				$.ajax({
					url: "checkDuplicateMobileNo.html",
					type: 'GET',
					data: {mobileNo: val},
					success: function(data)
					{
						if (data == "false")
						{
							commit(true);
						}
						else commit(false);
					},
					error: function()
					{
						commit(false);
					}
				});	
				}
			},
			{ input: '#mobile2', 	message: 'Mobile No. 2 length should be 9 !', 	action: 'blur', rule: 'length=9,9' },
			{ input: '#mobile2', 	message: 'Mobile No. 2 should be a number !', 	action: 'blur', rule: 'number' },
			{ input: '#mobile2', 	message: 'Mobile No. 2 cannot contain zero !', 	action: 'blur', rule: function(input) {
				var val = $("#mobile2").val();
				if(val.substring(0, 1) == "0") {
					return false;
				}
					return true;
				} 
			},
			{ input: '#landline', 	message: 'Landline No. is invalid !', 	action: 'blur', rule: 'length=9,9' },
			{ input: '#landline', 	message: 'Landline No. is invalid !', 	action: 'blur', rule: function(input) {
				var val = $("#landline").val();
				if(val.substring(0, 1) == "0") {
					return false;
				}
					return true;
				} 
			},
			{ input: '#email', 		message: 'Email Id is required!', 		action: 'blur', rule: 'required' },
			{ input: '#email', 		message: 'Invalid Email!', 				action: 'blur', rule: 'email' },			
			{ input: '#formAreaIdResidence', 	message: 'Residence area is required!', action: 'select', rule: function(input) {
				var val = $("#formAreaIdResidence").jqxComboBox('val');
				if(val==""){
					return false;
				}
					return true;
				} 
			},
			{ input: '#formAreaIdOffice', 	message: 'Office area is required!', action: 'select', rule: function(input) {
				var val = $("#formAreaIdOffice").jqxComboBox('val');
				if(val==""){
					return false;
				}
					return true;
				} 
			},
			{ input: '#residenceBuildingName', 	message: 'Residence Building name is required!', action: 'blur', rule: 'required' },
			{ input: '#residenceFlatNo', 	message: 'Residence Flat No. is required!', action: 'blur', rule: 'required' },
			{ input: '#officeBuildingName', 	message: 'Office Building name is required!', action: 'blur', rule: 'required' },
			{ input: '#officeFlatNo', 	message: 'Office Flat No. is required!', action: 'blur', rule: 'required' }
		]
	});
}

</script>
<div id="customerInfoAddPopup" style="display: none;">
	<div style="width: 100%; height: 650px; margin-top: 50px;"
		id="mainDemoContainer">
		<div id="addCustomerPopupWindow">
			<div id="windowHeader">
				<span> Add New Customer </span>
			</div>
			<div style="overflow: hidden;" id="windowContent">
				<table width="100%" border ="0">
				<tr valign="top">
				<td>
				<div id="createCustomerExpander" style="font-family: Verdana; font-size: 13px; width: 380px;">
			        <div>
			            Customer Info
			        </div>
			        <div style="font-family: Verdana; font-size: 13px;">
            			<form id="customerAddForm" style="overflow: hidden; margin: 10px;" action="./">
            			<table border="0" width="100%" class="popupFormTable">
							<tr>
								<td colspan="1">First Name :</td>
								<td colspan="1"><input id="firstName" /></td>
							</tr>
							<tr>
								<td colspan="1">Last Name :</td>
								<td colspan="1"><input id="lastName" /></td>
							</tr>
							<tr>
								<td colspan="1">Mobile No 1 :</td>
								<td colspan="1" nowrap><input id="mobile1Prefix" value="+971"> - <input id="mobile1" maxlength="9" /></td>
							</tr>
							<tr>
								<td colspan="1">Mobile No 2 :</td>
								<td colspan="1" nowrap><input id="mobile2Prefix" value="+971"> - <input id="mobile2" maxlength="9"/></td>
							</tr>
							<tr>
								<td colspan="1">Landline No :</td>
								<td colspan="1"><input id="landlinePrefix" value="+971"> - <input id="landline" /></td>
							</tr>					
							<tr>
								<td colspan="1">Email :</td>
								<td colspan="1"><input id="email" /></td>
							</tr>
							<tr>
								<td colspan="1">
									Pref. Contact
								</td>
								<td>
									<div id='preferenceCallElement' style='margin-left: 0px; width : 10px; float: left;'>Call</div>
									<div id='preferenceSmsElement' style='margin-left: 0px; width : 10px; float: left;'>SMS</div>
									<div id='preferenceEmailElement' style='margin-left: 0px; width : 10px; float: left;'>Email</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<input id="createCustomerButton" type="button" value="Add Customer" />
								</td>
							</tr>
						</table>            			            			
            			</form>
            		</div>	
		        </div>
		        </td>
		        <td rowspan="3">
		        	<table>
		        	<tr>
		        	<td>
		        		<div id="mapHolder">
		        			<input id="pac-input" class="controls" type="text" placeholder="Search Box">
    						<div id="map-canvas"></div>						
    						</div>   
		        	</td>
		        	</tr>
		        	</table>
		        </td>
		        </tr>
		        <tr>
		        <td>
		        <div id="customerResidenceAddressExpander" style="font-family: Verdana; font-size: 13px; width: 300px;">
		        	<div>
		        		<div style='width:20px ' id='residenceAddressRadio'>
            				<span>
            					&nbsp;&nbsp;&nbsp;
		        				Address - Residence
		        			</span>
		        		</div>
		        	</div>
		        	<div>
		        		<table class="popupFormTable" id="addressTableOffice" border="0" width="100%">
						 	 <tr>
						         <td align="right" width="30%">Area:</td>
						         <td align="left" width="70%"><div id="formAreaIdResidence" ></div></td>
						     </tr>
						     <tr>
						         <td align="right">Building Name:</td>
						         <td align="left"><input id="residenceBuildingName" /></td>
						     </tr>
						     <tr>
						         <td align="right">Flat No:</td>
						         <td align="left"><input id='residenceFlatNo'/></td>
						     </tr>
						     <tr>
						         <td align="right">Remarks:</td>
						         <td align="left">
						         	<input id='residenceRemarks'/>
						         	<input type="hidden" id='residenceLatitude' name="residenceLatitude"/>
						         	<input type="hidden" id='residenceLongitude' name="residenceLongitude"/>
						         </td>
						     </tr>
				        </table>
		        	</div>
		        </div>
		        </td>		        
		        </tr>
		        <tr valign="top">
		        	<td>
		        <div id="customerOfficeAddressExpander" style="font-family: Verdana; font-size: 13px; width: 300px;">
		        	<div>
		        		<div style='width:20px' id='officeAddressRadio'>
            				<span>&nbsp;&nbsp;&nbsp;
		        				Address - Office
		        			</span>
		        		</div>
		        	</div>
		        	<div>
		        		<table class="popupFormTable" id="addressTable" border="0" width="100%">
						 	 <tr>
						         <td align="right" width="30%">Area:</td>
						         <td align="left" width="70%"><div id="formAreaIdOffice" ></div></td>
						     </tr>
						     <tr>
						         <td align="right">Building Name:</td>
						         <td align="left"><input id="officeBuildingName" /></td>
						     </tr>
						     <tr>
						         <td align="right">Flat No:</td>
						         <td align="left"><input id='officeFlatNo'/></td>
						     </tr>
						     <tr>
						         <td align="right">Remarks:</td>
						         <td align="left">
						         	<input id='officeRemarks'/>
						         	<input type="hidden" id='officeLatitude' name="officeLatitude"/>
						         	<input type="hidden" id='officeLongitude' name="officeLongitude"/>
						         </td>
						     </tr>
				        </table>
		        	</div>
		        </div>
		        </td>
		        </tr>
		        
		        </table>
		        
			</div>
			
			
		</div>
	</div>
	
	
</div>
	
  	