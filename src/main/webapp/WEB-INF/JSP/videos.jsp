<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>HomeMedia Player: videos</title>
    </head>
    <body>
        <a href = "/">home</a>
        <c:forEach var="video" items="${videos}">
            <a href = https://www.youtube.com/watch?v=${video.youtubeId}>
                <h3>${video.name}</h3>
                <p>${video.description}</p>
            </a>
        </c:forEach>
    </body>
</html>