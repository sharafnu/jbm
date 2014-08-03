<jsp:include page="includes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<span class="breadcrumbs">Home > Job Management > Customer List </span>
</div>
<script type="text/javascript"
	src="<c:url value="/resources/scripts/customer-search-list.js" />"></script>
<script type="text/javascript">
        $(document).ready(function () {
        	document.title = 'Customer List';        	
        	setupCustomerListSearchFilters();
           	customerDetailsGrid();
           	if($('#actionMessage').val() != "") {
           		jqxAlert.alert($('#actionMessage').val());
           	}
        });

    </script>



<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">
	<input type="hidden" id="customerViewListJSON" value='<c:out value="${customerListJSON}"/>'/>
	<input type="hidden" id="actionMessage" name="actionMessage" value="${actionMessage}"/>
	<div id="createAccount" class="cornerDiv" style="height:750px">	
		<div style="background-color: #F4F0F5; color: #000; min-height: 1.5em; vertical-align: middle; padding: 5px; width: 800px;">
		Customer List</div>
        <div style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 0px;">
				<table border="0" width="100%" class="searchFiltersTable">
					<tr>
						<td colspan="1" width="15%" align="right" nowrap>Customer :</td>
						<td colspan="1" align="left" width="20%"><div id="formCustomerId" ></div></td>
					</tr>
					<tr>
						<td align="right" nowrap>
							<form id="customerSearchForm" action="customerList.html" method="post">
								<!-- All hidden fields goes here -->
								<input type="hidden" id="id" 		name="customerId" value="${customerView.id}"/>
								
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
