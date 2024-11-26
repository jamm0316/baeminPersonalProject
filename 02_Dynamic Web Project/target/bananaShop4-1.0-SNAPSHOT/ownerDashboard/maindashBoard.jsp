<%--
  Created by IntelliJ IDEA.
  User: evan
  Date: 11/22/24
  Time: 08:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}"></c:set>
<html>
<head>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.js"></script>
    <link href="${path}/ownerDashboard/maindashBoardStyle.css" rel="stylesheet">
    <script type="text/javascript" src="${path}/ownerDashboard/maindashBoardScript.js"></script>
    <title>판매자 스토어센터</title>
</head>
<body>
<%@ include file="/util/header.jsp" %>
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
        <ul class="quick-link nav navbar-account nav-pull-right">
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
                                <%--todo: ajax를 이용한 innerSection 수정--%>
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
        <span class="innerContent title">주문현황</span>
        <button id="refreshBoard" class="btn btn-primary mb-3">새로고침</button>
        <%--todo: ajax를 이용한 innerSection 수정--%>
        <section class="innerContent content-card" id="order-status">
            <div class="panel-body" id="order-list">
                <!-- AJAX로 주문 목록이 동적으로 삽입됩니다. -->
<%--                <c:forEach var="order" items="${orderList}">--%>
<%--                    <div class="order-item">--%>
<%--                        <p><strong>가게:</strong> ${order.storeName}</p>--%>
<%--                        <p><strong>음식:</strong> ${order.foodName}</p>--%>
<%--                        <p><strong>개수:</strong> ${order.quntity}</p>--%>
<%--                        <p><strong>예상 조리시간:</strong> ${order.deliveryTime}분</p>--%>
<%--                        <p><strong>가격:</strong> ₩${order.price}</p>--%>
<%--                        <p><strong>주문 상태:</strong> ${order.status}</p>--%>
<%--                        <div class="order-actions">--%>
<%--                            <button class="btn btn-success order-confirm" data-order-id="${order.id}">주문 확인</button>--%>
<%--                            <button class="btn btn-warning order-delivering" data-order-id="${order.id}">배달 중</button>--%>
<%--                            <button class="btn btn-info order-complete" data-order-id="${order.id}">배달 완료</button>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </c:forEach>--%>
            </div>
        </section>
        <span class="innerContent title">매출통계</span>
        <%--todo: ajax를 이용한 innerSection 수정--%>
        <section class="innerContent content-card" id="sales-statistics">
            asinvlaksnfeo
        </section>
    </section>
</section>
<footer id="seller-footer">
    셀러 푸터
</footer>
</body>
</html>
