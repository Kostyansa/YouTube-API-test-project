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
            <p>There should be a video</p>
            <a href = https://www.youtube.com/watch?v=${video.getYoutubeId()}>
                <h3>${video.getName()}</h3>
                <p>${video.getDescription()}</p>
            </a>
        </c:forEach>
    </body>
</html>