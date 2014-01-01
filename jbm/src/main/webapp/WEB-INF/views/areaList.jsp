<jsp:include page="includes/header.jsp" />
<div>
	<span class="breadcrumbs">Home </span>
</div>

<script type="text/javascript">
        $(document).ready(function () {
            var url = "../sampledata/products.xml";

            // prepare the data
            var source =
            {
                datatype: "xml",
                datafields: [
                    { name: 'areaName', type: 'string' },
                    { name: 'cityName', type: 'int' }
                 ],
                root: "areaName",
                record: "Area",
                id: 'areaId',
                url: url
            };

            var cellsrenderer = function (row, columnfield, value, defaulthtml, columnproperties, rowdata) {
                if (value < 20) {
                    return '<span style="margin: 4px; float: ' + columnproperties.cellsalign + '; color: #ff0000;">' + value + '</span>';
                }
                else {
                    return '<span style="margin: 4px; float: ' + columnproperties.cellsalign + '; color: #008000;">' + value + '</span>';
                }
            }

            var dataAdapter = new $.jqx.dataAdapter(source, {
                downloadComplete: function (data, status, xhr) { },
                loadComplete: function (data) { },
                loadError: function (xhr, status, error) { }
            });

            // initialize jqxGrid
            $("#jqxgrid").jqxGrid(
            {
                width: 570,
                source: dataAdapter,                
                pageable: true,
                autoheight: true,
                sortable: true,
                altrows: true,
                enabletooltips: true,
                editable: true,
                selectionmode: 'none',
                columns: [
                  { text: 'Area Name',  datafield: 'areaName', width: 350 },
                  { text: 'City Name',  datafield: 'cityName', width: 220 }                  
                ]
            });
        });
    </script>

<form id="form" action="./">

<!-- Container for create-account controls, populated by JavaScript code below. -->
<div id="SIU2" class="SIU2" style="opacity: 1;">
	<div id="createAccount" class="cornerDiv">	
		<div style="background-color: #F4F0F5; color: #000; min-height: 1.5em; vertical-align: middle; padding: 5px; width: 570px;">
				Area List</div>		
	        <div id="jqxgrid">
	        </div>
				
	</div>
</div>
</form>

<jsp:include page="includes/footer.jsp" />
