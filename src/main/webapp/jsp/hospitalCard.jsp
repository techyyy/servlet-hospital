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

<table class="table shadow-lg p-4 mb-5 bg-white rounded" style = "margin-bottom: 100px;">
  <thead>
  <tr>
    <th scope="col"><fmt:message key="label.diagnosis"/></th>
    <th scope="col"><fmt:message key="label.doctors_fn"/></th>
    <th scope="col"><fmt:message key="label.doctors_ln"/></th>
    <th scope="col"><fmt:message key="label.doctors_pos"/></th>
    <th scope="col"><fmt:message key="label.treatment"/></th>
  </tr>
  </thead>
  <tbody>
  <jsp:useBean id="hospitalCard" scope="request" type="java.util.List"/>
  <c:forEach items="${hospitalCard}" var="history" varStatus="status">
    <tr>
      <td><c:out value="${history.diagnosis}"/></td>
      <td><c:out value="${history.doctorFirstName}"/></td>
      <td><c:out value="${history.doctorLastName}"/></td>
      <td><c:out value="${history.doctorPosition}"/></td>
      <td><c:out value="${history.treatment}"/></td>
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