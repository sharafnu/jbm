<jsp:include page="includes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<span class="breadcrumbs">Home > Job Management > Job Details</span>
</div>
<script type="text/javascript"
	src="<c:url value="/resources/scripts/appoinment.js" />"></script>
<script type="text/javascript">

	var isDuplicateInvoice = true;
	
	function pad(num, size) {
	    var s = num+"";
	    while (s.length < size) s = "0" + s;
	    return s;
	}

	function calculateHoursSpent() {
		var startTime = $("#startTime").text();
		var endTime = $('#formEndTime').val();
		 var frmHr = parseInt(startTime.substr(0,2));
		 var frmMin = parseInt(startTime.substr(3,2));
    	 var toHr = parseInt(endTime.substr(0,2));
    	 var toMin = parseInt(endTime.substr(3,2));
    	 
    	 var frmAMPM = startTime.substr(6,2);
    	 var toAMPM = endTime.substr(6,2);
    	 
    	 var totalHours = 0;
    	 
    	 if(frmAMPM.toUpperCase() === "AM") {
    		 if(toAMPM.toUpperCase() === "AM") {
    			 totalHours = toHr - frmHr;	 
    		 } else {
    			 if(toHr == "12") {
    				 totalHours = toHr - frmHr;
    			 } else {
    			 	totalHours = toHr - frmHr+12;
    			 }
    		 }
    		 
    	 } else {
    		 if(toAMPM.toUpperCase() === "AM") {
    			 totalHours = toHr - frmHr+12;	 
    		 } else {
    			 totalHours = toHr - frmHr;	 
    		 }
    		 //totalHours = toHr - frmHr;
    	 }
    	 
    	 if(toMin > frmMin) {
    		 totalHours = totalHours + 0.5;
    	 } else if(frmMin > toMin) {
    		 totalHours = totalHours - 0.5;
    	 }
    	 /* if(totalHours > 12) {
    		 totalHours = totalHours - 12;
    	 }
    	 if(totalHours < 0) {
    		 totalHours = totalHours +12;
    	 } */
		$("#formHoursSpent").val(totalHours);
		
	}
	
	function handleAppoinmentDetailsData (appointmentView){
		$("#customerId").val(appointmentView.customerId);
		$("#appointmentNo").text(appointmentView.appointmentNo);
		$("#customerName").text(appointmentView.customerName);
		$("#employeeName").text(appointmentView.employeeName);
		//$('#formFromDate').jqxDateTimeInput({ value: new Date($('#startDate').val()) });
		var appointmentDateObj = new Date(appointmentView.appointmentDate);
		var formattedDate = pad(appointmentDateObj.getDate(), 2)+"-"+pad(appointmentDateObj.getMonth()+1, 2)+"-"+appointmentDateObj.getFullYear();
		//alert(new Date(appointmentView.appointmentDate));
		//alert(formattedDate);
		$("#appointmentDate").val(formattedDate);
		$("#appointmentDateDisplay").text(formattedDate);
		
		$("#startTime").text(appointmentView.startTime);
		$("#formEndTime").val(appointmentView.endTime);
		$("#notesDetailsDiv").text(appointmentView.remarks);
		//calculateHoursSpent();
		$("#location").text(appointmentView.flatNo +", "+appointmentView.buildingName+"," +appointmentView.areaName+", "+appointmentView.cityName);            		
		loadCustomerContractCombo(appointmentView.customerId);
	}	
	
	$(document).ready(function() {
		document.title = 'Appointment Completion';
		setupAppointmentDetailsForm();
		loadAppoinmentCombo();
		
		//formAppoinmentStatus
		if($('#appointmentId').val() != "") {
		
		 var appointmentDetailsURL = "getCustomerAppointmentDetails/";
		appointmentDetailsURL = appointmentDetailsURL +$('#appointmentId').val()+".html";
		$.ajax({
	        url : appointmentDetailsURL,
	        type: 'GET',
	        success : handleAppoinmentDetailsData
	    });
	    
    	 
    		
	}	
		
	
	$('#formAppoinmentStatus').on('change', function(event) {
			var args = event.args;
			if (args) {
				var item = args.item;
				if (item.value != "") {
					$("#updateAppointmentButton").jqxButton({ disabled: false });
					if(item.value == "Completed") {
						//$('#appoinmentDetailsTab').jqxTabs('enableAt', 1);
						//show table
						$("#jobCompletionTable").show();
						$(".endTimingsDiv").show();
						
						$(".cancellRemarksDiv").hide();
					} else {
						//$('#appoinmentDetailsTab').jqxTabs('disableAt', 1);
						//hide table
						$("#jobCompletionTable").hide();
						$(".endTimingsDiv").hide();
						$(".cancellRemarksDiv").show();
						//$('#formRemarks').attr("disabled", "enabled");
					}
				} else {
					$("#updateAppointmentButton").jqxButton({ disabled: true });
					//hide table
					$("#jobCompletionTable").hide();
					$(".endTimingsDiv").hide();
					$(".cancellRemarksDiv").hide();
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
				$.ajax({
			        url : appointmentDetailsURL,
			        type: 'GET',
			        success : handleAppoinmentDetailsData
			    });
            	/* $.get(appointmentDetailsURL, function(appointmentView){            		
            		$("#customerId").val(appointmentView.customerId);
            		$("#appointmentNo").text(appointmentView.appointmentNo);
            		$("#customerName").text(appointmentView.customerName);
            		$("#employeeName").text(appointmentView.employeeName);
            		//$("#appointmentDateDisplay").text(appointmentView.appointmentDate);
            		//$("#appointmentDate").val(appointmentView.appointmentDate);
            		var appointmentDateObj = new Date(appointmentView.appointmentDate);
		    		var formattedDate = pad(appointmentDateObj.getDate(), 2)+"-"+pad(appointmentDateObj.getMonth()+1, 2)+"-"+appointmentDateObj.getFullYear();
		    		//alert(new Date(appointmentView.appointmentDate));
		    		//alert(formattedDate);
		    		$("#appointmentDate").val(formattedDate);
		    		$("#appointmentDateDisplay").text(formattedDate);
            		$("#startTime").text(appointmentView.startTime);
            		$("#formEndTime").val(appointmentView.endTime);
            		$("#notesDetailsDiv").text(appointmentView.remarks);
            		calculateHoursSpent();
            		loadCustomerContractCombo(appointmentView.customerId);
            		$("#location").text(appointmentView.flatNo +", "+appointmentView.buildingName+"," +appointmentView.areaName+", "+appointmentView.cityName);            		
            	});	 */
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
         		calculateHoursSpent();
         	}
		}
	});	
	
	function validateContractPayment() {
		var formPaymentTypeCombo = $("#formPaymentType").jqxComboBox('getSelectedItem');
		if(formPaymentTypeCombo != null && formPaymentTypeCombo.value == "Contract") {
			var formCustomerContractCombo = $("#formCustomerContractId").jqxComboBox('getSelectedItem'); 
			if(formCustomerContractCombo == null || formCustomerContractCombo.value == "") {
				jqxAlert.alert('Please select customer contract details !');	
				return false;	
			} 
		}
		return true;
	}
	 
	
	
		$("#updateAppointmentButton").click(	
			function() {
				
				var appointmentIdCombo = $("#formAppointmentId").jqxComboBox('getSelectedItem'); 	
				if(appointmentIdCombo == null) {
					jqxAlert.alert('Please select an appointment to update !');	
					return;
				} else {
					$("#id").val(appointmentIdCombo.value);
				}
				var appointmentStatusCombo = $("#formAppoinmentStatus").jqxComboBox('getSelectedItem'); 	
				if(appointmentStatusCombo != null) {
					$("#appointmentStatus").val(appointmentStatusCombo.value);
					if(appointmentStatusCombo.value == "Completed") {
						/* if(parseFloat($("#formHoursSpent").val()) <4){
							jqxAlert.alert('Total hours spent should be greater than or equal to 4 hours. Please correct End Time !');
							return;
						} */
						
						
						var amountPayable = document.getElementById("formAmountPayable").value;
						if(amountPayable == "" || parseInt(amountPayable) <=0) {
							jqxAlert.alert('Please enter valid amount payable !');
							return;
						}
						
						var paymentStatusCombo = $("#formPaymentStatus").jqxComboBox('getSelectedItem'); 	
						if(paymentStatusCombo != null) {
							$("#paymentStatus").val(paymentStatusCombo.value);
						}
						
						if(paymentStatusCombo == null) {
							jqxAlert.alert('Please select payment status !');
							return;
						}
						
						var paymentTypeCombo = $("#formPaymentType").jqxComboBox('getSelectedItem'); 	
						if(paymentTypeCombo != null) {
							$("#paymentType").val(paymentTypeCombo.value);
						}
						
						if(paymentTypeCombo == null) {
							jqxAlert.alert('Please select payment type !');
							return;
						}
						
						if(!validateContractPayment()) {
							return;
						}
						
						var invoiceNo = document.getElementById("formInvoiceNo").value;
						if(invoiceNo.trim() == "" ) {
							jqxAlert.alert('Please enter Invoice No. !');
							return;
						}
						if(isDuplicateInvoice) {
							jqxAlert.alert('Duplicate Invoice No !');
							return;
						
						}
				
						/* if(!$('#appointmentUpdateMainForm').jqxValidator('validate')) {
							return;
						} */
										
						$("#cancellationReason").val($("#formRemarks").val());
						$("#endTime").val($("#formEndTime").val());
						$("#payableAmount").val(amountPayable);
						$("#hoursSpent").val($("#formHoursSpent").val());
						$("#invoiceNo").val($("#formInvoiceNo").val());
						$("#invoiceDate").val($("#formInvoiceDate").val());
						
						$.ajax({
							url: "validateAppointmentUpdate.html",
							type: 'GET',
							data: {appointmentId: $("#id").val(), appointmentDate: $("#appointmentDate").val(), endTime: $("#endTime").val()},
							success: function(actionStatus)
							{
								if(actionStatus.statusType == "Success") {
									//alert("Complete:Submit");
									if($("#sendCompletionSMSYes").val() == true) {
										$("#sendSMSFlagCompletion").val("Yes");
									} else {
										$("#sendSMSFlagCompletion").val("No");
									}
									$('#appoinmentUpdateForm').submit();
								} else {
									//jqxAlert.alert("Appointment end date/time cannot be greater than current date/time");
									jqxAlert.alert(actionStatus.statusMessage);
								}
							},
							error: function()
							{
								jqxAlert.alert("System Error Occured ! Please contact Support.");
							}
						});
					} else {
						
						$.ajax({
							url: "validateAppointmentUpdateForCancellation.html",
							type: 'GET',
							data: {appointmentId: $("#id").val(), appointmentDate: $("#appointmentDate").val(), startTime: $("#startTime").text()},
							success: function(actionStatus)
							{
								if(actionStatus.statusType == "Success") {
									$("#cancellationReason").val($("#formRemarks").val());
									
									if($("#sendCancellationSMSYes").val() == true) {
										$("#sendSMSFlagCancellation").val("Yes");
									} else {
										$("#sendSMSFlagCancellation").val("No");
									}
									
									//alert("Cancel:Submit");
									$('#appoinmentUpdateForm').submit();
								} else {
									//jqxAlert.alert("Appointment end date/time cannot be greater than current date/time");
									jqxAlert.alert(actionStatus.statusMessage);
								}
							},
							error: function()
							{
								jqxAlert.alert("System Error Occured ! Please contact Support.");
							}
						});
						
					}
				}	
				//
			});
	
		//hide table
		$("#jobCompletionTable").hide();
		$(".endTimingsDiv").hide();
		$(".cancellRemarksDiv").hide();
		
		setupFormValidations();
	});
	
	
	
	
	function loadAppoinmentCombo() {
		var appointmentListUrl = "customerAppoinmentComboListJSONActive.html";
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
						var valueToFind = $('#appointmentId').val();
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
			searchMode : "containsignorecase",
			source : appointmentListDataAdapter,
			displayMember : "appointmentNo",
			valueMember : "id",
			width : 130,
			height : 20
		});
	}
	
	$('#formPaymentType').on('change', function(event) {
		var args = event.args;
		if (args) {
			var item = args.item;
			if (item.value != "" && item.value== "Contract") {
				// Enable
				
			} else {
				//Disable
			}
		}
	});
	
function setupFormValidations() {
		isDuplicateInvoice = false;
		$('#appointmentUpdateMainForm').jqxValidator({
			//_margin: 30,
			rules: [
				{ input: '#formInvoiceNo', message: 'Duplicate Invoice No !', 
						action: 'blur', rule: function (input, commit) {					
						isDuplicateInvoice = true;
						var invoiceNo = $("#formInvoiceNo").val();
						if(invoiceNo == "") {
							return;
						}
						$.ajax({
							url: "checkDuplicateInvoiceNo.html",
							type: 'GET',
							data: {invoiceNo: invoiceNo},
							success: function(data)
							{
								if (data == "false")
								{
									isDuplicateInvoice = false;
									commit(true);
								}
								else {
									isDuplicateInvoice = true;
									commit(false);
								};
							},
							error: function()
							{
								isDuplicateInvoice = true;
								commit(false);
							}
						});
					}
				}
			]
		});
	}
</script>


<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">
	<div id="createAccount" class="cornerDiv">
			<div style="background-color: #F4F0F5; color: #000; min-height: 1.5em; vertical-align: middle; padding: 5px; width: 830px;">
				Job Details</div>
			<div style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 0px;">
				
				<table width="94%" class="popupFormTable">
				<form class="form" id="appointmentUpdateMainForm">
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
					                <li style="margin-left: 30px;">Notes</li>			        
					            </ul>
								<div id="jobDetailsDiv">
									<table>
										<tr valign="top">
											<td>
												<table>
													<tr>
														<td align="right" nowrap>Appointment No : </td>
														<td><span id="appointmentNo"></span></td>
													</tr>
													<tr>
														<td align="right" nowrap>Customer Name :</td>
														<td><span id="customerName"></span></td>
													</tr>
													<tr>
														<td align="right" nowrap>Staff Name :</td>
														<td><span id="employeeName"></span></td>
													</tr>
													<tr>
														<td align="right" nowrap>Appointment Date :</td>
														<td><span id="appointmentDateDisplay"></span></td>
													</tr>
													<tr>
														<td align="right" nowrap>Start Time :</td>
														<td><span id="startTime"></span></td>
													</tr>
													<tr>
														<td align="right" nowrap>Location :</td>
														<td><span id="location"></span></td>
													</tr>
													<tr>
														<td align="right" nowrap>Status :</td>
														<td><div id="formAppoinmentStatus" ></div></td>
													</tr>
													<tr class="cancellRemarksDiv">
														<td align="right" nowrap>Cancel Remarks :</td>
														<td><textarea class="textArea" id="formRemarks" rows="3" cols="24"></textarea></td>
													</tr>
													<tr class="cancellRemarksDiv">
														<td>
															Send SMS?
														</td>
														<td>
															<div id='sendCancellationSMSYes' style="width:80px; float:left;">
																Yes
															</div>
															<div id='sendCancellationSMSNo' style="width:80px; float:left;">
																No
															</div>
														</td>
													</tr>
													<tr class="endTimingsDiv">
														<td align="right" nowrap>End Time : </td>
														<td><div id="formEndTime" ></div></td>
													</tr>
													<tr class="endTimingsDiv">
														<td align="right" nowrap>No. of of hours :</td>
														<td><input type="text" id="formHoursSpent"/></td>
													</tr>
													</tr>													
												</table>
											</td>
											<td>
												<table id="jobCompletionTable">													
													<tr>
														<td align="right">Amount Payable :</td>
														<td><input type="text" id="formAmountPayable" onkeypress='numberonly(event)' /></td>
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
														<td align="right">Contract :</td>
														<td><div id="formCustomerContractId" ></div></td>
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
														<td align="right">Credit Card/Cheque <br>Other Details : </td>
														<td><input type="text" id="formPaymentDetails"/></td>
													</tr>
													<tr>
														<td align="right">Payment Due Date :</td>
														<td><div id="formDueDate" ></div></td>
													</tr>
													<tr>
														<td>
															Send SMS?
														</td>
														<td>
															<div id='sendCompletionSMSYes' style="width:80px; float:left;">
																Yes
															</div>
															<div id='sendCompletionSMSNo' style="width:80px; float:left;">
																No
															</div>
														</td>
													</tr>
												</table>
											</td>
										</tr>										
									</table>
	                				
	                			</div>
	                			<div id="notesDetailsTab">
	                				<div style="white-space: normal; margin: 5px;"><span id="notesDetailsDiv"></span></div>
	                			</div>
	                			
                			</div>
                								
						</td>
					</tr>
					</form>
					<tr>
						<td colspan="2">
							<form id="appoinmentUpdateForm" action="updateCustomerAppoinment.html" method="post">
								<input type="hidden" id="id" 	name="id"/>
								<input type="hidden" id="customerId" 	name="customerId"/>
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
								<input type="hidden" id="appointmentCompletionSMSFlag" 	name="appointmentCompletionSMSFlag" value="${appointmentCompletionSMSFlag}"/>
								<input type="hidden" id="appointmentCancellationSMSFlag" 	name="appointmentCancellationSMSFlag" value="${appointmentCancellationSMSFlag}"/>
								<input type="hidden" id="sendSMSFlagCompletion" 	name="sendSMSFlagCompletion"/>
								<input type="hidden" id="sendSMSFlagCancellation" 	name="sendSMSFlagCancellation"/>
								<input id="updateAppointmentButton" type="button" value="Update Appoinment Details" />
							</form>
						</td>
					</tr>
				</table>				
	</div>
</div>


<jsp:include page="includes/footer.jsp" />
