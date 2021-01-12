<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div align="center">
	<table>
		<tr>
			<!-- 홈 -->
			<td width="80"><a href="/"><spring:message code="header.home"/></a></td>
			<!-- 로그인을 하지 않은 경우 true -->
			<sec:authorize access="!isAuthenticated()">
				<!-- 회원게시판 메뉴 -->
				<td width="120"><a href="/board/list"><spring:message code="menu.board.member"/></a></td>
				<!-- 공지사항 메뉴 -->
				<td width="120"><a href="/notice/list"><spring:message code="menu.notice.member"/></a></td>
				<!-- 상품관리 메뉴 -->
				<td width="120"><a href="/item/list"><spring:message code="menu.item.member"/></a></td>
			</sec:authorize>
			<!-- 인증된 사용자인 경우 true -->
			<sec:authorize access="isAuthenticated()">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<!-- 코드그룹 관리 메뉴 -->
					<td width="120"><a href="/codegroup/list"><spring:message code="menu.codegroup.list"/></a></td>
					<!-- 코드 관리 메뉴 -->
					<td width="120"><a href="/codedetail/list"><spring:message code="menu.codedetail.list"/></a></td>
					<!-- 회원 관리 메뉴 -->
					<td width="120"><a href="/user/list"><spring:message code="menu.user.admin"/></a></td>
					<!-- 회원 게시판 메뉴 -->
					<td width="120"><a href="/board/list"><spring:message code="menu.board.member"/></a></td>
					<!-- 공지사항 메뉴 -->
					<td width="120"><a href="/notice/list"><spring:message code="menu.notice.admin"/></a></td>
					<!-- 상품관리 메뉴 -->
					<td width="120"><a href="/item/list"><spring:message code="menu.item.admin"/></a></td>
				</sec:authorize>
				<!-- 회원권한을 가진 사용자인 경우 true -->
				<sec:authorize access="hasRole('ROLE_MEMBER')">
					<!-- 회원게시판 메뉴  -->
					<td width="120"><a href="/board/list"><spring:message code="menu.board.member"/></a></td>
					<!-- 공지사항 메뉴 -->
					<td width="120"><a href="/notice/list"><spring:message code="menu.notice.member"/></a></td>
					<!-- 상품관리 메뉴 -->
					<td width="120"><a href="/item/list"><spring:message code="menu.item.member"/></a></td>
					<!-- 코인충전 메뉴 -->
					<td width="120"><a href="/coin/charge"><spring:message code="menu.coin.charge"/></a></td>
					<!-- 충전내역 메뉴 -->
					<td width="120"><a href="/coin/list"><spring:message code="menu.coin.list"/></a></td>
					<!-- 구매상품 메뉴 -->
					<td width="120"><a href="/useritem/list"><spring:message code="menu.useritem.list"/></a></td>
					<!-- 구매내역 메뉴 -->
					<td width="120"><a href="/coin/listPay"><spring:message code="menu.coin.listPay"/></a></td>
				</sec:authorize>
			</sec:authorize>
			
		</tr>
	</table>

</div>
<hr>