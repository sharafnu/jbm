<jsp:include page="includes/header.jsp" />
<div>
	<span class="breadcrumbs">Home > Master Setup > Area List</span>
</div>

<script type="text/javascript">
        $(document).ready(function () {
        	document.title = 'Area List';
        	var cityUrl = "cityListJSON.html";
            var citySource =
            {
                datatype: "json",
                datafields: [
                    { name: 'cityName', type: 'string' },
                    { name: 'cityId', type: 'int' }
                ],
                id: 'cityId',
                url: cityUrl
            };
            var cityDataAdapter = new $.jqx.dataAdapter(citySource);
            
            $("#formCityId").jqxComboBox({ selectedIndex: 0, source: cityDataAdapter, displayMember: "cityName", valueMember: "cityId", width: 200, height: 25});
            $("#formAreaName").jqxInput({
				width : '200px',
				height : '20px'
			});
        	var areaUrl = "areaListJSON.html";
            var areaSource =
            {
                datatype: "json",
                datafields: [
                    { name: 'areaName', type: 'string' },
                    { name: 'cityName', type: 'string' }
                ],
                id: 'areaId',
                url: areaUrl
            };
            var areaDataAdapter = new $.jqx.dataAdapter(areaSource);
            
            // initialize jqxGrid
            $("#jqxgrid").jqxGrid(
            {
                width: 600,
                source: areaDataAdapter,
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
                  { text: 'Area Name',  datafield: 'areaName', width: 350 },
                  { text: 'City Name',  datafield: 'cityName', width: 250 }
                ]
            });
            
            // initialize the popup window and buttons.
            $("#popupWindow").jqxWindow({
                title: "Add new area", width: 350, resizable: false,  isModal: true, autoOpen: false, cancelButton: $("#Cancel"), modalOpacity: 0.01           
            });

            $("#Cancel").jqxButton({ theme: theme });
            var saveButton =  $("#Save").jqxButton({ theme: theme });
			
            saveButton.click(function (event) {
            	var item = $("#formCityId").jqxComboBox('getSelectedItem');
	       	    var row = { areaName: $("#formAreaName").val(), cityName: item.label};
		      
		       	$.ajax({
		       	  type: "POST",
		       	  url: "saveArea.html",
		       	  data: { areaName: $("#formAreaName").val(), cityId: item.value }
		       	}).done(function( msg ) {
		       	    //alert( "Data Saved: " + msg );
		       	});
	       	    $("#jqxgrid").jqxGrid('addrow', null, row);
                //Clear form values
                $("#formAreaName").val("");
                $("#formCityId").val("");
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
	                <table>
	                    <tr>
	                        <td align="right">Area Name:</td>
	                        <td align="left"><input id="formAreaName" /></td>
	                    </tr>
	                    <tr>
	                        <td align="right">City Name:</td>
	                        <td align="left"><div id='formCityId'></div></td>
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
