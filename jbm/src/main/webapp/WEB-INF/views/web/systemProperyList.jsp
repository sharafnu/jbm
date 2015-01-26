<jsp:include page="includes/header.jsp" />
<div>
	<span class="breadcrumbs">Home > Master Setup > Master Setup List </span>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						document.title = 'System Property List';

						$("#formPropGroup").jqxInput({
							placeHolder: "Enter Property Group Code",
							width : '300px',
							height : '20px'
						});
						
						$("#formPropKey").jqxInput({
							placeHolder: "Enter Property Key",
							width : '300px',
							height : '20px'
						});
						
						$("#formPropValue").jqxInput({
							placeHolder: "Enter Property Value",
							width : '300px',
							height : '20px'
						});
												
						$("#formDescription").jqxInput({
						    placeHolder: "Enter a Description",
						    height: '20px',
						    width: '300px'
						});
						var masterSourceURL = "systemProperyListJSON.html";
						var masterSource = {
							datatype : "json",
							datafields : [ {
								name : 'id',
								type : 'string'
							}, {
								name : 'propGroup',
								type : 'string'
							}, {
								name : 'propKey',
								type : 'string'
							}, {
								name : 'propValue',
								type : 'string'
							}, {
								name : 'description',
								type : 'string'
							} ],
							id : 'id',
							url : masterSourceURL
						};
						var masterListDataAdapter = new $.jqx.dataAdapter(
								masterSource);
						
						// initialize jqxGrid
						$("#jqxgrid")
								.jqxGrid(
										{
											width : 800,
											source : masterListDataAdapter,
											showstatusbar : true,
											showfilterrow : true,
											filterable : true,
											renderstatusbar : function(
													statusbar) {
												// appends buttons to the status bar.

												var container = $("<div style='overflow: hidden; position: relative; margin: 5px;'></div>");
												var addButton = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='resources/images/add.png'/><span style='margin-left: 4px; position: relative; top: -3px;'>Add</span></div>");
												var editButton = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='resources/images/edit-icon.png'/><span style='margin-left: 4px; position: relative; top: -3px;'>Edit</span></div>");
												var deleteButton = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='resources/images/close.png'/><span style='margin-left: 4px; position: relative; top: -3px;'>Delete</span></div>");

												container.append(addButton);
												container.append(editButton);
												container.append(deleteButton);
												statusbar.append(container);
												addButton.jqxButton({
													width : 60,
													height : 20
												});
												editButton.jqxButton({
													width : 60,
													height : 20
												});
												deleteButton.jqxButton({
													width : 65,
													height : 20
												});
												// add new row.
												deleteButton
														.click(function(event) {
															var getselectedrowindexes = $('#jqxgrid').jqxGrid('getselectedrowindexes');
															if (getselectedrowindexes.length > 0) {
											                    // returns the selected row's data.
											                    var selectedRowData = $('#jqxgrid').jqxGrid('getrowdata', getselectedrowindexes[0]);
																if(confirm("Are you sure you want to delete the record : "+selectedRowData['propKey'])) {
																	$.ajax(
																			{
																				type : "POST",
																				url : "deleteSystemProperty.html",
																				data : {
																					id : selectedRowData['id']	
																					}
																			})
																	.done(
																			function(actionStatus) {
																				if (actionStatus.statusType == "Success") {
																					$("#jqxgrid")
																							.jqxGrid(
																									'updatebounddata');
																				} else {
																					alert(actionStatus.statusMessage);
																				}
																			});
																}
															} else {
																alert("Please select the record to delete!");
															}
														});
												// add new row.
												addButton
														.click(function(event) {
															//Clear data
															$("#formPropGroup").val("");
										                    $("#formPropKey").val("");
										                    $("#formPropValue").val("");
										                    $("#formDescription").val("");
										                    $("#formId").val("");
										                    
															var offset = 0;//$("#jqxgrid").offset();
															$("#popupWindow")
																	.jqxWindow(
																			{
																				position : {
																					x : parseInt(offset.left) + 60,
																					y : parseInt(offset.top) + 60
																				}
																			});
															$("#popupWindow")
																	.jqxWindow(
																			'open');
														});
												
												// edit selected row.
												editButton
														.click(function(event) {
															var getselectedrowindexes = $('#jqxgrid').jqxGrid('getselectedrowindexes');
															if (getselectedrowindexes.length > 0) {
											                    // returns the selected row's data.
											                    var selectedRowData = $('#jqxgrid').jqxGrid('getrowdata', getselectedrowindexes[0]);
											                    $("#formPropGroup").val((selectedRowData['propGroup']));
											                    $("#formPropKey").val((selectedRowData['propKey']));
											                    $("#formPropValue").val((selectedRowData['propValue']));
											                    $("#formDescription").val((selectedRowData['description']));
											                    $("#formId").val((selectedRowData['id']));
											                  
											                } else {
											                	alert("Please select a row to edit!")
											                }
															var offset = 0;//$("#jqxgrid").offset();
															$("#popupWindow")
																	.jqxWindow(
																			{
																				position : {
																					x : parseInt(offset.left) + 60,
																					y : parseInt(offset.top) + 60
																				}
																			});
															$("#popupWindow")
																	.jqxWindow(
																			'open');
														});
											},
											pageable : true,
											autoheight : true,
											sortable : true,
											altrows : true,
											enabletooltips : true,
											editable : false,
											//selectionmode: 'none',
											columns : [ {
												text : 'Property Group',
												filtertype : 'textbox',
												filtercondition : 'CONTAINS',
												datafield : 'propGroup',
												width : 200
											}, {
												text : 'Property Key',
												filtertype : 'textbox',
												filtercondition : 'CONTAINS',
												datafield : 'propKey',
												width : 250
											}, {
												text : 'Property Value',
												filtertype : 'textbox',
												filtercondition : 'CONTAINS',
												datafield : 'propValue',
												width : 350
											}, {
												text : 'Description',
												filtertype : 'textbox',
												filtercondition : 'CONTAINS',
												datafield : 'description',
												width : 150
											}]
										});

						// initialize the popup window and buttons.
						$("#popupWindow").jqxWindow({
							title : "Add/Edit Property Details",
							width : 500,
							resizable : false,
							isModal : true,
							autoOpen : false,
							cancelButton : $("#Cancel"),
							modalOpacity : 0.01
						});

						$("#Cancel").jqxButton({
							theme : theme
						});
						var saveButton = $("#Save").jqxButton({
							theme : theme
						});

						saveButton
								.click(function(event) {
									$.ajax(
													{
														type : "POST",
														url : "saveSystemProperty.html",
														data : {
															id : $("#formId").val(),
															propGroup : $("#formPropGroup").val(),
															propKey : $("#formPropKey").val(),
															propValue : $("#formPropValue").val(),
															description : $("#formDescription").val()
														}
													})
											.done(
													function(actionStatus) {
														if (actionStatus.statusType == "Success") {
															$("#jqxgrid")
																	.jqxGrid(
																			'updatebounddata');
														} else {
															alert(actionStatus.statusMessage);
														}
													});

									//Clear form values
									$("#formPropGroup").val("");
				                    $("#formPropKey").val("");
				                    $("#formPropValue").val("");
				                    $("#formDescription").val("");
				                    $("#formId").val("");

									$("#popupWindow").jqxWindow('hide');
								});
					});
</script>

<form id="form" action="./">

	<!-- Container for create-account controls, populated by JavaScript code below. -->
	<div id="SIU2" class="SIU2" style="opacity: 1;">
		<div id="createAccount" class="cornerDiv" style="height: 380px">
			<div id="jqxgrid"></div>

			<div id="popupWindow">
				<div style="overflow: hidden;">
					<table class="popupFormTable">
						<tr>
							<td align="right">Propery Group:</td>
							
							<input type="hidden" id="formId" />
							<td align="left"><input id="formPropGroup" /></td>
						</tr>
						<tr>
							<td align="right">Property Key:</td>
							<td align="left"><input id="formPropKey" /></td>
						</tr>
						<tr>
							<td align="right">Property Value:</td>
							<td align="left"><input id="formPropValue" /></td>
						</tr>
						<tr>
							<td align="right">Description:</td>
							<td align="left"><input id="formDescription" /></td>
						</tr>
						<tr>
							<td align="right"></td>
							<td style="padding-top: 10px;" align="right"><input
								style="margin-right: 5px;" type="button" id="Save" value="Save" /><input
								id="Cancel" type="button" value="Cancel" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</form>

<jsp:include page="includes/footer.jsp" />
