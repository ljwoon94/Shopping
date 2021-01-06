<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!--sec는 시큐리티 태크이다. 관리자 권한이 있다면 관리자 페이지 링크를 보여주거나,
 	로그인에 성공해서 특정 권한을 획득한 사용자에게는 로그인 버튼 대신 로그아웃 버튼을 
 	보여주는 등의 로직을 사용할 수 있다. -->

<h2><spring:message code="user.header.register"/></h2>
<form:form modelAttribute="member" action="register">
	<table>
		<tr>
			<td><spring:message code="user.userId"/></td>
			<td><form:input path="userId"/></td>
			<td><font color ="red"><form:errors path="userId"/></font></td>
		</tr>
		<tr>
			<td><spring:message code="user.userPw"/></td>
			<td><form:input path="userPw"/></td>
			<td><font color="red"><form:errors path="userPw"/></font></td>
		</tr>
		<tr>
			<td><spring:message code="user.userName"/></td>
			<td><form:input path="userName"/></td>
			<td><font color="red"><form:errors path="userName"/></font></td>
		</tr>
		<tr>
			<td><spring:message code="user.job"/></td>
			<td><form:select path="job" items="${jobList}" itemValue="value" itemLabel="label"/></td>
			<td><font color="red"><form:errors path="job"/></font></td>
		</tr>
	</table>
</form:form>

<!-- hasRole('role')	해당 권한이 있을 경우
		 hasAnyRole('role1,'role2')	포함된 권한 중 하나라도 있을 경우 -->
<div>
	<button type="submit" id ="btnRegister"><spring:message code="action.register"/></button>	
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<button type="submit" id ="btnList"><spring:message code="action.list"/></button>
	</sec:authorize>

</div>

<script>
	$(document).ready(function(){
		var formObj =$("#member");
		$("#btnRegister").on("click",function(){
			formObj.submit();
		});
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		$("#btnList").on("click",function(){
			self.location="list";
		});
	</sec:authorize>
	});
</script>
