<jsp:include page="includes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<span class="breadcrumbs">Home > Job Management > Monthly Appointments </span>
</div>
<style>
.ui-datepicker-calendar {
    display: none;
    }
</style>
<script type="text/javascript">
	var selectedDate = null;
	var dateSet = false;
	$(document).ready(
			function() {
				document.title = 'Monthly Appointments Report';
				
				 $(".monthYearDiv").datepicker({ 
				        dateFormat: 'yy-mm',
				        changeMonth: true,
				        changeYear: true,
				        showButtonPanel: true,
				        currentText : 'Current Month/Year',
				 
				        onClose: function(dateText, inst) {  
				            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val(); 
				            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val(); 
				            $(this).val($.datepicker.formatDate('yy-mm', new Date(year, month, 1)));
				        }, 
				        onChangeMonthYear: function (year, month) {
				        	$("#monthYear").val(year+"-"+pad(month,2));
				        }
				    });
				 
				    $(".monthYearDiv").focus(function () {
				        $(".ui-datepicker-calendar").hide();
				        $("#ui-datepicker-div").position({
				            my: "center top",
				            at: "center bottom",
				            of: $(this)
				        });
				       /*  if(dateSet) {
				        	$(this).datepicker('setDate', selectedDate);
				        } */
				    });
				    var selectedMonthYear = $("#monthYear").val();
				    if(selectedMonthYear == "") {
						var today = new Date();
			            $("#monthYear").val(today.getFullYear()+"-"+pad(today.getMonth()+1,2));
				    } else {
				    	var year = selectedMonthYear.substr(0, 4);
				    	var month = selectedMonthYear.substr(5, 2);
				    	//alert(year + " : "+month);
				    	$("#monthYearDiv").datepicker('setDate', new Date(year, (month-1), 1));
				    	
				    	//$("#monthYearDiv").val($.datepicker.formatDate('yy-mm', new Date(year, month, 1)));
				    }
				$("#downloadReportButton").jqxButton({
					theme : theme
				});

				$("#searchButton").jqxButton({
					theme : theme
				});

				$("#downloadReportButton").click(
						function() {
								$('#reportForm').attr('action',
										'downloadMonthlyAppointmentReport.html');
								$('#reportForm').submit();
						});

				$("#searchButton").click(
						function() {
								$('#reportForm').attr('action',
										'monthlyAppointmentReportList.html');
								$('#reportForm').submit();
							
						});

				setupList();
			});

	
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
			}, {
				name : 'payableAmount',
				type : 'float'
			}, {
				name : 'paymentStatus',
				type : 'string'
			}  ],
			id : 'id',
			localdata : appointmentViewListJSONStr
		//url: appointmentJSONURL
		};
		var appoinmentDataAdapter = new $.jqx.dataAdapter(
				appoinmentReportListSource);

		// initialize jqxGrid
		$("#jqxgrid")
				.jqxGrid(
						{
							width : 820,
							rowsheight : 30,
							source : appoinmentDataAdapter,
							showstatusbar : true,
							pageable : true,
							autoheight : true,
							autorowheight : true,
							sortable : true,
							altrows : true,
							enabletooltips : true,
							editable : false,
							selectionmode : 'none',
							columns : [
									{
										text : 'App. No',
										datafield : 'appointmentNo',
										width : 60
									},
									{
										text : 'App. Date',
										datafield : 'appointmentDate',
										width : 90
									},
									{
										text : 'Customer Name',
										datafield : 'customerName',
										width : 200
									},
									{
										text : 'Staff Name',
										datafield : 'employeeName',
										width : 150
									},
									{
										text : 'App. Status',
										datafield : 'appointmentStatus',
										width : 90								
									},
									{
										text : 'Payment Status',
										datafield : 'paymentStatus',
										width : 90								
									}, 
									{
										text : 'Amount',
										datafield : 'payableAmount',
										width : 90,
										cellsformat: 'F2',
										cellsalign: 'right'
									}
									]
						});
	}

	
</script>



<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">

	<div id="createAccount" class="cornerDiv" style="height: 830px">
		<div
			style="background-color: #F4F0F5; color: #000; min-height: 1.5em; vertical-align: middle; padding: 5px; width: 800px;">
			Monthly Appointments Report</div>
		<div
			style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 0px;">
			<table width="100%" class="searchFiltersTable">

				<tr>
					<td width="10%" />
					<td colspan="1" align="left" width="20%"><input type="hidden"
						id="appointmentViewListJSON"
						value='<c:out value="${appointmentListJSON}"/>' /> <span
						style="font-size: 13px; font-family: Verdana;">Month & Year</span>
						<div id="monthYearDiv" class="monthYearDiv"></div></td>					
				</tr>
				<tr>
					<td width="10%" />
					<td>
						<form id="reportForm" action="" method="post">
							<input type="hidden" name="monthYear" id="monthYear" value='<c:out value="${monthYear}"/>' /> 
							<input id="searchButton" type="button" value="Search" /> 
							<input id="downloadReportButton" type="button" value="Download Report" />
						</form>
					</td>
				</tr>
			</table>
			<table width="100%" class="searchFiltersTable" >
				
				<tr>
					<td colspan="10">
						<div id="jqxgrid"></div>
					</td>
				</tr>
			</table>
		</div>



	</div>
</div>


<jsp:include page="includes/footer.jsp" />
