<jsp:include page="includes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<span class="breadcrumbs">Home > Job Management > Edit Appointment Details</span>
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
		var appointmentId = "${appointmentView.id}";
		var customerId = "${appointmentView.customerId}";
		$("#customerId").val(customerId);
		$("#id").val(appointmentId);
		$("#appointmentId").val(appointmentId);
		var employeeId = "${appointmentView.employeeId}";
		var customerAddressId = "${appointmentView.customerAddressId}";
		var startTime = "${appointmentView.startTime}";
		var endTime = "${appointmentView.endTime}";
		var remarks = "${appointmentView.remarks}";
		setupAppointmentEditForm(customerId, employeeId, customerAddressId, startTime, endTime);
		
		$("#customerNameDisplay").text("${appointmentView.customerName}");
		$("#appointmentNoDisplay").text("${appointmentView.appointmentNo}");
		$("#formRemarks").val(remarks);
		
		$('#formAppointmentDate').jqxDateTimeInput({ value: new Date("${appointmentView.appointmentDate}") });	
		
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
	         		var startTime = item.value;
	         		$('#formEndTime').val(addTime(startTime, 4));
	        		/* var frmHr = startTime.substr(0,2);
	           	 	var frmMin = startTime.substr(3,2);
	        	   	 var frmAmPm = startTime.substr(6,2);
	        	   	 var toAmPm = " "+frmAmPm;
	        	   	 
	        	   	
	        	   	var toHr = parseInt(frmHr)+parseInt(4);
	        	   	 if(toHr == 12) {
	        	   		 	
	        			 if(frmAmPm === "AM") {
	        	       	 	toAmPm = " PM";
	        	       	 } else {
	        	       	 	toAmPm = " AM";
	        	       	 }		            		 
	        	       	 	
	        	   	 } else  if(toHr > 12) {
	        	   	 	toHr = toHr -12;
	        	   	 	if(frmHr == 12) {
	        	   	 		
	        	   	 	} else {
	        	       		if(frmAmPm === "AM") {
	        	       	 		toAmPm = " PM";
	        	       	 	} else {
	        	       	 		toAmPm = " AM";
	        	       	 	}
	        	   	 	}
	        	   	 }
	        	   	 toHr = zeroPad(toHr, 2);
	            	 
	            	 var toMin = item.value.substr(3,2);
	            	 var timeDiff = calculateTimeDifference(startTime, "12:00 AM");
	            	 $('#formEndTime').val(toHr+":"+toMin+toAmPm);*/
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

	         			//Validate Here
	         			$("#appointmentMainForm").jqxValidator('validateInput', '#formEndTime');
	         		}	 
	             }
	         }
        });
		
		
		//loadStaffAppointmentCountList($('#formAppointmentDate').val());
		loadMonthylAppointmentCalendar(-1);
		enableFormValidations();
		
	});
	
	function enableFormValidations() {
		$("#isValidSlot").val("false");
		$("#isNotDuplicate").val("false");
		var selectedAppointmentId = $("#appointmentId").val();
		
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
								url: "checkStaffAppointmentSlotsForEdit.html",
								type: 'POST',
								data: {startTime: fromTime, endTime: toTime, appointmentDate: $("#formAppointmentDate").val(), employeeId: employeeIdCombo.value, id:selectedAppointmentId},
								success: function(data)
								{
									if (data == "true")
									{
										$("#isValidSlot").val("true");
										commit(true);
									}
									else {
										$("#isValidSlot").val("false");	
										commit(false); 						
									}
								},
								error: function()
								{
									$("#isValidSlot").val("false");
									commit(false);
								}
							});
						}
				},
				{ 
					input: '#formCustomerAddressId', message: 'Duplicate appointment for customer on same slot/location !', action: 'change', rule: function (input, commit) {
						var fromTime = $("#formStartTime").val();
						var toTime = $("#formEndTime").val();
						var customerAddressIdCombo = $("#formCustomerAddressId").jqxComboBox('getSelectedItem'); 	
						if(customerAddressIdCombo == null || customerAddressIdCombo.value == "") {
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
						//alert(selectedAppointmentId);
						$.ajax({
							url: "checkCustomerDuplicateAppointmentsForEdit.html",
							type: 'POST',
							data: {startTime: fromTime, endTime: toTime, appointmentDate: $("#formAppointmentDate").val(), customerAddressId: customerAddressIdCombo.value, id:selectedAppointmentId},
							success: function(data)
							{
								if (data == "true")
								{
									$("#isNotDuplicate").val("true");
									commit(true);
								}
								else {
									$("#isNotDuplicate").val("false");
									commit(false);
								}
							},
							error: function()
							{
								$("#isNotDuplicate").val("false");
								commit(false);
							}
						});
					}
				}
			]
		});
	}
	
	function showEmployeeCancellations(customerId) {
		$("#customerAppDetails").html("");
		var url = 'getCancelledAppointments/'+customerId+".html";
		$.ajax({
			url: url,
			type: 'GET',
			success: function(cancelledAppList)
			{
				if(cancelledAppList != "") {
					$("#customerAppDetails").html('<i class="fa fa-info-circle" style="color:orange"></i> View');		
					//$("#customerAppDetails").jqxTooltip({ theme:'orange', content: 'The Amazing Spider-man', position: 'right', autoHide: true, name: 'customerAppDetailsTooltip'});
					$("#customerAppDetails").jqxTooltip({ content: cancelledAppList, position: 'right', autoHide: false, name: 'customerAppDetailsTooltip'});
				}
				//alert(cancelledAppList);			
			}
		});
			
	}
	
	function setupFormValidations1() {
		alert(11);
		$('#appointmentMainForm').jqxValidator({
			//_margin: 30,
			rules: [
			{ input: '#formEndTime', message: 'Time slot not available !', 
					action: 'change', rule: function (input, commit) {
						alert(1);
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
					alert(data);
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
			},
			{ 
				input: '#formCustomerAddressId', message: 'Duplicate appointment for customer on same slot/location !', action: 'change', rule: function (input, commit) {
					var fromTime = $("#formStartTime").val();
					var toTime = $("#formEndTime").val();
					var customerAddressIdCombo = $("#formCustomerAddressId").jqxComboBox('getSelectedItem'); 	
					if(customerAddressIdCombo == null || customerAddressIdCombo.value == "") {
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
						url: "checkCustomerDuplicateAppointments.html",
						type: 'POST',
						data: {startTime: fromTime, endTime: toTime, appointmentDate: $("#formAppointmentDate").val(), customerAddressId: customerAddressIdCombo.value},
						success: function(data)
						{
							if (data == "true")
							{
								$("#isNotDuplicate").val("true");
								commit(true);
							}
							else {
								$("#isNotDuplicate").val("false");
								commit(false);
							}
						},
						error: function()
						{
							$("#isNotDuplicate").val("false");
							commit(false);
						}
					});
				}
			}
			
			]
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
						<td colspan="1">Appointment No :</td>
						<td colspan="2"><span id="appointmentNoDisplay" ></div>
						</td>
					</tr>
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
						<td colspan="1">Customer :</td>
						<td colspan="2"><span id="customerNameDisplay" ></div>
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
						<td>
							Send SMS?
						</td>
						<td colspan="2">
									<div id='sendSMSYes' style="width:80px; float:left;">
										Yes
									</div>
									<div id='sendSMSNo' style="width:80px; float:left;">
										No
									</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="3">
								<input id="updateAppointmentButton" type="button" value="Update Appoinment" />
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
	<form id="appoinmentEditForm" action="modifyCustomerAppoinment.html" method="post">
		<input type="hidden" id="isValidSlot" 	name="isValidSlot"/>
		<input type="hidden" id="isNotDuplicate" 	name="isNotDuplicate"/>
		<input type="hidden" id="id" 			name="id"/>
		<input type="hidden" id="appointmentId" 			name="appointmentId"/>
		<input type="hidden" id="appointmentDate" 	name="appointmentDate"/>
		<input type="hidden" id="customerAddressId" 	name="customerAddressId"/>
		<input type="hidden" id="customerId" 			name="customerId"/>
		<input type="hidden" id="employeeId" 			name="employeeId"/>
		<input type="hidden" id="remarks" 			name="remarks"/>
		<input type="hidden" id="startTime" 			name="startTime"/>
		<input type="hidden" id="endTime" 			name="endTime"/>
		<input type="hidden" id="appointmentUpdateSMSFlag" 			name="appointmentUpdateSMSFlag" value="${appointmentView.sendSMSFlag}"/>
				<input type="hidden" id="sendSMSFlag" 			name="sendSMSFlag"/>
	</form>
</div>
<jsp:include page="detailedCalendarInfoPopup.jsp" />
<jsp:include page="includes/footer.jsp" />
