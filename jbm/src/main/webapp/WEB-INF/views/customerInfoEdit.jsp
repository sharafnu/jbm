<jsp:include page="includes/header.jsp" />
<div>
	<span class="breadcrumbs">Home > Customer > Update Customer Info</span>
</div>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						// Create jqxExpander.

						// Create jqxInput.
						$("#firstName").jqxInput({
							width : '200px',
							height : '20px'
						});
						$("#lastName").jqxInput({
							width : '200px',
							height : '20px'
						});
						$("#mobile1").jqxInput({
							width : '200px',
							height : '20px'
						});
						$("#mobile2").jqxInput({
							width : '200px',
							height : '20px'
						});
						$("#mobile3").jqxInput({
							width : '200px',
							height : '20px'
						});
						$("#email").jqxInput({
							width : '200px',
							height : '20px'
						});
						$("#preferenceCall").jqxCheckBox({ width: 30, height: 25});
						$("#preferenceEmail").jqxCheckBox({ width: 30, height: 25});
						$("#preferenceSMS").jqxCheckBox({ width: 30, height: 25});
						
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
						var data = [
					                {
					                    laptops:
					                    [
					                        {ram: '8GB DD3', cpu: 'Intel Core i7-3720QM', price: 2999, display: 15.4, hdd: '512GB SSD', model: 'Apple MacBook Pro' },
					                        {ram: '8GB DD3', cpu: 'Intel Core i7-3667U', price: 1299, display: 13.3, hdd: '256GB SSD', model: 'Apple MacBook Air' }
					                    ]
					                },
					                {
					                    laptops:
					                    [
					                        {ram: '4GB DD3', cpu: 'Intel Core i5-3317U', price: 1899, display: 13.3, hdd: '128GB SSD', model: 'ASUS TAICHI31-CX009H' },
					                        {ram: '4GB DD3', cpu: 'Intel Core i7-3517U', price: 1799, display: 13.3, hdd: '628GB', model: 'Asus TX300CA-C4024H' }
					                    ]
					                }
					            ];

						 var source =
				            {
				                localData: data,
				                dataType: "array"
				            };

				            var dataAdapter = new $.jqx.dataAdapter(source);
				            $("#dataTable").jqxDataTable(
				                    {
				                        width: 550,
				                        source: dataAdapter,
				                        sortable: false,
				                        pageable: false,
				                        pageSize: 1,
				                        pagerButtonsCount: 5,
				                        enableHover: false,
				                        selectionMode: 'none',
				                        rendered: function () {
				                            $(".buy").jqxButton();
				                            $(".buy").click(function () {
				                                itemsInCart++;
				                                $(".cart-top p").html(itemsInCart + " products");
				                            });
				                        },
				                        columns: [
				                              {
				                                  text: 'Address', align: 'left', dataField: 'model',
				                                  // row - row's index.
				                                  // column - column's data field.
				                                  // value - cell's value.
				                                  // rowData - rendered row's object.
				                                  cellsRenderer: function (row, column, value, rowData) {
				                                      var laptops = rowData.laptops;
				                                      var container = "<div>";
				                                      for (var i = 0; i < laptops.length; i++) {
				                                          var laptop = laptops[i];
				                                          var item = "<div style='float: left; width: 270px; overflow: hidden; white-space: nowrap; height: 200px;'>";
				                                          
				                                          var info = "<div style='margin: 5px; margin-left: 10px; margin-bottom: 3px;'>";
				                                          info += "<div><i>" + laptop.model + "</i></div>";
				                                          info += "<div>Price: $" + laptop.price + "</div>";
				                                          info += "<div>RAM: " + laptop.ram + "</div>";
				                                          info += "<div>HDD: " + laptop.hdd + "</div>";
				                                          info += "<div>CPU: " + laptop.cpu + "</div>";
				                                          info += "<div>Display: " + laptop.display + "</div>";
				                                          info += "</div>";

				                                          var buy = "<button class='buy' style='margin: 5px; width: 80px; left: -40px; position: relative; margin-left: 50%; margin-bottom: 3px;'>Buy</button>";

				                                          item += info;
				                                          item += buy;
				                                          item += "</div>";
				                                          container += item;
				                                      }
				                                      container += "</div>";
				                                      return container;
				                                  }
				                              }
				                        ]
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
				<table border="0" width="100%">
					<tr>
						<td colspan="3">First Name</td>
						<td rowspan="18" width="70%">
							    <div>
							    </div>
							    <br />
							    <div style="margin-left: 10px" id="dataTable"></div>

						</td>
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
							value="Update customer" /></td>
					</tr>
					
				</table>
				
	</div>
</div>
</form>

<jsp:include page="includes/footer.jsp" />
