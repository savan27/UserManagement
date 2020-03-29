<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>

	<c:if test="">
		
		<font color="red">
			Error: <c:out value="SomeThing Went Wrong. Please Login Again..!!"></c:out> 
		</font>
		
		<%-- include login page --%>
		<%@ include file="login.jsp"%>
		
	</c:if>
	
	<c:choose>
	  <c:when test="${pageContext.errorData.statusCode eq 500}">
	    
			<font color="red">
				Error: <c:out value="Some Data Is Missing. Please Login Again..!!"></c:out> 
			</font>
			
			<%-- include login page --%>
			<%@ include file="login.jsp"%>

	  </c:when>
	  <c:otherwise>
	    		
	    	  <!-- Status Code Is (404) redirect to login page -->
    	      <c:redirect url = "login.jsp"/>
	    
	  </c:otherwise>
	</c:choose>

</body>
</html>