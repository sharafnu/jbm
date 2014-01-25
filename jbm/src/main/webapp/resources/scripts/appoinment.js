function setupAppointmentDetailsForm() {
	loadPendingAppoinmentCombo();
	$("#updateAppointmentButton").jqxButton({
		theme : theme,
		disabled: true
	});
	 $('#appoinmentDetailsTab').jqxTabs({ width: '100%', height: 360, position: 'top'});
	 $('#appoinmentDetailsTab').jqxTabs({ animationType: 'fade' });
	 $('#appoinmentDetailsTab').jqxTabs({ selectionTracker: true });
	 //$('#appoinmentDetailsTab').jqxTabs('disableAt', 1);
	loadAppoinmentStatusCombo();
	loadCustomerContractCombo(-1);
	var endHourListArr = ["12:00 pm","01:00 pm","01:30 pm","02:00 pm","02:30 pm","03:00 pm","03:30 pm","04:00 pm","04:30 pm","05:00 pm","05:30 pm","06:00 pm","06:30 pm","07:00 pm","07:30 pm","08:00 pm","08:30 pm","09:00 pm","09:30 pm","10:00 pm","10:30 pm","11:00 pm"];

	$("#formEndTime").jqxComboBox({
			selectedIndex : -1,
			source : endHourListArr,
			width : 130,
			height : 20
	});

	 
	 $("#formHoursSpent").jqxInput({
			width : '130px',
			height : '20px',
			disabled: true 
	});
	 
	 $("#formAmountPayable").jqxInput({
			width : '130px',
			height : '20px'
	 });
	 
	 loadPaymentTypeCombo(); 
	 loadPaymentStatusCombo();
	 
	 $("#formInvoiceNo").jqxInput({
			width : '130px',
			height : '20px'
	 });
	 
	 
	 $("#formInvoiceDate").jqxDateTimeInput({
			width : '130px',
			height : '20px',
			formatString: 'dd-MM-yyyy' 
	});
	 
	 /*$("#formInvoiceAmount").jqxInput({
			width : '180px',
			height : '20px'
	 });*/
	 
	 //loadFinanceStatusCombo();
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
	var paymentTypeSource = [ "Contract", "Cash", "Credt Card", "Debit Card", "Bank Transfer", "Cheque", "DD" ];
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
	loadAppoinmentStatusComboAll();
	setupSearchButton();
}

function setupAppointmentForm() {
	loadCustomerCombo(280);
	loadEmployeeCombo(280);
	loadCustomerAddressCombo(280, -1);
	//loadAreaCombo();
	// Create jqxInput.
	/*$("#formJobId").jqxInput({
		width : '200px',
		height : '20px'
	});*/

	$("#formAppointmentDate").jqxDateTimeInput({
		width : '130px',
		height : '20px',
		formatString: 'dd-MM-yyyy' 
	});
	
	/*$("#formStartTime").jqxDateTimeInput({
		width : '130px',
		height : '20px',
		formatString: 'hh:mm tt',
		showCalendarButton: false
	});
	
	$("#formStartTime").jqxDateTimeInput('setDate', new Date());*/
	
	var startHourListArr = ["08:00 am","09:00 am","09:30 am","10:00 am","10:30 am","11:00 am","11:30 am","12:00 pm","01:00 pm","01:30 pm","02:00 pm","02:30 pm","03:00 pm","03:30 pm","04:00 pm","04:30 pm","05:00 pm","05:30 pm","06:00 pm","06:30 pm"];
	var endHourListArr = ["12:00 pm","01:00 pm","01:30 pm","02:00 pm","02:30 pm","03:00 pm","03:30 pm","04:00 pm","04:30 pm","05:00 pm","05:30 pm","06:00 pm","06:30 pm","07:00 pm","07:30 pm","08:00 pm","08:30 pm","09:00 pm","09:30 pm","10:00 pm","10:30 pm","11:00 pm"];
	/*var startHourListArr = [
                  {value: "09:00 am", label: "09:00 am"},
                  {value: "09:30 am", label: "09:30 am"},
                  {value: "10:00 am", label: "10:00 am"},
                  {value: "10:30 am", label: "10:30 am"},
                  {value: "11:00 am", label: "11:00 am"},
                  {value: "11:30 am", label: "11:30 am"},
                  {value: "12:00 pm", label: "12:00 pm"},
                  {value: "12:30 pm", label: "12:30 pm"},
                  {value: "13:00 pm", label: "01:00 pm"},
                  {value: "13:30 pm", label: "01:30 pm"},
                  {value: "14:00 pm", label: "02:00 pm"},
                  {value: "14:30 pm", label: "02:30 pm"},
                  {value: "15:00 pm", label: "03:00 pm"},
                  {value: "15:30 pm", label: "03:30 pm"},
                  {value: "16:00 pm", label: "04:00 pm"},
                  {value: "16:30 pm", label: "04:30 pm"},
                  {value: "17:00 pm", label: "05:00 pm"},
                  {value: "17:30 pm", label: "05:30 pm"},
                  {value: "18:00 pm", label: "06:00 pm"},
                  {value: "18:30 pm", label: "06:30 pm"},
                  {value: " pm", label: "07:00 pm"}
		        ];
	
	var endHourListArr = [
        {value: "13:00 pm", label: "01:00 pm"},
        {value: "13:30 pm", label: "01:30 pm"},
        {value: "14:00 pm", label: "02:00 pm"},
        {value: "14:30 pm", label: "02:30 pm"},
        {value: "15:00 pm", label: "03:00 pm"},
        {value: "15:30 pm", label: "03:30 pm"},
        {value: "16:00 pm", label: "04:00 pm"},
        {value: "16:30 pm", label: "04:30 pm"},
        {value: "17:00 pm", label: "05:00 pm"},
        {value: "17:30 pm", label: "05:30 pm"},
        {value: "18:00 pm", label: "06:00 pm"},
        {value: "18:30 pm", label: "06:30 pm"},
        {value: "19:00 pm", label: "07:00 pm"},
        {value: "19:30 pm", label: "07:30 pm"},
        {value: "20:00 pm", label: "08:00 pm"},
        {value: "20:30 pm", label: "08:30 pm"},
        {value: "21:00 pm", label: "09:00 pm"},
        {value: "21:30 pm", label: "09:30 pm"},
        {value: "22:00 pm", label: "10:00 pm"},
        {value: "22:30 pm", label: "10:30 pm"},
        {value: "23:00 pm", label: "11:00 pm"}
    ];*/
	
	$("#formStartTime").jqxComboBox({
		selectedIndex : -1,
		source : startHourListArr,
		width : 130,
		height : 20
	});
	$("#formEndTime").jqxComboBox({
		selectedIndex : -1,
		source : endHourListArr,
		width : 130,
		height : 20
	});
	
	$("#showCalendarLink").click(function (event) {
		//$('#detailedCalendar').fullCalendar('destroy'); 
		//setupDetailedCalendar();
		$('#detailedCalendarInfoPopupWindow').jqxWindow('show');
	});
	
	//$("#staffCalendar").jqxCalendar({ enableTooltips: false, width: 360, height: 340, theme: 'orange'});
	 var date1 = new Date();
     var date2 = new Date();
     var date3 = new Date();
     date1.setDate(5);
     date2.setDate(15);
     date3.setDate(16);
	//$("#staffCalendar").jqxCalendar('addSpecialDate', date1, '', '<div class="numberCircle">30</div>');
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
		$("#endTime").val($("#formEndTime").val());
		
		$("#remarks").val($("#formRemarks").val());
		
		
		$('#appoinmentAddForm').submit();
    });
	
	var addNewCustomerPopuptButton = $("#addNewCustomerPopuptButton").jqxButton({
		theme : theme
	});
	
	addNewCustomerPopuptButton.click(function (event) {
		$('#addCustomerPopupWindow').jqxWindow('show');
	});
	
	var addNewCustomerPopuptButton = $("#addNewCustomerPopuptButton").jqxButton({
		theme : theme
	});
	
	addNewCustomerPopuptButton.click(function (event) {
		$('#addCustomerPopupWindow').jqxWindow('show');
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
		//autoComplete: true,
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

function loadCustomerAddressCombo(comboWidth, customerId) {
	var customerAddressListUrl = "getCustomerAddressListJSON/"+customerId+".html";
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
		//autoComplete: true,
		width : comboWidth,
		height : 20,
		renderSelectedItem: function(index, item) {
			var item = customerAddressListDataAdapter.records[index];
			return item.addressType+", "+item.buildingName +", "+item.flatNo+", "+item.areaName+", "+item.cityName;   
        }
	});
}

function loadEmployeeCombo(comboWidth) {
	var employeeListUrl = "activeStaffListJSON.html";
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

function loadCustomerContractCombo(customerId) {
	var customerContractURL = "geCustomerActiveContractListByCustomerId/"+customerId+".html";
	var customerContractSource = {
			datatype : "json",
			datafields : [ {
				name : 'contractNo',
				type : 'string'
			},{
				name : 'contractType',
				type : 'string'
			}, 
			{
				name : 'contractDate',
				type : 'string'
			}, {
				name : 'visitCount',
				type : 'int'
			}, {
				name : 'amount',
				type : 'int'
			}, {
				name : 'id',
				type : 'int'
			} ],
			id : 'id',
			url : customerContractURL
		};
	
	var customerContractListAdapter = new $.jqx.dataAdapter(
			customerContractSource);

	$("#formCustomerContractId").jqxComboBox({
		selectedIndex : -1,
		source : customerContractListAdapter,
		displayMember : "comboBoxText",
		valueMember : "id",
		searchMode: "containsignorecase",
		//autoComplete: true,
		width : 230,
		height : 20,
		renderSelectedItem: function(index, item) {
			var item = customerContractListAdapter.records[index];
			return item.contractNo+" - "+item.contractType +", "+item.contractDate+" / "+item.visitCount+", "+item.amount;   
        }
	});
	
}

function loadAppoinmentCombo() {
	var appointmentListUrl = "customerAppoinmentComboListJSON.html";
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
		searchMode: "containsignorecase",
		source : appointmentListDataAdapter,
		displayMember : "appointmentNo",
		valueMember : "id",
		width : 130,
		height : 20
	});
}

function loadPendingAppoinmentCombo() {
	var appointmentListUrl = "customerPendingAppoinmentComboListJSON.html";
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
		searchMode: "containsignorecase",
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
		height : '20px',
		formatString: 'dd-MM-yyyy' 
	});
	$('#formFromDate').jqxDateTimeInput({allowNullDate: true});
	$('#formFromDate').jqxDateTimeInput({ value: new Date(2013, 0, 1) });
	
	$("#formToDate").jqxDateTimeInput({
		width : '130px',
		height : '20px',
		formatString: 'dd-MM-yyyy' 
	});
	$('#formToDate').jqxDateTimeInput({allowNullDate: true});
}

function loadAppoinmentStatusCombo() {
	var appoinmentStatusSource = [  "Completed", "Cancelled" ];
	$("#formAppoinmentStatus").jqxComboBox({
		selectedIndex : -1,
		source : appoinmentStatusSource,
		width : 210,
		height : 20
	});
}

function loadAppoinmentStatusComboAll() {
	var appoinmentStatusSource = [  "Created", "Completed", "Cancelled" ];
	$("#formAppoinmentStatus").jqxComboBox({
		selectedIndex : 0,
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
		height : '20px',
		formatString: 'dd-MM-yyyy' 
	});

	$("#frmExpiryDate").jqxDateTimeInput({
		width : '200px',
		height : '20px',
		formatString: 'dd-MM-yyyy' 
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
	var appointmentViewListJSONStr = $("#appointmentViewListJSON").val();

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
		}, {
			name : 'cancellationReason',
			type : 'string'
		}, {
			name : 'hoursSpent',
			type : 'int'
		}, {
			name : 'payableAmount',
			type : 'int'
		}, {
			name : 'paymentStatus',
			type : 'string'
		}, {
			name : 'paymentType',
			type : 'string'
		}, {
			name : 'invoiceNo',
			type : 'string'
		}, {
			name : 'startTime',
			type : 'string'
		}, {
			name : 'endTime',
			type : 'string'
		}, {
			name : 'areaName',
			type : 'string'
		}, {
			name : 'buildingName',
			type : 'string'
		}, {
			name : 'flatNo',
			type : 'string'
		}, {
			name : 'cityName',
			type : 'string'
		}, {
			name : 'id',
			type : 'string'
		}, {
			name : 'invoiceDate',
			type : 'string'
		} ],
		id : 'id',
		localdata : appointmentViewListJSONStr
	};
	var appoinmentDataAdapter = new $.jqx.dataAdapter(appoinmentListSource);

	var imagerenderer = function(row, datafield, value) {
		var url = "customerAppointmentDetailsForSelectedId.html?appointmentId="+value;
		return "<a href='"+url+"' ><img style='margin-left: 5px;margin-top: 5px;' src='resources/images/card.png'/></a>";
		return "";
	}
	// initialize jqxGrid
	$("#jqxgrid")
			.jqxGrid(
					{
						width : 780,
						source : appoinmentDataAdapter,
						showstatusbar : true,
						rowdetails: true,
						rowdetailstemplate: { rowdetails: "<div style='margin: 10px;'><ul style='margin-left: 30px;'><li class='title'></li><li>Notes</li><li>Job Completion</li></ul><div class='information'></div><div class='notes'></div><div class='jobcompletion'></div></div>", rowdetailsheight: 100 },
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
							datafield : 'id',
							width : 25,
							cellsrenderer : imagerenderer
						}
						]
					});
	
	$('#jqxGrid').on('rowclick', function (event) 
			{
			    var args = event.args;
			    var row = args.rowindex;
			    alert(row);
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
    	jobcompletion = tabsdiv.find('.jobcompletion');
        notes = tabsdiv.find('.notes');
        var title = tabsdiv.find('.title');
        title.text("Appoinment Details");
        
        var infoTable = '<div style="margin: 5px;"><table class="descTable" width="100%" border="0" cellpadding="0" cellspacing="5">';
        infoTable = infoTable + '<tr><td align="right"><b>Location :</b></td><td>'+datarecord.areaName+ '</td> <td align="right"> <b>Building : </b></td><td> '+datarecord.buildingName+' </td><td align="right"> <b>Flat :</b> </td><td> '+datarecord.flatNo+' </td></tr>';
        infoTable = infoTable + '<tr><td align="right"><b>Time Slot :</b></td><td>'+datarecord.startTime+ ' - '+datarecord.endTime+'</td> <td align="right"> <b>Remarks : </b></td><td colspan="3"> '+datarecord.remarks+' </td></tr>';
        infoTable = infoTable +'</table></div>';
       
        var infoContainer = $(infoTable);
        infoContainer.appendTo($(information));
        
        if(datarecord.appointmentStatus != "Created") {
        	var completionTable = '<div style="margin: 5px;"><table class="descTable" width="100%" border="0" cellpadding="0" cellspacing="5">';
            completionTable = completionTable + '<tr><td align="right"><b>No of Hrs. :</b></td><td>'+datarecord.hoursSpent+ '</td> <td align="right"> <b>Amount : </b></td><td> '+datarecord.payableAmount+' </td><td align="right"> <b>Payment Status :</b> </td><td> '+datarecord.paymentStatus+' </td></tr>';
            completionTable = completionTable + '<tr><td align="right"><b>Payment Type :</b></td><td>'+datarecord.paymentType+ '</td> <td align="right"> <b>Invoice No. : </b></td><td> '+datarecord.invoiceNo+' </td><td align="right"> <b>Invoice Date : </b></td><td> '+datarecord.invoiceDate+' </td></tr>';
            completionTable = completionTable +'</table></div>';
            
            var jobCompletionContainer = $(completionTable);
            jobCompletionContainer.appendTo($(jobcompletion));
            
            var notescontainer = $('<div style="white-space: normal; margin: 5px;"><span> <b>Cancellation Remarks : </b>' + datarecord.cancellationReason + '</span></div>');
            $(notes).append(notescontainer);
        } 
        
        $(tabsdiv).jqxTabs({ width: 700, height: 200});
    }
}

