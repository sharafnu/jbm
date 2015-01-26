<jsp:include page="includes/header.jsp" />
<div>
	<span class="breadcrumbs">Home > Master Setup > Master Setup List </span>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						document.title = 'Master List';

						$("#formMasterType").jqxInput({
							placeHolder: "Enter Master Type",
							width : '200px',
							height : '20px'
						});
						
						$("#formMasterCode").jqxInput({
							placeHolder: "Enter Master Code",
							width : '200px',
							height : '20px'
						});
						
						$("#formMasterDescription").jqxInput({
						    placeHolder: "Enter a Description",
						    height: '20px',
						    width: '200px'
						});
						var masterSourceURL = "masterListJSON/All.html";
						var masterSource = {
							datatype : "json",
							datafields : [ {
								name : 'id',
								type : 'string'
							},{
								name : 'masterType',
								type : 'string'
							}, {
								name : 'code',
								type : 'string'
							}, {
								name : 'description',
								type : 'string'
							} ],
							id : 'code',
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
																if(confirm("Are you sure you want to delete the record : "+selectedRowData['code'])) {
																	$.ajax(
																			{
																				type : "POST",
																				url : "deleteMasterSetup.html",
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
															$("#formMasterType").val("");
										                    $("#formMasterCode").val("");
										                    $("#formMasterDescription").val("");
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
											                    $("#formMasterType").val((selectedRowData['masterType']));
											                    $("#formMasterCode").val((selectedRowData['code']));
											                    $("#formMasterDescription").val((selectedRowData['description']));
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
												text : 'Master Type',
												filtertype : 'textbox',
												filtercondition : 'CONTAINS',
												datafield : 'masterType',
												width : 250
											}, {
												text : 'Code',
												filtertype : 'textbox',
												filtercondition : 'CONTAINS',
												datafield : 'code',
												width : 200
											}, {
												text : 'Description',
												filtertype : 'textbox',
												filtercondition : 'CONTAINS',
												datafield : 'description',
												width : 350
											}]
										});

						// initialize the popup window and buttons.
						$("#popupWindow").jqxWindow({
							title : "Add/Edit Master Details",
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
														url : "saveMasterSetup.html",
														data : {
															id : $("#formId").val(),
															masterType : $("#formMasterType").val(),
															code : $("#formMasterCode").val(),
															description : $("#formMasterDescription").val()
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
									$("#formMasterType").val("");
									$("#formMasterCode").val("");
									$("#formMasterDescription").val("");
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
							<td align="right">Master Type:</td>
							
							<input type="hidden" id="formId" />
							<td align="left"><input id="formMasterType" /></td>
						</tr>
						<tr>
							<td align="right">Code:</td>
							<td align="left"><input id="formMasterCode" /></td>
						</tr>
						<tr>
							<td align="right">Description:</td>
							<td align="left"><input id="formMasterDescription" /></td>
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
