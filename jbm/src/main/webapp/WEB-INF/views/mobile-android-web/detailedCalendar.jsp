<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>MaxMaid</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/jquery-mobile/jquery.mobile-1.4.2.css" />">
<script type="text/javascript" src="<c:url value="/resources/scripts/jquery-1.10.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery-mobile/jquery.mobile-1.4.2.min.js" />"></script>
<link href='<c:url value="/resources/cal/css/fullcalendar.css" />' rel='stylesheet' />
<link href='<c:url value="/resources/cal/css/fullcalendar.print.css" />' rel='stylesheet' />
<script src='<c:url value="/resources/cal/js/fullcalendar.min.js" />'></script>
<script src='<c:url value="/resources/cal/js/gcal.js" />'></script>
</head>
<body>
	<div data-role="page" class="jqm-demos ui-responsive-panel"
		id="panel-responsive-page1" data-title="Appointments Calendar">

		<div data-role="header" data-position="fixed" data-tap-toggle="false"
			data-theme="b">
			<h1>MaxMaid Services</h1>
			<a href="#nav-panel" data-icon="bars" data-iconpos="notext">Menu</a>
			<a href="#user-panel" data-icon="gear" data-iconpos="notext">User</a>
		</div>
		<!-- /header -->


		<div role="content" class="ui-content jqm-content jqm-fullwidth">
			<div id='detailedCalendar' style="width:100%;"></div>
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
		
		<script>
		$(document).on('pageinit', function() {
			try{
			var url = 'getStaffAppointmentsTimeBreakupsJSON/11-06-2014'+'.html';
			//alert(url);
			$('#detailedCalendar').fullCalendar({
				header: {
					left: 'prev,next today',
					center: 'title',
					right: 'agendaWeek,agendaDay'
				},
				defaultView: 'agendaDay',
				allDaySlot: false,
				minTime: '0',
				maxTime:'23',
				editable: false,
				events: url,
				lazyFetching: true,
				height: 650,
				eventRender: function(event, element) {
			        element.attr('title', event.tooltip);
			        if(calendarCurrentView == "agendaDay") {
			        	element.find('.fc-event-title').append("<br/>" + event.eventDescription);
			        }
			    },
			    viewRender: function(view, element) {
			    	calendarCurrentView = view.name;
			    	//alert('new view: ' + view.name); 
			    },
		/* 		events: [
					{
						title: '10',
						start: new Date(y, m, 1)
					},
					{
						title: '2',
						start: new Date(y, m, 5)
					},
					{
						id: 999,
						title: '30',
						start: new Date(y, m, 16)
					},
					{
						title: '12',
						start: new Date(y, m, 28),
						//url: 'http://google.com/'
					}
				], */
				loading: function(bool) {
					if (bool) {
						$('#loading').show();
					}else{
						$('#loading').hide();
					}
				}
			});
			//var day = appointmentDate.substr(0,2);
			//var mon = parseInt(appointmentDate.substr(3,2) - 1);
			//var year = appointmentDate.substr(6,4);
			
			//alert(day+" : "+mon+" : "+year);
			
			var date = new Date();
			var day = date.getDate();
			var month = date.getMonth() + 1;
			var year = date.getFullYear();
			if (month < 10) month = "0" + month;
			if (day < 10) day = "0" + day;
			return (year + "-" + month + "-" + day); 
			
			$('#detailedCalendar').fullCalendar( 'gotoDate', year, month, date);
			}catch(e) {
				alert(e);
			}
			});
</script>
		
	</div>
	<!-- /page -->


</body>
</html>
