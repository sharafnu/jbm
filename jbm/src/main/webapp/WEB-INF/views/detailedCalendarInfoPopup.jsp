<script>
$(document).ready(function() {
	$('#detailedCalendarInfoPopupWindow').jqxWindow({
        showCollapseButton: false, maxHeight: 670, maxWidth: 800, minHeight: 670, minWidth: 800, height: 670, width: 800,
        autoOpen: false,
        initContent: function () {
           // $('#tab').jqxTabs({ height: '100%', width:  '100%' });
           setupDetailedCalendar();
            $('#detailedCalendarInfoPopupWindow').jqxWindow('focus');
        }
    });
	
	
});


function setupDetailedCalendar() {
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	
	$('#detailedCalendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'agendaWeek,agendaDay'
		},
		defaultView: 'agendaWeek',
		allDaySlot: false,
		minTime: '9',
		maxTime:'23',
		editable: true,
		events: 'http://localhost:8080/jbm/resources/events.json',
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
}

function resetAndCloseCalendarPopupForm() {
	$('#detailedCalendarInfoPopupWindow').jqxWindow('hide');	
}

</script>
<div id="detailedCalendarInfoPopup">
	<div style="width: 100%; height: 650px; margin-top: 50px;"
		id="calendarWindowContainer">
		<div id="detailedCalendarInfoPopupWindow">
			<div id="calendarWindowHeader">
				<span> Detailed Calendar </span>
			</div>
			<div style="overflow: hidden;" id="calendarWindowContent">
				<div id='detailedCalendar'></div>
			</div>
		</div>
	</div>
</div>