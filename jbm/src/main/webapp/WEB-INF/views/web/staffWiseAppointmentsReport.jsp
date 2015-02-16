<jsp:include page="includes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<span class="breadcrumbs">Home > Job Management > Staff Appointments </span>
</div>
<script type="text/javascript"
	src="<c:url value="/resources/scripts/appoinment.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		document.title = 'Staff Appointments Report';
		setupStatusCombo();
		setupReportDate();
		loadEmployeeComboWithSelectedValue(280, $("#employeeId").val());
		$("#downloadReportButton").jqxButton({
			theme : theme
		});
		
		$("#resetButton").jqxButton({
			theme : theme
		});
		
		$("#resetButton").click(
			function() {
				$("#startDateDiv").jqxDateTimeInput('setDate', new Date());
				$("#endDateDiv").jqxDateTimeInput('setDate', new Date());
				$("#statusComboBox").jqxComboBox('clearSelection');
				$("#formEmployeeId").jqxComboBox('clearSelection');
								
			}
		);
		$("#searchButton").jqxButton({
			theme : theme
		});
		
		$("#downloadReportButton").click(function() {
			if(validSelection()) {
				$('#reportForm').attr('action', 'staffWiseAppointmentsReport.html');
				$('#reportForm').submit();
			}			
		});
		
		$("#searchButton").click(function() {
			if(validSelection()) {
				$('#reportForm').attr('action', 'staffWiseAppointmentsReportList.html');
				$('#reportForm').submit();
			}	
		});
		
		setupList();
	});
	
	function validSelection() {
		if ($("#startDateDiv").val() == "") {
			alert("Please select start date");				
			return false;	
		}
		if ($("#endDateDiv").val() == "") {
			alert("Please select end date");				
			return false;	
		}
		$("#startDate").val($("#startDateDiv").val());
		
		$("#endDate").val($("#endDateDiv").val());
		
		var employeeIdCombo = $("#formEmployeeId").jqxComboBox('getSelectedItem'); 	
		if(employeeIdCombo  != null) {
			$("#employeeId").val(employeeIdCombo.value);
		} else {
			alert('Please select staff !');
			return;
		}
		
		var items = $("#statusComboBox").jqxComboBox('getSelectedItems');
        var selectedItems = "";
        $.each(items, function (index) {
            selectedItems += this.label;
            if (items.length - 1 != index) {
                selectedItems += ", ";
            }
        });
 		$("#appointmentStatus").val(selectedItems);
		return true;
	}
	
	function setupStatusCombo() {
		var statusList = new Array("Created", "Completed", "Cancelled");	
        $("#statusComboBox").jqxComboBox({source: statusList, multiSelect: true, width: 350, height: 25});           
       
        var selected = $("#appointmentStatus").val();
        if(selected != undefined && selected != null && selected!="") {
        	var selectedStatusList = selected.split(',');
        	$.each(selectedStatusList, function(key, aStatus) {
        		$("#statusComboBox").jqxComboBox('selectItem', aStatus);
        	});
        }  else {
        	 $("#statusComboBox").jqxComboBox('selectItem', 'Created');
        }
	}
	
	function setupList() {
		//var appointmentJSONURL = "customerAppointmentListJSON.html";
		var appointmentViewListJSONStr = $("#appointmentViewListJSON").val();
		
		var appoinmentReportListSource = {
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
					name : 'customerName',
					type : 'string'
				}, {
					name : 'employeeName',
					type : 'string'
				}, {
					name : 'hoursSpent',
					type : 'int'
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
					name : 'customerMobileNo',
					type : 'string'
				}, {
					name : 'addressType',
					type : 'string'
				}, {
					name : 'addressRemarks',
					type : 'string'
				}, {
					name : 'id',
					type : 'string'
				}],
				id : 'id',
				localdata : appointmentViewListJSONStr
				//url: appointmentJSONURL
			};
			var appoinmentDataAdapter = new $.jqx.dataAdapter(appoinmentReportListSource);
			
    
        // initialize jqxGrid
        $("#jqxgrid").jqxGrid(
        {
            width: 820,
            rowsheight: 30,
            source: appoinmentDataAdapter,
            showstatusbar: true,
            pageable: true,
            autoheight: true,
            autorowheight: true,
            sortable: true,
            altrows: true,
            enabletooltips: true,
            editable: false,
            selectionmode: 'none',
            columns: [
              { text: 'App. No',  		datafield: 'appointmentNo', width: 60 },
              { text: 'App. Date',  	datafield: 'appointmentDate', width: 90 },
              { text: 'Appmnt. Time', 	datafield: 'startTime', width: 140,             	  
            	  cellsrenderer: function (row, columnfield, value, defaulthtml, columnproperties) {
           		       var rowData = $("#jqxgrid").jqxGrid('getrowdata', row);
           	           return '<div style="display: table-cell; vertical-align: middle;height:40px;">' + (rowData.startTime+"-"+rowData.endTime) + '</div>';
            	   }
              },
              { text: 'Customer Name',  datafield: 'customerName', width: 200},
              { text: 'Mobile No.',  	datafield: 'customerMobileNo', width: 90},
              { text: 'Address', 		datafield: 'address', width: 240,             	  
            	  cellsrenderer: function (row, columnfield, value, defaulthtml, columnproperties) {
           		       var rowData = $("#jqxgrid").jqxGrid('getrowdata', row);
           		    return '<div style="display: table-cell; vertical-align: middle; height:40px;">'
					+ (rowData.cityName +" - "+rowData.areaName+ " - "+rowData.buildingName
							+ " - "
							+ rowData.flatNo)
					+ '</div>';
       //    		    return '<div style="display: table-cell; vertical-align: middle; height:40px;">' + (rowData.addressType+"-"+rowData.flatNo+", "+rowData.buildingName+","+rowData.addressRemarks+","+rowData.cityName) + '</div>';
            	   }
              }
            ]
        });
	}

	function setupReportDate() {
		$("#startDateDiv").jqxDateTimeInput({
			width : '130px',
			height : '20px',
			formatString : 'dd-MM-yyyy'
		});
		$('#startDateDiv').jqxDateTimeInput({
			allowNullDate : true
		});
		
		$("#endDateDiv").jqxDateTimeInput({
			width : '130px',
			height : '20px',
			formatString : 'dd-MM-yyyy'
		});
		$('#endDateDiv').jqxDateTimeInput({
			allowNullDate : true
		});

		var selectedStartDate = $("#startDate").val();
		if (selectedStartDate != undefined && selectedStartDate != null && selectedStartDate != "") {
			//var arr = selectedEndDate.split('-');
			//dd-mm-yyyy
			var day = selectedStartDate.substr(0,2);
			var month = parseInt(selectedStartDate.substr(3,2))-1;
			var year = selectedStartDate.substr(6,4);
			var date = new Date();
			date.setFullYear(year, month, day);
			$('#startDateDiv').jqxDateTimeInput('setDate', date);
		} else {
			$('#startDateDiv').jqxDateTimeInput({
				value : new Date()
			});			
		}
		
		var selectedEndDate = $("#endDate").val();
		if (selectedEndDate != undefined && selectedEndDate != null && selectedEndDate != "") {
			//dd-mm-yyyy
			var day = selectedEndDate.substr(0,2);
			var month = parseInt(selectedEndDate.substr(3,2))-1;
			var year = selectedEndDate.substr(6,4);
			var date = new Date();
			date.setFullYear(year, month, day);
			$('#endDateDiv').jqxDateTimeInput('setDate', date);
		} else {
			$('#endDateDiv').jqxDateTimeInput({
				value : new Date()
			});			
		}
	}
</script>



<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">

	<div id="createAccount" class="cornerDiv" style="height:330px">
		<div
			style="background-color: #F4F0F5; color: #000; min-height: 1.5em; vertical-align: middle; padding: 5px; width: 800px;">
			Datewise Appointments Report</div>
		<div
			style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 0px;">
			<table  width="100%" class="searchFiltersTable">

				<tr>
					<td colspan="1" align="left" width="20%">
						<input type="hidden" id="appointmentViewListJSON" value='<c:out value="${appointmentListJSON}"/>'/>
						
						<span style="font-size: 13px; font-family: Verdana;">Start Date</span>
						<div id="startDateDiv"></div>
					</td>
					<td colspan="1" width="20%" align="left" nowrap>
						<span style="font-size: 13px; font-family: Verdana;">End Date</span>
						<div id="endDateDiv"></div>
					</td>
					<td>
						<span style="font-size: 13px; font-family: Verdana;">Appointment Status</span>
				         <div style="margin-top: 5px;" id='statusComboBox'>
				         </div>
					</td>
				</tr>
				<tr>
					<td align="left" width="20%" colspan="3">
						<span style="font-size: 13px; font-family: Verdana;">Select Staff</span>
						<div id="formEmployeeId" ></div>
					</td>
				</tr>
				
			</table>
			<table width="100%" class="searchFiltersTable">
				<tr>
					<td>
						<form id="reportForm" action="" method="post">
							<input type="hidden" name="startDate" id="startDate" value='<c:out value="${startDate}"/>' /> 
							<input type="hidden" name="endDate" id="endDate" value='<c:out value="${endDate}"/>' />
							<input type="hidden" name="employeeId" id="employeeId" value="${employeeId}"/>
							<input type="hidden" name="appointmentStatus" id="appointmentStatus" value='<c:out value="${selectedAppointmentStatus}"/>'/>
							<input id="searchButton" type="button" value="Search" />
							<input id="downloadReportButton" type="button" value="Download Report" /> 
							<input id="resetButton" type="button" value="Reset" />
						</form>
					</td>
				</tr>
				<tr>
					<td colspan="10">
						<div id="jqxgrid">
        				</div>
					</td>
				</tr>
			</table>
		</div>



	</div>
</div>


<jsp:include page="includes/footer.jsp" />
