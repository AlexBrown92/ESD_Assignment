<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    Models.ViewModel.BillView billView = (Models.ViewModel.BillView) request.getAttribute("billView");
    ArrayList<Models.ViewModel.BillItem> billItems = billView.getBillItems();
    ArrayList<Models.DatabaseModel.Medicine> medicines = billView.getMedicines();
    pageContext.setAttribute("billView", billView);
    pageContext.setAttribute("billItems", billItems);
    pageContext.setAttribute("medicines", medicines);
%>

<div class="modal fade" id="addMedicine" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Add Medicine</h4>
            </div>
            <form>
                <div class="modal-body">
                    <table>
                        <thead></thead>
                        <tbody>
                            <tr>
                                <td>
                                    <label>Medicine:</label>
                                </td>
                                <td>
                                    <select>
                                        <c:forEach items="${medicines}" var="medicine" >
                                            <option value="${medicine.ID}"><c:out value="${medicine.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Quanitiy:</label>
                                </td>
                                <td>
                                    <input type="text" />
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </div>
                <div class="modal-footer">
                    <submit type="button" class="btn btn-primary">Add Medicine</submit>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<div class="page-header">
    <h1 style="display: inline">Bill:&nbsp;<small><c:out value="${billView.billID}"/></small></h1>
</div>
<div class="panel panel-default">
    <div class="panel-body">
        <table class="table-condensed">
            <thead></thead>
            <tbody>
                <tr>
                    <td>
                        <h4>
                            <label>Patient:</label>
                        </h4>
                    </td>
                    <td>
                        <h4><c:out value="${billView.patientName}" /></h4>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>Date Created:</label>
                    </td>
                    <td>
                        <c:set value="${billView.dateCreated}" var="dateCreated" />
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${dateCreated}"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>Date Paid</label>
                    </td>
                    <td>
                        <c:set value="${billView.datePaid}" var="datePaid" />
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${datePaid}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="txtConsulationCost">Consultation Cost:</label>
                    </td>
                    <td>
                        <input id="txtConsultationCost" name="txtConsultationCost" type="text" class="form-control" 
                               value="<c:out value="${billView.consultationCost}"/>"
                               <c:if test="${billView.datePaid != null}">
                                   disabled
                               </c:if>
                               />
                    </td>
                    <td>
                        <input type="button" value="Update" class="btn btn-sm btn-primary
                               <c:if test="${billView.datePaid != null}">
                                   disabled
                               </c:if>
                               ">
                    </td>
                </tr>
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
                            <td><c:out value="${billItem.name}"/></td>
                            <td><c:out value="${billItem.quantity}" /></td>
                            <td><c:out value="${billItem.cost}" /></td>
                            <td><c:out value="${billItem.total}"/></td>
                            <td><input type="button" value="Edit" class="btn btn-sm btn-primary
                                       <c:if test="${billView.datePaid != null}">
                                           disabled
                                       </c:if>
                                       "></td>
                            <td><input type="button" value="Remove" class="btn btn-sm btn-danger
                                       <c:if test="${billView.datePaid != null}">
                                           disabled
                                       </c:if>
                                       "/></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="3" style="text-align:right"><strong>Total:</strong></td>
                    <td><c:out value="${billView.totalCost}"></c:out></td>
                        <td></td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-body">
            <form action="billviewpaybill" method="post" style="display:inline">
            <input type="submit" value="&nbsp;Pay&nbsp;" class="btn btn-sm btn-success 
            <c:if test="${billView.datePaid != null}">
                disabled
            </c:if>>
            "/>
            <input type="hidden" name="billID" value="${billView.billID}" />
            </form>
        <button type="button" class="btn btn-sm btn-primary 
                <c:if test="${billView.datePaid != null}">
                    disabled
                </c:if>
                " data-toggle="modal" data-target="#addMedicine">
            Add Medicine
        </button>
    </div>
</div>

