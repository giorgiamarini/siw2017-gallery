<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://springframekork.org/security/tags"%>
<%@ page session="false" %>
<html>
<head>
	<p>Welcome <sec:authentication property="name"/></p>
	<sec:authorize ifAnyGranted="ROLE_ADMIN">
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
