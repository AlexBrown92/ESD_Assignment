<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    Models.ViewModel.Bill billView = (Models.ViewModel.Bill) request.getAttribute("billView");
    ArrayList<Models.ViewModel.BillItem> billItems = billView.getBillItems();
    ArrayList<Models.DatabaseModel.Medicine> medicines = billView.getMedicines();
    pageContext.setAttribute("billView", billView);
    pageContext.setAttribute("billItems", billItems);
    pageContext.setAttribute("medicines", medicines);
%>

<div class="modal fade" id="addMedicineModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Add Medicine</h4>
            </div>
            <form action="billviewaddmedicine" method="post">
                <div class="modal-body">
                    <table>
                        <thead></thead>
                        <tbody>
                            <tr>
                                <td>
                                    <label for="medicine">Medicine:</label>
                                </td>
                                <td>
                                    <select name="medicineID" id="medicine" class="form-control">
                                        <c:forEach items="${medicines}" var="medicine" >
                                            <option value="${medicine.ID}"><c:out value="${medicine.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="quantity">Quantity:</label>
                                </td>
                                <td>
                                    <input type="number" id="quantity" name="quantity" class="form-control" />
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Add Medicine</button>
                    <input type="hidden" value="<c:out value="${billView.billID}"/>" name="billID" />
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<div class="modal fade" id="editMedicine" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Edit Medicine</h4>
            </div>
            <form action="billvieweditmedicine" method="post">
                <div class="modal-body">
                    <table>
                        <thead></thead>
                        <tbody>
                            <tr>
                                <td>
                                    <label for="medicine">Medicine:</label>
                                </td>
                                <td>
                                    <input type="text" id="editMedicineName" class="form-control" disabled />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="editQuantity">Quantity:</label>
                                </td>
                                <td>
                                    <input id="editQuantity" type="number"  name="quantity" class="form-control" />
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Update Medicine</button>
                    <input type="hidden" value="<c:out value="${billView.billID}"/>" name="billID" />
                    <input type="hidden" name="medicineID" id="editMedicineID" />
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
                        <label for="consultationCost">Consultation Cost:</label>
                    </td>
            <form action="billviewconsultationcost" method="post">
                <td>
                    <input id="consultationCost" name="consultationCost" type="text" class="form-control" 
                           value="<c:out value="${billView.consultationCost}"/>"
                           <c:if test="${billView.datePaid != null}">
                               disabled
                           </c:if>
                           />
                </td>
                <td>
                    <input type="submit" value="Update" class="btn btn-sm btn-primary
                           <c:if test="${billView.datePaid != null}">
                               disabled
                           </c:if>
                           ">
                    <input type="hidden" name="billID" value="<c:out value="${billView.billID}" />" />
                </td>
            </form>
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
                        <tr><td colspan="6" style="text-align:center"><h4><strong>No items</strong></h4></td></tr>
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
                                           " onclick="populateEditMedicine('${billItem.name}', '${billItem.quantity}', '${billItem.medicineID}')"></td>
                                <td>
                                    <form action="billviewremove" method="post">
                                        <input type="submit" value="Remove" class="btn btn-sm btn-danger
                                               <c:if test="${billView.datePaid != null}">
                                                   disabled
                                               </c:if>
                                               "/>
                                        <input type="hidden" value="<c:out value="${billItem.billID}"/>" name="billID"/>
                                        <input type="hidden" value="<c:out value="${billItem.medicineID}"/>" name="medicineID"/>

                                    </form>
                                </td>
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
                </c:if>
                "/>
            <input type="hidden" name="billID" value="${billView.billID}" />
        </form>
        <button type="button" class="btn btn-sm btn-primary 
                <c:if test="${billView.datePaid != null}">
                    disabled
                </c:if>
                " data-toggle="modal" data-target="#addMedicineModal">
            Add Medicine
        </button>
    </div>
</div>

<script type="text/javascript">
    function populateEditMedicine(medicineName, quantity, medicineID) {
        $("#editMedicineName").val(medicineName);
        $("#editQuantity").val(quantity);
        $("#editMedicineID").val(medicineID);
        $("#editMedicine").modal("show");
    }
</script>
