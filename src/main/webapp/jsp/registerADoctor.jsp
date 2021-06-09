<%@ include file="tags/directive/taglib.jspf"%>
<html lang="en">
<%@ include file="tags/head.jspf"%>

<body>

<%@ include file="tags/adminHeader.jspf"%>

<form action="controller" method="post">
    <input type="hidden" name="command" value="registerADoctorSubmit">
    <section>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-md-8 col-lg-8 col-xl-6">
                    <div class="row">
                        <div class="col text-center">
                            <h1><fmt:message key="label.new_doctor"/></h1>
                        </div>
                    </div>
                    <div class="row align-items-center mt-4">
                        <div class="col">
                            <input type="text" name="Username" class="form-control" placeholder=" <fmt:message key="placeholder.username"/>" required="required">
                        </div>
                    </div>
                    <div class="row align-items-center mt-4">
                        <div class="col">
                            <input type="text" name="Password" class="form-control" placeholder=" <fmt:message key="placeholder.password"/>" required="required">
                        </div>
                    </div>
                    <div class="row align-items-center mt-4">
                        <div class="col">
                            <input type="text" name="FirstName" class="form-control" placeholder=" <fmt:message key="label.first_name"/>" required="required">
                        </div>
                    </div>
                    <div class="row align-items-center mt-4">
                        <div class="col">
                            <input type="text" name="LastName" class="form-control" placeholder=" <fmt:message key="label.last_name"/>" required="required">
                        </div>
                    </div>
                    <div class="row align-items-center mt-4">
                        <div class="col">
                            <input type="text" name="Position" class="form-control" placeholder=" <fmt:message key="label.position"/>" required="required">
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
