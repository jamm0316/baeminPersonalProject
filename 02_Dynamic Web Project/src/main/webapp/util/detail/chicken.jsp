<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category Details</title>
    <!-- External CSS and JS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.1.0.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="${path}/util/detail/categoryStyle.css" rel="stylesheet">
</head>
<body>
<%@ include file="/util/header.jsp" %>
<button class="back-btn" onclick="history.back()">← 뒤로가기</button>
<!-- Quantity Selection Modal -->
<div class="modal fade" id="quantityModal" tabindex="-1" aria-labelledby="quantityModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="quantityModalLabel">수량 선택</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="foodQuantity">수량</label>
                    <input type="number" id="foodQuantity" class="form-control" min="1" value="1">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="button" id="addToCartConfirm" class="btn btn-primary">추가</button>
            </div>
        </div>
    </div>
</div>

<section class="category-detail">
    <div class="container">
        <div class="row">
            <!-- Left Section: Stores -->
            <div class="col-lg-6 store-section">
                <h3>가게 목록</h3>
                <ul class="store-list">
                    <c:forEach var="store" items="${storeList}">
                        <span class="storeLocation">
                            <c:choose>
                                <c:when test="${store.area_id == 1}">강남구</c:when>
                                <c:when test="${store.area_id == 2}">강동구</c:when>
                                <c:when test="${store.area_id == 3}">강서구</c:when>
                                <c:when test="${store.area_id == 4}">관악구</c:when>
                                <c:when test="${store.area_id == 5}">구로구</c:when>
                                <c:when test="${store.area_id == 6}">금천구</c:when>
                                <c:when test="${store.area_id == 7}">동작구</c:when>
                                <c:when test="${store.area_id == 8}">서초구</c:when>
                                <c:when test="${store.area_id == 9}">송파구</c:when>
                                <c:when test="${store.area_id == 10}">양천구</c:when>
                                <c:when test="${store.area_id == 11}">영등포구</c:when>
                            </c:choose>
                        </span>
                        <li class="store-item" data-store-id="${store.id}" data-owner-email="${store.ownerEmail}">
                            <div class="store-name">${store.name}</div>
                            <c:choose>
                                <c:when test="${store.category == 1}">치킨</c:when>
                                <c:when test="${store.category == 2}">중식</c:when>
                                <c:when test="${store.category == 3}">돈까스</c:when>
                                <c:when test="${store.category == 4}">피자</c:when>
                                <c:when test="${store.category == 5}">회</c:when>
                                <c:when test="${store.category == 6}">한식</c:when>
                                <c:when test="${store.category == 7}">족발</c:when>
                                <c:when test="${store.category == 8}">디저트</c:when>
                                <c:when test="${store.category == 9}">분식</c:when>
                                <c:when test="${store.category == 10}">카페</c:when>
                            </c:choose>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <!-- Right Section: Food Menu -->
            <div class="col-lg-6 menu-section hidden">
                <h3 id="store-name">가게의 음식 메뉴</h3>
                <ul class="menu-list" id="menu-list">
                    <!-- 메뉴 리스트는 AJAX를 통해 동적으로 업데이트됩니다. -->
                </ul>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript" src="${path}/util/detail/mainCategoryScript.js"></script>
</body>
</html>