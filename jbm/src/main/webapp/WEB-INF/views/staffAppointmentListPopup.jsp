<script>
$(document).ready(function() {
	$('#addCustomerPopupWindow').jqxWindow({
        showCollapseButton: false, maxHeight: 420, maxWidth: 800, minHeight: 420, minWidth: 800, height: 420, width: 800,
        autoOpen: false,
        initContent: function () {
           // $('#tab').jqxTabs({ height: '100%', width:  '100%' });
            $('#addCustomerPopupWindow').jqxWindow('focus');
        }
    });
	$("#createCustomerExpander").jqxExpander({   width: '380px', showArrow:false });
	$("#customerResidenceAddressExpander").jqxExpander({   width: '380px', showArrow:false });
	$("#customerOfficeAddressExpander").jqxExpander({   width: '380px', showArrow:false });
	
	setupCustomerAddPopupForm();
	//$('#addCustomerPopupWindow').jqxWindow('hide');
});


function setupCustomerAddPopupForm() {
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
	$("#mobile1").jqxInput({
		width : '230px',
		height : '20px'
	});
	$("#mobile2").jqxInput({
		width : '230px',
		height : '20px'
	});
	$("#mobile3").jqxInput({
		width : '230px',
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
	
	$("#officeBuildingName").jqxInput({
		width : '230px',
		height : '20px'
	});
	
	$("#officeFlatNo").jqxInput({
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
		
		$.ajax({
       	  type: "POST",
       	  url: "saveCustomerAndAddress.html",
       	  data: { 	firstName:$("#firstName").val(), lastName:$("#lastName").val(),  
       				mobile1: $("#mobile1").val(), mobile2: $("#mobile2").val(),
       			 	mobile3: $("#mobile3").val(),  email: $("#email").val(),
       				preferenceCall: callPref, preferenceEmail: emailPref,
       				preferenceSms: smsPref, residenceAreaId :residenceAreaId,
       				residenceBuildingName:$("#residenceBuildingName").val(), residenceFlatNo:$("#residenceFlatNo").val(),
       				officeAreaId:officeAreaId, officeBuildingName:$("#officeBuildingName").val(), officeFlatNo:$("#officeFlatNo").val()}
       	}).done(function( msg ) {
       	    //alert( "Data Saved: " + msg );
       	 	loadCustomerCombo(280);
       	});
        //Clear form values
        
       resetAndClosePopupForm();
    });
}

function resetAndClosePopupForm() {
	$('#addCustomerPopupWindow').jqxWindow('hide');	
}

</script>
<div id="staffAppointmentListPopup">
	<div style="width: 100%; height: 650px; margin-top: 50px;"
		id="mainDemoContainer">
		<div id="addCustomerPopupWindow">
			<div id="windowHeader">
				<span> Staff Appointment List </span>
			</div>
			<div style="overflow: hidden;" id="windowContent">
				
			</div>
		</div>
	</div>
</div>