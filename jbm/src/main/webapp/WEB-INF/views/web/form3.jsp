<jsp:include page="includes/header.jsp" />
<div>
	<span class="breadcrumbs">Home > Customer Info</span>
</div>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						// Create jqxExpander.

						// Create jqxInput.
						$("#firstName").jqxInput({
							width : '300px',
							height : '20px'
						});
						$("#lastName").jqxInput({
							width : '300px',
							height : '20px'
						});
						$("#mobile1").jqxInput({
							width : '130px',
							height : '20px'
						});
						$("#mobile2").jqxInput({
							width : '130px',
							height : '20px'
						});
						$("#landline").jqxInput({
							width : '130px',
							height : '20px'
						});
						$("#building").jqxInput({
							width : '300px',
							height : '20px'
						});
						$("#flat").jqxInput({
							width : '300px',
							height : '20px'
						});
						$("#email").jqxInput({
							width : '300px',
							height : '20px'
						});

						// Create jqxDateTimeInpput.
						//$("#birthday").jqxDateTimeInput({  width: '300px', height: '20px' });
						// Create jqxDropDownList.
						var cityList = [ "Dubai", "Sharjah", "Abu Dhabi",
								"Al Ain", "Fujairah", "Ras Al Khaima", "Ajman" ];
						$("#city").jqxDropDownList({
							source : cityList,
							selectedIndex : -1,
							width : '300px',
							height : '20px',
							promptText : "Select City..",
							dropDownHeight : "160px"
						});
						var areaList = [ "Dubai Silicon Oasis", "Deira",
								"Jumeira", "Al Karama", "Dubai Academic City",
								"Al Barsha", "Al Warqa" ];
						$("#area").jqxDropDownList({
							source : areaList,
							selectedIndex : -1,
							width : '300px',
							height : '20px',
							promptText : "Select Area..",
							dropDownHeight : "120px"
						});

						$("#addressTypeResidence").jqxRadioButton({
							width : 50,
							height : 25
						});
						$("#addressTypeOffice").jqxRadioButton({
							width : 50,
							height : 25
						});

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
														input : "#landline",
														message : 'Invalid mobile 3!',
														action : 'valuechanged, blur',
														rule : 'phone'
													},
													{
														input : '#email',
														message : 'Invalid e-mail!',
														action : 'keyup',
														rule : 'email'
													},
													{
														input : "#passwordConfirm",
														message : "Passwords should match!",
														action : 'keyup, blur',
														rule : function(input,
																commit) {
															var firstPassword = $(
																	"#password")
																	.jqxPasswordInput(
																			'val');
															var secondPassword = $(
																	"#passwordConfirm")
																	.jqxPasswordInput(
																			'val');
															return firstPassword == secondPassword;
														}
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
	<div id="createAccount">
			<div
				style="background-color: #555; color: #fff; min-height: 1.5em; vertical-align: middle; padding-top: 5px; width: 500px;">
				Customer Info</div>
			<div
				style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 10px;">
				<table border="0">
					<tr>
						<td colspan="3">First Name</td>
					</tr>
					<tr>
						<td><input id="firstName" /></td>
					</tr>
					<tr>
						<td colspan="3">Last Name</td>
					</tr>
					<tr>
						<td><input id="lastName" /></td>

					</tr>

					<tr>
						<td colspan="2">Email</td>
					</tr>
					<tr>
						<td colspan="2"><input id="email" /></td>
					</tr>


				</table>
			</div>

			<div
				style="background-color: #555; color: #fff; min-height: 1.5em; vertical-align: middle; padding-top: 5px; width: 500px;">
				Contact Info</div>
			<div
				style="font-family: Verdana; font-size: 13px; overflow: hidden; margin: 10px;">
				<table border="0">

					<tr>
						<td colspan="3">City</td>
					</tr>
					<tr>
						<td colspan="3">
							<div id="city"></div>
						</td>
					</tr>

					<tr>
						<td colspan="3">Area</td>
					</tr>
					<tr>
						<td colspan="3">
							<div id="area"></div>
						</td>
					</tr>

					<tr>
						<td colspan="3">Bldg. Name</td>
					</tr>
					<tr>
						<td colspan="3"><input id="building" /></td>
					</tr>

					<tr>
						<td colspan="3">Flat No.</td>
					</tr>
					<tr>
						<td colspan="3"><input id="flat" /></td>
					</tr>

					<tr>
						<td colspan="3">Address Type</td>
					</tr>
					<tr>
						<td colspan="3" nowrap>
							<div id='addressTypeResidence'>Residence</div>
							<div id='addressTypeOffice'>Office</div>
						</td>
					</tr>
					<tr>
						<td colspan="1">Mobile No 1</td>
						<td colspan="1" style="padding-left: 15px">Mobile No 2</td>
						<td colspan="1" style="padding-left: 15px">Mobile No 3</td>
					</tr>
					<tr>
						<td colspan="1"><input id="mobile1" /></td>
						<td colspan="1" style="padding-left: 15px"><input
							id="mobile2" /></td>
						<td colspan="1" style="padding-left: 15px"><input
							id="landline" /></td>
					</tr>

					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2"><input id="submit" type="button"
							value="Create account" /></td>
					</tr>
				</table>
			</div>
	</div>
</div>
</form>

<jsp:include page="includes/footer.jsp" />
