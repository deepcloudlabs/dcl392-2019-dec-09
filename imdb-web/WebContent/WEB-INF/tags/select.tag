<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="options" type="java.util.Collection"
	rtexprvalue="true" required="true"%>
<%@ attribute name="name" required="true"%>
<%@ attribute name="label" required="true"%>
<%@ attribute name="property" required="true"%>

<label for="${name}">${label}</label>
<select id="${name}" name="${name}" class="form-control">
	<c:forEach items="${options}" var="opt">
		<c:choose>
			<c:when test="${opt[property] eq param[name]}">
				<option selected value="${opt[property]}">${opt[property]}</option>
			</c:when>
			<c:otherwise>
				<option value="${opt[property]}">${opt[property]}</option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select>