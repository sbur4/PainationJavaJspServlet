<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>

<body class="w-100 h-auto m-0 p-0">

<div class="container m-5 align-content-center">
    <div class="table-responsive">
        <table class="table" id="tableSort" data-toggle="table">
            <thead>
            <tr>
                <th>id</th>
                <th data-field="name" data-sortable="true">name</th>
                <th data-field="description" data-sortable="true">description</th>
                <th data-field="price" data-sortable="true">price</th>
                <th>image</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td>${product.image_url}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div class="container m-5">
    <span> Page ${page} of ${pageCount}</span><span> | </span>

    <span>
    <c:choose>
        <c:when test="${page - 1 > 0}">
            <a href="page?page=${page-1}&pageSize=${pageSize}">Previous</a>
        </c:when>
        <c:otherwise>
            Previous
        </c:otherwise>
    </c:choose>
    </span>

    <span>
    <c:forEach var="p" begin="${minPossiblePage}" end="${maxPossiblePage}">
        <c:choose>
            <c:when test="${page == p}">${p}</c:when>
            <c:otherwise>
                <a href="page?page=${p}&pageSize=${pageSize}">${p}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    </span>

    <span>
    <c:choose>
        <c:when test="${page + 1 <= pageCount}">
            <a href="page?page=${page+1}&pageSize=${pageSize}">Next</a>
        </c:when>
        <c:otherwise>
            Next
        </c:otherwise>
    </c:choose>
    </span>

</div>

<script>
    // bootstrap
    $(document).ready(function () {
        $('#tableSort').DataTable({
            "order": [[3, 'desc']]
            // [[ 3, 'desc' ], [ 0, 'asc' ]]
        });
        $('.dataTables_length').addClass('bs-select');
    });
</script>

</body>

<!-- bootstrap 5 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<%-- table sort--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.19.1/bootstrap-table.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.19.1/bootstrap-table.min.css" rel="stylesheet"/>

</html>
