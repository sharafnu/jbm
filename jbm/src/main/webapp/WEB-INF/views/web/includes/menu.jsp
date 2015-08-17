<%@page import="com.innovazions.jbm.common.JBMUIHelper"%>
<script type="text/javascript">
            $(document).ready(function () {
                // Create a jqxMenu
                $("#jqxMenu").jqxMenu({ width: '938', height: '30px', theme: 'shinyblack'});
                $("#jqxMenu").css('visibility', 'visible');
                $("#jqxMenu").jqxMenu({ showTopLevelArrows: true });
            });
        </script>
        <div id='jqxWidget' style='height: 40px;'>
            <div id='jqxMenu' style='visibility: hidden; margin-left: 0px;'>
                <ul>
                    <li><a href="home.html">Home</a></li>
                    <li>Appointments
                        <ul style='width: 250px;'>
                            <li><a href="customerApointmentAdd.html">Book Appointment</a></li>
                            <li><a href="customerAppointmentList.html">Appointment Search/Update</a></li>
                            <!-- <li><a href="customerAppointmentDetails.html">Update Job Details</a></li> -->
                        </ul>
                    </li>
                    <li>Customer
                        <ul style='width: 250px;'>
                            <!-- <li><a href="customerList.html">Search Customer</a></li> -->
                            <!-- <li><a href="customerInfoAdd.html">Add New Customer</a></li>  -->
                            <li><a href="customerInfoEdit.html">Search/Edit Customer</a></li>
                            <!-- <li><a href="customerList.html">Search/Edit Customer</a></li> -->
                            <li><a href="customerContractDetails.html">Customer Contracts</a></li>
                        </ul>
                    </li>
                   	<%if(JBMUIHelper.isAdminUser()) {%>
                   	<li>User Accounts
                        <ul>
                            <li><a href="userDetails.html">User Management</a></li>
                        </ul>
                    </li>
                    <li>Master Setup
                        <ul>
                            <!-- <li><a href="areaList.html">Area List</a></li> -->
                            <li><a href="staffDetails.html">Staff Details</a></li>
                            <li><a href="masterList.html">Master Setup</a></li>
                            <li><a href="systemProperyList.html">System Properties</a></li>
                            
                        </ul>
                    </li>
                    <li>Reports
                        <ul>
                            <!-- <li><a href="areaList.html">Area List</a></li> -->
                            <li><a href="dailyAppointmentsReportList.html">Daily Appointments</a></li>
                            <li><a href="staffWiseAppointmentsReport.html">Staff Appointments</a></li>
                            <li><a href="monthlyAppointmentReportList.html">Monthly Report</a></li>
                            
                        </ul>
                    </li>
                    <%}%>
                    
                    <li>Help
                        <ul style='width: 180px;'>
                            <li><a href="#Help">Help Topic</a></li>
                            <li><a href="#Support">Support</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
 