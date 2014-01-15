<jsp:include page="includes/header.jsp" />
<div>
	<span class="breadcrumbs">Home > User Account > User Management </span>
</div>

<script type="text/javascript">

	function setupUserForm() {
		// Create jqxInput.
		$("#formUsername").jqxInput({
			width : '230px',
			height : '20px'
		});
		
		$("#formPassword").jqxPasswordInput({
			width : '230px',
			height : '20px',
			showStrength : true,
			showStrengthPosition : "right"
		});
		$("#formEmail").jqxInput({
			width : '230px',
			height : '20px'
		});
		$("#formFirstName").jqxInput({
			width : '230px',
			height : '20px'
		});
		$("#formLastName").jqxInput({
			width : '230px',
			height : '20px'
		});

		$("#formUserRoleCheckBox").jqxCheckBox({
			width : 80,
			height : 25
		});
		$("#formAdminRoleCheckBox").jqxCheckBox({
			width : 80,
			height : 25
		});

		// initialize the popup window and buttons.
		$("#popupWindow").jqxWindow({
			title : "Add New User",
			width : 400,
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

		saveButton.click(function(event) {
			var useRole = 0;
			var adminRole = 0;

			var userRoleCheck = $('#formUserRoleCheckBox').jqxCheckBox(
					'checked');
			var adminRoleChecked = $('#formAdminRoleCheckBox').jqxCheckBox(
					'checked');

			if (userRoleCheck) {
				useRole = 1;
			}
			if (adminRoleChecked) {
				adminRole = 1;
			}

			var row = {
				username : $("#formUsername").val(),
				email : $("#formEmail").val(),
				firstName : $("#formFirstName").val(),
				lastName : $("#formLastName").val(),
				userRole : useRole,
				adminRole : adminRole
			};
			//password: $("#formPassword").val()
			//enabled : 'true'
			$.ajax({
				type : "POST",
				url : "saveUser.html",
				data : {
					username : $("#formUsername").val(),
					email : $("#formEmail").val(),
					firstName : $("#formFirstName").val(),
					lastName : $("#formLastName").val(),
					userRole : useRole,
					adminRole : adminRole,
					password : $("#formPassword").val(),
					enabled : 'true'
				}
			}).done(function(msg) {
				//alert( "Data Saved: " + msg );
			});
			$("#jqxgrid").jqxGrid('addrow', null, row);
			//Clear form values
			$("#formUsername").val("");
			$("#formEmail").val("");
			$("#formFirstName").val("");
			$("#formLastName").val("");
			$("#formPassword").val("");
			$("#formUserRoleCheckBox").jqxCheckBox({
				checked : false
			});
			$("#formAdminRoleCheckBox").jqxCheckBox({
				checked : false
			});
			$("#popupWindow").jqxWindow('hide');
		});
	}

	$(document)
			.ready(
					function() {
						document.title = 'User List';

						setupUserForm();
						var userListUrl = "userListJSON.html";
						var userListSource = {
							datatype : "json",
							datafields : [ {
								name : 'username',
								type : 'string'
							}, {
								name : 'email',
								type : 'string'
							}, {
								name : 'firstName',
								type : 'string'
							}, {
								name : 'lastName',
								type : 'string'
							}, {
								name : 'userRole',
								type : 'bool'
							}, {
								name : 'adminRole',
								type : 'bool'
							} ],
							id : 'id',
							url : userListUrl
						};
						var userListDataAdapter = new $.jqx.dataAdapter(
								userListSource);

						// initialize jqxGrid
						$("#jqxgrid")
								.jqxGrid(
										{
											width : 800,
											source : userListDataAdapter,
											showstatusbar : true,
											renderstatusbar : function(
													statusbar) {
												// appends buttons to the status bar.
												var container = $("<div style='overflow: hidden; position: relative; margin: 5px;'></div>");
												var addButton = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='resources/images/add.png'/><span style='margin-left: 4px; position: relative; top: -3px;'>Add</span></div>");
												var deleteButton = $("<div style='float: left; margin-left: 5px;'><img style='position: relative; margin-top: 2px;' src='resources/images/close.png'/><span style='margin-left: 4px; position: relative; top: -3px;'>Delete</span></div>");
												container.append(addButton);
												container.append(deleteButton);
												statusbar.append(container);
												addButton.jqxButton({
													width : 60,
													height : 20
												});
												deleteButton.jqxButton({
													width : 65,
													height : 20
												});
												// add new row.
												addButton
														.click(function(event) {
															var offset = $(
																	"#jqxgrid")
																	.offset();
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
											selectionmode : 'none',
											columns : [ {
												text : 'User Id',
												datafield : 'username',
												width : 100
											}, {
												text : 'Email',
												datafield : 'email',
												width : 200
											}, {
												text : 'First Name',
												datafield : 'firstName',
												width : 170
											}, {
												text : 'Last Name',
												datafield : 'lastName',
												width : 170
											}, {
												text : 'User Role',
												datafield : 'userRole',
												width : 80,
												columntype : 'checkbox',
											}, {
												text : 'Admin Role',
												datafield : 'adminRole',
												width : 80,
												columntype : 'checkbox',
											} ]
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
	                        <td align="right">User Id:</td>
	                        <td align="left"><input id="formUsername" /></td>
	                    </tr>
	                    <tr>
	                        <td align="right">Password:</td>
	                        <td align="left"><input id="formPassword" type="password" /></td>
	                    </tr>	
	                    
	                    <tr>
	                        <td align="right">Email:</td>
	                        <td align="left"><input id="formEmail" /></td>
	                    </tr>
	                    <tr>
	                        <td align="right">First Name:</td>
	                        <td align="left"><input id="formFirstName" /></td>
	                    </tr>
	                    <tr>
	                        <td align="right">Last Name:</td>
	                        <td align="left"><input id='formLastName'></td>
	                    </tr>
	                    <tr>
	                        <td align="right">User Role:</td>
	                        <td align="left"><div id="formUserRoleCheckBox"></div></td>
	                    </tr>
	                    <tr>
	                        <td align="right">Admin Role:</td>
	                        <td align="left"><div id="formAdminRoleCheckBox"></div></td>
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
