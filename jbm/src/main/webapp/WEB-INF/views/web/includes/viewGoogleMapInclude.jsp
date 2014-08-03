    <style>
      #map-canvas-view {
      	width:800px;
      	height:500px;
        margin: 0px;
        padding: 0px
      }
    </style>

<script>
		var mapReady = false;
		var infowindow = new google.maps.InfoWindow(
		  { 
		    size: new google.maps.Size(300,100)
		  });
	function showMap() {
		mapReady = true;
		var selectedAddress = $("#formCustomerAddressId").jqxComboBox('getSelectedItem'); 
		var address = selectedAddress.label;
		var start = address.indexOf("Position : </b>");
		var end = address.lastIndexOf("</div>");
		var positionStr = address.substring(parseInt(start)+15,end);
		var lat = positionStr.substring(0,positionStr.indexOf(","));
		var lng = positionStr.substring(parseInt(positionStr.indexOf(","))+1,positionStr.length);
		//alert(positionStr);
		//alert(lat+" - "+lng);
	  var myLatlng = new google.maps.LatLng(lat,lng);
	  var mapOptions = {
	    zoom: 16,
	    center: myLatlng
	  }
	  var map = new google.maps.Map(document.getElementById('map-canvas-view'), mapOptions);
	
	  var marker = new google.maps.Marker({
	      position: myLatlng,
	      map: map,
	      title: address
	  });
	  
	  google.maps.event.addListener(marker, 'click', function() {
		  	
			
	  		//var mapContent = "<div style='width:300px;margin:0 0 20px 20px;height:90px;'><b>Residence&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>";
	  		//mapContent = mapContent + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	  		//mapContent = mapContent + "<br>Building : <br>Flat No: <br>Landmark: </div>";
		  	infowindow.setContent("<div style='width:300px;margin:0 0 20px 20px;height:90px;'>"+address+"</div>"); 
			infowindow.open(map,marker);
		});
		google.maps.event.trigger(marker, 'click');
		
	}
	
	//google.maps.event.addDomListener(window, 'load', showMap);

	$(document).ready(function() {
		$('#viewGoogleMapPopupWindow').jqxWindow({
	        showCollapseButton: false, maxHeight: 500, maxWidth: 800, minHeight: 500, minWidth: 800, height: 500, width: 800,
	        autoOpen: false,
	        initContent: function () {
	            $('#viewGoogleMapPopupWindow').jqxWindow('focus');
	            //showMap();
	        }
	    });
		$('#viewGoogleMapPopupWindow').on('open', function (event) {
			showMap();
        });

	});
</script>
	
<div id="viewGoogleMapPopup" style="display: none;">
	<div style="width: 100%; height: 500px; margin-top: 50px;" id="viewGoogleMapPopupContainer">
		<div id="viewGoogleMapPopupWindow">
			<div id="viewGoogleMapPopupHeader">
				<span> View Map </span>
			</div>
			<div style="overflow: hidden;" id="viewGoogleMapPopupContent">
				<div id="map-canvas-view"></div>
			</div>
		</div>
	</div>
</div>

