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
		
		//formAppoinmentStatus
	if($('#appointmentId').val() != "") {
		var appointmentDetailsURL = "getCustomerAppointmentDetails/";
		appointmentDetailsURL = appointmentDetailsURL +$('#appointmentId').val()+".html";
    	$.get(appointmentDetailsURL, function(appointmentView){
    		$("#appointmentNo").text(appointmentView.appointmentNo);
    		$("#customerName").text(appointmentView.customerName);
    		$("#employeeName").text(appointmentView.employeeName);
    		$("#appointmentDateDisplay").text(appointmentView.appointmentDate);
    		$("#appointmentDate").val(appointmentView.appointmentDate);
    		$("#startTime").text(appointmentView.startTime);
    		$("#location").text(appointmentView.flatNo +", "+appointmentView.buildingName+"," +appointmentView.areaName+", "+appointmentView.cityName);            		
    	});	
	}	
		
	$('#formAppoinmentStatus').on('change', function(event) {
			var args = event.args;
			if (args) {
				var item = args.item;
				if (item.value != "") {
					$("#updateAppointmentButton").jqxButton({ disabled: false });
					if(item.value == "Completed") {
						$('#appoinmentDetailsTab').jqxTabs('enableAt', 1);
					} else {
						$('#appoinmentDetailsTab').jqxTabs('disableAt', 1);
					}
				} else {
					$("#updateAppointmentButton").jqxButton({ disabled: true });
				}
			}
		});
		
	$('#formAppointmentId').on('change', function(event) {
		var args = event.args;
		if (args) {
			var item = args.item;
			if (item.value != "") {
				var appointmentDetailsURL = "getCustomerAppointmentDetails/";
				appointmentDetailsURL = appointmentDetailsURL +item.value+".html";
            	$.get(appointmentDetailsURL, function(appointmentView){
            		$("#appointmentNo").text(appointmentView.appointmentNo);
            		$("#customerName").text(appointmentView.customerName);
            		$("#employeeName").text(appointmentView.employeeName);
            		$("#appointmentDateDisplay").text(appointmentView.appointmentDate);
            		$("#appointmentDate").val(appointmentView.appointmentDate);
            		$("#startTime").text(appointmentView.startTime);
            		$("#location").text(appointmentView.flatNo +", "+appointmentView.buildingName+"," +appointmentView.areaName+", "+appointmentView.cityName);            		
            	});	
			}
		}
	});
	
		$("#updateAppointmentButton").click(	
			function() {
				// $('#updateAppointmentButton').jqxValidator('validate');
				
				var appointmentIdCombo = $("#formAppointmentId").jqxComboBox('getSelectedItem'); 	
				if(appointmentIdCombo == null) {
					jqxAlert.alert('Please select an appointment to update !');	
					return;
				} else {
					$("#id").val(appointmentIdCombo.value);
				}
				
				var paymentStatusCombo = $("#formPaymentStatus").jqxComboBox('getSelectedItem'); 	
				if(paymentStatusCombo != null) {
					$("#paymentStatus").val(paymentStatusCombo.value);
				}
				
				
				var appointmentStatusCombo = $("#formAppoinmentStatus").jqxComboBox('getSelectedItem'); 	
				if(appointmentStatusCombo != null) {
					$("#appointmentStatus").val(appointmentStatusCombo.value);
					if(appointmentStatusCombo.value == "Completed" && paymentStatusCombo == null) {
						jqxAlert.alert('Please enter payment details for completed jobs !');
						return;
					}
				}
				
				
				var paymentTypeCombo = $("#formPaymentType").jqxComboBox('getSelectedItem'); 	
				if(paymentTypeCombo != null) {
					$("#paymentType").val(paymentTypeCombo.value);
				}
								
				$("#cancellationReason").val($("#formRemarks").val());
				$("#endTime").val($("#formEndTime").val());
				$("#payableAmount").val($("#formAmountPayable").val());
				$("#hoursSpent").val($("#formHoursSpent").val());
				$("#invoiceNo").val($("#formInvoiceNo").val());
				$("#invoiceDate").val($("#formInvoiceDate").val());
				
				
				$('#appoinmentUpdateForm').submit();
			});
	
	});
</script>


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
						<input type="hidden" id="appointmentId" name="appointmentId" value="${appointmentId}"/>
					</tr>
					<tr>
						<td colspan="2" width="100%" align="left">
							<div id='appoinmentDetailsTab'>
								<ul>
					                <li style="margin-left: 30px;">Job Details</li>
					                <li>Job Completion Details</li>					        
					            </ul>
								<div id="jobDetailsDiv">
									<table>
										<tr>
											<td align="right">Appointment No : </td>
											<td><span id="appointmentNo"></span></td>
										</tr>
										<tr>
											<td align="right">Customer Name :</td>
											<td><span id="customerName"></span></td>
										</tr>
										<tr>
											<td align="right">Staff Name :</td>
											<td><span id="employeeName"></span></td>
										</tr>
										<tr>
											<td align="right">Appointment Date :</td>
											<td><span id="appointmentDateDisplay"></span></td>
										</tr>
										<tr>
											<td align="right">Start Time :</td>
											<td><span id="startTime"></span></td>
										</tr>
										<tr>
											<td align="right">Location :</td>
											<td><span id="location"></span></td>
										</tr>
										<tr>
											<td align="right">Status :</td>
											<td><div id="formAppoinmentStatus" ></div></td>
										</tr>
										<tr>
											<td align="right">Remarks :</td>
											<td><textarea id="formRemarks" rows="4" cols="24"></textarea></td>
										</tr>
										<tr>
											
										</tr>										
									</table>
	                				
	                			</div>
	                			<div id="jobCompletionDetailsDiv">
									<table width="100%" border="0">
										<tr valign="top">
											<td>
												<table>
													<tr>
														<td align="right">End Time : </td>
														<td><div id="formEndTime" ></div></td>
													</tr>
													<tr>
														<td align="right">No. of of hours :</td>
														<td><input type="text" id="formHoursSpent"/></td>
													</tr>
													<tr>
														<td align="right">Amount Payable :</td>
														<td><input type="text" id="formAmountPayable"/></td>
													</tr>
													<tr>
														<td align="right">Payment Status :</td>
														<td><div id="formPaymentStatus" ></div></td>
													</tr>
													<tr>
														<td align="right">Payment Type :</td>
														<td><div id="formPaymentType" ></div></td>
													</tr>
													<tr>
														<td align="right">Invoice No : </td>
														<td><input type="text" id="formInvoiceNo"/></td>
													</tr>
													<tr>
														<td align="right">Invoice Date :</td>
														<td><div id="formInvoiceDate" ></div></td>
													</tr>
													<tr>
														
													</tr>
												</table>	
											</td>
											<!-- <td>
												<table>
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
														
													</tr>													
												</table>
											</td> -->
										</tr>
										
									</table>
	                				
	                			</div>
	                			
                			</div>
                								
						</td>
					</tr>
					
					<tr>
						<td colspan="2">
							<form id="appoinmentUpdateForm" action="updateCustomerAppoinment.html" method="post">
								<input type="hidden" id="id" 	name="id"/>
								<input type="hidden" id="appointmentStatus" 	name="appointmentStatus"/>
								<input type="hidden" id="cancellationReason" 	name="cancellationReason"/>
								<input type="hidden" id="endTime" 				name="endTime"/>
								<input type="hidden" id="payableAmount" 		name="payableAmount"/>
								<input type="hidden" id="hoursSpent" 			name="hoursSpent"/>
								<input type="hidden" id="paymentStatus" 		name="paymentStatus"/>
								<input type="hidden" id="paymentType" 			name="paymentType"/>
								<input type="hidden" id="invoiceNo" 			name="invoiceNo"/>
								<input type="hidden" id="invoiceDate" 		name="invoiceDate"/>
								<input type="hidden" id="appointmentDate" 	name="appointmentDate"/>
								<input id="updateAppointmentButton" type="button" value="Update Appoinment Details" />
							</form>
						</td>
					</tr>
				</table>				
	</div>
</div>


<jsp:include page="includes/footer.jsp" />
