<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Updated Design</title>
  <!-- External CSS and JS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.1.0.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.1.0.js"></script>
  <script type="text/javascript" src="${path}/util/mainCategoryScript.js"></script>
  <!-- External Stylesheet -->
  <link href="${path}/util/mainCategoryStyle.css" rel="stylesheet">
<%--  <link href="${path}/css/newStyle.css" rel="stylesheet">--%>
  <style>

  </style>
</head>
<body>
<%@ include file="/util/header.jsp" %>

<!-- Search Section -->
<section class="search-bar">
  <div class="container">
    <div class="d-flex align-items-center justify-content-between">
      <p class="location">마포구</p>
      <input type="text" class="search-input" placeholder="찾는 메뉴가 뭐에요?">
      <a href="${path}/customer/signup.do" class="btn-search">로그아웃</a>
    </div>
  </div>
</section>

<!-- Action Buttons Section -->
<section class="action-buttons">
  <button class="btn-action" id="moveOrderList">주문하기</button>
  <button class="btn-action" id="moveDeliveryStatus">배달현황</button>
</section>

<!-- Categories -->
<section class="category-section">
  <div class="container">
    <div class="category-grid">
      <button type="submit" class="category-item" value="1" id="moveToChicken">
        <img src="${path}/projectImg/chicken_state.png" alt="치킨" class="static-img">
        <img src="${path}/projectImg/chicken_ani.gif" alt="치킨" class="animated-img">
        <p class="category-text">치킨</p>
      </button>
      <button class="category-item" value="2" id="moveToChinese">
        <img src="${path}/projectImg/chinese_state.png" alt="중식" class="static-img">
        <img src="${path}/projectImg/chinese_ani.gif" alt="중식" class="animated-img">
        <p class="category-text">중식</p>
      </button>
      <button class="category-item" value="3" id="moveToDonkazhu">
        <img src="${path}/projectImg/donkazhu_state.png" alt="돈까스" class="static-img">
        <img src="${path}/projectImg/donkazhu_ani.gif" alt="돈까스" class="animated-img">
        <p class="category-text">돈까스</p>
      </button>
      <button class="category-item" value="4" id="moveToPizza">
        <img src="${path}/projectImg/pizza_state.png" alt="피자" class="static-img">
        <img src="${path}/projectImg/pizza_ani.gif" alt="피자" class="animated-img">
        <p class="category-text">피자</p>
      </button>
      <button class="category-item" value="5" id="moveToSushi">
        <img src="${path}/projectImg/sushi_state.png" alt="회" class="static-img">
        <img src="${path}/projectImg/sushi_ani.gif" alt="회" class="animated-img">
        <p class="category-text">회</p>
      </button>
      <button class="category-item" value="6" id="moveToMela">
        <img src="${path}/projectImg/mela_state.png" alt="한식" class="static-img">
        <img src="${path}/projectImg/mela_ani.gif" alt="한식" class="animated-img">
        <p class="category-text">한식</p>
      </button>
      <button class="category-item" value="7" id="moveToBeef">
        <img src="${path}/projectImg/beef_state.png" alt="족발" class="static-img">
        <img src="${path}/projectImg/beef_ani.gif" alt="족발" class="animated-img">
        <p class="category-text">족발</p>
      </button>
      <button class="category-item" value="8" id="moveToDessert">
        <img src="${path}/projectImg/dessert_state.png" alt="디저트" class="static-img">
        <img src="${path}/projectImg/dessert_ani.gif" alt="디저트" class="animated-img">
        <p class="category-text">디저트</p>
      </button>
      <button class="category-item" value="9" id="moveToSideMeal">
        <img src="${path}/projectImg/side_meal_state.png" alt="분식" class="static-img">
        <img src="${path}/projectImg/side_meal_ani.gif" alt="분식" class="animated-img">
        <p class="category-text">분식</p>
      </button>
      <button class="category-item" value="10" id="moveToCoffee">
        <img src="${path}/projectImg/coffee_state.png" alt="카페" class="static-img">
        <img src="${path}/projectImg/coffee_ani.gif" alt="카페" class="animated-img">
        <p class="category-text">카페</p>
      </button>
    </div>
  </div>
</section>
  <script type="text/javascript" src="${path}/util/categoryScript.js"></script>
</body>
</html>
</body>
</html>