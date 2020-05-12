<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>HomeMedia Player: Suggestion</title>
    </head>
    <body>
        <form action="suggestions/videos" method="GET">
            <div>
                <select name="mode" size="1">
                    <option>Best</option>
                    <option>Controversial</option>
                </select>
            </div>
            <div>
                <label for="tag">Tag</label>
                <input type="text" name="tag" placeholder="Tag to search">
            </div>
            <div>
                <label for="hours">Hours</label>
                <input type="number" name="hours" placeholder="Hours part of length" value="0">
            </div>
            <div>
                <label for="minutes">Minutes</label>
                <input type="number" name="minutes" placeholder="Minutes part of length" value="0">
            </div>
            <div>
                <label for="seconds">Seconds</label>
                <input type="number" name="seconds" placeholder="Seconds part of length" value="0">
            </div>
            <div>
                <button>Submit</button>
            </div>
        </form>
    </body>
</html>