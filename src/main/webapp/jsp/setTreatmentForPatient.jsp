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

<div class="container" style="margin-top: 100px">
  <div class="col-lg-12">
    <h2><fmt:message key="label.treatment"/></h2>
    <br>
    <br>
  </div>
  <div class="col-lg-8 push-lg-4 personal-info">
    <form action="controller" method="post">
      <input type="hidden" name="command" value="setTreatmentForPatientApply"/>
      <jsp:useBean id="patient" scope="request" type="com.hospital.Hospital.model.Patient"/>
      <input type="hidden" name="patientId" value="${patient.id}">
      <div class="form-group row">
        <div class="col-lg-9">
          <label for="textarea"><fmt:message key="label.describe_treatment"/></label>
          <textarea class="form-control rounded-0" id="textarea" rows="10" name="Treatment"></textarea>
          <button type="submit" class="btn btn-primary" style="margin-top: 10px"><fmt:message key="button.submit"/></button>
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
