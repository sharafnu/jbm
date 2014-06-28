<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>MaxMaid</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/jquery-mobile/jquery.mobile-1.4.2.css" />">
	<style type="text/css">
        @media all and (max-width: 62em)
        {
            .my-breakpoint .ui-block-a, .my-breakpoint .ui-block-b, .my-breakpoint .ui-block-c, .my-breakpoint .ui-block-d, .my-breakpoint .ui-block-e
            {
                width: 100%;
                float: none;
            }
        }
        
        @media all and (min-width: 72em)
        {
            .my-breakpoint .ui-block-a
            {
                width: 64.95%;
            }
        
            .my-breakpoint .ui-block-b, .my-breakpoint .ui-block-c, .my-breakpoint .ui-block-d, .my-breakpoint .ui-block-e
            {
                width: 34.95%;
            }
        }
    </style>
	
<script type="text/javascript"
	src="<c:url value="/resources/scripts/jquery-1.10.2.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jquery-mobile/jquery.mobile-1.4.2.min.js" />"></script>

<link type="text/css" rel="Stylesheet" href="<c:url value="/resources/jqwidgets/styles/jqx.base.css" />" />
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
	$(document).on('pagecreate', function() {

    	alert("hii");
        // jqxGrid data source
        var data = new Array();
        var firstNames =
        [
            "Andrew", "Nancy", "Shelley", "Regina", "Yoshi", "Antoni", "Mayumi", "Ian", "Peter", "Lars", "Petra", "Martin", "Sven", "Elio", "Beate", "Cheryl", "Michael", "Guylene"
        ];
        var lastNames =
        [
            "Fuller", "Davolio", "Burke", "Murphy", "Nagase", "Saavedra", "Ohno", "Devling", "Wilson", "Peterson", "Winkler", "Bein", "Petersen", "Rossi", "Vileid", "Saylor", "Bjorn", "Nodier"
        ];
        var productNames =
        [
            "Black Tea", "Green Tea", "Caffe Espresso", "Doubleshot Espresso", "Caffe Latte", "White Chocolate Mocha", "Cramel Latte", "Caffe Americano", "Cappuccino", "Espresso Truffle", "Espresso con Panna", "Peppermint Mocha Twist"
        ];
        var priceValues =
        [
            "2.25", "1.5", "3.0", "3.3", "4.5", "3.6", "3.8", "2.5", "5.0", "1.75", "3.25", "4.0"
        ];
        for (var i = 0; i < 100; i++) {
            var row = {};
            var productindex = Math.floor(Math.random() * productNames.length);
            var price = parseFloat(priceValues[productindex]);
            var quantity = 1 + Math.round(Math.random() * 10);
            row["firstname"] = firstNames[Math.floor(Math.random() * firstNames.length)];
            row["lastname"] = lastNames[Math.floor(Math.random() * lastNames.length)];
            row["productname"] = productNames[productindex];
            row["price"] = price;
            row["quantity"] = quantity;
            row["total"] = price * quantity;
            data[i] = row;
        }
        var source =
        {
            localdata: data,
            datatype: "array"
        };
        var dataAdapter = new $.jqx.dataAdapter(source, {
            loadComplete: function (data) { },
            loadError: function (xhr, status, error) { }
        });
        // jqxGrid initialization
        $("#jqxGrid").jqxGrid(
        {
            width: "100%",
            height: 400,
            source: dataAdapter,
            columns: [
                { text: 'First Name', datafield: 'firstname', width: "20%" },
                { text: 'Last Name', datafield: 'lastname', width: "20%" },
                { text: 'Product', datafield: 'productname', width: "20%" },
                { text: 'Quantity', datafield: 'quantity', width: "10%", cellsalign: 'right' },
                { text: 'Unit Price', datafield: 'price', width: "10%", cellsalign: 'right', cellsformat: 'c2' },
                { text: 'Total', datafield: 'total', width: "20%", cellsalign: 'right', cellsformat: 'c2' }
            ]
        });
       
        
    });
</script>

</head>
<body>
	<div data-role="page" class="jqm-demos ui-responsive-panel"
		id="panel-responsive-page1" data-title="Panel responsive page">

		<div data-role="header" data-position="fixed" data-tap-toggle="false"
			data-theme="b">
			<h1>MaxMaid Services</h1>
			<a href="#nav-panel" data-icon="bars" data-iconpos="notext">Menu</a>
			<a href="#user-panel" data-icon="gear" data-iconpos="notext">User</a>
		</div>
		<!-- /header -->


		<div class="ui-grid-a my-breakpoint">
            <div class="ui-block-a" style="padding: 10px;">
                <div id="jqxGrid">
                </div>
            </div>
        </div>
		<!-- /content -->

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
					<li class="ui-last-child"><a href="#Makingthepanelresponsive"
						class="ui-btn">Making the panel responsive</a></li>
				</ul>
			</div>
		</div>

		<div data-role="footer" data-position="fixed" data-tap-toggle="true"
			data-theme="b">
			<div data-role="navbar" data-iconpos="left">
				<ul>
					<li><a style="text-align: left" href="../" data-rel="back" data-ajax="false" data-icon="arrow-l">Back</a></li>
					<li><a style="text-align: left" href="home.html" data-icon="home">Home</a></li>
					<li><a style="text-align: left" href="#" data-icon="arrow-r">Logout</a></li>
				</ul>
			</div>
			<!-- /navbar -->
		</div>
		<!-- /footer -->

	</div>
	<!-- /page -->


</body>
</html>
