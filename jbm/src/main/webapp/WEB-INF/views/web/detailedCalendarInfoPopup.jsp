<script>
var calendarCurrentView = "agendaDay";

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
	var appointmentDate = $("#formAppointmentDate").val();
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	
	var url = 'getStaffAppointmentsTimeBreakupsJSON/'+appointmentDate+'.html';
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
	var day = appointmentDate.substr(0,2);
	var mon = parseInt(appointmentDate.substr(3,2) - 1);
	var year = appointmentDate.substr(6,4);
	
	//alert(day+" : "+mon+" : "+year);
	
	$('#detailedCalendar').fullCalendar( 'gotoDate', year, mon, day);
	//$('#detailedCalendar').fullCalendar( 'refetchEvents' );
}

function resetAndCloseCalendarPopupForm() {
	$('#detailedCalendarInfoPopupWindow').jqxWindow('hide');	
}

</script>
<div id="detailedCalendarInfoPopup" style="display: none;">
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