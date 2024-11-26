<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>배달현황</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.1.0.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <link href="${path}/delivery/deliveryStatusStyle.css" rel="stylesheet">
</head>
<body>
<%@ include file="/util/header.jsp" %>
<button class="back-btn" onclick="history.back()">← 뒤로가기</button>

<section class="delivery-container">
    <h3 class="text-center">배달현황</h3>
    <c:forEach var="orders" items="${orderList}">
        <div class="delivery-item">
            <p><strong>가게:</strong> ${orders.storeName}</p>
            <p><strong>음식:</strong> ${orders.foodName}</p>
            <p><strong>개수:</strong> ${orders.quntity}</p>
            <p><strong>배달상태:</strong> <span class="status-btn confirming">${orders.status}</span></p>
        </div>
    </c:forEach>
</section>
<script>
  const statusButtons = document.querySelectorAll(".status-btn");

  statusButtons.forEach(button => {
    // 상태 텍스트를 가져옵니다.
    const status = button.textContent.trim();

    // 상태에 따라 클래스를 추가합니다.
    switch (status) {
      case "주문확인 중":
        button.classList.add("confirming"); // Bright Red
        break;
      case "주문확인":
        button.classList.add("confirmed"); // Blue
        break;
      case "배달중":
        button.classList.add("in-progress"); // Yellow
        break;
      case "배달완료":
        button.classList.add("completed"); // Green
        break;
      default:
        button.classList.add("pending"); // Red
        break;
    }
  });
</script>
</body>
</html>