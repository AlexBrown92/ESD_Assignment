<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    Models.DatabaseModel.Patient patient = (Models.DatabaseModel.Patient) request.getAttribute("patient");
    ArrayList<Models.ViewModel.Bill> bills = (ArrayList<Models.ViewModel.Bill>) request.getAttribute("bills");
    
    pageContext.setAttribute("bills", bills);
    pageContext.setAttribute("patient", patient);
%>

<div class="page-header">
    <h1 style="display:inline">Bills for: &nbsp;<small> <c:out value="${patient.name}" /></small> </h1>
</div>
<div class="panel panel-default">
    <div class="panel-body">
        <table  class="table table-hover table-striped">
            <thead>
                <tr>
                    <th style="width:10%">Bill Number</th>
                    <th>Total Cost</th>
                    <th>Date Paid</th>
                    <th style="width:5%"></th>
                    <th style="width:5%"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${bills}" var="bill">
                    <tr>
                        <td>${bill.billID}</td>
                        <td>${bill.totalCost}</td>
                        <td>
                            <c:set var="billDate" value="${bill.datePaid}" />
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${billDate}" />
                        </td>
                        <td>
                            <form action="billview" method="post">
                                <input type="submit" value="View Details" class="btn btn-sm btn-primary"/>
                                <input type="hidden" value="${bill.billID}" name="billID"/>
                            </form>
                        </td>
                        <td>
                            <form action="patientviewpaybill" method="post">
                                <c:if test="${bill.datePaid == null}">
                                    <input type="submit" value="Pay" class="btn btn-sm btn-success"/>
                                    <input type="hidden" value="${bill.billID}" name="billID" />
                                    <input type="hidden" value="${bill.patientID}" name="patientID"/> 
                                </c:if>
                            </form>
                        </td>
                    </tr>

                </c:forEach>

            </tbody>
        </table>
    </div>
</div>
