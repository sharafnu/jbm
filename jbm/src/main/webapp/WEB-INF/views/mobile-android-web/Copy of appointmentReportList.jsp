<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>MaxMaid</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/jquery-mobile/jquery.mobile-1.4.2.css" />">
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/jquery-mobile/dataTable.latest.css" />">

<script type="text/javascript" src="<c:url value="/resources/scripts/jquery-1.10.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery-mobile/jquery.mobile-1.4.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jquery-mobile/dataTable.latest.js" />"></script>
  
</head> 

<body> 

<div data-role="page">

  <div data-role="header" data-position="fixed">
    <h1>Tableview Demo</h1>
  </div><!-- /header -->

  <div data-role="content">  
    
    <table cellspacing="0" class="ui-responsive table-stroke" data-role="table" id="sample" 
    data-mode="columntoggle" data-top-container="true" data-bottom-container="true" data-inset="true" data-filter="true">
       <thead>
        <tr>
          <th rowspan="2" >Company</th>
          <th rowspan="2" data-sortable="true" data-priority="6">Last Trade</th>
          <th rowspan="2" data-priority="3" data-sortable="true">Trade Time</th>
          <th rowspan="2" data-priority="6">Change</th>
          <th rowspan="2" data-priority="3">Prev Close</th>
          <th rowspan="2" data-priority="5" data-sortable="true">Open</th>
        </tr>
       </thead>
       <tbody class="table_body">
        <tr>
          <th>GOOG <span class="co-name">Google Inc.</span></th>
          <td>597.74</td>
          <td>12:12PM</td>
          <td>14.81 (2.54%)</td>
          <td>582.93</td>
          <td>597.95</td>
        </tr>      
        <tr>
          <th>AAPL <span class="co-name">Apple Inc.</span></th>
          <td>378.94</td>
          <td>12:22PM</td>
          <td>5.74 (1.54%)</td>
          <td>373.20</td>
          <td>381.02</td>
        </tr>      
        <tr>
          <th>AMZN <span class="co-name">Amazon.com Inc.</span></th>
          <td>191.55</td>
          <td>12:23PM</td>
          <td>3.16 (1.68%)</td>
          <td>188.39</td>
          <td>194.99</td>
        </tr>       
        <tr>
          <th>ORCL <span class="co-name">Oracle Corporation</span></th>
          <td>31.15</td>
          <td>12:44PM</td>
          <td>1.41 (4.72%)</td>
          <td>29.74</td>
          <td>30.67</td>
        </tr>      
        <tr>
          <th>MSFT <span class="co-name">Microsoft Corporation</span></th>
          <td>25.50</td>
          <td>12:27PM</td>
          <td>0.66 (2.67%)</td>
          <td>24.84</td>
          <td>25.37</td>
        </tr>
        <tr>
          <th>CSCO <span class="co-name">Cisco Systems, Inc.</span></th>
          <td>18.65</td>
          <td>12:45PM</td>
          <td>0.97 (5.49%)</td>
          <td>17.68</td>
          <td>18.23</td>
        </tr>      
        <tr>
          <th>YHOO <span class="co-name">Yahoo! Inc.</span></th>
          <td>15.81</td>
          <td>12:25PM</td>
          <td>0.11 (0.67%)</td>
          <td>15.70</td>
          <td>15.94</td>
        </tr>
       </tbody>
    </table>
    <br />
    

  </div>
  

</div>
<script type="text/javascript">
  var count = 0
  $(document).on("click", "#refresh_table", function (e) {
     var tb = $('.table_body'),
        numbers = [1,2,3,4,5,6,7,8,9],
        chars = ["a","b","c","d","e","f","g","h","i"],
        use = count % 2 === 1 ? numbers : chars,
        newRow = '<tr><th>'+use[0]+'</th><td>'+use[1]+'</td><td>'+use[2]+'</td><td>'+use[3]+'</td><td>'+use[4]+'</td><td>'+use[5]+'</td><td>'+use[6]+'</td><td>'+use[7]+'</td><td>'+use[8]+'</td></tr>', 
        newBody = "";
    for (var i = 0, l = 6; i < l; i += 1) {
      newBody += newRow;
    }
    tb.empty()
      .append(newBody)
      .closest('table')
      .table('refresh');
    count += 1;
  });
</script>
</body>
</html>