<%@ include file="tags/directive/taglib.jspf"%>
<html lang="en">
<%@ include file="tags/head.jspf"%>
<body>

<%@ include file="tags/adminHeader.jspf"%>

<form action="controller" method="post">
    <input type="hidden" name="command" value="setDoctorForAPatientSubmit">
    <section>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-md-8 col-lg-8 col-xl-6">

                    <div class="row">
                        <div class="col text-center">
                            <h1><fmt:message key="label.doctor_appointment"/></h1>
                        </div>
                    </div>

                    <div class="row align-items-center mt-4">
                        <div class="col">
                            <input type="text" name="DoctorID" class="form-control" placeholder=" <fmt:message key="placeholder.doctor_id"/>" required="required">
                        </div>
                    </div>

                    <div class="row align-items-center mt-4">
                        <div class="col">
                            <input type="text" name="PatientID" class="form-control" placeholder=" <fmt:message key="placeholder.patient_id"/>" required="required">
                        </div>
                    </div>

                    <jsp:useBean id="error" scope="session" type="java.lang.String"/>
                    <div class="row align-items-center mt-4">
                        <div class="col">
                            <c:out value="${error}"/>
                        </div>
                    </div>

                    <div class="row justify-content-start mt-4">
                        <div class="col">
                            <button type="submit" class="btn btn-primary mt-4"><fmt:message key="button.submit"/></button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </section>
</form>

<%@include file="tags/footer.jspf"%>

</body>
</html>
