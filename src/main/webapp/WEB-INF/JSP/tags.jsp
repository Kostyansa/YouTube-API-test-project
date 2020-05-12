<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>HomeMedia Player: Tags</title>
    </head>
    <body>
        <a href = "/">home</a>
        <h3>All the tags</h3>
        <c:forEach var="tag" items="${tags}">
            <p>${tag.value}</p>
        </c:forEach>
    </body>
</html>