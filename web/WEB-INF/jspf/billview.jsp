<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    DatabaseModel.Patient patient = (DatabaseModel.Patient) request.getAttribute("patient");
    DatabaseModel.Bill bill = (DatabaseModel.Bill) request.getAttribute("bill");
    ArrayList<DatabaseModel.BillItem> billItems = (ArrayList<DatabaseModel.BillItem>) request.getAttribute("billItems");
    ArrayList<DatabaseModel.Medicine> medicines = (ArrayList<DatabaseModel.Medicine>) request.getAttribute("medicines");
    pageContext.setAttribute("patient", patient);
    pageContext.setAttribute("bill", bill);
    pageContext.setAttribute("billItems", billItems);
    pageContext.setAttribute("medicines", medicines);
%>

<div class="page-header">
    <h1 style="display: inline">Bill:&nbsp;<small><c:out value="${bill.id}"/></small></h1>
</div>
<div class="panel panel-default">
    <div class="panel-body">
        <table class="table-condensed">
            <thead></thead>
            <tbody>
                <tr><td><h4><label>Patient:</label></h4></td><td><h4><c:out value="${patient.name}" /></h4></td></tr>
                <c:set value="${bill.dateCreated}" var="dateCreated" />
                <tr><td><label>Date Created:</label></td><td><fmt:formatDate pattern="dd/MM/yyyy" value="${dateCreated}"/></td></tr>
                <c:set value="${bill.datePaid}" var="datePaid" />
                <tr><td><label>Date Paid</label></td><td><fmt:formatDate pattern="dd/MM/yyyy" value="${datePaid}"/></td></tr>
                <tr><td><label for="txtConsulationCost">Consultation Cost:</label></td><td><input id="txtConsultationCost" name="txtConsultationCost" type="text" class="form-control" value="<c:out value="${bill.consultationCost}"/>"/></td><td><input type="button" value="Update" class="btn btn-sm btn-primary"></td></tr>
            </tbody>
        </table>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-body">
        <table  class="table table-hover table-striped">
            <thead>
                <tr>
                    <th>Medicine</th>
                    <th >Quantity</th>
                    <th >Unit Cost</th>
                    <th >Sub Total</th>
                    <th style="width:5%"></th>
                    <th style="width:5%"></th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty billItems}">
                    <td colspan="6" style="text-align:center"><h4><strong>No items</strong></h4></td>
                    </c:when>
                <c:otherwise>
                    <c:forEach items="${billItems}" var="billItem">
                        <tr>
                            <td>[name]</td>
                            <td><c:out value="${billItem.quanitity}"/></td>
                            <td>[cost]/></td>
                            <td>[subtotal]</td>
                            <td><input type="button" value="Edit" class="btn btn-sm btn-primary"></td>
                            <td><input type="button" value="Remove" class="btn btn-sm btn-danger"/></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>

            </c:choose>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="3" style="text-align:right"><strong>Total:</strong></td>
                    <td><c:out value="${bill.totalCost}"></c:out></td>
                    <td></td>
                </tr>
            </tfoot>
        </table>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-body">
        <input type="button" value="&nbsp;Pay&nbsp;" class="btn btn-sm btn-success"/>
        <input type="button" value="Add Medicine" class="btn btn-sm btn-primary"/>
    </div>
</div>