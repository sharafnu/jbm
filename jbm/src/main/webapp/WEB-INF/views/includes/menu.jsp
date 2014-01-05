        <script type="text/javascript">
            $(document).ready(function () {
                // Create a jqxMenu
                $("#jqxMenu").jqxMenu({ width: '880', height: '30px'});
                $("#jqxMenu").css('visibility', 'visible');
                $("#jqxMenu").jqxMenu({ showTopLevelArrows: true });
            });
        </script>
        <div id='jqxWidget' style='height: 40px;'>
            <div id='jqxMenu' style='visibility: hidden; margin-left: 0px;'>
                <ul>
                    <li><a href="home.html">Home</a></li>
                    <li>Master Setup
                        <ul>
                            <li><a href="areaList.html">Area List</a></li>
                            <li><a href="staffDetails.html">Staff Details</a></li>
                        </ul>
                    </li>
                    <li>Customer
                        <ul style='width: 250px;'>
                            <li><a href="customerInfoAdd.html">Add Customer Info</a></li>
                            <li><a href="customerInfoEdit.html">Edit Customer Info</a></li>
                            <li><a href="customerContractDetails.html">Customer Contracts</a></li>
                        </ul>
                    </li>
                    <li>Job Management
                        <ul style='width: 200px;'>
                            <li><a href="customerApointmentAdd.html">Add Appointment</a></li>
                            <li><a href="customerAppointmentList.html">Job List</a></li>
                            <li><a href="customerAppointmentDetails.html">Job Details</a></li>
                        </ul>
                    </li>
                    <li>Other Options
                        <ul style='width: 200px;'>
                            <li><a href="#NewFeatures">Upcoming Features</a></li>
                        </ul>
                    </li>
                    <li>Help
                        <ul style='width: 180px;'>
                            <li><a href="#About">Help Topic</a></li>
                            <li><a href="#Press">Support</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
 