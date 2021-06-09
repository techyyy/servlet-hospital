<%@ include file="tags/directive/taglib.jspf"%>>
<html lang="en">
<%@ include file="tags/head.jspf"%>

<body>

<c:if test = "${userRole == 'ADMIN'}">
  <%@ include file="tags/adminHeader.jspf"%>
</c:if>
<c:if test = "${userRole == 'DOCTOR'}">
  <%@ include file="tags/doctorHeader.jspf"%>
</c:if>

<div class="container" style="margin-top: 100px">
  <div class="col-lg-12">
    <h2><fmt:message key="label.edit_patient"/></h2>
    <br>
    <br>
  </div>
  <div class="col-lg-8 push-lg-4 personal-info">
    <form action="controller" method="post">
      <input type="hidden" name="command" value="editPatientApply"/>
      <jsp:useBean id="patient" scope="request" type="com.hospital.Hospital.model.Patient"/>
      <input type="hidden" name="id" value="${patient.id}">
      <div class="form-group row">
        <label class="col-lg-3 col-form-label form-control-label"><fmt:message key="label.first_name"/></label>
        <div class="col-lg-9">
          <input class="form-control" type="text" name = "FirstName" value="<c:out value="${patient.firstName}"/>"/>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-lg-3 col-form-label form-control-label"><fmt:message key="label.last_name"/></label>
        <div class="col-lg-9">
          <input class="form-control" type="text" name = "LastName" value="<c:out value="${patient.lastName}"/>"/>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-lg-3 col-form-label form-control-label" ><fmt:message key="label.diagnosis"/></label>
        <div class="col-lg-9">
          <input class="form-control" type="text" name="Diagnosis" value="<c:out value="${patient.diagnosis}"/>"/>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-lg-3 col-form-label form-control-label"><fmt:message key="label.birth_date"/></label>
        <div class="col-lg-9">
          <input class="form-control" type="date" name = "BirthDate" value="<c:out value="${patient.birthDate}"/>"/>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-lg-3 col-form-label form-control-label"></label>
        <div class="col-lg-9">
          <button type="submit" class="btn btn-primary"><fmt:message key="label.apply_changes"/></button>
        </div>
      </div>
    </form>
  </div>
</div>

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


</body><app-content ng-version="11.1.0"></app-content></html>
