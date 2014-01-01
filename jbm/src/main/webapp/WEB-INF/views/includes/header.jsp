<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<c:url value="/resources/styles/desktop.css" />"
	rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/styles/SIU2.css" />"
	rel="stylesheet" type="text/css">
<link href="<c:url value="/resources/styles/rounded-box.css" />"
	rel="stylesheet" type="text/css">	
<title>Use Registration</title>
<link type="text/css" rel="Stylesheet"
	href="<c:url value="/resources/jqwidgets/styles/jqx.base.css" />" />

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
    </style>
	
	
	</head>
<body>
	<div class="content">
		<div class="wrap">
			<div class="main">
				<div class="header-container">
					<div class="header">
						<img id="header-logo"
							src="<c:url value="/resources/styles/images/sys_header.png" />"
							alt="" class="header-logo" />
					</div>
					<div class="header-adobe-logo">
						<img id="header-adobe-logo"
							src="<c:url value="/resources/styles/images/logo_adobe.png" />" />
					</div>
					<jsp:include page="menu.jsp" />					
				</div>
				