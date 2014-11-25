<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    DatabaseModel.Patient patient = (DatabaseModel.Patient) request.getAttribute("patient");
    pageContext.setAttribute("patient", patient);
%>

<div>
    <h1>Bills for <c:out value="${patient.name}" /> </h1>
</div>
<div>
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
            <%
                ArrayList<DatabaseModel.Bill> bills = (ArrayList<DatabaseModel.Bill>) request.getAttribute("bills");
                pageContext.setAttribute("bills", bills);
            %>
            <c:forEach items="${bills}" var="bill">
                <tr>
                    <td>${bill.id}</td>
                    <td>${bill.cost}</td>
                    <td>
                        <c:set var="billDate" value="${bill.datePaid}" />
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${billDate}" />
                    </td>
                    <td>
                        <form action="billView" method="post">
                            <input type="submit" value="View Details" class="btn btn-sm btn-primary"/>
                            <input type="hidden" value="${bill.id}" name="billID"/>
                        </form>
                    </td>
                    <td>
                        <form action="billPay" method="post">
                            <c:if test="${bill.datePaid == null}">
                                <input type="submit" value="Pay" class="btn btn-sm btn-success"/>
                                <input type="hidden" value="${bill.id}" name="billID" />
                            </c:if>
                        </form>
                    </td>
                </tr>

            </c:forEach>

        </tbody>
    </table>
</div>