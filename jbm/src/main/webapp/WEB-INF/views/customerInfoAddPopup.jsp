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
	$("#landline").jqxInput({
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
       			 	landline: $("#landline").val(),  email: $("#email").val(),
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
<div id="customerInfoAddPopup">
	<div style="width: 100%; height: 650px; margin-top: 50px;"
		id="mainDemoContainer">
		<div id="addCustomerPopupWindow">
			<div id="windowHeader">
				<span> Add New Customer </span>
			</div>
			<div style="overflow: hidden;" id="windowContent">
				<table width="100%" border ="0">
				<tr valign="top">
				<td rowspan="3">
				<div id="createCustomerExpander" style="font-family: Verdana; font-size: 13px; width: 380px;">
			        <div>
			            Customer Info
			        </div>
			        <div style="font-family: Verdana; font-size: 13px;">
            			<form id="form" style="overflow: hidden; margin: 10px;" action="./">
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
								<td colspan="1"><input id="mobile1" /></td>
							</tr>
							<tr>
								<td colspan="1">Mobile No 2 :</td>
								<td colspan="1"><input id="mobile2" /></td>
							</tr>
							<tr>
								<td colspan="1">Landline No :</td>
								<td colspan="1"><input id="landline" /></td>
							</tr>					
							<tr>
								<td colspan="1">Email :</td>
								<td colspan="1"><input id="email" /></td>
							</tr>
							<tr>
								<td colspan="2">Preferred Contact Method :</td>
							</tr>
							<tr>
								<td colspan="2">
									<div id='preferenceCallElement' style='margin-left: 10px; width : 60px; float: left;'>Call</div>
									<div id='preferenceSmsElement' style='margin-left: 10px; width : 60px; float: left;'>SMS</div>
									<div id='preferenceEmailElement' style='margin-left: 10px; width : 60px; float: left;'>Email</div>
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
		        <td>
		        <div id="customerResidenceAddressExpander" style="font-family: Verdana; font-size: 13px; width: 300px;">
		        	<div>
		        		Address - Residence
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
						     	<td>&nbsp;</td>
						     </tr>
				        </table>
		        	</div>
		        </div>
		        </td>		        
		        </tr>
		        <tr>
		        	<td>
		        		&nbsp;<!-- spacer -->
		        	</td>
		        </tr>
		        <tr valign="top">
		        	<td>
		        <div id="customerOfficeAddressExpander" style="font-family: Verdana; font-size: 13px; width: 300px;">
		        	<div>
		        		Address - Office
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
						     	<td>&nbsp;</td>
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