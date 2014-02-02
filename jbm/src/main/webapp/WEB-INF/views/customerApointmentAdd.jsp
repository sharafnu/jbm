<jsp:include page="includes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<span class="breadcrumbs">Home > Job Management > Add Appointment</span>
</div>

<!-- For Calendar -->
<link href='<c:url value="/resources/cal/css/fullcalendar.css" />' rel='stylesheet' />
<link href='<c:url value="/resources/cal/css/fullcalendar.print.css" />' rel='stylesheet' />
<script src='<c:url value="/resources/scripts/jquery-ui.min.js" />'></script>
<script src='<c:url value="/resources/cal/js/fullcalendar.min.js" />'></script>
<script src='<c:url value="/resources/cal/js/gcal.js" />'></script>

<script type="text/javascript"
	src="<c:url value="/resources/scripts/appoinment.js" />"></script>
<script type="text/javascript">

	function loadStaffAppointmentCountList(appointmentDate) {
		var staffAppoinmentCountListURL = "staffAppoinmentCountListJSON/"+$('#formAppointmentDate').val()+".html";
		//alert(staffAppoinmentCountListURL);
        var staffAppoinmentCountListSource =
        {
            datatype: "json",
            datafields: [
                { name: 'employeeName', 		type: 'string' },
                { name: 'appointmentCount', 	type: 'number' }
            ],
            id: 'employeeId',
            url: staffAppoinmentCountListURL
        };
        var staffAppoinmentCountListDataAdapter = new $.jqx.dataAdapter(staffAppoinmentCountListSource);
		
        $("#staffAppoinmentInfoList").jqxGrid(
                {
                    width: 315,
                    source: staffAppoinmentCountListDataAdapter,
                    showfilterrow: true,
                    filterable: true,
                    selectionmode: 'multiplecellsextended',
                    cellhover: function (element, pageX, pageY)
                    {
                        // update tooltip.
                        $("#staffAppoinmentInfoList").jqxTooltip({ content: 'Please double click to open appoinment details' });
                        // open tooltip.
                        $("#staffAppoinmentInfoList").jqxTooltip('open', pageX + 15, pageY + 15);
                    },
                    columns: [
                      { text: 'Staff Name', columntype: 'textbox', filtertype: 'textbox', filtercondition: 'CONTAINS', datafield: 'employeeName', width: 240 },
                      { text: 'Count', datafield: 'appointmentCount', filtertype: 'number',  cellsalign: 'right', width: 55 }
                    ]
                });
	}
	
	function zeroPad(num, places) {
		  var zero = places - num.toString().length + 1;
		  return Array(+(zero > 0 && zero)).join("0") + num;
		}
	
	$(document).ready(function() {
		document.title = 'Add Appointment';
		setupAppointmentForm();
		
		$('#formCustomerId').on('change', function(event) {
			var args = event.args;
			if (args) {
				var item = args.item;
				if (item.value != "") {
					loadCustomerAddressCombo(280, item.value);
					showEmployeeCancellations(item.value);
				}
			}
		});
		
		$('#formEmployeeId').on('change', function(event) {
			var args = event.args;
			if (args) {
				var item = args.item;
				if (item.value != "") {
					$('#calendar').fullCalendar('destroy'); 
					loadMonthylAppointmentCalendar(item.value);
					$("#appointmentMainForm").jqxValidator('validateInput', '#formEndTime');
				}
			}
		});
		
		$('#formAppointmentDate').on('valuechanged', function (event) {           
			//alert($('#formAppointmentDate').val());
			//loadStaffAppointmentCountList($('#formAppointmentDate').val());
			$('#calendar').fullCalendar('destroy'); 
			loadMonthylAppointmentCalendar(-1);
			$("#appointmentMainForm").jqxValidator('validateInput', '#formEndTime');
        });
		
		$('#formStartTime').on('change', function (event) {   
			var args = event.args;
			if(args == undefined) {
				alert("Invalid Selection");
				$('#formStartTime').val("");
			} 
			if (args) {
	         	var item = args.item;
	         	 if(item.value != null && item.value != "") {
	            	 var frmHr = item.value.substr(0,2);
	            	 var toHr = parseInt(frmHr)+4;
	            	 if(toHr > 12) {
	            	 	toHr = toHr -12;
	            	 }
	            	 toHr = zeroPad(toHr, 2);
	            	 
	            	 var toMin = item.value.substr(3,2);
	            	 $('#formEndTime').val(toHr+":"+toMin+" pm");
	            	 $("#appointmentMainForm").jqxValidator('validateInput', '#formEndTime');
	             }
	         }
        });
		
		$('#formEndTime').on('change', function (event) {
			var args = event.args;
			if(args == undefined) {
				alert("Invalid Selection");
				$('#formEndTime').val("");
			} 
			if (args) {
	         	var item = args.item;
	         	 if(item.value != null && item.value != "") {
	            	var toHr = item.value.substr(0,2);
	            	var toMin = item.value.substr(3,5);
	            	var frmTimeCombo = $("#formStartTime").jqxComboBox('getSelectedItem'); 	
	         		if(frmTimeCombo  != null) {
	         			var frmHr = frmTimeCombo.value.substr(0,2);
	         			var frmMin = frmTimeCombo.value.substr(3,5);
	         			//alert(toHr+":"+toMin);
	         			//alert(frmHr+":"+frmMin);
	         			/* if(parseInt(toHr) <(parseInt(frmHr)+4)) {
	         				alert("End time should be atleast 4 hours greater than Start time !");
	         				toHr = parseInt(frmHr)+4;
	   	            		//$('#formEndTime').val(toHr+":"+frmMin);
	   	            		toHr = toHr -12;
	   	            		toHr = zeroPad(toHr, 2);
	   	            	 	 $('#formEndTime').val(toHr+":"+frmTimeCombo.value.substr(3,2)+" pm");  
	         			} else if(parseInt(toHr) == (parseInt(frmHr)+4)) {
	         				if(parseInt(toMin) < parseInt(frmMin)) {
	         					alert("End time should be atleast 4 hours greater than Start time !");
	         					toHr = toHr -12;
	         					toHr = parseInt(frmHr)+4;
		   	            		$('#formEndTime').val(toHr+":"+frmMin);
		   	            		toHr = zeroPad(toHr, 2);
		   	            	 	 $('#formEndTime').val(toHr+":"+frmTimeCombo.value.substr(3,2)+" pm");  
	         				} 
	         			}*/
	         			//Validate Here
	         			$("#appointmentMainForm").jqxValidator('validateInput', '#formEndTime');
	         		}	 
	             }
	         }
        });
		
		
		//loadStaffAppointmentCountList($('#formAppointmentDate').val());
		loadMonthylAppointmentCalendar(-1);
		
		setupFormValidations();
		
	});
	
	
	function showEmployeeCancellations(customerId) {
		var url = 'getCancelledAppointments/'+customerId+".html";
		$.ajax({
			url: url,
			type: 'GET',
			success: function(cancelledAppList)
			{
				alert(cancelledAppList);			
			}
		});
		
		$("#customerAppDetails").html('<i class="fa fa-info-circle" style="color:orange"></i> View');		
		$("#customerAppDetails").jqxTooltip({ theme:'orange', content: 'The Amazing Spider-man', position: 'right', autoHide: true, name: 'customerAppDetailsTooltip'});
	}
	
	function setupFormValidations() {
		
		$('#appointmentMainForm').jqxValidator({
			//_margin: 30,
			rules: [
			{ input: '#formEndTime', message: 'Time slot not available !', 
					action: 'change', rule: function (input, commit) {
				var fromTime = $("#formStartTime").val();
				var toTime = $("#formEndTime").val();
				var employeeIdCombo = $("#formEmployeeId").jqxComboBox('getSelectedItem'); 	
				if(employeeIdCombo == null || employeeIdCombo.value == "") {
					return;
				}
				if(fromTime == "") {
					return;
				}
				if(toTime == "") {
					return;
				}
				if($("#formAppointmentDate").val() == "") {
					return;
				}
				$.ajax({
				url: "checkStaffAppointmentSlots.html",
				type: 'POST',
				data: {startTime: fromTime, endTime: toTime, appointmentDate: $("#formAppointmentDate").val(), employeeId: employeeIdCombo.value},
				success: function(data)
				{
					if (data == "true")
					{
						commit(true);
					}
					else commit(false);
				},
				error: function()
				{
					commit(false);
				}
			});
			}
			}]
		});
	}
	
	function loadMonthylAppointmentCalendar(staffId) {
		$('#calendar').fullCalendar({
			editable: true,
			showFooter: true,
			events: 'staffAppointmentCountListJSON/'+staffId+'.html',
			eventRender: function(event, element) {
		        element.attr('title', event.tooltip);
		    },
			loading: function(bool) {
				if (bool) {
					$('#loading').show();
				}else{
					$('#loading').hide();
				}
			}
		});
		var appointmentDate =  $("#formAppointmentDate").val();
		var day = appointmentDate.substr(0,2);
		var mon = parseInt(appointmentDate.substr(3,2) - 1);
		var year = appointmentDate.substr(6,4);
		
		//alert(day+" : "+mon+" : "+year);
		
		$('#calendar').fullCalendar( 'gotoDate', appointmentDate);
		$('#calendar').fullCalendar( 'gotoDate', year, mon, day);
		
	}
	
</script>




<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">
	<div id="createAccount" class="cornerDiv">
			<div style="background-color: #F4F0F5; color: #000; min-height: 1.5em; vertical-align: middle; padding: 5px; width: 830px;">
				<span>New Appointments</span><span id='showCalendarLink' style="margin-left: 350px">
					Staff Availability <i class='fa fa-calendar'></i>
				</span></div>
			<div style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 5px;">
				<form class="form" id="appointmentMainForm">
				<table border="0" width="100%" class="popupFormTable">
					<tr>
						<td colspan="1" width="20%" nowrap>Appointment Date :</td>
						<td colspan="2" width="35%"><div id="formAppointmentDate" ></div></td>	
						<td rowspan="18" width="45%" valign="top">
							    <!-- <div style="margin-left: 5px; margin-top: 0px" id="staffAppoinmentInfoList"></div>	 -->						
						    	<!-- <div style="margin-left: 5px; margin-top: 0px" id="staffCalendar"></div> -->
								<div id='calendar'></div>								    	
						</td>
					</tr>
<!-- 					<tr>
						<td colspan="1">Appointment Date :</td>
						<td colspan="1"><div id="formAppointmentDate" ></div></td>						
					</tr> -->
					<tr>
						<td colspan="1">Select Staff :</td>
						<td colspan="2"><div id="formEmployeeId" ></div></td>
					</tr>
					<tr>
						<td colspan="1">Start/End Time :</td>
						<td colspan="1"><div id="formStartTime" ></div></td>
						<td><div id="formEndTime" ></div></td>
					</tr>
<!-- 					<tr>
						<td colspan="1">End Time :</td>
						<td colspan="1"><div id="formEndTime" ></div></td>
					</tr> -->
					<tr>
						<td colspan="1">Select Customer :</td>
						<td colspan="2"><div id="formCustomerId" ></div>
						<span id="customerAppDetails">
							<!-- //Customer Cancellation Details -->
						</span>
						</td>
					</tr>
					<tr>
						<td colspan="1">Select Location :</td>
						<td colspan="2"><div id="formCustomerAddressId" ></div></td>
					</tr>
					<tr>
						<td colspan="1">Remarks :</td>
						<td colspan="2"><textarea class="textArea" id="formRemarks" rows="4" cols="34"></textarea></td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="3">
							<!-- <form id="appoinmentAddForm" action="saveCustomerAppoinment.html" method="post">
								<input type="hidden" id="appointmentDate" 	name="appointmentDate"/>
								<input type="hidden" id="customerAddressId" 	name="customerAddressId"/>
								<input type="hidden" id="customerId" 			name="customerId"/>
								<input type="hidden" id="employeeId" 			name="employeeId"/>
								<input type="hidden" id="remarks" 			name="remarks"/>
								<input type="hidden" id="startTime" 			name="startTime"/>
								<input type="hidden" id="endTime" 			name="endTime"/>
								<input id="createAppointmentButton" type="button" value="Create Appoinment" />
								<input id="addNewCustomerPopuptButton" type="button" value="Add New Customer" />
								</form> -->
								<input id="createAppointmentButton" type="button" value="Create Appoinment" />
								<input id="addNewCustomerPopuptButton" type="button" value="Add New Customer" />
						</td>						
					</tr>
					<tr>
						<td colspan="3">
							<c:if test="${not empty errorMessage}">
							    <span class="errorMessage">${errorMessage}</span>
							</c:if>
							<c:if test="${not empty infoMessage}">
							    <span class="infoMessage">${infoMessage}</span>
							</c:if>
						</td>						
					</tr>
				</table>
				</form>				
	</div>
	<form id="appoinmentAddForm" action="saveCustomerAppoinment.html" method="post">
								<input type="hidden" id="appointmentDate" 	name="appointmentDate"/>
								<input type="hidden" id="customerAddressId" 	name="customerAddressId"/>
								<input type="hidden" id="customerId" 			name="customerId"/>
								<input type="hidden" id="employeeId" 			name="employeeId"/>
								<input type="hidden" id="remarks" 			name="remarks"/>
								<input type="hidden" id="startTime" 			name="startTime"/>
								<input type="hidden" id="endTime" 			name="endTime"/>
								</form>
</div>
<jsp:include page="customerInfoAddPopup.jsp" />
<%-- <jsp:include page="staffAppointmentListPopup.jsp" /> --%>
<jsp:include page="detailedCalendarInfoPopup.jsp" />
<jsp:include page="includes/footer.jsp" />
