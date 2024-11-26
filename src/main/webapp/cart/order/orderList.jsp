<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문하기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.1.0.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <link href="${path}/cart/order/orderStyle.css" rel="stylesheet">
</head>
<body>
<%@ include file="/util/header.jsp" %>
<button class="back-btn" onclick="history.back()">← 뒤로가기</button>

<section class="order-container">
    <h3 class="text-center">주문 목록</h3>
    <c:forEach var="orders" items="${orderList}">
        <div class="order-item">
            <p><strong>가게:</strong> ${orders.storeName}</p>
            <p><strong>음식:</strong> ${orders.foodName}</p>
            <p><strong>개수:</strong> ${orders.quntity}</p>
            <p><strong>예상 조리시간:</strong> ${orders.deliveryTime}</p>
            <p><strong>가격:</strong> ${orders.price}</p>
        </div>
    </c:forEach>
    <div class="order-summary">
        <h4>총액: ${totalAmount}원</h4>
    </div>
    <button class="order-btn" id="order-confirm">주문하기</button>
</section>
<script type="text/javascript" src="${path}/cart/order/orderListScript.js"></script>
</body>
</html>