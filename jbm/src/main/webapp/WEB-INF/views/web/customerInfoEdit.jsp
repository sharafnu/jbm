<jsp:include page="includes/header.jsp" />
<div>
	<span class="breadcrumbs">Home > Customer > Update Customer Info</span>
</div>
<script type="text/javascript">
function loadCustomerCombo(comboWidth) {
	var customerListUrl = "customerListJSON.html";
	var customerListSource = {
		datatype : "json",
		datafields : [ {
			name : 'fullName',
			type : 'string'
		},{
			name : 'comboBoxText',
			type : 'string'
		}, 
		{
			name : 'mobile1',
			type : 'string'
		}, {
			name : 'customerCode',
			type : 'string'
		}, {
			name : 'id',
			type : 'int'
		} ],
		id : 'id',
		url : customerListUrl
	};
	var customerListDataAdapter = new $.jqx.dataAdapter(customerListSource);

	$("#formCustomerId").jqxComboBox({
		selectedIndex : -1,
		source : customerListDataAdapter,
		displayMember : "comboBoxText",
		valueMember : "id",
		searchMode: "containsignorecase",
		//autoComplete: true,
		width : comboWidth,
		height : 20,
		renderSelectedItem: function(index, item) {
			var item = customerListDataAdapter.records[index];
			return item.fullName;   
        }
	});
	
	}


	$(document)
			.ready(
					function() {
						document.title = 'Update Customer Info';
						setupAddressForm();
						loadCustomerCombo(230);
						// Create jqxExpander.
						
						 $('#formCustomerId').on('change', function (event) {
							    var args = event.args;
			                    if (args) {
			                    	var item = args.item;
			                        if(item.value != "") {
			                        	var customerDetailsURL = "getCustomerDetailsJSON/";
			                        	customerDetailsURL = customerDetailsURL +item.value+".html";
			                        	$.get(customerDetailsURL, function(customerView){
			                        		$("#firstName").val(customerView.firstName);
			                        		$("#lastName").val(customerView.lastName);
			                        		$("#mobile1").val(customerView.mobile1);
			                        		$("#mobile2").val(customerView.mobile2);
			                        		$("#landline").val(customerView.landline);
			                        		$("#email").val(customerView.email);
			                        		if(customerView.preferenceCall == 1) {
			                        			$("#preferenceCallElement").jqxCheckBox({ checked: true });	
			                        		} else {
			                        			$("#preferenceCallElement").jqxCheckBox({ checked: false });
			                        		}
			                        		if(customerView.preferenceSms == 1) {
			                        			$("#preferenceSmsElement").jqxCheckBox({ checked: true });	
			                        		} else {
			                        			$("#preferenceSmsElement").jqxCheckBox({ checked: false });
			                        		}
			                        		if(customerView.preferenceEmail == 1) {
			                        			$("#preferenceEmailElement").jqxCheckBox({ checked: true });	
			                        		} else {
			                        			$("#preferenceEmailElement").jqxCheckBox({ checked: false });
			                        		}
			                        	});
			                        	loadAddressGrid(item.value);
			                        }
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

						// Create jqxButton.
						$("#updateCustomerButton").jqxButton({
							theme : theme
						});
						var addAddressButton = $("#addAddressButton").jqxButton({
							theme : theme
						});
						addAddressButton.click(function (event) {
	                         $("#popupWindow").jqxWindow({ position: { x: 660, y: 185 } });
	                        $("#popupWindow").jqxWindow('open');
	                         
							//$('#addCustomerPopupWindow').jqxWindow('show');
	                        
	                    });
						// Create jqxButton.
						var editAddressButton = $("#editAddressButton").jqxButton({
							theme : theme
						});
						editAddressButton.click(function (event) {
	                        $("#popupWindow").jqxWindow({ position: { x: 660, y: 185 } });
	                        $("#popupWindow").jqxWindow('open');
	                         
							//$('#addCustomerPopupWindow').jqxWindow('show');
	                        
	                    });
						
						// Create jqxValidator.
						$("#form")
								.jqxValidator(
										{
											rules : [
													{
														input : "#firstName",
														message : "First name is required!",
														action : 'keyup, blur',
														rule : function(input,
																commit) {
															return input.val() != ""
																	&& input
																			.val() != "First";
														}
													},
													{
														input : "#lastName",
														message : "Last name is required!",
														action : 'keyup, blur',
														rule : function(input,
																commit) {
															return input.val() != ""
																	&& input
																			.val() != "Last";
														}
													},
													/* {
														input : "#mobile1",
														message : 'Invalid mobile 1!',
														action : 'valuechanged, blur',
														rule : 'phone'
													},
													{
														input : "#mobile2",
														message : 'Invalid mobile 2!',
														action : 'valuechanged, blur',
														rule : 'phone'
													},
													{
														input : "#landline",
														message : 'Invalid mobile 3!',
														action : 'valuechanged, blur',
														rule : 'phone'
													}, */
													{
														input : '#email',
														message : 'Invalid e-mail!',
														action : 'keyup',
														rule : 'email'
													} ],
											hintType : "label"
										});
						// Validate the Form.
						$("#updateCustomerButton").click(function() {
							$('#form').jqxValidator('validate');
						});
						// Update the jqxExpander's content if the validation is successful.
						$('#form')
								.on(
										'validationSuccess',
										function(event) {
											$("#createAccount")
													.jqxExpander('setContent',
															'<span style="margin: 10px;">Account created.</span>');
										});
					
		
	});
	
	function loadAddressGrid(customerId) {
		var empAddressSource = {
		        datatype: "json",
		        datafields: [
		                    { name: 'addressType', type: 'string' },
		                    { name: 'buildingName', type: 'string' },
		                    { name: 'flatNo', type: 'string' },
		                    { name: 'areaId', type: 'int' },
		                    { name: 'customerId', type: 'int' },
		                    { name: 'cityId', type: 'int' },
		                    { name: 'areaName', type: 'string' },
		                    { name: 'cityName', type: 'string' }
		                ],
		        id: 'id',
		        url: 'getCustomerAddressListJSON/'+customerId+'.html',
		        async: false
		    };
		var dataAdapter = new $.jqx.dataAdapter(empAddressSource, { autoBind: true });
	    var records = dataAdapter.records;
	    var empAddressArray = new Array();
	    for (var i = 0; i < records.length; i++) {
	    	empAddressArray.push(records[i]);
	    };
	    //alert(empAddressArray.length);
		$("#dataTable")
				.jqxDataTable(
						{
							width : 350,
							source : dataAdapter,
							sortable : false,
							pageable : false,
							pageSize : 1,
							pagerButtonsCount : 5,
							enableHover : false,
							selectionMode : 'none',
							rendered : function() {
								/* $(".buy").jqxButton();
								$(".buy")
										.click(
												function() {
													itemsInCart++;
													$(
															".cart-top p")
															.html(
																	itemsInCart
																			+ " products");
												}); */
							},
							columns : [ {
								text : 'Address',
								align : 'left',
								dataField : 'model',
								// row - row's index.
								// column - column's data field.
								// value - cell's value.
								// rowData - rendered row's object.
								cellsRenderer : function(row,
										column, value, employeeAddressRow) {
									var container = "<div>";
									var item = "<div style='float: left; width: 270px; overflow: hidden; white-space: nowrap; height: 130px;'>";

									var info = "<div style='margin: 5px; margin-left: 10px; margin-bottom: 3px;'>";
									info += "<div><i><b>"
											+ employeeAddressRow.addressType
											+ "</b></i></div>";
									info += "<div>Flat No.: "+ employeeAddressRow.flatNo+ "</div>";
									info += "<div>Building.: "+ employeeAddressRow.buildingName+ "</div>";
									info += "<div>Area.: "+ employeeAddressRow.areaName+ "</div>";
									info += "<div>City.: "+ employeeAddressRow.cityName+ "</div>";			
									info += "</div>";

									var buy = "<button class='editAddress' id='editAddressButton' style='margin: 5px; width: 80px; position: relative;  margin-bottom: 3px;'>Edit</button>";

									item += info;
									item += buy;
									item += "</div>";
									container += item;
									container += "</div>";
									return container;
								}
							} ]
						});
	}
	
	function setupAddressForm() {
		
		$("#popupWindow").jqxWindow({
            title: "Add Address", width: 400, resizable: false,  isModal: true, autoOpen: false, cancelButton: $("#Cancel"), modalOpacity: 0.01           
        });

        var addressTypeSource = [
                      "Office",
                      "Residence"
        ];
                   
		$("#formAddressType").jqxComboBox({ selectedIndex: 0, source: addressTypeSource, width: 230, height: 20});
                   
/*         var cityUrl = "cityListJSON.html";
        var citySource =
        {
            datatype: "json",
            datafields: [
                { name: 'cityName', type: 'string' },
                { name: 'cityId', type: 'int' }
            ],
            id: 'cityId',
            url: cityUrl
        };
        var cityDataAdapter = new $.jqx.dataAdapter(citySource);
        
        $("#formCityId").jqxComboBox({ selectedIndex: 0, source: cityDataAdapter, displayMember: "cityName", valueMember: "cityId", width: 230, height: 25});
 */	
       /*  var areaListUrl = "areaListJSON.html";
        var areaListSource =
        {
            datatype: "json",
            datafields: [
                { name: 'areaName', type: 'string' },
                { name: 'areaId', type: 'int' }
            ],
            id: 'areaId',
            url: areaListUrl
        };
        */ 
        //$("#formAreaId").jqxComboBox({ selectedIndex: 0, source: areaDataAdapter, displayMember: "areaName", valueMember: "areaId", width: 230, height: 25});
	
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
	
		$("#formAreaId").jqxComboBox({
			selectedIndex : -1,
			source : areaListDataAdapter,
			displayMember : "comboBoxText",
			valueMember : "areaId",
			width : 230,
			height : 20,
			searchMode: "containsignorecase",
			//autoComplete: true,
			renderSelectedItem: function(index, item) {
				var item = areaListDataAdapter.records[index];
				return item.areaName;   
	        }
		});
	
        $("#formBuildingName").jqxInput({
			width : '230px',
			height : '20px'
		});
        
        $("#formFlatNo").jqxInput({
			width : '230px',
			height : '20px'
		});

        $("#Cancel").jqxButton({ theme: theme });
        var saveButton =  $("#Save").jqxButton({ theme: theme });
        
        saveButton.click(function (event) {
        	
         	var addressTypeItem = $("#formAddressType").jqxComboBox('getSelectedItem');
        	//var cityItem = $("#formCityId").jqxComboBox('getSelectedItem');
        	var areaItem = $("#formAreaId").jqxComboBox('getSelectedItem');
        	var customerIdItem = $("#formCustomerId").jqxComboBox('getSelectedItem');
			var custId = customerIdItem.value;
			$.ajax({
	       	  type: "POST",
	       	  url: "saveCustomerAddress.html",
	       	  data: { addressType: addressTypeItem.value, buildingName: $("#formBuildingName").val(), flatNo: $("#formFlatNo").val(), 
	       		customerId: custId, areaId: areaItem.value}
	       	}).done(function( msg ) {
	       	    //alert( "Data Saved: " + msg );
	       	});
            //Clear form values
            
            $("#formAddressType").val("");
            //$("#formCityId").val("");
            $("#formAreaId").val("");
            $("#formBuildingName").val("");
            $("#formFlatNo").val("");
            $("#popupWindow").jqxWindow('hide');
            loadAddressGrid(custId);
        });
	}
</script>


<form id="form" action="./">

<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">
	<div id="createAccount" class="cornerDiv">
			<div style="background-color: #F4F0F5; color: #000; min-height: 1.5em; vertical-align: middle; padding: 5px; width: 800px;">
				Customer Info</div>
			<div style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 10px;">
				<table border="0" width="100%" class="popupFormTable">
					<tr>
						<td colspan="1">Select Customer :</td>
						<td colspan="1"><div id="formCustomerId" ></div></td>
						<td rowspan="18" width="50%" valign="top">
						    <div>
						    </div>
						    <br />
						    <div style="margin-left: 20px; margin-top: -18px" id="dataTable"></div>
						</td>
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
	</div>
</div>
</form>
<div id="popupWindow">
	<div style="overflow: hidden;">
 		<table class="popupFormTable">
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

<jsp:include page="includes/footer.jsp" />
<jsp:include page="customerInfoAddPopup.jsp" />
