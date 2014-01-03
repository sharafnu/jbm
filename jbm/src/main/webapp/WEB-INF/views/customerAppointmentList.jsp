<jsp:include page="includes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<span class="breadcrumbs">Home > Job Management > Job List </span>
</div>

<script type="text/javascript">
        $(document).ready(function () {
            
            $("#addCustomerButton").jqxButton({
				theme : theme
			});
        	
        	$("#addCustomerButton").click(function() {
				//$('#form').jqxValidator('validate');
				$('#form').submit();
			});
        	
        	var customerListUrl = "customerListJSON.html";
            var customerListSource =
            {
                datatype: "json",
                datafields: [
                    { name: 'fullName', type: 'string' },
                    { name: 'id', type: 'int' }
                ],
                id: 'id',
                url: customerListUrl
            };
            var customerListDataAdapter = new $.jqx.dataAdapter(customerListSource);
            

			$("#formCustomerId").jqxComboBox({
				selectedIndex : -1,
				source : customerListDataAdapter,
				displayMember : "fullName",
				valueMember : "id",
				width : 230,
				height : 25
			});
			
			 $('#formCustomerId').on('change', function (event) {
				    var args = event.args;
                    if (args) {
                    	var item = args.item;
                        if(item.value != "") {                        	
                        	//loadContractDetailsGrid(item.value);
                        }
                    }
             });
			
			 setupContractsPopupForm();
			 loadContractDetailsGrid();
        });
        
        function loadContractDetailsGrid() {
        	var appointmentViewListJSONStr = eval('('+'${appoinmentListJSON}'+')'); 
        	//var appointmentViewListJSONStr = '<c:out value="${appoinmentListJSON}"/>';
        	//alert(appointmentViewListJSONStr);
            var appoinmentListSource =
            {
                datatype: "json",
                datafields: [
                    { name: 'appointmentDate', 		type: 'string' },
                    { name: 'appointmentNo', 		type: 'string' },
                    { name: 'appointmentStatus', 	type: 'string' },
                    { name: 'remarks', 				type: 'string' },
                    { name: 'customerName', 		type: 'string' },
                    { name: 'employeeName', 		type: 'string' }
                ],
                id: 'id',
                localdata: appointmentViewListJSONStr
            };
            var appoinmentDataAdapter = new $.jqx.dataAdapter(appoinmentListSource);
            
            // initialize jqxGrid
            $("#jqxgrid").jqxGrid(
            {
                width: 750,
                source: appoinmentDataAdapter,
                showstatusbar: true,
                renderstatusbar: function (statusbar) {
                    // appends buttons to the status bar.
                    var container = $("<div style='overflow: hidden; position: relative; margin: 5px;'></div>");
                    var addButton = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='resources/images/add.png'/><span style='margin-left: 4px; position: relative; top: -3px;'>Add</span></div>");
                    var deleteButton = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='resources/images/close.png'/><span style='margin-left: 4px; position: relative; top: -3px;'>Delete</span></div>");
                    container.append(addButton);
                    container.append(deleteButton);
                    statusbar.append(container);
                    addButton.jqxButton({  width: 60, height: 20 });
                    deleteButton.jqxButton({  width: 65, height: 20 });
                    // add new row.
                    addButton.click(function (event) {
                   		var offset = $("#jqxgrid").offset();
                        $("#popupWindow").jqxWindow({ position: { x: parseInt(offset.left) + 60, y: parseInt(offset.top)  } });
                        $("#popupWindow").jqxWindow('open');
                    });
                },
                pageable: true,
                autoheight: true,
                sortable: true,
                altrows: true,
                enabletooltips: true,
                editable: false,
                selectionmode: 'none',
                columns: [
		  		  { text: 'Appointment No',  	datafield: 'appointmentNo', 		width: 170 }, 	
                  { text: 'Appointment Date',  	datafield: 'appointmentDate', 		width: 150 },
                  { text: 'Customer Name',  	datafield: 'customerName', 		width: 100 },
                  { text: 'Staff Name',  		datafield: 'employeeName', 			width: 100 },
                  { text: 'Appointment Status', datafield: 'appointmentStatus', 		width: 100 },
                  { text: 'Remarks',  			datafield: 'remarks', 	width: 130 }
                ]
            });
        }
        
        function setupContractsPopupForm() {
         	var contractTypeSource = [
                          "Contract Type 1",
                          "Contract Type 2",
                          "Contract Type 3"
      		];
         	            
            $("#fromContractType").jqxComboBox({ selectedIndex: 0, source: contractTypeSource, width: 200, height: 20});
            
            var contractStatusSource = [
                                      "Active",
                                      "Expired"
                  		];
                     	            
            $("#frmContractStatus").jqxComboBox({ selectedIndex: 0, source: contractStatusSource, width: 200, height: 20});
            
            $("#frmAmount").jqxInput({
				width : '80px',
				height : '20px'
			});
            $("#frmContractNo").jqxInput({
				width : '200px',
				height : '20px'
			});
            $("#frmContractDate").jqxDateTimeInput({width: '200px', height: '20px'});
           	
            $("#frmExpiryDate").jqxDateTimeInput({width: '200px', height: '20px'});
            
            // initialize the popup window and buttons.
            $("#popupWindow").jqxWindow({
                title: "Add New Contract", width: 400, resizable: false,  isModal: true, autoOpen: false, cancelButton: $("#Cancel"), modalOpacity: 0.01           
            });

            $("#Cancel").jqxButton({ theme: theme });
            var saveButton =  $("#Save").jqxButton({ theme: theme });
			
            saveButton.click(function (event) {
            	var contractTypeItem = $("#fromContractType").jqxComboBox('getSelectedItem');
            	var contractStatusItem = $("#frmContractStatus").jqxComboBox('getSelectedItem');
            	var customerIdItem = $("#formCustomerId").jqxComboBox('getSelectedItem');
	       	    var row = { contractNo: $("#frmContractNo").val(), contractType: contractTypeItem.value, contractDate: $("#frmContractDate").val(), 
	       	    		amount: $("#frmAmount").val(), expiryDate: $("#frmExpiryDate").val(), contractStatus: contractStatusItem.value};
		      
		       	$.ajax({
		       	  type: "POST",
		       	  url: "saveCustomerContract.html",
		       	  data: { contractNo: $("#frmContractNo").val(), contractType: contractTypeItem.value, contractDate: $("#frmContractDate").val(), 
		       		amount: $("#frmAmount").val(), expiryDate: $("#frmExpiryDate").val(), contractStatus: contractStatusItem.value, customerId:customerIdItem.value }
		       	}).done(function( msg ) {
		       	    //alert( "Data Saved: " + msg );
		       	});
	       	    $("#jqxgrid").jqxGrid('addrow', null, row);
                //Clear form values
                $("#frmAmount").val("");
                $("#frmContractNo").val("");
                $("#frmContractStatus").val("");
                $("#fromContractType").val("");
                $("#frmContractDate").val("");
                $("#frmExpiryDate").val("");
                $("#popupWindow").jqxWindow('hide');
            });
        }
        
    </script>



<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">
	<div id="createAccount" class="cornerDiv">	
		<div style="background-color: #F4F0F5; color: #000; min-height: 1.5em; vertical-align: middle; padding: 5px; width: 800px;">
		Job List</div>
        <div style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 10px;">
				<form id="form" action="customerAppointmentList.html" method="post">
				<table border="0" width="100%" class="popupFormTable">
					<tr>
						<td colspan="1" width="20%">Select Customer :</td>
						<td colspan="1" align="left" width="80%"><div id="formCustomerId" ></div></td>
					</tr>
					<tr>
						<td colspan="1">
							
							<%-- <c:out value="${appoinmentListJSON}"/> --%>
							<input id="addCustomerButton" type="button" value="GO" />
						</td>
					</tr>
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