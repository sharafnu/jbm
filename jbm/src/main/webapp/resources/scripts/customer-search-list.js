
function setupCustomerListSearchFilters() {
	loadCustomerCombo(210);
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



//7
function setupSearchButton() {
	$("#searchButton").jqxButton({
		theme : theme
	});
	$("#searchButton").click(
			function() {
				$("#customerId").val("");
				
				var customerIdCombo = $("#formCustomerId").jqxComboBox('getSelectedItem'); 	
				if(customerIdCombo  != null) {
					$("#customerId").val(customerIdCombo.value);
				}
				
				$('#customerSearchForm').submit();
			});
	
	$("#resetButton").jqxButton({
		theme : theme
	});
	$("#resetButton").click(
			function() {
				
				$('#formCustomerId').val("");
				$("#formCustomerId").jqxComboBox({ selectedIndex: -1 });				
				$("#customerId").val("");
	});
	
}

function customerDetailsGrid() {
	var customerViewListJSONStr = $("#customerViewListJSON").val();

	var customerListSource = {
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
		pagesize: 20,
		id : 'id',
		localdata : customerViewListJSONStr
	};
	var customerDataAdapter = new $.jqx.dataAdapter(customerListSource);

	var imagerenderer = function(row, datafield, value) {
		if(customerDataAdapter.records[row].appointmentStatus == "Created") {
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
						source : customerDataAdapter,
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