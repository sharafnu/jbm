<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>MaxMaid</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/jquery-mobile/jquery.mobile-1.4.2.css" />">
		<script type="text/javascript" src="<c:url value="/resources/scripts/jquery-1.10.2.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/jquery-mobile/jquery.mobile-1.4.2.min.js" />"></script>
	<style>
		table {
		    *border-collapse: collapse; /* IE7 and lower */
		    border-spacing: 0;
		    width: 100%;    
		}
		
		.bordered {
		    border: solid #ccc 1px;
		    -moz-border-radius: 6px;
		    -webkit-border-radius: 6px;
		    border-radius: 6px;
		    -webkit-box-shadow: 0 1px 1px #ccc; 
		    -moz-box-shadow: 0 1px 1px #ccc; 
		    box-shadow: 0 1px 1px #ccc;         
		}
		
		.bordered tr:hover {
		    background: #fbf8e9;
		    -o-transition: all 0.1s ease-in-out;
		    -webkit-transition: all 0.1s ease-in-out;
		    -moz-transition: all 0.1s ease-in-out;
		    -ms-transition: all 0.1s ease-in-out;
		    transition: all 0.1s ease-in-out;     
		}    
		    
		.bordered td, .bordered th {
		    border-left: 1px solid #ccc;
		    border-top: 1px solid #ccc;
		    padding: 1px;
		    text-align: left;    
		}
		
		.bordered th {
		    background-color: #dce9f9;
		    background-image: -webkit-gradient(linear, left top, left bottom, from(#ebf3fc), to(#dce9f9));
		    background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
		    background-image:    -moz-linear-gradient(top, #ebf3fc, #dce9f9);
		    background-image:     -ms-linear-gradient(top, #ebf3fc, #dce9f9);
		    background-image:      -o-linear-gradient(top, #ebf3fc, #dce9f9);
		    background-image:         linear-gradient(top, #ebf3fc, #dce9f9);
		    -webkit-box-shadow: 0 1px 0 rgba(255,255,255,.8) inset; 
		    -moz-box-shadow:0 1px 0 rgba(255,255,255,.8) inset;  
		    box-shadow: 0 1px 0 rgba(255,255,255,.8) inset;        
		    border-top: none;
		    text-shadow: 0 1px 0 rgba(255,255,255,.5); 
		}
		
		.bordered td:first-child, .bordered th:first-child {
		    border-left: none;
		}
		
		.bordered th:first-child {
		    -moz-border-radius: 6px 0 0 0;
		    -webkit-border-radius: 6px 0 0 0;
		    border-radius: 6px 0 0 0;
		}
		
		.bordered th:last-child {
		    -moz-border-radius: 0 6px 0 0;
		    -webkit-border-radius: 0 6px 0 0;
		    border-radius: 0 6px 0 0;
		}
		
		.bordered th:only-child{
		    -moz-border-radius: 6px 6px 0 0;
		    -webkit-border-radius: 6px 6px 0 0;
		    border-radius: 6px 6px 0 0;
		}
		
		.bordered tr:last-child td:first-child {
		    -moz-border-radius: 0 0 0 6px;
		    -webkit-border-radius: 0 0 0 6px;
		    border-radius: 0 0 0 6px;
		}
		
		.bordered tr:last-child td:last-child {
		    -moz-border-radius: 0 0 6px 0;
		    -webkit-border-radius: 0 0 6px 0;
		    border-radius: 0 0 6px 0;
		}
		
		
		
		/*----------------------*/
		
		.zebra td, .zebra th {
		    padding: 1px;
		    border-bottom: 1px solid #f2f2f2;    
		}
		
		.zebra tbody tr:nth-child(even) {
		    background: #f5f5f5;
		    -webkit-box-shadow: 0 1px 0 rgba(255,255,255,.8) inset; 
		    -moz-box-shadow:0 1px 0 rgba(255,255,255,.8) inset;  
		    box-shadow: 0 1px 0 rgba(255,255,255,.8) inset;        
		}
		
		.zebra th {
		    text-align: left;
		    text-shadow: 0 1px 0 rgba(255,255,255,.5); 
		    border-bottom: 1px solid #ccc;
		    background-color: #eee;
		    background-image: -webkit-gradient(linear, left top, left bottom, from(#f5f5f5), to(#eee));
		    background-image: -webkit-linear-gradient(top, #f5f5f5, #eee);
		    background-image:    -moz-linear-gradient(top, #f5f5f5, #eee);
		    background-image:     -ms-linear-gradient(top, #f5f5f5, #eee);
		    background-image:      -o-linear-gradient(top, #f5f5f5, #eee); 
		    background-image:         linear-gradient(top, #f5f5f5, #eee);
		}
		
		.zebra th:first-child {
		    -moz-border-radius: 6px 0 0 0;
		    -webkit-border-radius: 6px 0 0 0;
		    border-radius: 6px 0 0 0;  
		}
		
		.zebra th:last-child {
		    -moz-border-radius: 0 6px 0 0;
		    -webkit-border-radius: 0 6px 0 0;
		    border-radius: 0 6px 0 0;
		}
		
		.zebra th:only-child{
		    -moz-border-radius: 6px 6px 0 0;
		    -webkit-border-radius: 6px 6px 0 0;
		    border-radius: 6px 6px 0 0;
		}
		
		.zebra tfoot td {
		    border-bottom: 0;
		    border-top: 1px solid #fff;
		    background-color: #f1f1f1;  
		}
		
		.zebra tfoot td:first-child {
		    -moz-border-radius: 0 0 0 6px;
		    -webkit-border-radius: 0 0 0 6px;
		    border-radius: 0 0 0 6px;
		}
		
		.zebra tfoot td:last-child {
		    -moz-border-radius: 0 0 6px 0;
		    -webkit-border-radius: 0 0 6px 0;
		    border-radius: 0 0 6px 0;
		}
		
		.zebra tfoot td:only-child{
		    -moz-border-radius: 0 0 6px 6px;
		    -webkit-border-radius: 0 0 6px 6px
		    border-radius: 0 0 6px 6px
		}
	</style>
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
			<input id="filterTable-input" data-type="search" />			
			<table id="appointmentListTable" data-role="table" id="table-column-toggle" data-input="#filterTable-input" data-mode="columntoggle" class="ui-responsive table-stroke bordered" data-filter="true" placeholder="Search" data-mini = true>
              <thead>
                 <tr>
                  <th data-priority="1">App. No.</th>
                  <th data-priority="1">App. Date</th>
                  <th data-priority="1">Customer Name</th>
                  <th data-priority="1">Staff Name</th>
                  <th data-priority="1">Status</th>
                </tr>
              </thead>
              <tbody id="appointmentListTableBody">
<!--                <tr>
                  <th>1</th>
                  <td>Citizen Kane</td>
                  <td>1941</td>
                  <td>100%</td>
                  <td>74</td>
                </tr>
                <tr>
                  <th>2</th>
                  <td>Casablanca</td>
                  <td>1942</td>
                  <td>97%</td>
                  <td>64</td>
                </tr>
                <tr>
                  <th>3</th>
                  <td>The Godfather</td>
                  <td>1972</td>
                  <td>97%</td>
                  <td>87</td>
                </tr>
                <tr>
                  <th>4</th>
                  <td>Gone with the Wind</td>
                  <td>1939</td>
                  <td>96%</td>
                  <td>87</td>
                </tr>
                <tr>
                  <th>5</th>
                  <td>Lawrence of Arabia</td>
                  <td>1962</td>
                  <td>94%</td>
                  <td>87</td>
                </tr>
                <tr>
                  <th>6</th>
                  <td>Love the Bomb</td>
                  <td>1964</td>
                  <td>92%</td>
                  <td>74</td>
                </tr>
                <tr>
                  <th>7</th>
                  <td>The Graduate</td>
                  <td>1967</td>
                  <td>91%</td>
                  <td>122</td>
                </tr>
                <tr>
                  <th>8</th>
                  <td>The Wizard of Oz</td>
                  <td>1939</td>
                  <td>90%</td>
                  <td>72</td>
                </tr>
                <tr>
                  <th>9</th>
                  <td>Singin' in the Rain</td>
                  <td>1952</td>
                  <td>89%</td>
                  <td>85</td>
                </tr>
                <tr>
                  <th>10</th>
                  <td>Inception</td>
                  <td>2010</td>
                  <td>84%</td>
                  <td>78</td>
                </tr> -->
              </tbody>
            </table>
			</div>
		</div>
		<script>
		$(document).ready(function() {
			loadTable();
		});
		
		function loadTable() {
			alert(1);
			$.getJSON("customerAppointmentListJSON.html", function(data){
		        jsonObj = data;
		        alert(jsonObj);
		        $(jsonObj).each(function(index, element){  
		            $('#appointmentListTableBody').append('<tr><th>' + element.appointmentNo + '</tH><td>'
		            + element.appointmentDate + '</td><td>'
		            + element.customerName + '</td><td>'
		            + element.employeeName + '</td><td>'
		            + element.appointmentStatus + '</td><td>');       
		        });
		    });

		}
		</script>	
	</body>
</html>