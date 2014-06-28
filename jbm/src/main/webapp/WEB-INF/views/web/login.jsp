<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   
<html lang="en-us"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/login/bootstrap.min.css" />">	
		
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/login/css/font-awesome.min.css" />">

		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/login/smartadmin-production.css" />">
		
		<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/login/demo.css" />">

		<!-- page related CSS -->
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/login/lockscreen.css" />">

		<!-- FAVICONS -->
		<link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico" />" type="image/x-icon"/>
		<link rel="icon" href="<c:url value="/resources/images/favicon.ico" />" type="image/x-icon"/>

		<!-- GOOGLE FONT -->
		<link rel="stylesheet" href="<c:url value="/resources/fonts/font-family.css" />">

		<title>
			:: Welcome to Appointments Manager ::
		</title>
<script language="Javascript">
	//alert(1);
	//document.getElementById("j_username").focus();
</script>
<div class="pace-activity"></div></div>

		<div id="main" role="main">

			<!-- MAIN CONTENT -->
			
			<form class="lockscreen animated flipInY" action="j_spring_security_check" method="post">
				<div>
					<img id="header-logo"
							src="<c:url value="/resources/images/maxmaid_logo_new.gif" />"
							alt="" class="header-logo"/>
				</div>
				<div class="logo">
					<h5 class="semi-bold">AppointmentsManager</h1>
				</div>
				<div>
					<img src="<c:url value="/resources/login/images/appointment_setting.jpg" />" width="120" height="150">
					<div>
						<h1><i class="fa fa-user fa-3x text-muted air air-top-right hidden-mobile"></i> 
<small><i class="fa fa-lock text-muted"></i> &nbsp;Login</small></h1>
						
						<p>
							<input class="form-control" id="j_username" name="j_username" type="text" placeholder="User Id" style="width:220px" autofocus/>
						</p>
						<p>
							<input class="form-control" id="j_password" name="j_password" type="password" placeholder="Password" style="width:220px">
						
						</p>
						<p>
							<button type="submit" class="btn btn-labeled btn-info">
							 <span class="btn-label">
								Login
							</span>	
							  <i class="fa fa-sign-in"></i>							 
							</button>
							<c:if test = "${error == 'true'}">
							    <div class="alert alert-danger fade in">
									<i class="fa-fw fa fa-times"></i>
									${errorMessage}
								</div>
							</c:if>
							
						</p>
						<p class="no-margin margin-top-5">
							Forgot your password ? <a href="#"> Click here</a>
						</p>
					</div>

				</div>
				<p class="font-xs margin-top-5">
					&copy; 2013 Sharaf Innovazions Inc. All rights reserved.
				</p>
			</form>

		</div>
