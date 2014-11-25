<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <h1>Bills for [customer name]</h1>
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
                    ArrayList<DatabaseModel.Bill> bills = (ArrayList<DatabaseModel.Bill>)request.getAttribute("bills");
                    pageContext.setAttribute("bills", bills); 
            %>
            <c:forEach items="${bills}" var="bill">
                  <tr>
                    <td>${bill}.ID</td>
                    <td>${bill}.Cost</td>
                    <td>${bill}.DatePaid</td>
                    <td>
                        <form action="" method="post">
                            <input type="submit" value="View Details" class="btn btn-sm btn-primary"/>
                        </form>
                    </td>
                    <td>
                        <form action="" method="post">
                            <input type="submit" value="Pay" class="btn btn-sm btn-success"/>
                        </form>
                    </td>
                </tr>

            </c:forEach>

        </tbody>
    </table>
</div>