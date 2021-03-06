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
                                    <select name="medicineID" id="ddMedicine" class="form-control" onchange="updateCost()">
                                        <c:forEach items="${medicines}" var="medicine" >
                                            <option value="${medicine.ID}" data-cost="<c:out value="${medicine.cost}"/>"><c:out value="${medicine.name}"/> </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="txtCost">Cost:</label>
                                </td>
                                <td>
                                    <h4 id="txtCost" class="form-control" style="background-color:rgb(238, 238, 238)"><c:out value="${medicines.get(0).cost}"/></h4>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="quantity">Quantity:</label>
                                </td>
                                <td>
                                    <input type="number" id="txtQuantity" name="quantity" class="form-control" onchange="updateSubtotal()" onblur="updateSubtotal()"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="quantity">Sub Total:</label>
                                </td>
                                <td>
                                    <h4 id="txtSubTotal" class="form-control" style="background-color:rgb(238, 238, 238)">0</h4>
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
                                    <label for="txtCost">Cost:</label>
                                </td>
                                <td>
                                    <h4 id="editCost" class="form-control" style="background-color:rgb(238, 238, 238)"></h4>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="editQuantity">Quantity:</label>
                                </td>
                                <td>
                                    <input id="editQuantity" type="number"  name="quantity" class="form-control" onchange="updateEditSubtotal()" onblur="updateEditSubtotal()"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="editSubTotal">SubTotal:</label>
                                </td>
                                <td>
                                    <h4 id="editSubTotal" class="form-control" style="background-color:rgb(238, 238, 238)"></h4>
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
                <c:if test="${billView.datePaid == null}">
                    <td>
                        <input type="submit" value="Update" class="btn btn-sm btn-primary">
                        <input type="hidden" name="billID" value="<c:out value="${billView.billID}" />" />
                    </td>
                </c:if>
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
                        <c:if test="${billView.datePaid == null}">
                        <th style="width:5%"></th>
                        <th style="width:5%"></th>
                        </c:if>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty billItems}">
                        <tr><td colspan="6" style="text-align:center">
                                <h4><strong>No items</strong>
                                </h4>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${billItems}" var="billItem">
                            <tr>
                                <td><c:out value="${billItem.name}"/></td>
                                <td><c:out value="${billItem.quantity}" /></td>
                                <td><c:out value="${billItem.cost}" /></td>
                                <td><c:out value="${billItem.total}"/></td>
                                <c:if test="${billView.datePaid == null}">
                                    <td>
                                        <input type="button" value="Edit" class="btn btn-sm btn-primary" onclick="populateEditMedicine('${billItem.name}', '${billItem.quantity}', '${billItem.cost}','${billItem.medicineID}')">
                                    </td>
                                    <td>
                                        <form action="billviewremove" method="post">
                                            <input type="submit" value="Remove" class="btn btn-sm btn-danger"/>
                                            <input type="hidden" value="<c:out value="${billItem.billID}"/>" name="billID"/>
                                            <input type="hidden" value="<c:out value="${billItem.medicineID}"/>" name="medicineID"/>
                                        </form>
                                    </td>
                                </c:if>
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
        <c:if test="${billView.datePaid == null}">
            <form action="billviewpaybill" method="post" style="display:inline">
                <input type="submit" value="&nbsp;Pay&nbsp;" class="btn btn-sm btn-success"/>
                <input type="hidden" name="billID" value="${billView.billID}" />
            </form>
            <button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#addMedicineModal">
                Add Medicine
            </button>
        </c:if>
    </div>
</div>

<script type="text/javascript">
    function populateEditMedicine(medicineName, quantity, cost, medicineID) {
        $("#editMedicineName").val(medicineName);
        $("#editQuantity").val(quantity);
        $("#editCost").html(cost);
        $("#editMedicineID").val(medicineID);
        $("#editMedicine").modal("show");
        updateEditSubtotal();
    }

    function updateEditSubtotal(){
        var cost = $("#editCost").html();
        var quantity = $("#editQuantity").val();
        var subtotal = cost * quantity;
        $("#editSubTotal").html(subtotal); 
    }



    function updateCost() {
        var cost = $("#ddMedicine option:selected").data("cost");
        $("#txtCost").html(cost);
    }
    
    function updateSubtotal(){
        var cost = $("#ddMedicine option:selected").data("cost");
        var quantity = $("#txtQuantity").val();
        var subtotal = cost * quantity;
        $("#txtSubTotal").html(subtotal); 
    }
</script>
