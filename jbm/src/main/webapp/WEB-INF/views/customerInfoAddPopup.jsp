<script>
$(document).ready(function() {
	$('#window').jqxWindow({
        showCollapseButton: true, maxHeight: 600, maxWidth: 800, minHeight: 200, minWidth: 200, height: 450, width: 800,
        initContent: function () {
           // $('#tab').jqxTabs({ height: '100%', width:  '100%' });
            $('#window').jqxWindow('focus');
        }
    });
	$("#createCustomerExpander").jqxExpander({   width: '380px' });
	$("#customerAddressExpander").jqxExpander({   width: '280px' });
	
});

</script>
<div id="customerInfoAddPopup">
	<div style="width: 100%; height: 650px; margin-top: 50px;"
		id="mainDemoContainer">
		<div id="window">
			<div id="windowHeader">
				<span> Movies </span>
			</div>
			<div style="overflow: hidden;" id="windowContent">
				<table width="100%">
				<tr valign="top">
				<td>
				<div id="createCustomerExpander" style="font-family: Verdana; font-size: 13px; width: 380px;">
			        <div>
			            Customer Info
			        </div>
			        <div style="font-family: Verdana; font-size: 13px;">
            			<form id="form" style="overflow: hidden; margin: 10px;" action="./">
            			<table border="1" width="100%" class="popupFormTable">
							<tr>
								<td colspan="1">Select Customer :</td>
								<td colspan="1"><div id="formCustomerId" ></div></td>
								<!-- <td rowspan="18" width="50%" valign="top">
								    
								</td> -->
							</tr>
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
								<td colspan="1">Mobile No 3 :</td>
								<td colspan="1"><input id="mobile3" /></td>
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
								<td colspan="2">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="1">
									<input id="updateCustomerButton" type="button" value="Update Customer" />
								</td>
								<td colspan="1">
									<input id="addAddressButton" type="button" value="Add Address" />
								</td>
							</tr>
						</table>            			            			
            			</form>
            		</div>	
		        </div>
		        </td>
		        <td>
		        <div id="customerAddressExpander" style="font-family: Verdana; font-size: 13px; width: 300px;">
		        	<div>
		        		Address
		        	</div>
		        	<div>
		        		<table class="addressTable">
						 	<tr>
						         <td align="right">Address Type:</td>
						         <td align="left"><div id="formAddressType" ></div></td>
						     </tr>
						     <!-- <tr>
						         <td align="right">City:</td>
						         <td align="left"><div id="formCityId" ></div></td>
						     </tr> -->
						     <tr>
						         <td align="right">Area:</td>
						         <td align="left"><div id="formAreaId" ></div></td>
						     </tr>
						     <tr>
						         <td align="right">Buliding Name:</td>
						         <td align="left"><input id="formBuildingName" /></td>
						     </tr>
						     <tr>
						         <td align="right">Flat No:</td>
						         <td align="left"><input id='formFlatNo'/></td>
						     </tr>
						     <tr>
						         <td align="right"></td>
						         <td style="padding-top: 10px;" align="right"><input style="margin-right: 5px;" type="button" id="Save" value="Save" /><input id="Cancel" type="button" value="Cancel" /></td>
				            </tr>
				            <tr>
						         <td align="right" colspan="2"></td>
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