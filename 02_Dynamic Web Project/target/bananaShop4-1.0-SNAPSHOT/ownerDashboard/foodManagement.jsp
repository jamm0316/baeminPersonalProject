<%--
  Created by IntelliJ IDEA.
  User: evan
  Date: 11/23/24
  Time: 08:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}"></c:set>
<html>
<head>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.js"></script>
    <link href="${path}/ownerDashboard/maindashBoardStyle.css" rel="stylesheet">
    <script type="text/javascript" src="${path}/ownerDashboard/storefoodScript.js"></script>
    <title>가게 관리/수정</title>
</head>
<body>
<%@ include file="/util/header.jsp" %>
<div class="modal fade" id="foodSelectButton" tabindex="-1" aria-labelledby="foodSelect" aria-hidden="true">
    <div class="modal-dialog food">
        <div class="modal-content food">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="foodList">음식 리스트</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="food-insert food-insert">
                <button type="button" class="btn btn-primary food-insert-form" id="food-insert-form">
                    등록
                </button>
            </div>
            <div class="modal-body">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="submit" class="btn btn-primary" data-bs-dismiss="modal">완료</button>
            </div>
        </div>
    </div>

    <%--1. 음식 등록 팝오버--%>
    <div class="popover-form hidden" id="popoverInsertForm">
        <form id="foodInsertForm">
            <!-- Hidden Field: storeId -->
            <input type="hidden" id="food-insert-storeId" name="storeId" value="">

            <!-- 음식 이름 -->
            <div class="mb-3">
                <label for="food-insert-name" class="form-label">음식 이름</label>
                <input type="text" id="food-insert-name" name="foodName" class="form-control food-name" required>
            </div>

            <!-- 음식 가격 -->
            <div class="mb-3">
                <label for="food-insert-price" class="form-label">가격</label>
                <input type="number" id="food-insert-price" name="foodPrice" class="form-control food-price" required>
            </div>

            <!-- 최대 조리시간 -->
            <div class="mb-3">
                <label for="food-insert-max-cooking-time" class="form-label">최대 조리시간</label>
                <input type="number" name="foodMaxCookingTime" id="food-insert-max-cooking-time"
                       class="form-control food-max-cooking-time" required>
            </div>

            <!-- 등록 버튼 -->
            <button type="submit" class="btn btn-primary" id="food-insert-button">등록</button>

            <!-- 취소 버튼 -->
            <button type="button" class="btn btn-secondary" id="cancel-insert-Popover">취소</button>
        </form>
    </div>
</div>

<%--*글로발 네비--%>
<nav id="global-nav">
    <div class="navbar-header">
        <ul class="quick-link">
            <li>
                <a class="btn btn-primary quick-button txt">쇼핑파트너 센터</a>
            </li>
            <li>
                <a class="btn btn-primary quick-button txt">커머스 솔루션 마켓</a>
            </li>
            <li>
                <a class="btn btn-primary quick-button txt">우리가게 홍보하기</a>
            </li>
        </ul>
        <ul class="nav navbar-account nav-pull-right">
            <li class="login-info">
                <a class="seller-member">
                    <span class="login-id text-overflow quick-button txt">${ownerDTO.name}님 정보</span>
                </a>
                <a class="seller-member">
                    <span class="my-info text-overflow quick-button txt">내정보</span>
                </a>
            </li>
            <li class="login-info">
                <a href= "${path}/owner/signup.do" class="logout quick-button txt">로그아웃</a>
            </li>
        </ul>
    </div>
</nav>
<%--* 사이드카드--%>
<section class="main-content">
    <aside class="side-nav">
        <div class="pn-transclude">
            <div class="navbar-logo">
                <ul class="logo">
                    <li>
                        <a class="service">로고</a>
                    </li>
                    <li>
                        <a class="sellerShop">배달의민족 판매자센터</a>
                    </li>
                </ul>
            </div>
            <div class="store">
                <a>
                <span class="thumb">
                    <img class="img-circle" width="80" height="80">
                </span>
                    <span class="shop">${ownerDTO.name}</span>
                </a>
                <span>통합 관리자</span>
            </div>
            <div role="menuitem">
                <div class="accordion" id="menuitemAccordionPanelsStayOpen">
                    <div class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
                                    aria-controls="panelsStayOpen-collapseOne">
                                가게 관리
                            </button>
                        </h2>
                        <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show">
                            <div class="accordion-body">
                                <a class="title-group" id="store-management">
                                    가게 관리/수정
                                </a>
                                <a class="title-group" id="food-management">
                                    음식 관리/수정
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false"
                                    aria-controls="panelsStayOpen-collapseTwo">
                                주문관리
                            </button>
                        </h2>
                        <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse">
                            <div class="accordion-body">
                                <a class="title-group">
                                    주문 관리/수정
                                </a>
                                <a class="title-group">
                                    주문 내역
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </aside>

    <%--가게 목록--%>
    <section id="seller-sub-content">
        <section class="store-management-group">
            <div class="innerContent content-card" id="store-status">
                <div class="panel-body">
                    <ul class="panel-list">
                        <li>
                            <div class="order-info">
                                <div class="order-summary">
                                <span class="order-info-title">
                                    <em> 가게목록</em>
                                </span>
                                    <span class="number-area">
                                    <a>${storeDTOS.size()}</a>
                                    <span>개</span>
                                </span>
                                </div>
                                <c:forEach var="store" items="${storeDTOS}">
                                    <div class="each-order">
                                        <div class="each-info">
                                            <span>${store.name}</span>
                                            <span>
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
                                            <span>
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
                                            </span>
                                        </div>
                                        <button id="foodSelectButton" type="button" class="btn btn-success food-select"
                                                data-bs-toggle="modal"
                                                data-bs-target="#foodSelectButton" data-bs-whatever="@mdo"
                                                data-id="${store.id}" data-name="${store.name}"
                                                data-area_Id="${store.area_id}"
                                                data-category="${store.category}">
                                            상세보기
                                        </button>

                                            <%--                                        delete버튼--%>
                                            <%--                                        <!-- Button trigger modal -->--%>
                                            <%--                                        <button type="button" class="btn btn-danger delete-store" id="open-delete-modal"--%>
                                            <%--                                                data-bs-toggle="modal"--%>
                                            <%--                                                data-bs-target="#delete-store-modal" data-id="${store.id}"--%>
                                            <%--                                                data-name="${store.name}">--%>
                                            <%--                                            삭제--%>
                                            <%--                                        </button>--%>
                                    </div>
                                </c:forEach>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
    </section>
</section>
<footer id="seller-footer">
    셀러 푸터
</footer>
</body>
</html>
