<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    ArrayList<Models.DatabaseModel.Medicine> medicines = (ArrayList<Models.DatabaseModel.Medicine>) request.getAttribute("medicines");
    pageContext.setAttribute("medicines", medicines);
%>
<div class="modal fade" id="editMedicineModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Edit Medicine</h4>
            </div>
            <form action="medicinelistupdatemedicine" method="post">
                <div class="modal-body">
                    <table>
                        <thead></thead>
                        <tbody>
                            <tr>
                                <td>
                                    <label for="medicine">Medicine:</label>
                                </td>
                                <td>
                                    <input type="text" id="editMedicineName" class="form-control disabled"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="quantity">Cost:</label>
                                </td>
                                <td>
                                    <input type="number" id="editMedicineCost" name="cost" class="form-control" />
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Add Medicine</button>
                    <input type="hidden" name="medicineID" id="editMedicineID"/>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<div class="page-header">
    <h1 style="display: inline">All Medicines&nbsp;</h1>
</div>
<div class="panel panel-default">
    <div class="panel-body">
        <table  class="table table-hover table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Cost</th>
                    <th style="width:5%"></th>
                </tr>
            </thead>
            <tbody>    
                <c:choose>
                    <c:when test="${empty medicines}">
                        <tr>
                        <tr>
                            <td colspan="6" style="text-align:center">
                                <h4><strong>No Medicines</strong></h4>
                            </td>
                        </tr>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${medicines}" var="medicine">
                            <tr>
                                <td>
                                    <c:out value="${medicine.name}" />
                                </td>
                                <td>
                                    <c:out value="${medicine.cost}"/>
                                </td>
                                <td>
                                    <input type="button" value="Edit" onclick="editMedicine('${medicine.ID}', '${medicine.name}', '${medicine.cost}')"  class="btn btn-sm btn-primary"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    function editMedicine(medicineID, medicineName, medicineCost) {
        $("#editMedicineID").val(medicineID);
        $("#editMedicineName").val(medicineName);
        $("#editMedicineCost").val(medicineCost);
        $("#editMedicineModal").modal("show");
    }
</script>
