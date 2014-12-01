<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    ArrayList<Models.DatabaseModel.Patient> patients = (ArrayList<Models.DatabaseModel.Patient>) request.getAttribute("patients");
    pageContext.setAttribute("medicines", patients);
%>
<div class="page-header">
    <h1 style="display: inline">New Bill:&nbsp;</h1>
</div>
<div class="panel panel-default">
    <div class="panel-body">
        <form action="createbill" method="post">
            <div class="form-group">
                <label for="patientName">Patient</label>
                <select name="patientID" id="ddMedicine" class="form-control">
                    <c:forEach items="${patients}" var="patient" >
                        <option value="${patient.ID}"> <c:out value="${patient.name}"/> </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="consultationCost">Initial Consultation Cost</label>
                <input name="consultationCost" type="text" class="form-control" id="consultationCost">
            </div>
            <button type="submit" class="btn btn-primary">Proceed</button>
        </form>
    </div>
</div>
