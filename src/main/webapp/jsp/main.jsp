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
<c:if test = "${userRole == 'NURSE'}">
    <%@ include file="tags/nurseHeader.jspf"%>
</c:if>

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

