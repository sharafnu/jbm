<jsp:include page="includes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<span class="breadcrumbs">Home > Job Management > Job List </span>
</div>
<script type="text/javascript"
	src="<c:url value="/resources/scripts/appoinment-search-list.js" />"></script>
<script type="text/javascript">
        $(document).ready(function () {
        	document.title = 'Job List';
        	setupAppoinmentListSearchFilters();
           	//setupContractsPopupForm();
           	appoinmentDetailsGrid();
           	if($('#actionMessage').val() != "") {
           		alert($('#actionMessage').val());
           	}
			//set selected filters
			//setSelectedFilters();
        });

    </script>



<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">
	<input type="hidden" id="appointmentViewListJSON" value='<c:out value="${appointmentListJSON}"/>'/>
	<input type="hidden" id="actionMessage" name="actionMessage" value="${actionMessage}"/>
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
						<td align="right" nowrap>
							<form id="appoinmentSearchForm" action="customerAppointmentList.html" method="post">
								<!-- All hidden fields goes here -->
								<input type="hidden" id="id" 	name="id" value="${appointmentView.id}"/>
								<input type="hidden" id="appointmentNo" 	name="appointmentNo" value="${appointmentView.appointmentNo}"/>
								<input type="hidden" id="appointmentStatus" name="appointmentStatus" value="${appointmentView.appointmentStatus}"/>
								<input type="hidden" id="startDate" 		name="startDate" value="${appointmentView.startDateStr}"/>
								<input type="hidden" id="endDate" 			name="endDate" value="${appointmentView.endDateStr}"/>
								<input type="hidden" id="areaId" 			name="areaId" value="${appointmentView.areaId}"/>
								<input type="hidden" id="customerId" 		name="customerId" value="${appointmentView.customerId}"/>
								<input type="hidden" id="employeeId" 		name="employeeId" value="${appointmentView.employeeId}"/>
								
								<input id="searchButton" type="button" value="Search" />
								<input id="resetButton" type="button" value="Reset" />
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
		
		
        			       	</div>
      </div>


<jsp:include page="includes/footer.jsp" />
