
function setupAppoinmentListSearchFilters() {
	loadCustomerCombo(210);
	loadEmployeeCombo(210);
	loadAreaCombo();
	loadAppoinmentCombo();
	setupDateFilters();
	loadAppoinmentStatusComboAll();
	setupSearchButton();
}
//1
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
	var customerListDataAdapter = new $.jqx.dataAdapter(customerListSource, {
		loadComplete : function() {
			var indexToSelect = -1;
			var valueToFind = $("#customerId").val();
			if(valueToFind != "") {
				var items = $("#formCustomerId").jqxComboBox("getItems");
				$.each(items, function(index) {
					if (this.value == valueToFind) {
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

	$('#formCustomerId').on('change', function (event) {
		var args = event.args;
		if(args == undefined) {
			$('#formCustomerId').val("");
			$("#formCustomerId").jqxComboBox({ selectedIndex: -1 });
		} 
	});	
}

//2
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
	var employeeListDataAdapter = new $.jqx.dataAdapter(employeeListSource, {
		loadComplete : function() {
			var indexToSelect = -1;
			var valueToFind = $("#employeeId").val();
			if(valueToFind != "") {
				var items = $("#formEmployeeId").jqxComboBox("getItems");
				$.each(items, function(index) {
					if (this.value == valueToFind) {
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
	
	$('#formEmployeeId').on('change', function (event) {
		var args = event.args;
		if(args == undefined) {
			$('#formEmployeeId').val("");
			$("#formEmployeeId").jqxComboBox({ selectedIndex: -1 });
		} 
	});	
}

//3
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
	var areaListDataAdapter = new $.jqx.dataAdapter(areaListSource, {
		loadComplete : function() {
			var indexToSelect = -1;
			var valueToFind = $("#areaId").val();
			if(valueToFind != "") {
				var items = $("#formAreaId").jqxComboBox("getItems");
				$.each(items, function(index) {
					if (this.value == valueToFind) {
					    indexToSelect = index;
					    return false;
					}
				});
				$("#formAreaId").jqxComboBox({ selectedIndex: indexToSelect });
			}
		}
	});

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
	
	$('#formAreaId').on('change', function (event) {
		var args = event.args;
		if(args == undefined) {
			$('#formAreaId').val("");
			$("#formAreaId").jqxComboBox({ selectedIndex: -1 });
		} 
	});
}

//4
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
			appointmentListSource, {
				loadComplete : function() {
					var indexToSelect = -1;
					var valueToFind = $("#id").val();
					if(valueToFind != "") {
						var items = $("#formAppointmentId").jqxComboBox("getItems");
						$.each(items, function(index) {
							if (this.value == valueToFind) {
							    indexToSelect = index;
							    return false;
							}
						});
						$("#formAppointmentId").jqxComboBox({ selectedIndex: indexToSelect });
					}
				}
			});

	$("#formAppointmentId").jqxComboBox({
		selectedIndex : -1,
		searchMode: "containsignorecase",
		source : appointmentListDataAdapter,
		displayMember : "appointmentNo",
		valueMember : "id",
		width : 130,
		height : 20
	});
	
	$('#formAppointmentId').on('change', function (event) {
		var args = event.args;
		if(args == undefined) {
			$('#formAppointmentId').val("");
			$("#formAppointmentId").jqxComboBox({ selectedIndex: -1 });
		} 
	});
}

//5
function setupDateFilters() {
	$("#formFromDate").jqxDateTimeInput({
		width : '130px',
		height : '20px',
		formatString: 'dd-MM-yyyy' ,
		showFooter : true
	});
	$('#formFromDate').jqxDateTimeInput({allowNullDate: true});
	if($('#startDate').val() != "") {
		$('#formFromDate').jqxDateTimeInput({ value: new Date($('#startDate').val()) });	
	} else {
		$('#formFromDate').val(null);
	}
	$("#formToDate").jqxDateTimeInput({
		width : '130px',
		height : '20px',
		formatString: 'dd-MM-yyyy',
		showFooter : true
	});
	if($('#endDate').val() != "") {
		$('#formToDate').jqxDateTimeInput({ value: new Date($('#endDate').val()) });	
	} else {
		$('#formToDate').val(null);
	}
	$('#formToDate').jqxDateTimeInput({allowNullDate: true});
}

//6
function loadAppoinmentStatusComboAll() {
	var appoinmentStatusSource = [  "All", "Created", "Completed", "Cancelled" ];
	$("#formAppoinmentStatus").jqxComboBox({
		selectedIndex : 1,
		source : appoinmentStatusSource,
		width : 210,
		height : 20
	});
}

//7
function setupSearchButton() {
	$("#searchButton").jqxButton({
		theme : theme
	});
	$("#searchButton").click(
			function() {
				// $('#appoinmentSearchForm').jqxValidator('validate');
				
				$("#id").val("");
				$("#areaId").val("");
				$("#customerId").val("");
				$("#employeeId").val("");
				$("#appointmentStatus").val("");
				$("#startDate").val("");
				$("#endDate").val("");	
				
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
	
	$("#resetButton").jqxButton({
		theme : theme
	});
	$("#resetButton").click(
			function() {
				
				$('#formAppointmentId').val("");
				$("#formAppointmentId").jqxComboBox({ selectedIndex: -1 });
				
				$('#formCustomerId').val("");
				$("#formCustomerId").jqxComboBox({ selectedIndex: -1 });
				
				$('#formEmployeeId').val("");
				$("#formEmployeeId").jqxComboBox({ selectedIndex: -1 });
				
				$('#formAppointmentId').val("");
				$("#formAppointmentId").jqxComboBox({ selectedIndex: -1 });
				
				$('#formFromDate').val(null);
				$('#formToDate').val(null);
				
				$("#id").val("");
				$("#areaId").val("");
				$("#customerId").val("");
				$("#employeeId").val("");
				$("#appointmentStatus").val("");
				$("#startDate").val("");
				$("#endDate").val("");				
	});
	
}

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
		if(appoinmentDataAdapter.records[row].appointmentStatus == "Created") {
			var updateURL = "customerAppointmentDetailsForSelectedId.html?appointmentId="+value;
			var editURL = "customerApointmentEdit.html?appointmentId="+value;
			
			return "<span><a href='"+updateURL+"' ><img title='Update Appointment Details' width='16px' height='16px' style='margin-left: 5px;margin-top: 5px;' src='resources/images/card.png'/></a><a title='Edit Appointment' href='"+editURL+"' ><img style='margin-left: 5px;margin-top: 5px;' width='16px' height='16px' src='resources/images/edit-icon.png'/></a></span>";			
		} else {
			return "";
		}
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
							text : 'Appnmt. No.',
							datafield : 'appointmentNo',
							width : 100
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
							width : 45,
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
           
        } 
        var notesContent = '<div style="white-space: normal; margin: 5px;"><span> <b>Remarks : </b>' + datarecord.remarks + '</span></div>';
        if(datarecord.appointmentStatus == "Cancelled") {
        	notesContent = notesContent + '<div style="white-space: normal; margin: 5px;"><span> <b>Cancellation Reason : </b>' + datarecord.cancellationReason + '</span></div>';
        }
       var notescontainer = $(notesContent);        
        $(notes).append(notescontainer);
        $(tabsdiv).jqxTabs({ width: 700, height: 200});
    }
}