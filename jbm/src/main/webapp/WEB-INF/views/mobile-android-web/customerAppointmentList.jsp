<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>MaxMaid</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/jquery-mobile/jquery.mobile-1.4.2.css" />">
<script type="text/javascript" src="<c:url value="/resources/scripts/jquery-1.10.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery-mobile/jquery.mobile-1.4.2.min.js" />"></script>
	
<link type="text/css" rel="Stylesheet" href="http://www.jqwidgets.com/jquery-widgets-documentation/jqwidgets/styles/jqx.base.css" />
    
    
</head>
<body>
    <div data-role="page" class="jqm-demos ui-responsive-panel"
		id="appointmentList" data-title="Panel responsive page">

		<div data-role="header" data-position="fixed" data-tap-toggle="false"
			data-theme="b">
			<h1>MaxMaid Services</h1>
			<a href="#nav-panel" data-icon="bars" data-iconpos="notext">Menu</a>
			<a href="#user-panel" data-icon="gear" data-iconpos="notext">User</a>
		</div>
		<!-- /header -->


        <div role="main" class="ui-content jqm-content jqm-fullwidth">
            	
            	<input type="hidden" id="appointmentViewListJSON" value='<c:out value="${appointmentListJSON}"/>'/>
                <form id="appoinmentSearchForm" action="customerAppointmentList.html" method="post" data-ajax="false">
					<!-- All hidden fields goes here -->
					<input type="hidden" id="id" 	name="id" value="${appointmentView.id}"/>
					
					<div class="ui-corner-all custom-corners" style="width:95%">
					  <div class="ui-bar ui-bar-a" style="width:95%">
					    <h3>Search Filters</h3>
					  </div>
					  <div class="ui-body ui-body-a" style="width:95%">
					    
							<div class="ui-grid-b" style="width:100%">
							    <div class="ui-block-a" style="width:42%">
									<label for="startDateFilter">From Date:</label>
									<input type="date" data-clear-btn="true" name="startDateFilter" id="startDateFilter" value="" data-mini="true">
								</div>
							    <div class="ui-block-b" style="width:42%">
									<label for="endDateFilter">End Date:</label>
									<input type="date" data-clear-btn="true" name="endDateFilter" id="endDateFilter" value="" data-mini="true">
								</div>
							</div>
							<div class="ui-grid-b" style="width:100%">	
								<div class="ui-block-a" style="width:44%">
									<label for="appointmentNo">Appointment No:</label>
									<input type="text" data-clear-btn="true" name="appointmentNo" id="appointmentNo" value="" data-mini="true">
								</div>
								<div class="ui-block-b" style="width:42%">
									<label for="appointmentStatus" class="select">Status</label>
									<select id="appointmentStatus" name="appointmentStatus" data-mini="true">
								        <option value="All">All</option>
								        <option value="Created">Created</option>
								        <option value="Completed">Completed</option>
								        <option value="Cancelled">Cancelled</option>
								    </select>
								    
								</div>
							    
							</div>	
							<div class="ui-grid-b">		
								<div class="ui-block-b">
									<input type="submit" value="Search" data-clear-btn="true" data-mini="true" class="ui-btn ui-shadow ui-corner-all ui-btn-b ui-mini">
						    	</div>
							</div>						       
				
					  </div>
					</div>
				</form>
							
                <div id="jqxGrid" style="width:100%">
                </div>            
        </div>
<div data-role="panel" data-display="push" data-theme="b"
			id="nav-panel">

			<ul data-role="listview">
				<li data-icon="delete">
						<a href="#" data-rel="close" style="background-color: #777">Close
						menu</a></li>
				<li><a href="home.html">Home</a></li>		
				<li><a href="customerAppointmentList.html">Appointment List</a></li>
				<li><a href="detailedAppointmentCalendar.html">Appointment Calendar</a></li>
				<li><a href="appointmentReportList.html">Reports</a></li>
			</ul>

		</div>
		<!-- /panel -->
		<div data-role="panel" id="user-panel"
			class="jqm-nav-panel jqm-quicklink-panel ui-panel ui-panel-position-right ui-panel-display-overlay ui-body-a ui-panel-animate ui-panel-open"
			data-position="right" data-display="overlay" data-theme="a">
			<div class="ui-panel-inner">
				<ul data-role="listview" data-inset="false" data-theme="a"
					data-divider-theme="a" data-icon="false"
					class="jqm-list ui-listview ui-group-theme-a">
					<li data-role="list-divider" role="heading"
						class="ui-li-divider ui-bar-a ui-first-child">Quick Links</li>
					<li><a href="#Panelexamples" class="ui-btn">Panel examples</a></li>
					<li><a href="#Wherepanelmarkupgoesinthemarkup" class="ui-btn">Where
							panel markup goes in the markup</a></li>
					<li><a href="#Stylingpanels" class="ui-btn">Styling panels</a></li>
					<li class="ui-last-child"><a href="#Makingthepanelresponsive"
						class="ui-btn">Making the panel responsive</a></li>
				</ul>
			</div>
		</div>

		<div data-role="footer" data-position="fixed" data-tap-toggle="true"
			data-theme="b" data-theme="b">
			<div data-role="navbar" data-iconpos="left">
				<ul>
					<li><a style="text-align: left" href="../" data-rel="back" data-ajax="false" data-icon="arrow-l">Back</a></li>
					<li><a style="text-align: left" href="home.html" data-icon="home">Home</a></li>
					<li><a style="text-align: left" href='<c:url value="/logout.html"/>' data-icon="arrow-r">Logout</a></li>
				</ul>
			</div>
			<!-- /navbar -->
		</div>
		<!-- /footer -->
	<script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxcore.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxdata.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxbuttons.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxscrollbar.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxmenu.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxlistbox.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxdropdownlist.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxgrid.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxgrid.selection.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxgrid.columnsresize.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxgrid.filter.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxgrid.sort.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxgrid.pager.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jqwidgets/jqxgrid.grouping.js" />"></script>
		
	<script type="text/javascript">
	//$("#appointmentList").on( "pageload", function( event ) {
	//$(document).on('pagecreate', function() {
    	
		function getCurrentDate() {
			var date = new Date();
			var day = date.getDate();
			var month = date.getMonth() + 1;
			var year = date.getFullYear();
			if (month < 10) month = "0" + month;
			if (day < 10) day = "0" + day;
			return (year + "-" + month + "-" + day);  
		}
		
		
		//$(document).ready(function () {
		$(document).on('pageinit', function() {
			
			
		try {			     
			$('#startDateFilter').val(getCurrentDate());
			$('#endDateFilter').val(getCurrentDate());
			
		//$('#startDateFilter').val(new Date().toDateInputValue());
    	var appointmentViewListJSONStr = $("#appointmentViewListJSON").val();

    	var appoinmentListSource = {
    		datatype : "json",
    		datafields : [ {
    			name : 'appointmentDate',
    			type : 'string'
    		}, {
    			name : 'appointmentNo',
    			type : 'string'
    		}, {
    			name : 'appointmentStatus',
    			type : 'string'
    		}, {
    			name : 'remarks',
    			type : 'string'
    		}, {
    			name : 'customerName',
    			type : 'string'
    		}, {
    			name : 'employeeName',
    			type : 'string'
    		}, {
    			name : 'cancellationReason',
    			type : 'string'
    		}, {
    			name : 'hoursSpent',
    			type : 'int'
    		}, {
    			name : 'payableAmount',
    			type : 'int'
    		}, {
    			name : 'paymentStatus',
    			type : 'string'
    		}, {
    			name : 'paymentType',
    			type : 'string'
    		}, {
    			name : 'invoiceNo',
    			type : 'string'
    		}, {
    			name : 'startTime',
    			type : 'string'
    		}, {
    			name : 'endTime',
    			type : 'string'
    		}, {
    			name : 'areaName',
    			type : 'string'
    		}, {
    			name : 'buildingName',
    			type : 'string'
    		}, {
    			name : 'flatNo',
    			type : 'string'
    		}, {
    			name : 'cityName',
    			type : 'string'
    		}, {
    			name : 'id',
    			type : 'string'
    		}, {
    			name : 'invoiceDate',
    			type : 'string'
    		} ],
    		id : 'id',
    		localdata : appointmentViewListJSONStr
    	};
    	var appoinmentDataAdapter = new $.jqx.dataAdapter(appoinmentListSource);

/*     	var imagerenderer = function(row, datafield, value) {
    		if(appoinmentDataAdapter.records[row].appointmentStatus == "Created") {
    			var updateURL = "customerAppointmentDetailsForSelectedId.html?appointmentId="+value;
    			var editURL = "customerApointmentEdit.html?appointmentId="+value;
    			
    			return "<span><a href='"+updateURL+"' ><img title='Update Appointment Details' width='16px' height='16px' style='margin-left: 5px;margin-top: 5px;' src='resources/images/card.png'/></a><a title='Edit Appointment' href='"+editURL+"' ><img style='margin-left: 5px;margin-top: 5px;' width='16px' height='16px' src='resources/images/edit-icon.png'/></a></span>";			
    		} else {
    			return "";
    		}
    	} */
    	// initialize jqxGrid
    	$("#jqxGrid")
    			.jqxGrid(
    					{
    						width : "100%",
    						source : appoinmentDataAdapter,
    						showstatusbar : true,
    						//rowdetails: true,
    						//rowdetailstemplate: { rowdetails: "<div style='margin: 10px;'><ul style='margin-left: 30px;'><li class='title'></li><li>Notes</li><li>Job Completion</li></ul><div class='information'></div><div class='notes'></div><div class='jobcompletion'></div></div>", rowdetailsheight: 100 },
    		                ready: function () {
    		                    //$("#jqxgrid").jqxGrid('showrowdetails', 0);		               
    		                },
    		                //initrowdetails: initrowdetails,
    						renderstatusbar : function(statusbar) {
    							// appends buttons to the status bar.							
    						},
    						pageable : true,
    						autoheight : true,
    						sortable : true,
    						altrows : true,
    						enabletooltips : true,
    						editable : false,
    						selectionmode : 'none',
    						columns : [ {
    							text : 'App. No.',
    							datafield : 'appointmentNo',
    							width : "10%"
    						}, {
    							text : 'App. Date',
    							datafield : 'appointmentDate',
    							width : "15%"
    						}, {
    							text : 'Customer Name',
    							datafield : 'customerName',
    							width : "32%"
    						}, {
    							text : 'Staff Name',
    							datafield : 'employeeName',
    							width : "28%"
    						}, {
    							text : 'Status',
    							datafield : 'appointmentStatus',
    							width : "15%"
    						}/* , {
    							text : '',
    							datafield : 'id',
    							width : 45,
    							cellsrenderer : imagerenderer
    						} */
    						]
    					});
		}catch(e) {
			alert(e);
		}
    	
	//	setupDateFilters();
    
    	
    	 });
    
	
	function loadAppoinmentCombo() {
		var appointmentListUrl = "customerAppoinmentComboListJSON.html";
		var appointmentListSource = {
			datatype : "json",
			datafields : [ {
				name : 'appointmentNo',
				type : 'string'
			}, {
				name : 'id',
				type : 'int'
			} ],
			id : 'id',
			url : appointmentListUrl
		};
		var appointmentListDataAdapter = new $.jqx.dataAdapter(
				appointmentListSource, {
					loadComplete : function() {
						var indexToSelect = -1;
						var valueToFind = $("#id").val();
						if(valueToFind != "") {
							var items = $("#formAppointmentId").jqxComboBox("getItems");
							$.each(items, function(index) {
								if (this.value == valueToFind) {
								    indexToSelect = index;
								    return false;
								}
							});
							$("#formAppointmentId").jqxComboBox({ selectedIndex: indexToSelect });
						}
					}
				});

		$("#formAppointmentId").jqxComboBox({
			selectedIndex : -1,
			searchMode: "containsignorecase",
			source : appointmentListDataAdapter,
			displayMember : "appointmentNo",
			valueMember : "id",
			width : 130,
			height : 20
		});
		
		$('#formAppointmentId').on('change', function (event) {
			var args = event.args;
			if(args == undefined) {
				$('#formAppointmentId').val("");
				$("#formAppointmentId").jqxComboBox({ selectedIndex: -1 });
			} 
		});
	}
	
	function loadAppoinmentStatusComboAll() {
		var appoinmentStatusSource = [  "All", "Created", "Completed", "Cancelled" ];
		$("#formAppoinmentStatus").jqxComboBox({
			selectedIndex : 1,
			source : appoinmentStatusSource,
			width : 210,
			height : 20
		});
	}
    </script>
	</div>
	<!-- /page -->


</body>
</html>