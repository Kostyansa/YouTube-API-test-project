<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>HomeMedia Player: videos</title>
        <link href="../CSS/videos.css" rel="stylesheet" type="text/css"><meta charset="utf-8"/>
    </head>
    <body>
        <a href = "/">home</a>
        <div>${errorMsg}</div>
        <c:forEach var="video" items="${videos}">
            <a href = https://www.youtube.com/watch?v=${video.getYoutubeId()}>
                <h3>${video.getName()}</h3>
            </a>
            <div class="description-text">${video.getDescription()}</div>
        </c:forEach>
    </body>
</html>