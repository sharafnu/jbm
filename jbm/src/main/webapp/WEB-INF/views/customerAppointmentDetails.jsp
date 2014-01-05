<jsp:include page="includes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<span class="breadcrumbs">Home > Job Management > Job Details</span>
</div>
<script type="text/javascript"
	src="<c:url value="/resources/scripts/appoinment.js" />"></script>
<script type="text/javascript">

	$(document).ready(function() {
		document.title = 'Add Appointment';
		setupAppointmentDetailsForm();		
	});
	

</script>


<form id="form" action="./">

<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">
	<div id="createAccount" class="cornerDiv">
			<div style="background-color: #F4F0F5; color: #000; min-height: 1.5em; vertical-align: middle; padding: 5px; width: 800px;">
				Job Details</div>
			<div style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 10px;">
				<table border="0" width="100%" class="popupFormTable">
					<tr>
						<td colspan="1" width="10%" align="right" nowrap> Appointment Id :</td>
						<td colspan="1" align="left" width="90%"><div id="formAppointmentId" ></div></td>
					</tr>
					<tr>
						<td colspan="2" width="100%" align="left">
							<div id='appoinmentDetailsTab'>
								<ul>
					                <li style="margin-left: 30px;">Job Details</li>
					                <li>Job Completion Details</li>
					                <li>Job Cancellation Details</li>
					                <li>Finance Details</li>
					            </ul>
								<div id="appoinmentDetailsDiv">
									<table>
										<tr>
											<td>Appointment Id : </td>
											<td>A/P-10001</td>
										</tr>
										<tr>
											<td>Customer Name :</td>
											<td>Nadeer Ali</td>
										</tr>
										<tr>
											<td>Appointment Date :</td>
											<td>31/12/1983</td>
										</tr>
										<tr>
											<td>Start Time :</td>
											<td><div id="formStartTime" ></div></td>
										</tr>
										<tr>
											<td>Location :</td>
											<td><div id="formAreaId" ></div></td>
										</tr>
										<tr>
											<td>Status :</td>
											<td><div id="formAppoinmentStatus" ></div></td>
										</tr>
										<tr>
											<td>Remarks :</td>
											<td><textarea id="formRemarks" rows="4" cols="24"></textarea></td>
										</tr>
										<tr>
											
										</tr>
										<tr>
											<td><input id="updateAppointmentButton" type="button" value="Update Appoinment Details" /></td>
										</tr>
									</table>
	                				
	                			</div>
	                			<div id="jobCompletionDetailsDiv">
									<table>
										<tr>
											
										</tr>
										<tr>
											<td>End Time : </td>
											<td><div id="formEndTime" ></div></td>
										</tr>
										<tr>
											<td>No. of of hours :</td>
											<td><input type="text" id="formHoursSpent"/></td>
										</tr>
										<tr>
											<td>Amount Payable :</td>
											<td><input type="text" id="formAmountPayable"/></td>
										</tr>
										<tr>
											<td>Payment Status :</td>
											<td><div id="formPaymentStatus" ></div></td>
										</tr>
										<tr>
											<td>Payment Type :</td>
											<td><div id="formPaymentType" ></div></td>
										</tr>
										
										<tr>
											
										</tr>
										<tr>
											<td><input id="updateAppointmentButton" type="button" value="Update Details" /></td>
										</tr>
									</table>
	                				
	                			</div>
	                			<div id="jobCancellationDetailsDiv">
									<table>
										
										<tr>
											
										</tr>
										<tr>
											<td>Cancellation Reason :</td>
											<td><textarea id="formCancellationReason" rows="4" cols="24"></textarea></td>
										</tr>										
										<tr>
											
										</tr>
										<tr>
											<td><input id="updateAppointmentButton" type="button" value="Update Appoinment Details" /></td>
										</tr>
									</table>
	                				
	                			</div>
	                			<div id="jobFinanceDetailsDiv">
									<table>
										<tr>
											
										</tr>
										<tr>
											<td>Invoice No : </td>
											<td><input type="text" id="formInvoiceNo"/></td>
										</tr>
										<tr>
											<td>Invoice Date :</td>
											<td><div id="formInvoiceDate" ></div></td>
										</tr>
										<tr>
											<td>Amount :</td>
											<td><input type="text" id="formInvoiceAmount"/></td>
										</tr>
										<tr>
											<td>Status :</td>
											<td><div id="formFinanceStatus" ></div></td>
										</tr>
										<tr>
											
										</tr>
										<tr>
											<td><input id="updateAppointmentButton" type="button" value="Update Appoinment Details" /></td>
										</tr>
									</table>	                				
	                			</div>
                			</div>
                								
						</td>
					</tr>
					
				</table>				
	</div>
</div>
</form>


<jsp:include page="includes/footer.jsp" />
