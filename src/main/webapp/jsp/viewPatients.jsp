<%@ include file="tags/directive/taglib.jspf"%>
<html lang="en">
<%@ include file="tags/head.jspf"%>

<body>


<c:if test = "${userRole == 'ADMIN'}">
    <%@ include file="tags/adminHeader.jspf"%>
</c:if>
<c:if test = "${userRole == 'DOCTOR'}">
    <%@ include file="tags/doctorHeader.jspf"%>
</c:if>

<div class="row align-items-start mb-2 ml-1">
    <form action="controller" method="get">
        <c:if test = "${userRole == 'ADMIN'}">
            <input type="hidden" name="command" value="sortAllPatientsByAlphabet"/>
        </c:if>
        <c:if test = "${userRole == 'DOCTOR'}">
            <input type="hidden" name="command" value="sortMyPatientsByAlphabet"/>
        </c:if>
        <button class="btn btn-sm btn-primary ml-2"><fmt:message key="label.sort_by_alphabet"/></button>
    </form>
    <form action="controller" method="get">
        <c:if test = "${userRole == 'ADMIN'}">
            <input type="hidden" name="command" value="sortAllPatientsByBirthDate"/>
        </c:if>
        <c:if test = "${userRole == 'DOCTOR'}">
            <input type="hidden" name="command" value="sortMyPatientsByBirthDate"/>
        </c:if>
        <button class="btn btn-sm btn-primary ml-2"><fmt:message key="label.sort_by_birth_date"/></button>
    </form>
</div>
<table class="table shadow-lg p-4 mb-5 bg-white rounded" style = "margin-bottom: 100px;">
    <thead>
    <tr>
        <c:if test = "${userRole == 'ADMIN'}">
            <th scope="col">id</th>
        </c:if>
        <th scope="col"><fmt:message key="label.first_name"/></th>
        <th scope="col"><fmt:message key="label.last_name"/></th>
        <th scope="col"><fmt:message key="label.diagnosis"/></th>
        <th scope="col"><fmt:message key="label.birth_date"/></th>
        <th scope="col"><fmt:message key="label.operations"/></th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="patients" scope="request" type="java.util.List"/>
    <c:forEach items="${patients}" var="patient" varStatus="status">
        <tr>
            <c:if test = "${userRole == 'ADMIN'}">
                <td><c:out value="${patient.id}"/></td>
            </c:if>
            <td><c:out value="${patient.firstName}"/></td>
            <td><c:out value="${patient.lastName}"/></td>
            <td><c:out value="${patient.diagnosis}"/></td>
            <td><c:out value="${patient.birthDate}"/></td>
            <td>
                <form action="controller" method = "GET">
                    <input type="hidden" name="command" value="editPatient"/>
                    <input type="hidden" name="patientId" value="${patient.id}">
                    <button class = "btn-sm btn-primary" style="width: 100px" type="submit"><fmt:message key="label.edit"/></button>
                </form>
                <c:if test = "${userRole == 'DOCTOR'}">
                    <form action="controller" method = "GET">
                        <input type="hidden" name="command" value="setTreatmentForPatient"/>
                        <input type="hidden" name="patientId" value="${patient.id}">
                        <button class = "btn-sm btn-primary" style="width: 100px" type="submit"><fmt:message key="label.set_treatment"/></button>
                    </form>
                    <form action="controller" method = "POST">
                        <input type="hidden" name="command" value="dischargePatient"/>
                        <input type="hidden" name="patientId" value="${patient.id}">
                        <button class = "btn-sm btn-primary" style="width: 100px" type="submit" onclick="return confirm('Are you sure you want to discharge this patient?')"><fmt:message key="label.discharge"/></button>
                    </form>
                </c:if>
                <form action="controller" method = "GET">
                    <input type="hidden" name="command" value="viewHospitalCard"/>
                    <input type="hidden" name="patientId" value="${patient.id}">
                    <button class = "btn-sm btn-primary" style="width: 100px" type="submit"><fmt:message key="label.hospital_card"/></button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="tags/footer.jspf"%>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="../../assets/js/vendor/popper.min.js"></script>
<script src="../../dist/js/bootstrap.min.js"></script>
<script src="../../assets/js/vendor/holder.min.js"></script>
<script>
    Holder.addTheme('thumb', {
        bg: '#55595c',
        fg: '#eceeef',
        text: 'Thumbnail'
    });
</script>


</body><app-content ng-version="11.1.0"></app-content>
</html>
