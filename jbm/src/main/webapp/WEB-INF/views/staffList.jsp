<jsp:include page="includes/header.jsp" />
<div>
	<span class="breadcrumbs">Home > Master Setup > Staff List </span>
</div>

<script type="text/javascript">
        $(document).ready(function () {
        	document.title = 'Staff List';
        	var nationalitySource = [
                          "UAE National",
                          "USA",
                          "UK",
                          "Lebabnon",
                          "Egypt",
                          "India"
      		];
            
            $("#formNationality").jqxComboBox({ selectedIndex: 0, source: nationalitySource, width: 200, height: 20});
            $("#formEmpCode").jqxInput({
				width : '80px',
				height : '20px'
			});
            $("#formEmpName").jqxInput({
				width : '200px',
				height : '20px'
			});
            $("#formJoinDate").jqxDateTimeInput({width: '200px', height: '20px'});
           
            $("#formSalary").jqxInput({
				width : '80px',
				height : '20px'
			});
            $("#fromRemarks").jqxInput({
				width : '200px',
				height : '20px'
			});
        	var staffListUrl = "staffListJSON.html";
            var staffListSource =
            {
                datatype: "json",
                datafields: [
                    { name: 'employeeCode', type: 'string' },
                    { name: 'firstName', 	type: 'string' },
                    { name: 'joinDate', 	type: 'string' },
                    { name: 'nationality', 	type: 'string' },
                    { name: 'remarks', 		type: 'string' },
                    { name: 'salary', 		type: 'int' }
                ],
                id: 'id',
                url: staffListUrl
            };
            var staffListDataAdapter = new $.jqx.dataAdapter(staffListSource);
            
            // initialize jqxGrid
            $("#jqxgrid").jqxGrid(
            {
                width: 800,
                source: staffListDataAdapter,
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
                        $("#popupWindow").jqxWindow({ position: { x: parseInt(offset.left) + 60, y: parseInt(offset.top) + 60 } });
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
                  { text: 'Emp ID',  		datafield: 'employeeCode', width: 80 },
                  { text: 'Emp Name',  		datafield: 'firstName', width: 250 },
                  { text: 'Nationality',  	datafield: 'nationality', width: 100 },
                  { text: 'Join Date',  	datafield: 'joinDate', width: 100 },
                  { text: 'Salary',  		datafield: 'salary', width: 60 },
                  { text: 'Remarks',  		datafield: 'remarks', width: 210 }
                ]
            });
            
            // initialize the popup window and buttons.
            $("#popupWindow").jqxWindow({
                title: "Add new area", width: 400, resizable: false,  isModal: true, autoOpen: false, cancelButton: $("#Cancel"), modalOpacity: 0.01           
            });

            $("#Cancel").jqxButton({ theme: theme });
            var saveButton =  $("#Save").jqxButton({ theme: theme });
			
            saveButton.click(function (event) {
            	var item = $("#formNationality").jqxComboBox('getSelectedItem');
	       	    var row = { employeeCode: $("#formEmpCode").val(), firstName: $("#formEmpName").val(), nationality: item.label, 
	       	    		joinDate: $("#formJoinDate").val(), salary: $("#formSalary").val(), remarks: $("#fromRemarks").val()};
		      
		       	$.ajax({
		       	  type: "POST",
		       	  url: "saveEmployee.html",
		       	  data: { employeeCode: $("#formEmpCode").val(), firstName: $("#formEmpName").val(), joinDate: $("#formJoinDate").val(), 
		       		  nationality: item.value, remarks: $("#fromRemarks").val(), salary: $("#formSalary").val()}
		       	}).done(function( msg ) {
		       	    //alert( "Data Saved: " + msg );
		       	});
	       	    $("#jqxgrid").jqxGrid('addrow', null, row);
                //Clear form values
                $("#formEmpCode").val("");
                $("#formEmpName").val("");
                $("#formJoinDate").val("");
                $("#fromRemarks").val("");
                $("#formSalary").val("");
                $("#formNationality").val("");
                $("#popupWindow").jqxWindow('hide');
            });
        });
    </script>

<form id="form" action="./">

<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">
	<div id="createAccount" class="cornerDiv">	
        <div id="jqxgrid">
        </div>
        
        <div id="popupWindow">
                <div style="overflow: hidden;">
	                <table class="popupFormTable">
	                	<tr>
	                        <td align="right">Employee Id:</td>
	                        <td align="left"><input id="formEmpCode" /></td>
	                    </tr>
	                    <tr>
	                        <td align="right">Employee Name:</td>
	                        <td align="left"><input id="formEmpName" /></td>
	                    </tr>
	                    <tr>
	                        <td align="right">Join Date:</td>
	                        <td align="left"><div id="formJoinDate" /></div></td>
	                    </tr>
	                    <tr>
	                        <td align="right">Nationality:</td>
	                        <td align="left"><div id='formNationality'></div></td>
	                    </tr>
	                    <tr>
	                        <td align="right">Salary:</td>
	                        <td align="left"><input id="formSalary" /></td>
	                    </tr>
	                    <tr>
	                        <td align="right">Remarks:</td>
	                        <td align="left"><input id="fromRemarks" /></td>
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
