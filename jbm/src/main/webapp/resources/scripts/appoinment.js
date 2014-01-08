function setupAppointmentDetailsForm() {
	loadAppoinmentCombo();
	$("#updateAppointmentButton").jqxButton({
		theme : theme
	});
	 $('#appoinmentDetailsTab').jqxTabs({ width: '90%', height: 380, position: 'top'});
	 $('#appoinmentDetailsTab').jqxTabs({ animationType: 'fade' });
	 $('#appoinmentDetailsTab').jqxTabs({ selectionTracker: true });
	 loadAreaCombo();
	 loadAppoinmentStatusCombo();
	 $("#formStartTime").jqxDateTimeInput({
			width : '130px',
			height : '20px',
			formatString: 'T',
			showCalendarButton: false
	 });
		
	 $("#formStartTime").jqxDateTimeInput('setDate', new Date());
	 $("#formEndTime").jqxDateTimeInput({
			width : '130px',
			height : '20px',
			formatString: 'T',
			showCalendarButton: false
	 });
		
	 $("#formEndTime").jqxDateTimeInput('setDate', new Date());
	 
	 $("#formHoursSpent").jqxInput({
			width : '130px',
			height : '20px'
	});
	 
	 $("#formAmountPayable").jqxInput({
			width : '130px',
			height : '20px'
	 });
	 
	 loadPaymentTypeCombo(); 
	 loadPaymentStatusCombo();
	 
	 $("#formInvoiceNo").jqxInput({
			width : '180px',
			height : '20px'
	 });
	 
	 
	 $("#formInvoiceDate").jqxDateTimeInput({
			width : '130px',
			height : '20px'
	});
	 
	 $("#formInvoiceAmount").jqxInput({
			width : '180px',
			height : '20px'
	 });
	 
	 loadFinanceStatusCombo();
}



function loadFinanceStatusCombo() {
	var financeStatusSource = [ "Completed", "Not Completed"];
	$("#formFinanceStatus").jqxComboBox({
		selectedIndex : -1,
		source : financeStatusSource,
		width : 210,
		height : 20
	});
}

function loadPaymentTypeCombo() {
	var paymentTypeSource = [ "Cash", "Credt Card", "Debit Card", "Bank Transfer", "Cheque", "DD" ];
	$("#formPaymentType").jqxComboBox({
		selectedIndex : -1,
		source : paymentTypeSource,
		width : 210,
		height : 20
	});
}

function loadPaymentStatusCombo() {
	var paymentStatusSource = [ "Paid", "Not Paid"];
	$("#formPaymentStatus").jqxComboBox({
		selectedIndex : -1,
		source : paymentStatusSource,
		width : 210,
		height : 20
	});
}

function setupAppoinmentListSearchFilters() {
	loadCustomerCombo(210);
	loadEmployeeCombo(210);
	loadAreaCombo();
	loadAppoinmentCombo();
	setupDateFilters();
	loadAppoinmentStatusCombo();
	setupSearchButton();
}

function setupAppointmentForm() {
	loadCustomerCombo(280);
	loadEmployeeCombo(280);
	loadCustomerAddressCombo(280);
	//loadAreaCombo();
	// Create jqxInput.
	/*$("#formJobId").jqxInput({
		width : '200px',
		height : '20px'
	});*/

	$("#formAppointmentDate").jqxDateTimeInput({
		width : '130px',
		height : '20px'
	});
	
	$("#formStartTime").jqxDateTimeInput({
		width : '130px',
		height : '20px',
		formatString: 'T',
		showCalendarButton: false
	});
	
	$("#formStartTime").jqxDateTimeInput('setDate', new Date());
	
	//$("#staffCalendar").jqxCalendar({ enableTooltips: true, width: 360, height: 300});
	
	 $('#formEmployeeId').on('change', function (event) {
		 var args = event.args;
         if (args) {
         	var item = args.item;
             if(item.value != "") {
             	/*var customerDetailsURL = "getCustomerDetailsJSON/";
             	customerDetailsURL = customerDetailsURL +item.value+".html";
             	$.get(customerDetailsURL, function(customerView){
             		
             	});*/
             // Create Date objects.
                var date1 = new Date();
                var date2 = new Date();
                var date3 = new Date();
                date1.setDate(5);
                date2.setDate(15);
                date3.setDate(16);
                // Add special dates by invoking the addSpecialDate method.
               /* $("#staffCalendar").jqxCalendar('addSpecialDate', date1, '', '<table border="1" width="400px"><tr><td>3 appoinments</td></tr></table>');
                $("#staffCalendar").jqxCalendar('addSpecialDate', date2, '', '6 appoinments');
                $("#staffCalendar").jqxCalendar('addSpecialDate', date3, '', '1 appoinment'); */
             }
         }
	 });
	 
	var createAppointmentButton = $("#createAppointmentButton").jqxButton({
		theme : theme
	});
	createAppointmentButton.click(function (event) {
		//$('#form').jqxValidator('validate');
		
		var customerIdCombo = $("#formCustomerId").jqxComboBox('getSelectedItem'); 	
		if(customerIdCombo  != null) {
			$("#customerId").val(customerIdCombo.value);
		}
		
		var employeeIdCombo = $("#formEmployeeId").jqxComboBox('getSelectedItem'); 	
		if(employeeIdCombo  != null) {
			$("#employeeId").val(employeeIdCombo.value);
		}
		
		var customerAddressIdCombo = $("#formCustomerAddressId").jqxComboBox('getSelectedItem'); 	
		if(customerAddressIdCombo  != null) {
			$("#customerAddressId").val(customerAddressIdCombo.value);
		}
		
		$("#appointmentDate").val($("#formAppointmentDate").val());
		
		$("#startTime").val($("#formStartTime").val());
		
		$("#remarks").val($("#formRemarks").val());
		
		
		//$('#form').submit();
    });
	// Create jqxValidator.
	/*$("#form")
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
								}
							],
						hintType : "label"
					});*/
	
	// Update the jqxExpander's content if the validation is successful.
	$('#form')
			.on(
					'validationSuccess',
					function(event) {
						$("#createAccount")
								.jqxExpander('setContent',
										'<span style="margin: 10px;">Account created.</span>');
	});
}

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
		autoComplete: true,
		width : comboWidth,
		height : 20,
		renderSelectedItem: function(index, item) {
			var item = customerListDataAdapter.records[index];
			return item.fullName;   
        }
	});

	/*
	 * $('#formCustomerId').on('change', function (event) { var args =
	 * event.args; if (args) { var item = args.item; if(item.value != "") {
	 * //loadContractDetailsGrid(item.value); } } });
	 */
}

function loadCustomerAddressCombo(comboWidth) {
	var customerAddressListUrl = "getCustomerAddressListJSON/2.html";
	var customerAddressListSource = {
		datatype : "json",
		datafields : [ {
			name : 'addressType',
			type : 'string'
		},{
			name : 'comboBoxText',
			type : 'string'
		}, 
		{
			name : 'buildingName',
			type : 'string'
		}, {
			name : 'areaName',
			type : 'string'
		}, {
			name : 'cityName',
			type : 'string'
		}, {
			name : 'flatNo',
			type : 'string'
		}, {
			name : 'id',
			type : 'int'
		} ],
		id : 'id',
		url : customerAddressListUrl
	};
	var customerAddressListDataAdapter = new $.jqx.dataAdapter(customerAddressListSource);

	$("#formCustomerAddressId").jqxComboBox({
		selectedIndex : -1,
		source : customerAddressListDataAdapter,
		displayMember : "comboBoxText",
		valueMember : "id",
		searchMode: "containsignorecase",
		autoComplete: true,
		width : comboWidth,
		height : 20,
		renderSelectedItem: function(index, item) {
			var item = customerAddressListDataAdapter.records[index];
			return item.addressType+", "+item.buildingName +", "+item.flatNo+", "+item.areaName+", "+item.cityName;   
        }
	});

	/*
	 * $('#formCustomerId').on('change', function (event) { var args =
	 * event.args; if (args) { var item = args.item; if(item.value != "") {
	 * //loadContractDetailsGrid(item.value); } } });
	 */
}

function loadEmployeeCombo(comboWidth) {
	var employeeListUrl = "staffListJSON.html";
	var employeeListSource = {
		datatype : "json",
		datafields : [ {
			name : 'firstName',
			type : 'string'
		}, {
			name : 'id',
			type : 'int'
		} ],
		id : 'id',
		url : employeeListUrl
	};
	var employeeListDataAdapter = new $.jqx.dataAdapter(employeeListSource);

	$("#formEmployeeId").jqxComboBox({
		selectedIndex : -1,
		source : employeeListDataAdapter,
		displayMember : "firstName",
		valueMember : "id",
		width : comboWidth,
		height : 20
	});
}

function loadAreaCombo() {
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
		width : 210,
		height : 20,
		renderSelectedItem: function(index, item) {
			var item = areaListDataAdapter.records[index];
			return item.areaName;   
        }
	});
}

function loadAppoinmentCombo() {
	var appointmentListUrl = "customerAppoinmentListJSON.html";
	var appointmentListSource = {
		datatype : "json",
		datafields : [ {
			name : 'appointmentNo',
			type : 'string'
		}, {
			name : 'id',
			type : 'int'
		} ],
		id : 'id',
		url : appointmentListUrl
	};
	var appointmentListDataAdapter = new $.jqx.dataAdapter(
			appointmentListSource);

	$("#formAppointmentId").jqxComboBox({
		selectedIndex : -1,
		source : appointmentListDataAdapter,
		displayMember : "appointmentNo",
		valueMember : "id",
		width : 130,
		height : 20
	});
}

function setupDateFilters() {
	$("#formFromDate").jqxDateTimeInput({
		width : '130px',
		height : '20px'
	});
	$('#formFromDate').jqxDateTimeInput({allowNullDate: true});
	$('#formFromDate').jqxDateTimeInput({ value: new Date(2013, 0, 1) });
	
	$("#formToDate").jqxDateTimeInput({
		width : '130px',
		height : '20px'
	});
	$('#formToDate').jqxDateTimeInput({allowNullDate: true});
}

function loadAppoinmentStatusCombo() {
	var appoinmentStatusSource = [ "Created", "Completed", "Cancelled" ];
	$("#formAppoinmentStatus").jqxComboBox({
		selectedIndex : -1,
		source : appoinmentStatusSource,
		width : 210,
		height : 20
	});
}

function setupSearchButton() {
	$("#searchButton").jqxButton({
		theme : theme
	});
	$("#searchButton").click(
			function() {
				// $('#appoinmentSearchForm').jqxValidator('validate');
				var appointmentNoCombo = $("#formAppointmentId").jqxComboBox('getSelectedItem'); 	
				if(appointmentNoCombo != null) {
					$("#appointmentNo").val(appointmentNoCombo.value);
				}
				
				var areaIdCombo = $("#formAreaId").jqxComboBox('getSelectedItem'); 	
				if(areaIdCombo  != null) {
					$("#areaId").val(areaIdCombo.value);
				}
				
				var customerIdCombo = $("#formCustomerId").jqxComboBox('getSelectedItem'); 	
				if(customerIdCombo  != null) {
					$("#customerId").val(customerIdCombo.value);
				}
				
				var employeeIdCombo = $("#formEmployeeId").jqxComboBox('getSelectedItem'); 	
				if(employeeIdCombo  != null) {
					$("#employeeId").val(employeeIdCombo.value);
				}
				
				var appointmentStatusCombo = $("#formAppoinmentStatus").jqxComboBox('getSelectedItem'); 	
				if(appointmentStatusCombo  != null) {
					$("#appointmentStatus").val(appointmentStatusCombo.value);
				}
				
				$("#startDate").val($("#formFromDate").val());
				$("#endDate").val($("#formToDate").val());
				
				$('#appoinmentSearchForm').submit();
			});
}

// ////////////////////////
// ///////////////////////
// //////////////////////
function setupContractsPopupForm() {
	var contractTypeSource = [ "Contract Type 1", "Contract Type 2",
			"Contract Type 3" ];

	$("#fromContractType").jqxComboBox({
		selectedIndex : 0,
		source : contractTypeSource,
		width : 200,
		height : 20
	});

	var contractStatusSource = [ "Active", "Expired" ];

	$("#frmContractStatus").jqxComboBox({
		selectedIndex : 0,
		source : contractStatusSource,
		width : 200,
		height : 20
	});

	$("#frmAmount").jqxInput({
		width : '80px',
		height : '20px'
	});
	$("#frmContractNo").jqxInput({
		width : '200px',
		height : '20px'
	});
	$("#frmContractDate").jqxDateTimeInput({
		width : '200px',
		height : '20px'
	});

	$("#frmExpiryDate").jqxDateTimeInput({
		width : '200px',
		height : '20px'
	});

	// initialize the popup window and buttons.
	$("#popupWindow").jqxWindow({
		title : "Add New Contract",
		width : 400,
		resizable : false,
		isModal : true,
		autoOpen : false,
		cancelButton : $("#Cancel"),
		modalOpacity : 0.01
	});

	$("#Cancel").jqxButton({
		theme : theme
	});
	var saveButton = $("#Save").jqxButton({
		theme : theme
	});

	saveButton.click(function(event) {
		var contractTypeItem = $("#fromContractType").jqxComboBox(
				'getSelectedItem');
		var contractStatusItem = $("#frmContractStatus").jqxComboBox(
				'getSelectedItem');
		var customerIdItem = $("#formCustomerId")
				.jqxComboBox('getSelectedItem');
		var row = {
			contractNo : $("#frmContractNo").val(),
			contractType : contractTypeItem.value,
			contractDate : $("#frmContractDate").val(),
			amount : $("#frmAmount").val(),
			expiryDate : $("#frmExpiryDate").val(),
			contractStatus : contractStatusItem.value
		};

		$.ajax({
			type : "POST",
			url : "saveCustomerContract.html",
			data : {
				contractNo : $("#frmContractNo").val(),
				contractType : contractTypeItem.value,
				contractDate : $("#frmContractDate").val(),
				amount : $("#frmAmount").val(),
				expiryDate : $("#frmExpiryDate").val(),
				contractStatus : contractStatusItem.value,
				customerId : customerIdItem.value
			}
		}).done(function(msg) {
			// alert( "Data Saved: " + msg );
		});
		$("#jqxgrid").jqxGrid('addrow', null, row);
		// Clear form values
		$("#frmAmount").val("");
		$("#frmContractNo").val("");
		$("#frmContractStatus").val("");
		$("#fromContractType").val("");
		$("#frmContractDate").val("");
		$("#frmExpiryDate").val("");
		$("#popupWindow").jqxWindow('hide');
	});
}

// //////////////
// //////////////
// /////////////
function appoinmentDetailsGrid() {
	//var appointmentViewListJSONStr = eval('(' + '${appoinmentListJSON}' + ')');
	var appointmentViewListJSONStr = $("#appointmentViewListJSON").val();
	// var appointmentViewListJSONStr = '<c:out
	// value="${appoinmentListJSON}"/>';
	// alert(appointmentViewListJSONStr);
	var appoinmentListSource = {
		datatype : "json",
		datafields : [ {
			name : 'appointmentDate',
			type : 'string'
		}, {
			name : 'appointmentNo',
			type : 'string'
		}, {
			name : 'appointmentStatus',
			type : 'string'
		}, {
			name : 'remarks',
			type : 'string'
		}, {
			name : 'customerName',
			type : 'string'
		}, {
			name : 'employeeName',
			type : 'string'
		} ],
		id : 'id',
		localdata : appointmentViewListJSONStr
	};
	var appoinmentDataAdapter = new $.jqx.dataAdapter(appoinmentListSource);

	var imagerenderer = function() {
		return '<a href="www.google.com" ><img style="margin-left: 5px;margin-top: 5px;" src="resources/images/card.png"/></a>';
	}
	// initialize jqxGrid
	$("#jqxgrid")
			.jqxGrid(
					{
						width : 780,
						source : appoinmentDataAdapter,
						showstatusbar : true,
						rowdetails: true,
						rowdetailstemplate: { rowdetails: "<div style='margin: 10px;'><ul style='margin-left: 30px;'><li class='title'></li><li>Notes</li><li>Job Completion</li><li>Job Payments</li></ul><div class='information'></div><div class='notes'></div><div class='jobcompletion'></div><div class='jobpayments'></div></div>", rowdetailsheight: 100 },
		                ready: function () {
		                    //$("#jqxgrid").jqxGrid('showrowdetails', 0);		               
		                },
		                initrowdetails: initrowdetails,
						renderstatusbar : function(statusbar) {
							// appends buttons to the status bar.							
						},
						pageable : true,
						autoheight : true,
						sortable : true,
						altrows : true,
						enabletooltips : true,
						editable : false,
						selectionmode : 'none',
						columns : [ {
							text : 'Appointment No',
							datafield : 'appointmentNo',
							width : 125
						}, {
							text : 'Appnmt. Date',
							datafield : 'appointmentDate',
							width : 110
						}, {
							text : 'Customer Name',
							datafield : 'customerName',
							width : 200
						}, {
							text : 'Staff Name',
							datafield : 'employeeName',
							width : 200
						}, {
							text : 'Status',
							datafield : 'appointmentStatus',
							width : 90
						}, {
							text : '',
							datafield : 'edit',
							width : 25,
							cellsrenderer : imagerenderer
						}
						// { text: 'Edit', datafield: 'Edit', columntype:
						// 'button', cellsrenderer: function () {
						// return "Edit";
						// }, buttonclick: function (row) {
						// open the popup window when the user clicks a button.
						// editrow = row;
						// var offset = $("#jqxgrid").offset();
						// $("#popupWindow").jqxWindow({ position: { x:
						// parseInt(offset.left) + 60, y: parseInt(offset.top) +
						// 60 } });

						// get the clicked row's data and initialize the input
						// fields.
						/*
						 * var dataRecord = $("#jqxgrid").jqxGrid('getrowdata',
						 * editrow); $("#firstName").val(dataRecord.firstname);
						 * $("#lastName").val(dataRecord.lastname);
						 * $("#product").val(dataRecord.productname);
						 * $("#quantity").jqxNumberInput({ decimal:
						 * dataRecord.quantity }); $("#price").jqxNumberInput({
						 * decimal: dataRecord.price });
						 *  // show the popup window.
						 * $("#popupWindow").jqxWindow('open');
						 */
						// }
						// }
						/* { text: 'Remarks', datafield: 'remarks', width: 120 } */
						]
					});
	
	
}

var initrowdetails = function (index, parentElement, gridElement, datarecord) {
	var tabsdiv = null;
    var information = null;
    var notes = null;
    var jobpayments = null;
    var jobcompletion = null;
    tabsdiv = $($(parentElement).children()[0]);
    if (tabsdiv != null) {
    	information = tabsdiv.find('.information');
        notes = tabsdiv.find('.notes');
        var title = tabsdiv.find('.title');
        title.text("Appoinment Details");
        var container = $('<div style="margin: 5px;"></div>');
        container.appendTo($(information));
        var notescontainer = $('<div style="white-space: normal; margin: 5px;"><span>' + datarecord.remarks + '</span></div>');
        $(notes).append(notescontainer);
        $(tabsdiv).jqxTabs({ width: 600, height: 170});
    }
}

