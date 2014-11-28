<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    ArrayList<Models.ViewModel.Bill> bills = (ArrayList<Models.ViewModel.Bill>) request.getAttribute("billView");
    pageContext.setAttribute("bills", bills);
%>
<div class="page-header">
    <h1 style="display: inline">All Bills:&nbsp;</h1>
</div>
<div class="panel panel-default">
    <div class="panel-body">
        <table  class="table table-hover table-striped">
            <thead>
                <tr>
                    <th>Bill ID</th>
                    <th>Patient Name</th>
                    <th>Consultation Cost</th>
                    <th>Total Cost</th>
                    <th>Date Created</th>
                    <th>Date Paid</th>
                    <th style="width:5%"></th>
                    <th style="width:5%"></th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty bills}">
                        <tr>
                            <td colspan="6" style="text-align:center">
                                <h4><strong>No Bills</strong></h4>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${bills}" var="bill">
                            <tr>
                                <td>
                                    <c:out value="${bill.billID}" />
                                </td>
                                <td>
                                    <c:out value="${bill.patientName}" />
                                </td>
                                <td>
                                    <c:out value="${bill.consultationCost}" />
                                </td>
                                <td>
                                    <c:out value="${bill.totalCost}" /> 
                                </td>
                                <td>
                                    <c:set value="${bill.dateCreated}" var="dateCreated" />
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${dateCreated}"/>
                                </td>
                                <td>
                                    <c:set value="${bill.datePaid}" var="datePaid" />
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${datePaid}"/>
                                </td>
                                <td>
                                    <form action="billview" method="post">
                                        <input type="submit" value="View Details" class="btn btn-sm btn-primary"/>
                                        <input type="hidden" value="${bill.billID}" name="billID"/>
                                    </form>
                                </td>
                                <td>    
                                    <c:if test="${bill.datePaid == null}">
                                        <form action="billlistpaybill" method="post" style="display:inline">
                                            <input type="submit" value="&nbsp;Pay&nbsp;" class="btn btn-sm btn-success "/>
                                            <input type="hidden" name="billID" value="${bill.billID}" />
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>