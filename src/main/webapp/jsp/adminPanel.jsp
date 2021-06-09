<%@ include file="tags/directive/taglib.jspf"%>
<html lang="en">
<%@ include file="tags/head.jspf"%>
<body>

<%@ include file="tags/adminHeader.jspf"%>

<div class = "text-center" style = "padding-top: 200px">
    <form action="controller" method="get">
        <input type="hidden" name="command" value="registerADoctor"/>
        <button type="submit" class="btn btn-primary btn-lg"><fmt:message key="button.add_a_doctor"/></button>
    </form>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="registerANurse"/>
        <button type="submit" class="btn btn-primary btn-lg"><fmt:message key="button.add_a_nurse"/></button>
    </form>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="registerAPatient"/>
        <button type="submit" class="btn btn-secondary btn-lg"><fmt:message key="button.add_a_patient"/></button>
    </form>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="setDoctorForAPatient"/>
        <button type="submit" class="btn btn-secondary btn-lg"><fmt:message key="button.set_doctor_for_a_patient"/></button>
    </form>
</div>

<%@include file="tags/footer.jspf"%>

</body>

</html>
