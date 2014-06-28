<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>MaxMaid</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/jquery-mobile/jquery.mobile-1.4.2.css" />">
<script type="text/javascript"
	src="<c:url value="/resources/scripts/jquery-1.10.2.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jquery-mobile/jquery.mobile-1.4.2.min.js" />"></script>

<script>
	
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


		<div role="main" class="ui-content jqm-content jqm-fullwidth">

			<div class="logo">
				<a href="index.html"><img
					src="<c:url value="/resources/images/maxmaid_logo_new.gif" />"
					alt="MaxMaid Logo" /></a>
			</div>
			<div class="clear"></div>
			<h2>Welcome</h2>
			<p>MaxMaid Cleaning services is one of the most widely respected
				house cleaning services in UAE. When you use our residential maid
				services, you are not just buying more time for yourself; you are
				buying quality and dependability.</p>
			<p>We understand that our customers value their time and expect
				great customer service at an affordable price. That is why we offer
				whole house cleaning services in the UAE area that are tailored to
				meet your needs. There is no job that is too big or too small for
				our staff to handle. We will provide you with prompt and courteous
				housekeeping services so you will never have to worry about spending
				valuable time looking for the perfect cleaning team again! Since
				each one of our staff have been expertly trained in home cleaning
				services, you will be sure to receive the same high level of service
				each time we visit.</p>
			<p>Our pleasant staff has been carefully chosen and has been
				proven to be honest and reliable; which is one of the reasons why we
				are one of the most trusted names in the residential cleaning
				business today. They truly care about each of their customers and
				are responsive to their needs.</p>
			<p>Our customers are what keep us going. That is why we will
				provide top quality cleaning services for you like our business
				depends on it, because we know it does.</p>
			<p>Green equipment and cleaning supplies are of the highest
				quality used; therefore we are able to clean your home in a safe and
				environmentally friendly manner.</p>
			<p>Safe staff has been individually screened and carefully
				selected and regularityhealthy checked.</p>
			<p>Cleaning is the main focus to satisfy your expectations.</p>
			<p></p>
			<p>&nbsp;</p>

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
					<li><a href="#Panelsoutsidepages" class="ui-btn">Panels
							outside pages</a></li>
					<li><a href="#Dynamiccontent" class="ui-btn">Dynamic
							content</a></li>
					<li><a href="#Openingapanel" class="ui-btn">Opening a
							panel</a></li>
					<li><a href="#Closingapanel" class="ui-btn">Closing a
							panel</a></li>
					<li><a href="#Panelanimations" class="ui-btn">Panel
							animations</a></li>
					<li><a href="#Panelpositioning" class="ui-btn">Panel
							positioning</a></li>
					<li><a href="#Stylingpanels" class="ui-btn">Styling panels</a></li>
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
					<li><a style="text-align: left" href='<c:url value="/logout.html"/>' data-icon="arrow-r">Logout</a></li>
				</ul>
			</div>
			<!-- /navbar -->
		</div>
		<!-- /footer -->

	</div>
	<!-- /page -->


</body>
</html>
