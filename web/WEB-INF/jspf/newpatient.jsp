<div class="page-header">
    <h1 style="display: inline">New Patient:&nbsp;</h1>
</div>
<div class="panel panel-default">
    <div class="panel-body">
        <form action="createbill" method="post">
            <div class="form-group">
                <label for="patientName">Patient Name</label>
                <input name="patientName" type="text" class="form-control" id="patientName" placeholder="Enter name">
            </div>
            <div class="form-group">
                <label for="consultationCost">Initial Consultation Cost</label>
                <input name="consultationCost" type="text" class="form-control" id="consultationCost">
            </div>
            <button type="submit" class="btn btn-primary">Proceed</button>
        </form>
    </div>
</div>
