<%--
  Created by IntelliJ IDEA.
  User: evan
  Date: 11/22/24
  Time: 14:33
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
<div class="modal fade" id="delete-store-modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="delete-storeLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="delete-storeLabel">신중히 선택해주세요</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <strong><p id="modal-store-delete-info"></p></strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button id="confirm-delete" type="button" class="btn btn-danger" data-id="">삭제</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="storeEditButton" tabindex="-1" aria-labelledby="storeEdit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="storeEdit">가게 수정</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="${path}/editStore.do" method="post">
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="store-edit-name" class="col-form-label">가게이름</label>
                        <input type="text" class="form-control" id="store-edit-name" name="store-name" value="">
                        <input hidden="hidden" type="text" class="form-control" id="store-id" name="store-id" value="">

                    </div>
                    <div class="mb-3">
                        <label for="store-edit-area" class="col-form-label">가게위치</label>
                        <select id="store-edit-area" name="store-area" class="form-control mb-4">
                            <option value="1">강남구</option>
                            <option value="2">강동구</option>
                            <option value="3">강서구</option>
                            <option value="4">관악구</option>
                            <option value="5">구로구</option>
                            <option value="6">금천구</option>
                            <option value="7">동작구</option>
                            <option value="8">서초구</option>
                            <option value="9">송파구</option>
                            <option value="10">양천구</option>
                            <option value="11">영등포구</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="store-edit-category" class="col-form-label">음식종류</label>
                        <select id="store-edit-category" name="store-category" class="form-control mb-4">
                            <option value="1">치킨</option>
                            <option value="2">중식</option>
                            <option value="3">돈까스</option>
                            <option value="4">피자</option>
                            <option value="5">회</option>
                            <option value="6">한식</option>
                            <option value="7">족발</option>
                            <option value="8">디저트</option>
                            <option value="9">분식</option>
                            <option value="10">카페</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="submit" class="btn btn-primary">등록</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="storeInsertButton" tabindex="-1" aria-labelledby="storeInsert" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="storeInsert">가게 등록</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="${path}/insertStore.do" method="post">
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="store-name" class="col-form-label">가게이름</label>
                        <input type="text" class="form-control" id="store-name" name="store-name">
                    </div>
                    <div class="mb-3">
                        <label for="store-area" class="col-form-label">가게위치</label>
                        <select id="store-area" name="store-area" class="form-control mb-4">
                            <option value="1">강남구</option>
                            <option value="2">강동구</option>
                            <option value="3">강서구</option>
                            <option value="4">관악구</option>
                            <option value="5">구로구</option>
                            <option value="6">금천구</option>
                            <option value="7">동작구</option>
                            <option value="8">서초구</option>
                            <option value="9">송파구</option>
                            <option value="10">양천구</option>
                            <option value="11">영등포구</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="store-category" class="col-form-label">음식종류</label>
                        <select id="store-category" name="store-category" class="form-control mb-4">
                            <option value="1">치킨</option>
                            <option value="2">중식</option>
                            <option value="3">돈까스</option>
                            <option value="4">피자</option>
                            <option value="5">회</option>
                            <option value="6">한식</option>
                            <option value="7">족발</option>
                            <option value="8">디저트</option>
                            <option value="9">분식</option>
                            <option value="10">카페</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    <button type="submit" class="btn btn-primary">등록</button>
                </div>
            </form>
        </div>
    </div>
</div>
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
    <section id="seller-sub-content">
        <section class="store-management-group">
            <div class="store-button-group">
                <%--모달로 등록하기--%>
                <button id="storeInsertButton" type="button" class="btn btn-primary" data-bs-toggle="modal"
                        data-bs-target="#storeInsertButton"
                        data-bs-whatever="@mdo">가게등록
                </button>
            </div>
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
                                        <button id="storeEditButton" type="button" class="btn btn-success store-detail"
                                                data-bs-toggle="modal"
                                                data-bs-target="#storeEditButton" data-bs-whatever="@mdo"
                                                data-id="${store.id}" data-name="${store.name}"
                                                data-area_Id="${store.area_id}"
                                                data-category="${store.category}">
                                            수정하기
                                        </button>
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-danger delete-store" id="open-delete-modal"
                                                data-bs-toggle="modal"
                                                data-bs-target="#delete-store-modal" data-id="${store.id}"
                                                data-name="${store.name}">
                                            삭제
                                        </button>
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
