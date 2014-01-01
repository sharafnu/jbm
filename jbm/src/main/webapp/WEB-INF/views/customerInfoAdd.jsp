<jsp:include page="includes/header.jsp" />
<div>
	<span class="breadcrumbs">Home > Customer > Add Customer Info</span>
</div>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						// Create jqxExpander.

						// Create jqxInput.
						$("#firstName").jqxInput({
							width : '230px',
							height : '20px'
						});
						$("#lastName").jqxInput({
							width : '230px',
							height : '20px'
						});
						$("#mobile1").jqxInput({
							width : '230px',
							height : '20px'
						});
						$("#mobile2").jqxInput({
							width : '230px',
							height : '20px'
						});
						$("#mobile3").jqxInput({
							width : '230px',
							height : '20px'
						});
						$("#email").jqxInput({
							width : '230px',
							height : '20px'
						});
						$("#preferenceCall").jqxCheckBox({ width: 10, height: 25});
						$("#preferenceEmail").jqxCheckBox({ width: 10, height: 25});
						$("#preferenceSMS").jqxCheckBox({ width: 10, height: 25});
						
						// Create jqxButton.
						$("#submit").jqxButton({
							theme : theme
						});
						// Create jqxValidator.
						$("#form")
								.jqxValidator(
										{
											rules : [
													{
														input : "#firstName",
														message : "First name is required!",
														action : 'keyup, blur',
														rule : function(input,
																commit) {
															return input.val() != ""
																	&& input
																			.val() != "First";
														}
													},
													{
														input : "#lastName",
														message : "Last name is required!",
														action : 'keyup, blur',
														rule : function(input,
																commit) {
															return input.val() != ""
																	&& input
																			.val() != "Last";
														}
													},
													{
														input : "#mobile1",
														message : 'Invalid mobile 1!',
														action : 'valuechanged, blur',
														rule : 'phone'
													},
													{
														input : "#mobile2",
														message : 'Invalid mobile 2!',
														action : 'valuechanged, blur',
														rule : 'phone'
													},
													{
														input : "#mobile3",
														message : 'Invalid mobile 3!',
														action : 'valuechanged, blur',
														rule : 'phone'
													},
													{
														input : '#email',
														message : 'Invalid e-mail!',
														action : 'keyup',
														rule : 'email'
													} ],
											hintType : "label"
										});
						// Validate the Form.
						$("#submit").click(function() {
							$('#form').jqxValidator('validate');
						});
						// Update the jqxExpander's content if the validation is successful.
						$('#form')
								.on(
										'validationSuccess',
										function(event) {
											$("#createAccount")
													.jqxExpander('setContent',
															'<span style="margin: 10px;">Account created.</span>');
										});
						

	});
</script>


<form id="form" action="./">

<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">
	<div id="createAccount" class="cornerDiv">
			<div style="background-color: #F4F0F5; color: #000; min-height: 1.5em; vertical-align: middle; padding: 5px; width: 800px;">
				Customer Info</div>
			<div style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 10px;">
				<table border="0" width="35%">
					<tr>
						<td colspan="3">First Name</td>
					</tr>
					<tr>
						<td colspan="3"><input id="firstName" /></td>
					</tr>
					<tr>
						<td colspan="3">Last Name</td>
					</tr>
					<tr>
						<td colspan="3"><input id="lastName" /></td>
					</tr>
					<tr>
						<td colspan="3">Mobile No 1</td>
					</tr>
					<tr>
						<td colspan="3"><input id="mobile1" /></td>
					</tr>
					<tr>
						<td colspan="3">Mobile No 2</td>
					</tr>
					<tr>
						<td colspan="3"><input id="mobile2" /></td>
					</tr>
					<tr>
						<td colspan="3">Mobile No 3</td>
					</tr>
					<tr>
						<td colspan="3"><input id="mobile3" /></td>
					</tr>
					<tr>
						<td colspan="3">Email</td>
					</tr>
					<tr>
						<td colspan="3"><input id="email" /></td>
					</tr>
					<tr>
						<td colspan="3">Preferred Contact Method</td>
					</tr>
					<tr>
						<td colspan="1">
							<div id='preferenceCall' style='margin-left: 10px; float: left;'>Call</div>
						</td>
						<td colspan="1">
							<div id='preferenceSMS' style='margin-left: 10px; float: left;'>SMS</div>
						</td>
						<td colspan="1">
							<div id='preferenceEmail' style='margin-left: 10px; float: left;'>Email</div>
						</td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="3"><input id="submit" type="button"
							value="Add Customer" /></td>
					</tr>
					
				</table>
				
	</div>
</div>
</form>

<jsp:include page="includes/footer.jsp" />
