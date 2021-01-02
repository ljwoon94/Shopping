<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div align="center">
	<table>
		<tr>
			<!-- 홈 -->
			<td width="80"><a href="/"><spring:message code="header.home"/></a></td>
			<!-- 코드그룹 관리 메뉴 -->
			<td width="120"><a href="/codegroup/list"><spring:message code="menu.codegroup.list"/></a></td>
			<!-- 코드 관리 메뉴 -->
			<td width="120"><a href="/codedetail/list"><spring:message code="menu.codedetail.list"/></a></td>
			
		</tr>
	</table>

</div>
<hr>