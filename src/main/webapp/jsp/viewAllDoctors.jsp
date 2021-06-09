<%@ include file="tags/directive/taglib.jspf"%>
<html lang="en">
<%@ include file="tags/head.jspf"%>

<body>

<%@ include file="tags/adminHeader.jspf"%>

<div class="row align-items-start mb-2 ml-1">
  <form action="controller" method="get">
    <input type="hidden" name="command" value="sortDoctorsByAlphabet"/>
    <input type="hidden" name="page" value="1"/>
    <button class="btn btn-sm btn-primary ml-2"><fmt:message key="label.sort_by_alphabet"/></button>
  </form>
  <form action="controller" method="get">
    <input type="hidden" name="command" value="sortDoctorsByPosition"/>
    <input type="hidden" name="page" value="1"/>
    <button class="btn btn-sm btn-primary ml-2"><fmt:message key="label.sort_by_position"/></button>
  </form>
</div>
<table class="table shadow-lg p-4 mb-5 bg-white rounded" style = "margin-bottom: 100px;">
  <thead>
  <tr>
    <th scope="col">id</th>
    <th scope="col"><fmt:message key="label.first_name"/></th>
    <th scope="col"><fmt:message key="label.last_name"/></th>
    <th scope="col"><fmt:message key="label.position"/></th>
  </tr>
  </thead>
  <tbody>
  <jsp:useBean id="doctors" scope="request" type="java.util.List"/>
  <c:forEach items="${doctors}" var="doctor" varStatus="status">
    <tr>
      <td><c:out value="${doctor.id}"/> </td>
      <td><c:out value="${doctor.firstName}"/></td>
      <td><c:out value="${doctor.lastName}"/></td>
      <td><c:out value="${doctor.position}"/></td>
    </tr>
  </c:forEach>

  </tbody>
</table>

<table>
  <tr>
    <c:forEach begin="1" end="${numberOfPages+1}" var="i">
      <td><a href="${pageContext.request.contextPath}/controller?command=viewAllDoctors&page=${i}">${i}</a></td>
    </c:forEach>
  </tr>
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
