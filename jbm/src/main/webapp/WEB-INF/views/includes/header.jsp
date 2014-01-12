<%@page import="com.innovazions.jbm.common.JBMUIHelper"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<c:url value="/resources/images/favicon.ico" />" rel="icon" type="image/x-icon" />

<link href="<c:url value="/resources/styles/desktop.css" />"
	rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/styles/SIU2.css" />"
	rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/styles/rounded-box.css" />"
	rel="stylesheet" type="text/css">	
<title>:: Welcome to Appointments Manager ::</title>
<link type="text/css" rel="Stylesheet"
	href="<c:url value="/resources/jqwidgets/styles/jqx.base.css" />" />

<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/login/css/font-awesome.min.css" />">
	
<script type="text/javascript"
	src="<c:url value="/resources/scripts/jquery-1.10.2.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxcore.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxpasswordinput.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxinput.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxdatetimeinput.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxcalendar.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxtooltip.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/globalization/globalize.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxbuttons.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxscrollbar.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxlistbox.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxdropdownlist.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxexpander.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxvalidator.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxmaskedinput.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxradiobutton.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxmenu.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxcheckbox.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxgrid.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxdatatable.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxdata.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxgrid.filter.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxgrid.sort.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxgrid.pager.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxgrid.selection.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxgrid.edit.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxwindow.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxcombobox.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jqwidgets/jqxtabs.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/scripts/demos.js" />"></script>
	
	<style>
h4 {
	font-size: 14px;
	margin: 18px 0 15px 15px;
}

.tag-title-info {
	background: transparent;
	width: 1px;
	height: 0px;
	border-color: #4272b8 transparent #4272b8 #4272b8 !important;
	border-width: 26px 26px;
	border-style: solid;
	z-index: 1000;
	top: 0px;
	position: absolute;
	left: 598px;
}

.tag-title {
	font-size: 16px;
	color: #fff;
	position: absolute;
	z-index: 100;
	left: 0;
	line-height: 100%;
	background: #4272b8 !important;
	height: 52px;
	padding: 0px;
	margin: 0px;
	top: 0px;
	width: 500px;
	vertical-align: middle;
}

.cart-top {
	font-family: Arial, Helvetica, sans-serif;
	height: 35px;
	position: absolute;
	left: 500px;
	top: 0;
	color: #fff;
	padding: 16px 14px 1px 14px;
	font-size: 11px;
	font-weight: 400;
	background: #4272b8 !important;
	z-index: 199;
}

.cart-top p {
	font-weight: bold;
	font-size: 11px;
	background: url(../../images/cart-icon.png) no-repeat right center;
	min-height: 16px;
	text-align: left;
	padding: 0 24px 0 0;
	margin-top: 2px;
	float: left;
	font-size: 11px;
	color: #fff;
	text-decoration: none;
}

.jqx-alert {
	position: absolute;
	overflow: hidden;
	z-index: 99999;
	margin: 0;
	padding: 0;
}

.jqx-alert-header {
	outline: none;
	border: 1px solid #999;
	overflow: hidden;
	padding: 5px;
	height: auto;
	white-space: nowrap;
	overflow: hidden;
	background-color: #E8E8E8;
	background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#fafafa),
		to(#dadada));
	background-image: -moz-linear-gradient(top, #fafafa, #dadada);
	background-image: -o-linear-gradient(top, #fafafa, #dadada);
}

.jqx-alert-content {
	outline: none;
	overflow: auto;
	text-align: left;
	background-color: #fff;
	padding: 5px;
	border: 1px solid #999;
	border-top: none;
}
</style>
	
<script type="text/javascript">  
      jqxAlert = {  
           // top offset.  
           top: 0,  
           // left offset.  
           left: 0,  
           // opacity of the overlay element.  
           overlayOpacity: 0.2,  
           // background of the overlay element.  
           overlayColor: '#ddd',  
           // display alert.  
           alert: function (message, title) {  
                if (title == null) title = 'Alert';  
                jqxAlert._show(title, message);  
           },  
           // initializes a new alert and displays it.  
           _show: function (title, msg) {  
                jqxAlert._hide();  
                jqxAlert._handleOverlay('show');  
                $("BODY").append(  
                          '<div class="jqx-alert" style="width: auto; height: auto; overflow: hidden; white-space: nowrap;" id="alert_container">' +  
                          '<div id="alert_title"></div>' +  
                          '<div id="alert_content">' +  
                               '<div id="message"></div>' +  
                               '<input style="margin-top: 10px;" type="button" value="Ok" id="alert_button"/>' +  
                          '</div>' +  
                          '</div>');  
                $("#alert_title").text(title);  
                $("#alert_title").addClass('jqx-alert-header');  
                $("#alert_content").addClass('jqx-alert-content');  
                $("#message").text(msg);  
                $("#alert_button").width(70);  
                $("#alert_button").click(function () {  
                     jqxAlert._hide();  
                });  
                jqxAlert._setPosition();  
           },  
           // hide alert.  
           _hide: function () {  
                $("#alert_container").remove();  
                jqxAlert._handleOverlay('hide');  
           },  
           // initialize the overlay element.   
           _handleOverlay: function (status) {  
                switch (status) {  
                     case 'show':  
                          jqxAlert._handleOverlay('hide');  
                          $("BODY").append('<div id="alert_overlay"></div>');  
                          $("#alert_overlay").css({  
                               position: 'absolute',  
                               zIndex: 99998,  
                               top: '0px',  
                               left: '0px',  
                               width: '100%',  
                               height: $(document).height(),  
                               background: jqxAlert.overlayColor,  
                               opacity: jqxAlert.overlayOpacity  
                          });  
                          break;  
                     case 'hide':  
                          $("#alert_overlay").remove();  
                          break;  
                }  
           },  
           // sets the alert's position.  
           _setPosition: function () {  
                // center screen with offset.  
                var top = (($(window).height() / 2) - ($("#alert_container").outerHeight() / 2)) + jqxAlert.top;  
                var left = (($(window).width() / 2) - ($("#alert_container").outerWidth() / 2)) + jqxAlert.left;  
                if (top < 0) {  
                     top = 0;  
                }  
                if (left < 0) {  
                     left = 0;  
                }  
                // set position.  
                $("#alert_container").css({  
                     top: top + 'px',  
                     left: left + 'px'  
                });  
                // update overlay.  
                $("#alert_overlay").height($(document).height());  
           }  
      }  
 </script>  
 
	</head>
<body>
	<div class="content">
		<div class="wrap">
			<div class="main">
				<div class="header-container">
					<div class="header">
						<img id="header-logo"
							src="<c:url value="/resources/styles/images/logo-o.png" />"
							alt="" class="header-logo" width="26" height="26"/>
						<img id="header-logo"
							src="<c:url value="/resources/styles/images/sys_header1.png" />"
							alt="" class="header-logo" />
							
							<table class="userInfoTable" border="0" width="100%">
								<tr>
									<td width="70%">
									</td>
									<td>
										<a><span class="userInfo"><i class="fa fa-user"></i> <%=JBMUIHelper.getLoggedInUserFullName(request)%></span></a>
									</td>
								</tr>
								<tr>
									<td width="60%">
									</td>
									<td>
										<a href='<c:url value="/logout.html" />'><span class="logoutLink"><i class="fa fa-sign-out"></i> Logout</span></a>
									</td>
								</tr>
							</table>
							
							
					</div>
					<div class="header-adobe-logo">
						<img id="header-adobe-logo"
							src="<c:url value="/resources/styles/images/header_logo.png" />" />
					</div>
					<jsp:include page="menu.jsp" />					
				</div>
				