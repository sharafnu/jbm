
function numberonly(evt) {
	  var theEvent = evt || window.event;
	  var key = theEvent.keyCode || theEvent.which;
	  key = String.fromCharCode( key );
	  var regex = /[0-9]|\./;
	  if( !regex.test(key) ) {
	    theEvent.returnValue = false;
	    if(theEvent.preventDefault) theEvent.preventDefault();
	  }
	}

function setupAppointmentDetailsForm() {
	loadPendingAppoinmentCombo();
	$("#updateAppointmentButton").jqxButton({
		theme : theme,
		disabled: true
	});
	 $('#appoinmentDetailsTab').jqxTabs({ width: '100%', height: 370, position: 'top'});
	 $('#appoinmentDetailsTab').jqxTabs({ animationType: 'fade' });
	 $('#appoinmentDetailsTab').jqxTabs({ selectionTracker: true });
	 //$('#appoinmentDetailsTab').jqxTabs('disableAt', 1);
	loadAppoinmentStatusCombo();
	loadCustomerContractCombo(-1);
	
	
	$("#sendCompletionSMSYes").jqxRadioButton({ groupName: '1', width: 70, height: 10});
	$("#sendCompletionSMSNo").jqxRadioButton({ groupName: '1', width: 70, height: 10});
	
	if($("#appointmentCompletionSMSFlag").val() == "Yes") {
		$('#sendCompletionSMSYes').jqxRadioButton('check');
	} else {
		$('#sendCompletionSMSNo').jqxRadioButton('check');
	}
	
	$("#sendCancellationSMSYes").jqxRadioButton({ groupName: '2', width: 70, height: 10});
	$("#sendCancellationSMSNo").jqxRadioButton({ groupName: '2', width: 70, height: 10});
	
	if($("#appointmentCancellationSMSFlag").val() == "Yes") {
		$('#sendCancellationSMSYes').jqxRadioButton('check');
	} else {
		$('#sendCancellationSMSNo').jqxRadioButton('check');
	}
	
	
	//var endHourListArr = ["12:00 PM","12:30 PM","01:00 PM","01:30 PM","02:00 PM","02:30 PM","03:00 PM","03:30 PM","04:00 PM","04:30 PM","05:00 PM","05:30 PM","06:00 PM","06:30 PM","07:00 PM","07:30 PM","08:00 PM","08:30 PM","09:00 PM","09:30 PM","10:00 PM","10:30 PM","11:00 PM"];
	var endHourListArr = ["00:00 AM","00:30 AM","01:00 AM","01:30 AM","02:00 AM","02:30 AM","03:00 AM","03:30 AM","04:00 AM","04:30 AM","05:00 AM","05:30 AM","06:00 AM","06:30 AM","07:00 AM","07:30 AM","08:00 AM","08:30 AM","09:00 AM","09:30 AM","10:00 AM","10:30 AM","11:00 AM","11:30 AM","12:00 PM","12:30 PM","01:00 PM","01:30 PM","02:00 PM","02:30 PM","03:00 PM","03:30 PM","04:00 PM","04:30 PM","05:00 PM","05:30 PM","06:00 PM","06:30 PM","07:00 PM","07:30 PM","08:00 PM","08:30 PM","09:00 PM","09:30 PM","10:00 PM","10:30 PM","11:00 PM","11:30 PM"];
	
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
	 
	 $("#formAmountPayable").jqxNumberInput({ width: '130px', height: '20px', spinButtons: false, inputMode: 'simple' });

/*	 $("#formAmountPayable").jqxNumberInput({
			width : '130px',
			height : '20px'
	 });*/
	 
	 loadPaymentTypeCombo(); 
	 loadPaymentStatusCombo();
	 
	 $("#formInvoiceNo").jqxInput({
			width : '130px',
			height : '20px'
	 });
	 
	 
	 $("#formInvoiceDate").jqxDateTimeInput({
			width : '130px',
			height : '20px',
			formatString: 'dd-MM-yyyy'//,
			//min: new Date()
	});
	 
	 $("#formPaymentDetails").jqxInput({
			width : '210px',
			height : '20px'
	 });
	 
	 
	 $("#formDueDate").jqxDateTimeInput({
			width : '130px',
			height : '20px',
			formatString: 'dd-MM-yyyy'
			//minDate: $.jqx._jqxDateTimeInput.getDateTime(new Date())
			//min: new Date()			
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
	loadAllAppoinmentCombo();
	setupDateFilters();
	loadAppoinmentStatusComboAll();
	setupSearchButton();
}

function setupAppointmentForm() {
	loadCustomerCombo(280);
	loadEmployeeCombo(280);
	loadCustomerAddressCombo(280, -1);
	$("#sendSMSYes").jqxRadioButton({ width: 70, height: 10});
	$("#sendSMSNo").jqxRadioButton({ width: 70, height: 10});
	if($("#appointmentCreationSMSFlag").val() == "Yes") {
		$('#sendSMSYes').jqxRadioButton('check');
	} else {
		$('#sendSMSNo').jqxRadioButton('check');
	}

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
	
	/*var startHourListArr = ["08:00 AM","09:00 AM","09:30 AM","10:00 AM","10:30 AM","11:00 AM","11:30 AM","12:00 PM","01:00 PM","01:30 PM","02:00 PM","02:30 PM","03:00 PM","03:30 PM","04:00 PM","04:30 PM","05:00 PM","05:30 PM","06:00 PM","06:30 PM"];
	var endHourListArr = ["12:00 PM","01:00 PM","01:30 PM","02:00 PM","02:30 PM","03:00 PM","03:30 PM","04:00 PM","04:30 PM","05:00 PM","05:30 PM","06:00 PM","06:30 PM","07:00 PM","07:30 PM","08:00 PM","08:30 PM","09:00 PM","09:30 PM","10:00 PM","10:30 PM","11:00 PM"];*/
	
	var startHourListArr = ["00:00 AM","00:30 AM","01:00 AM","01:30 AM","02:00 AM","02:30 AM","03:00 AM","03:30 AM","04:00 AM","04:30 AM","05:00 AM","05:30 AM","06:00 AM","06:30 AM","07:00 AM","07:30 AM","08:00 AM","08:30 AM","09:00 AM","09:30 AM","10:00 AM","10:30 AM","11:00 AM","11:30 AM","12:00 PM","12:30 PM","01:00 PM","01:30 PM","02:00 PM","02:30 PM","03:00 PM","03:30 PM","04:00 PM","04:30 PM","05:00 PM","05:30 PM","06:00 PM","06:30 PM","07:00 PM","07:30 PM","08:00 PM","08:30 PM","09:00 PM","09:30 PM","10:00 PM","10:30 PM","11:00 PM","11:30 PM"];
	var endHourListArr = ["00:00 AM","00:30 AM","01:00 AM","01:30 AM","02:00 AM","02:30 AM","03:00 AM","03:30 AM","04:00 AM","04:30 AM","05:00 AM","05:30 AM","06:00 AM","06:30 AM","07:00 AM","07:30 AM","08:00 AM","08:30 AM","09:00 AM","09:30 AM","10:00 AM","10:30 AM","11:00 AM","11:30 AM","12:00 PM","12:30 PM","01:00 PM","01:30 PM","02:00 PM","02:30 PM","03:00 PM","03:30 PM","04:00 PM","04:30 PM","05:00 PM","05:30 PM","06:00 PM","06:30 PM","07:00 PM","07:30 PM","08:00 PM","08:30 PM","09:00 PM","09:30 PM","10:00 PM","10:30 PM","11:00 PM","11:30 PM"];
	
	/*var startHourListArr = [
                  {value: "09:00 AM", label: "09:00 AM"},
                  {value: "09:30 AM", label: "09:30 AM"},
                  {value: "10:00 AM", label: "10:00 AM"},
                  {value: "10:30 AM", label: "10:30 AM"},
                  {value: "11:00 AM", label: "11:00 AM"},
                  {value: "11:30 AM", label: "11:30 AM"},
                  {value: "12:00 PM", label: "12:00 PM"},
                  {value: "12:30 PM", label: "12:30 PM"},
                  {value: "13:00 PM", label: "01:00 PM"},
                  {value: "13:30 PM", label: "01:30 PM"},
                  {value: "14:00 PM", label: "02:00 PM"},
                  {value: "14:30 PM", label: "02:30 PM"},
                  {value: "15:00 PM", label: "03:00 PM"},
                  {value: "15:30 PM", label: "03:30 PM"},
                  {value: "16:00 PM", label: "04:00 PM"},
                  {value: "16:30 PM", label: "04:30 PM"},
                  {value: "17:00 PM", label: "05:00 PM"},
                  {value: "17:30 PM", label: "05:30 PM"},
                  {value: "18:00 PM", label: "06:00 PM"},
                  {value: "18:30 PM", label: "06:30 PM"},
                  {value: " PM", label: "07:00 PM"}
		        ];
	
	var endHourListArr = [
        {value: "13:00 PM", label: "01:00 PM"},
        {value: "13:30 PM", label: "01:30 PM"},
        {value: "14:00 PM", label: "02:00 PM"},
        {value: "14:30 PM", label: "02:30 PM"},
        {value: "15:00 PM", label: "03:00 PM"},
        {value: "15:30 PM", label: "03:30 PM"},
        {value: "16:00 PM", label: "04:00 PM"},
        {value: "16:30 PM", label: "04:30 PM"},
        {value: "17:00 PM", label: "05:00 PM"},
        {value: "17:30 PM", label: "05:30 PM"},
        {value: "18:00 PM", label: "06:00 PM"},
        {value: "18:30 PM", label: "06:30 PM"},
        {value: "19:00 PM", label: "07:00 PM"},
        {value: "19:30 PM", label: "07:30 PM"},
        {value: "20:00 PM", label: "08:00 PM"},
        {value: "20:30 PM", label: "08:30 PM"},
        {value: "21:00 PM", label: "09:00 PM"},
        {value: "21:30 PM", label: "09:30 PM"},
        {value: "22:00 PM", label: "10:00 PM"},
        {value: "22:30 PM", label: "10:30 PM"},
        {value: "23:00 PM", label: "11:00 PM"}
    ];*/
	
	$("#formStartTime").jqxComboBox({
		selectedIndex : -1,
		source : startHourListArr,
		width : 120,
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
		
		if($("#formAppointmentDate").val() == null || $("#formAppointmentDate").val() =="") {
			jqxAlert.alert('Please choose appointment date !');
			return;
		}
		
		var employeeIdCombo = $("#formEmployeeId").jqxComboBox('getSelectedItem'); 	
		if(employeeIdCombo  != null) {
			$("#employeeId").val(employeeIdCombo.value);
		} else {
			jqxAlert.alert('Please select staff !');
			return;
		}
		
		if($("#formStartTime").val() == null || $("#formStartTime").val() =="") {
			jqxAlert.alert('Please choose start time !');
			return;
		}
		
		if($("#formEndTime").val() == null || $("#formEndTime").val() =="") {
			jqxAlert.alert('Please choose end time !');
			return;
		}
		
		var customerIdCombo = $("#formCustomerId").jqxComboBox('getSelectedItem'); 	
		if(customerIdCombo  != null) {
			$("#customerId").val(customerIdCombo.value);
		} else {
			jqxAlert.alert('Please select customer !');
			return;
		}
		
		var customerAddressIdCombo = $("#formCustomerAddressId").jqxComboBox('getSelectedItem'); 	
		if(customerAddressIdCombo  != null) {
			$("#customerAddressId").val(customerAddressIdCombo.value);
		} else {
			jqxAlert.alert('Please select customer appointment location !');
			return;
		}
		
		$('#appointmentMainForm').jqxValidator('validate');
		
		if($("#isValidSlot").val() == "false") {
			//$("#appointmentMainForm").jqxValidator('hide');
			jqxAlert.alert('Select staff is not available on the selected time slot !');
			return;
		}
		
		//alert($("#isNotDuplicate").val());
		
		if($("#isNotDuplicate").val() == "false") {
			//$("#appointmentMainForm").jqxValidator('hide');
			jqxAlert.alert('Customer already has an appointment on the selected address at the same time !');
			return;
		}
		//alert("test 123");
		//Do server side validation
		if(confirm("Do you want to save ?")) {
			isValidData = false;
			$.ajax({
				url: "validateAppointmentSave.html",
				type: 'GET',
				data: {appointmentDate: $("#formAppointmentDate").val(), startTime: $("#formStartTime").val(), endTime: $("#formEndTime").val()},
				success: function(actionStatus)
				{
					//alert(actionStatus.statusType);	
					if(actionStatus.statusType != "Success") {
						jqxAlert.alert(actionStatus.statusMessage);					
					} else {
						isValidData = true;
						$("#appointmentDate").val($("#formAppointmentDate").val());
						$("#startTime").val($("#formStartTime").val());
						$("#endTime").val($("#formEndTime").val());
						$("#remarks").val($("#formRemarks").val());
						
						if($("#sendSMSYes").val() == true) {
							$("#sendSMSFlag").val("Yes");
						} else {
							$("#sendSMSFlag").val("No");
						}
						
						
						$('#appoinmentAddForm').submit();
					}
				},
				error: function()
				{
					jqxAlert.alert("System Error Occured ! Please contact Support.");
				}
			});
		}
		
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
		var selectedAddress = $("#formCustomerAddressId").jqxComboBox('getSelectedItem');
		
		$('#addCustomerPopupWindow').jqxWindow('show');
	});
	
	/*var viewMapPopupButton = $("#viewMapPopupButton").jqxButton({
		theme : theme,
		width: '40px', height: '35px'
	});
	
	$('#viewMapPopupButton').jqxButton({disabled: true });
	
	viewMapPopupButton.click(function (event) {
		$('#viewGoogleMapPopupWindow').jqxWindow('show');
	});*/
	


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
}

function loadCustomerComboWithSelectedId(comboWidth, selectedId) {
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
	var customerListDataAdapter = new $.jqx.dataAdapter(customerListSource, {
		loadComplete : function() {
			var indexToSelect = -1;
			if(selectedId != "") {
				var items = $("#formCustomerId").jqxComboBox("getItems");
				$.each(items, function(index) {
					if (this.value == selectedId) {
					    indexToSelect = index;
					    return false;
					}
				});
				$("#formCustomerId").jqxComboBox({ selectedIndex: indexToSelect });
			}
		}
	});

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
			var address = item.addressType;
			if(item.buildingName != null && item.buildingName != "") {
				address += ","+item.buildingName;
			}
			if(item.flatNo != null && item.flatNo != "") {
				address += ","+item.flatNo;
			}
			if(item.areaName != null && item.areaName != "") {
				address += ","+item.buildingName;
			}
			if(item.cityName != null && item.cityName != "") {
				address += ","+item.cityName;
			}
			return address;   
        }
	});
}

function loadCustomerAddressComboWithSelectedId(comboWidth, customerId, customerAddressId) {
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
	var customerAddressListDataAdapter = new $.jqx.dataAdapter(customerAddressListSource, {
		loadComplete : function() {
			var indexToSelect = -1;
			if(customerAddressId != "") {
				var items = $("#formCustomerAddressId").jqxComboBox("getItems");
				$.each(items, function(index) {
					if (this.value == customerAddressId) {
					    indexToSelect = index;
					    return false;
					}
				});
				$("#formCustomerAddressId").jqxComboBox({ selectedIndex: indexToSelect });
			}
		}
	});

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

function loadEmployeeComboWithSelectedValue(comboWidth, employeeId) {
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
	var employeeListDataAdapter = new $.jqx.dataAdapter(employeeListSource, {
		loadComplete : function() {
			var indexToSelect = -1;
			if(employeeId != "") {
				var items = $("#formEmployeeId").jqxComboBox("getItems");
				$.each(items, function(index) {
					if (this.value == employeeId) {
					    indexToSelect = index;
					    return false;
					}
				});
				$("#formEmployeeId").jqxComboBox({ selectedIndex: indexToSelect });
			}
		}
	});

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
		width : 210,
		height : 20,
		renderSelectedItem: function(index, item) {
			var item = customerContractListAdapter.records[index];
			return item.contractNo+" - "+item.contractType +", "+item.contractDate+" / "+item.visitCount+", "+item.amount;   
        }
	});
	
}

function loadActiveAppoinmentCombo() {
	var appointmentListUrl = "customerAppoinmentComboListJSONActive.html";
	alert(appointmentListUrl);
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

function loadAllAppoinmentCombo() {
		var appointmentListUrl = "customerAppoinmentComboListJSON.html";
		alert(appointmentListUrl);
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
	var appoinmentStatusSource = [  "All", "Created", "Completed", "Cancelled" ];
	$("#formAppoinmentStatus").jqxComboBox({
		selectedIndex : 1,
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
					$("#id").val(appointmentNoCombo.value);
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

function setupAppointmentEditForm(customerId, employeeId, customerAddressId, startTime, endTime) {
	loadEmployeeComboWithSelectedValue(280, employeeId);
	loadCustomerAddressComboWithSelectedId(280, customerId, customerAddressId);
	

	$("#formAppointmentDate").jqxDateTimeInput({
		width : '130px',
		height : '20px',
		formatString: 'dd-MM-yyyy' 
	});
	
	var startHourListArr = ["08:00 AM","09:00 AM","09:30 AM","10:00 AM","10:30 AM","11:00 AM","11:30 AM","12:00 PM","01:00 PM","01:30 PM","02:00 PM","02:30 PM","03:00 PM","03:30 PM","04:00 PM","04:30 PM","05:00 PM","05:30 PM","06:00 PM","06:30 PM"];
	var endHourListArr = ["12:00 PM","01:00 PM","01:30 PM","02:00 PM","02:30 PM","03:00 PM","03:30 PM","04:00 PM","04:30 PM","05:00 PM","05:30 PM","06:00 PM","06:30 PM","07:00 PM","07:30 PM","08:00 PM","08:30 PM","09:00 PM","09:30 PM","10:00 PM","10:30 PM","11:00 PM"];
	
	$("#formStartTime").jqxComboBox({
		selectedIndex : -1,
		source : startHourListArr,
		width : 130,
		height : 20
	});
	$("#formStartTime").jqxComboBox('val', startTime);
	
	
	$("#formEndTime").jqxComboBox({
		selectedIndex : -1,
		source : endHourListArr,
		width : 130,
		height : 20
	});
	
	$("#formEndTime").jqxComboBox('val', endTime);
	
	$("#showCalendarLink").click(function (event) {
		$('#detailedCalendarInfoPopupWindow').jqxWindow('show');
	});
	
	 var date1 = new Date();
     var date2 = new Date();
     var date3 = new Date();
     date1.setDate(5);
     date2.setDate(15);
     date3.setDate(16);
	
     $('#formEmployeeId').on('change', function (event) {
		 var args = event.args;
         if (args) {
         	var item = args.item;
             if(item.value != "") {
             // Create Date objects.
                var date1 = new Date();
                var date2 = new Date();
                var date3 = new Date();
                date1.setDate(5);
                date2.setDate(15);
                date3.setDate(16);
             }
         }
	 });
	 
	var updateAppointmentButton = $("#updateAppointmentButton").jqxButton({
		theme : theme
	});
	updateAppointmentButton.click(function (event) {
		//$('#form').jqxValidator('validate');
		
		
		if($("#isValidSlot").val() == "false") {
			//$("#appointmentMainForm").jqxValidator('hide');
			jqxAlert.alert('Select staff is not available on the selected time slot !');
			return;
		}
		
		//alert($("#isNotDuplicate").val());
		
		if($("#isNotDuplicate").val() == "false") {
			//$("#appointmentMainForm").jqxValidator('hide');
			jqxAlert.alert('Customer already has an appointment on the selected address at the same time !');
			return;
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
		
		
		$('#appoinmentEditForm').submit();
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
}
