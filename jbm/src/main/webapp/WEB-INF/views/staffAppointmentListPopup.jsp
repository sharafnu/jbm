<script>
$(document).ready(function() {
	$('#addCustomerPopupWindow').jqxWindow({
        showCollapseButton: false, maxHeight: 420, maxWidth: 800, minHeight: 420, minWidth: 800, height: 420, width: 800,
        autoOpen: false,
        initContent: function () {
           // $('#tab').jqxTabs({ height: '100%', width:  '100%' });
            $('#addCustomerPopupWindow').jqxWindow('focus');
        }
    });
	$("#createCustomerExpander").jqxExpander({   width: '380px', showArrow:false });
	$("#customerResidenceAddressExpander").jqxExpander({   width: '380px', showArrow:false });
	$("#customerOfficeAddressExpander").jqxExpander({   width: '380px', showArrow:false });
	
});



function resetAndClosePopupForm() {
	$('#staffAppointmentListPopupWindow').jqxWindow('hide');	
}

</script>
<div id="staffAppointmentListPopup">
	<div style="width: 100%; height: 650px; margin-top: 50px;"
		id="staffAppointmentListPopupContainer">
		<div id="staffAppointmentListPopupWindow">
			<div id="staffAppointmentListwindowHeader">
				<span> Staff Appointment List </span>
			</div>
			<div style="overflow: hidden;" id="staffAppointmentListWindowContent">
				Hi..
			</div>
		</div>
	</div>
</div>