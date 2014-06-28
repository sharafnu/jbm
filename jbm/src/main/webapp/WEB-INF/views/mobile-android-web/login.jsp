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

</head>
<script>
$(document).ready(function() {
	$(document).on('click', '#loginButton', function() {
		$("#loginForm").submit();
	}); 
	
    $(document).on('click', '#resetButton', function() {
		$("#loginForm").reset();
	});
});

$(document).on('pagebeforeshow', '#login', function(){  
    
});

</script>
<body style="background-color: #fff">
	<div data-role="page" id="login" style="background-color: #fff">


		<div data-role="header" data-position="fixed" data-tap-toggle="false"
			data-theme="b" style="background-color: #A01F62; color: #fff">
			<h1>MaxMaid Services</h1>
		</div>
		<!-- /header -->

		<div role="main" class="ui-content" style="background-color: #fff">
			<div class="logo" style="background-color: #fff">
				<a href="index.html"><img width="60%" height="60%"
					src="<c:url value="/resources/images/logo.jpg" />"
					alt="MaxMaid Logo" /></a>
			</div>
			<form id="loginForm" method="post" action="j_spring_security_check">

				<h2>Login</h2>

				<label for="name">Username:</label> 
				<input type="text" name="j_username" id="j_username" value="" data-clear-btn="true" placeholder="User Id" data-mini="true" autofocus> 
				<label for="password">Password:</label> 
				<input type="password" name="j_password" id="j_password" value="" data-clear-btn="true" autocomplete="off" placeholder="Password" data-mini="true">

				<div class="ui-grid-a">
					<div class="ui-block-b">
						<a id="loginButton" href="#" data-role="button"
							class="ui-btn ui-shadow ui-corner-all ui-btn-b ui-mini">Login</a>
					</div>
					<div id="resetButton" class="ui-block-b">
						<a href="#" data-role="button"
							class="ui-btn ui-shadow ui-corner-all ui-btn-b ui-mini">Reset</a>
					</div>
				</div>
			</form>
		</div>

		<jsp:include page="includes/footer.jsp" />