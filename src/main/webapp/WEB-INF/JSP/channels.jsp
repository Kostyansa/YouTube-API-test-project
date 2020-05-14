<%@ page contentType="text/html; charset=utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>HomeMedia Player: Channels</title>
    </head>
    <body>
    <div>
        <form action="channels" method="POST">
            <input type="text" name="youtubeId" placeholder="Channel ID">
            <button>Submit</button>
        </form>
    </div>
    <div>
        <a href = "/">home</a>
        <c:forEach var="channel" items="${channels}">
            <p>${channel.getName()}</p>
        </c:forEach>
    </div>
    </body>
</html>