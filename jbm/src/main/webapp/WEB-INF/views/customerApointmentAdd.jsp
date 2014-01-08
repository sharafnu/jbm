<jsp:include page="includes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<span class="breadcrumbs">Home > Job Management > Add Appointment</span>
</div>
<script type="text/javascript"
	src="<c:url value="/resources/scripts/appoinment.js" />"></script>
<script type="text/javascript">

	$(document).ready(function() {
		document.title = 'Add Appointment';
		setupAppointmentForm();
		
		var staffAppoinmentCountListURL = "staffAppoinmentCountListJSON/1.html";
        var staffAppoinmentCountListSource =
        {
            datatype: "json",
            datafields: [
                { name: 'staffName', 		type: 'string' },
                { name: 'appoinmentCount', 	type: 'number' }
            ],
            id: 'staffId',
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
                      { text: 'Staff Name', columntype: 'textbox', filtertype: 'textbox', filtercondition: 'CONTAINS', datafield: 'staffName', width: 240 },
                      { text: 'Count', datafield: 'appoinmentCount', filtertype: 'number',  cellsalign: 'right', width: 55 }
                    ]
                });
	});
</script>




<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">
	<div id="createAccount" class="cornerDiv">
			<div style="background-color: #F4F0F5; color: #000; min-height: 1.5em; vertical-align: middle; padding: 5px; width: 800px;">
				<span>New Appointments</span><span style="margin-left: 350px">Staff Appointments List</span></div>
			<div style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 5px;">
				<table border="0" width="100%" class="popupFormTable">
					<tr>
						<td colspan="1" width="20%" nowrap>Appointment Date :</td>
						<td colspan="1" width="35%"><div id="formAppointmentDate" ></div></td>	
						<td rowspan="18" width="45%" valign="top">
							    <div style="margin-left: 5px; margin-top: 0px" id="staffAppoinmentInfoList"></div>							
						    <!-- <div style="margin-left: 5px; margin-top: 0px" id="staffCalendar"></div> -->
						</td>
					</tr>
<!-- 					<tr>
						<td colspan="1">Appointment Date :</td>
						<td colspan="1"><div id="formAppointmentDate" ></div></td>						
					</tr> -->
					<tr>
						<td colspan="1">Start Time :</td>
						<td colspan="1"><div id="formStartTime" ></div></td>
					</tr>
					<tr>
						<td colspan="1">Select Staff :</td>
						<td colspan="1"><div id="formEmployeeId" ></div></td>
					</tr>
					<tr>
						<td colspan="1">Select Customer :</td>
						<td colspan="1"><div id="formCustomerId" ></div></td>
					</tr>
					<tr>
						<td colspan="1">Select Location :</td>
						<td colspan="1"><div id="formCustomerAddressId" ></div></td>
					</tr>
					<tr>
						<td colspan="1">Remarks :</td>
						<td colspan="1"><textarea id="formRemarks" rows="4" cols="24"></textarea></td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2">
							<form id="appoinmentAddForm" action="saveCustomerAppoinment.html" method="post">
								<input type="hidden" id="appointmentDate" 	name="appointmentDate"/>
								<input type="hidden" id="customerAddressId" 	name="customerAddressId"/>
								<input type="hidden" id="customerId" 			name="customerId"/>
								<input type="hidden" id="employeeId" 			name="employeeId"/>
								<input type="hidden" id="remarks" 			name="remarks"/>
								<input type="hidden" id="startTime" 			name="startTime"/>
								<input id="createAppointmentButton" type="button" value="Create Appoinment" />
								</form>
						</td>						
					</tr>
				</table>				
	</div>
</div>
<jsp:include page="customerInfoAddPopup.jsp" />

<jsp:include page="includes/footer.jsp" />
