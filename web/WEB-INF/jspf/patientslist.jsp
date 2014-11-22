<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <h1>Patients:</h1>
</div>
<div>
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
                ArrayList<DatabaseModel.Patient> patients;
                patients = (ArrayList<DatabaseModel.Patient>) request.getAttribute("patients");
                pageContext.setAttribute("patients", patients);
            %>
            <c:forEach items="${patients}" var="patient" >
                <tr>
                    <td><c:out value="${patient.name}"/></td>
                    <td>
                        <form action="patientview" method="post">
                            <input type="submit" value="View Details" name="${patient.ID}" class="btn btn-sm btn-primary"/>
                        </form>
                    </td>
                    <td><form action="patientremove" method="post">
                            <input type="submit" value="Remove" name="${patient.ID}" class="btn btn-sm btn-danger"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>