<%--
  Created by IntelliJ IDEA.
  User: evan
  Date: 11/24/24
  Time: 05:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}"></c:set>

<html>
<head>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.js"></script>
    <link href="${path}/ownerDashboard/maindashBoardStyle.css" rel="stylesheet">
    <script type="text/javascript" src="${path}/ownerDashboard/storefoodScript.js"></script>
</head>
<body>
<%@ include file="/util/header.jsp" %>
<div class="food-list">
    <%--2. 음식 수정 팝오버--%>
    <div class="popover-form hidden" id="popoverEditForm">
        <form id="foodEditForm">
            <!-- Hidden Field: storeId -->
            <input type="hidden" id="food-edit-storeId" name="storeId" value="">

            <!-- 음식 이름 -->
            <div class="mb-3">
                <label for="food-edit-name" class="form-label">음식 이름</label>
                <input type="text" id="food-edit-name" name="foodName" class="form-control food-name" required>
            </div>

            <!-- 음식 가격 -->
            <div class="mb-3">
                <label for="food-edit-price" class="form-label">가격</label>
                <input type="number" id="food-edit-price" name="foodPrice" class="form-control food-edit-price" required>
            </div>

            <!-- 최대 조리시간 -->
            <div class="mb-3">
                <label for="food-edit-max-cooking-time" class="form-label">최대 조리시간</label>
                <input type="number" id="food-edit-max-cooking-time" name="foodMaxCookingTime"
                       class="form-control food-edit-max-cooking-time" required>
            </div>

            <!-- 등록 버튼 -->
            <button type="submit" class="btn btn-primary" id="food-edit-button">등록</button>

            <!-- 취소 버튼 -->
            <button type="button" class="btn btn-secondary" id="cancel-edit-Popover">취소</button>
        </form>
    </div>

    <c:forEach var="food" items="${foodList}">
        <div class="food-img">
            <img src="" alt="음식이미지">
        </div>
        <div class="food-info">
            <ul class="food-info-each">
                <li class="food-name">${food.name}</li>
                <li class="food-price">가격: ${food.price}</li>
                <li class="food-maxCookingTime">최대 조리시간: ${food.maxCookingTime}</li>
            </ul>
        </div>
        <div class="food-buttons">
            <button class="btn btn-success food-edit-button" id="food-select-each-button">수정</button>
            <button class="btn btn-danger food-delete-button" data-id="${food.foodId}" data-store-id="${food.storeId}">삭제</button>
        </div>
    </c:forEach>
    <c:if test="${empty foodList}">
        <p>No data available</p>
    </c:if>
</div>
</body>
</html>
