<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page-header">
    <h1>Patients:</h1>
</div>
<div class="panel panel-default">
    <div class="panel-body">
        <table class="table table-hover table-striped">
            <thead>
                <tr>
                    <th>Patient Name</th>
                    <th style="width:5%"></th>
                    <th style="width:5%"></th>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Models.DatabaseModel.Patient> patients;
                    patients = (ArrayList<Models.DatabaseModel.Patient>) request.getAttribute("patients");
                    pageContext.setAttribute("patients", patients);
                %>
                <c:forEach items="${patients}" var="patient" >
                    <tr>
                        <td><c:out value="${patient.name}"/></td>
                        <td>
                            <form action="patientview" method="post">
                                <input type="submit" value="View Details" class="btn btn-sm btn-primary"/>
                                <input type="hidden" value="${patient.ID}" name="patientID"/>
                            </form>
                        </td>
                        <td>
                            <form action="patientremove" method="post">
                                <input type="submit" value="Remove" name="patient${patient.ID}" class="btn btn-sm btn-danger
                                       <c:if test="${patient.removable == false}">
                                           disabled
                                       </c:if>
                                       " />
                                <input type="hidden" value="${patient.ID}" name="patientID" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>