<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <td><input type="button" value="View"/></td>
                <td><input type="button" value="Remove"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>