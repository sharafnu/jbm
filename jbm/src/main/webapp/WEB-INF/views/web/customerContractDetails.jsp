<jsp:include page="includes/header.jsp" />
<div>
	<span class="breadcrumbs">Home > Customer > Customer Contracts </span>
</div>

<script type="text/javascript">
        $(document).ready(function () {
        	document.title = 'Customer Contracts';
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
                        	loadContractDetailsGrid(item.value);
                        }
                    }
             });
			
			 setupContractsPopupForm();
        });
        
        function loadContractDetailsGrid(customerId) {
        	var customerContractListUrl = "getCustomerContractListJSON/"+customerId+".html";
            var customerContractListSource =
            {
                datatype: "json",
                datafields: [
                    { name: 'amount', 			type: 'int' },
                    { name: 'visitCount', 		type: 'int' },
                    //{ name: 'utilizedVisitCount', 		type: 'int' },
                    { name: 'contractNo', 		type: 'string' },
                    { name: 'contractStatus', 	type: 'string' },
                    { name: 'contractType', 	type: 'string' },
                    { name: 'contractDate', 	type: 'string' },
                    { name: 'expiryDate', 		type: 'string' }
                ],
                id: 'id',
                url: customerContractListUrl
            };
            var customerContractDataAdapter = new $.jqx.dataAdapter(customerContractListSource);
            
            // initialize jqxGrid
            $("#jqxgrid").jqxGrid(
            {
                width: 760,
                source: customerContractDataAdapter,
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
		  		  { text: 'Contract No',  		datafield: 'contractNo', 		width: 150 }, 	
                  { text: 'Contract Type',  	datafield: 'contractType', 		width: 160 },
                  { text: 'Contract Date',  	datafield: 'contractDate', 		width: 100 },
                  { text: 'Amount',  			datafield: 'amount', 			width: 80 },
                  { text: 'No. of Visits',  	datafield: 'visitCount', 		width: 90 },
                  //{ text: 'Utilized',  			datafield: 'utilizedVisitCount', 	width: 90 },
                  { text: 'Expiry Date',  		datafield: 'expiryDate', 		width: 100 },
                  { text: 'Status',  			datafield: 'contractStatus', 	width: 80 }
                ]
            });
        }
        
        function setupContractsPopupForm() {
         	var contractTypeSource = [
                          "Visits Promotion"
      		];
         	            
            $("#fromContractType").jqxComboBox({ selectedIndex: 0, source: contractTypeSource, width: 200, height: 20});
            
            var contractStatusSource = [
                                      "Active",
                                      "Expired"
                  		];
                     	            
            $("#frmContractStatus").jqxComboBox({ selectedIndex: 0, source: contractStatusSource, width: 200, height: 20});
            
            
            $("#frmVisitCount").jqxInput({
				width : '80px',
				height : '20px'
			});
            $("#frmAmount").jqxInput({
				width : '80px',
				height : '20px'
			});
            $("#frmContractNo").jqxInput({
				width : '200px',
				height : '20px',
				disabled: true
			});
            $("#frmContractDate").jqxDateTimeInput({width: '200px', height: '20px', formatString: 'dd-MM-yyyy'});
           	
            $("#frmExpiryDate").jqxDateTimeInput({width: '200px', height: '20px', formatString: 'dd-MM-yyyy'});
            
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
		       		amount: $("#frmAmount").val(), visitCount: $("#frmVisitCount").val(), expiryDate: $("#frmExpiryDate").val(), contractStatus: contractStatusItem.value, customerId:customerIdItem.value }
		       	}).done(function( msg ) {
		       	    //alert( "Data Saved: " + msg );
		       		$("#jqxgrid").jqxGrid('updatebounddata');
		       	});
	       	    //$("#jqxgrid").jqxGrid('addrow', null, row);
                //Clear form values
                $("#frmAmount").val("");
                $("#frmVisitCount").val("");
                $("#frmContractNo").val("");
                $("#frmContractStatus").val("");
                $("#fromContractType").val("");
                $("#frmContractDate").val("");
                $("#frmExpiryDate").val("");
                $("#popupWindow").jqxWindow('hide');
            });
        }
        
    </script>

<form id="form" action="./">

<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">
	<div id="createAccount" class="cornerDiv" style="height:330px">	
		<div style="background-color: #F4F0F5; color: #000; min-height: 1.5em; vertical-align: middle; padding: 5px; width: 800px;">
		Customer Contracts</div>
        <div style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 5px;">
				<table border="0" width="100%" class="popupFormTable">
					<tr>
						<td colspan="1" width="20%">Select Customer :</td>
						<td colspan="1" align="left" width="80%"><div id="formCustomerId" ></div></td>
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
	                        <td align="right">No. of Visits:</td>
	                        <td align="left"><input id='frmVisitCount'/></td>
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
</form>

<jsp:include page="includes/footer.jsp" />
