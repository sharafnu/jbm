<jsp:include page="includes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<span class="breadcrumbs">Home > Job Management > Job List </span>
</div>
<script type="text/javascript"
	src="<c:url value="/resources/scripts/appoinment.js" />"></script>
<script type="text/javascript">
        $(document).ready(function () {
        	document.title = 'Job List';
        	setupAppoinmentListSearchFilters();
           	setupContractsPopupForm();
           	appoinmentDetailsGrid();
        });
        
    </script>



<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">
	<input type="hidden" id="appointmentViewListJSON" value='<c:out value="${appoinmentListJSON}"/>'/>
	<div id="createAccount" class="cornerDiv">	
		<div style="background-color: #F4F0F5; color: #000; min-height: 1.5em; vertical-align: middle; padding: 5px; width: 800px;">
		Job List</div>
        <div style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 0px;">
				<table border="0" width="100%" class="searchFiltersTable">
					<tr>
						<td colspan="1" width="15%" align="right" nowrap>Customer :</td>
						<td colspan="1" align="left" width="20%"><div id="formCustomerId" ></div></td>
						<td colspan="1" width="15%" align="right" nowrap> Staff :</td>
						<td colspan="1" align="left" width="20%"><div id="formEmployeeId" ></div></td>
						<td colspan="1" width="15%" align="right" nowrap> App. Id :</td>
						<td colspan="1" align="left" width="20%"><div id="formAppointmentId" ></div></td>						
					</tr>
					<tr>
						<td colspan="1" width="15%" align="right" nowrap>Location :</td>
						<td colspan="1" align="left" width="20%"><div id="formAreaId" ></div></td>
						<td colspan="1" width="15%" align="right" nowrap>From Date :</td>
						<td colspan="1" align="left" width="20%"><div id="formFromDate" ></div></td>
						<td colspan="1" width="15%" align="right" nowrap>To Date :</td>
						<td colspan="1" align="left" width="20%"><div id="formToDate" ></div></td>
					</tr>
					<tr>
						<td colspan="1" width="15%" align="right" nowrap>Status :</td>
						<td colspan="1" align="left" width="20%"><div id="formAppoinmentStatus" ></div></td>						
						<td align="right">
							<form id="appoinmentSearchForm" action="customerAppointmentList.html" method="post">
								<!-- All hidden fields goes here -->
								<input type="hidden" id="appointmentNo" 	name="appointmentNo"/>
								<input type="hidden" id="appointmentStatus" name="appointmentStatus"/>
								<input type="hidden" id="startDate" 		name="startDate"/>
								<input type="hidden" id="endDate" 			name="endDate"/>
								<input type="hidden" id="areaId" 			name="areaId"/>
								<input type="hidden" id="customerId" 		name="customerId"/>
								<input type="hidden" id="employeeId" 		name="employeeId"/>
								
								<input id="searchButton" type="button" value="Search" />
							</form>
						</td>						
					</tr>
				</table>				
		</div>
		
		<div style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 0px;">
				<table border="0" width="100%" class="popupFormTable">
					<tr>
						<td colspan="2" width="100%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="2" width="100%">
							<div id="jqxgrid"></div>
						</td>
					</tr>
				
				</table>
				</form>
		</div>
		
		
        				
        <div id="popupWindow">
                <div style="overflow: hidden;">
	                <table class="popupFormTable">
	                	<tr>
	                        <td align="right">Contract No:</td>
	                        <td align="left"><input id="frmContractNo" /></td>
	                    </tr>
	                    <tr>
	                        <td align="right">Contract Type:</td>
	                        <td align="left"><div id="fromContractType" ></div></td>
	                    </tr>
	                    <tr>
	                        <td align="right">Contract Date:</td>
	                        <td align="left"><div id="frmContractDate" /></div></td>
	                    </tr>
	                    <tr>
	                        <td align="right">Amount:</td>
	                        <td align="left"><input id='frmAmount'/></td>
	                    </tr>
	                    <tr>
	                        <td align="right">Expiry Date:</td>
	                        <td align="left"><div id="frmExpiryDate" ></div></td>
	                    </tr>
	                    <tr>
	                        <td align="right">Status:</td>
	                        <td align="left"><div id="frmContractStatus" ></div></td>
	                    </tr>
	                    <tr>
	                        <td align="right"></td>
	                        <td style="padding-top: 10px;" align="right"><input style="margin-right: 5px;" type="button" id="Save" value="Save" /><input id="Cancel" type="button" value="Cancel" /></td>
	                    </tr>
	                </table>
	            </div>
       		</div>
       	</div>
      </div>


<jsp:include page="includes/footer.jsp" />
