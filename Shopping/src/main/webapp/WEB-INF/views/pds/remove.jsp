<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2><spring:message code="pds.header.remove"/></h2>
<form:form modelAttribute="pds" action="remove" enctype="multipart/form-data">
	<form:hidden path="itemId"/>
	<table>
		<tr>
			<td><spring:message code="pds.itemName"/></td>
			<td><form:input path="itemName" disabled="true"/></td>
			<td><font color="red"><form:errors path="itemName"/></font></td>
		</tr>
		<tr>
			<td><spring:message code="pds.itemFile"/></td>
			<td>
				<div class="uploadedList"></div>
			</td>
			<td></td>
		</tr>
		<tr>
			<td><spring:message code="pds.itemDescription"/></td>
			<td><form:textarea path ="description" disabled="true"/></td>
		</tr>
	</table>
</form:form>

<div>
	<button type="submit" id="btnRemove"><spring:message code="action.remove"/></button>
	<button type="submit" id="btnList"><spring:message code="action.list"/></button>
</div>

<script>
	$(document).ready(function(){
		var formObj = $("#pds");
		
		$("#btnRemove").on("click",function(){
			formObj.submit();
		});
		
		$("#btnList").on("click",function(){
			self.location = "list";
		});
		
	});
</script>
<script type="text/javascript">
	$(document).on("click",function(){
		function getOriginalName(fileName){
			var idx= fileName.indexOf("_") +1;
			return fileName.substr(idx);
		}
		
		var itemId = ${pds.itemId};
		console.log("itemId : " + itemId);

		
		//첨부파일 목록 조회
		$.getJSON("/pds/getAttach"+itemId,function(list){
			$(list).each(function(){
				console.log("data : "+this);
				var data = this;
				
				console.log("data:" + data);
				
				var str="<div>"+getOriginalName(data)+"</div>";
				$(".uploadedList").append(str);
			});
		});
	});
</script>