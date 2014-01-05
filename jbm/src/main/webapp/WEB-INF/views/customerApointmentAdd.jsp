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
	});
	

</script>


<form id="form" action="./">

<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">
	<div id="createAccount" class="cornerDiv">
			<div style="background-color: #F4F0F5; color: #000; min-height: 1.5em; vertical-align: middle; padding: 5px; width: 800px;">
				New Appointments</div>
			<div style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 10px;">
				<table border="0" width="100%" class="popupFormTable">
					<tr>
						<td colspan="1">Job ID :</td>
						<td colspan="1"><input id="formJobId" /></td>
						<td colspan="1"><b>Staff Appointments Calendar</b></td>
					</tr>
					<tr>
						<td colspan="1">Select Customer :</td>
						<td colspan="1"><div id="formCustomerId" ></div></td>
						<td rowspan="18" width="50%" valign="top">
						    <div style="margin-left: 5px; margin-top: 0px" id="staffCalendar"></div>
						</td>
					</tr>
					<tr>
						<td colspan="1">Select Staff :</td>
						<td colspan="1"><div id="formEmployeeId" ></div></td>
					</tr>
					<tr>
						<td colspan="1">Select Location :</td>
						<td colspan="1"><div id="formAreaId" ></div></td>
					</tr>
					<tr>
						<td colspan="1">Appointment Date :</td>
						<td colspan="1"><div id="formAppointmentDate" ></div></td>
					</tr>
					<tr>
						<td colspan="1">Start Time :</td>
						<td colspan="1"><div id="formStartTime" ></div></td>
					</tr>
					<tr>
						<td colspan="1">Remarks :</td>
						<td colspan="1"><textarea id="formRemarks" rows="4" cols="24"></textarea></td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="1">
							<input id="createAppointmentButton" type="button" value="Create Appoinment" />
						</td>
						
					</tr>
				</table>				
	</div>
</div>
</form>


<jsp:include page="includes/footer.jsp" />
