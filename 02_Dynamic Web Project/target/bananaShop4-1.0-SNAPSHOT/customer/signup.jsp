<%--
  Created by IntelliJ IDEA.
  User: evan
  Date: 11/18/24
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}"></c:set>
<%--todo: customer와 서로 다른 것 모두 마이그레이션 하고, 사는 지역은 제외하기--%>
<!doctype html>
<html lang="en">
<head>
    <meta name="contextPath" content="${pageContext.request.contextPath}">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <%--font--%>
    <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>

    <%--css--%>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="signupStyle.css" rel="stylesheet"/>

    <%--script--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.js"></script>
    <script src="${path}/customer/signupScript.js"></script>

    <title>환영합니다!</title>
</head>
<body class="container-fluid align-items-center">

<!-- 별을 생성하는 여러 개의 div 요소 -->
<div class="star" style="top: 10%; left: 20%; animation-duration: 1s;"></div>
<div class="star" style="top: 40%; left: 15%; animation-duration: 1.5s;"></div>
<div class="star" style="top: 60%; left: 50%; animation-duration: 2s;"></div>
<div class="star" style="top: 80%; left: 70%; animation-duration: 1.2s;"></div>
<div class="star" style="top: 20%; left: 80%; animation-duration: 1.8s;"></div>
<div class="star" style="top: 30%; left: 25%; animation-duration: 2.2s;"></div>
<div class="star" style="top: 20%; left: 57%; animation-duration: 1s;"></div>
<div class="star" style="top: 60%; left: 32%; animation-duration: 1.5s;"></div>
<div class="star" style="top: 80%; left: 86%; animation-duration: 2s;"></div>
<div class="star" style="top: 75%; left: 43%; animation-duration: 1.2s;"></div>
<div class="star" style="top: 25%; left: 27%; animation-duration: 1.8s;"></div>
<div class="star" style="top: 15%; left: 76%; animation-duration: 2.2s;"></div>


<!-- Example Code -->
<div class="container" id="loginSection">
    <div class="row align-items-center">
        <div class="col d-flex justify-content-center">
            <c:if test="${sessionScope.isLoginFailed}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert" id="alert">
                        <%=session.getAttribute("alertMessage")%>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <c:remove var="alertMessage" scope="session"/>
                <c:remove var="isLoginFailed" scope="session"/>
            </c:if>
            <span id="admin-select" type="button" data-bs-toggle="modal"
                  data-bs-target="#staticBackdrop" class="badge shifted text-bg-light">구매자 회원</span>
            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="true" data-bs-keyboard="false"
                 tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="staticBackdropLabel">로그인할 회원을 선택해주세요</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <input type="radio" class="btn-check" name="options-outlined" id="customer-member"
                                   autocomplete="off" checked>
                            <label class="btn btn-outline-primary modal-button" for="customer-member">구매자 회원</label>

                            <input type="radio" class="btn-check" name="options-outlined" id="owner-member"
                                   autocomplete="off">
                            <label class="btn btn-outline-success modal-button" for="owner-member">판매자 회원</label>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                            <button type="button" class="btn btn-primary" id="confirm-button">확인</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card shadow-lg shifted" style="width: 25rem;">
                <div class="card-body">
                    <h5 id="welcome" class="card-title mb-4 mt-2">환영합니다!</h5>
                    <form action="${path}/customer/signup.do" method="post">
                        <div class="form-floating mb-3">
                            <input id="userId" type="email" class="form-control" name="userId"
                                   value="<%=session.getAttribute("userId") == null ? "" : session.getAttribute("userId")%>"
                                   placeholder="name@example.com">
                            <label for="userId">이메일</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input id="userPassword" type="password" class="form-control" name="userPassword"
                                   placeholder="Password">
                            <label for="userPassword">비밀번호</label>
                        </div>

                        <div class="mb-3 form-check mb-3">
                            <input type="checkbox" class="form-check-input" id="keep-login-status"
                                   name="keep-login-status" value="keepStatus"
                                <%=session.getAttribute("keepLoginStatus") != null &&
                             (boolean) session.getAttribute("keepLoginStatus") ? "checked" : ""%>>
                            <label class="form-check-label" for="keep-login-status">로그인 상태 유지</label>
                            <a href="${path}/check/findPass.do?authority=customer" class="card-link find_password">비밀번호을
                                잊으셨나요?</a>
                        </div>

                        <div class="d-grid gap-2 col-12 mx-auto">
                            <button id="loginButton" class="btn btn-outline-success" disabled="disabled" type="submit">
                                로그인
                            </button>
                        </div>
                    </form>
                </div>
                <div>
                    <ul class="find_wrap">
                        <li>
                            아직회원이 아니시라면 <a href="#signUpSection" class="card-link singin transition-link">우리의 멤버가
                            되어주세요!</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%session.removeAttribute("userId");%>

<!--회원가입 창-->
<!-- 별을 생성하는 여러 개의 div 요소 -->
<div class="star" style="top: 110%; left: 20%; animation-duration: 1s;"></div>
<div class="star" style="top: 140%; left: 15%; animation-duration: 1.5s;"></div>
<div class="star" style="top: 160%; left: 50%; animation-duration: 2s;"></div>
<div class="star" style="top: 180%; left: 70%; animation-duration: 1.2s;"></div>
<div class="star" style="top: 120%; left: 80%; animation-duration: 1.8s;"></div>
<div class="star" style="top: 130%; left: 25%; animation-duration: 2.2s;"></div>
<div class="star" style="top: 120%; left: 57%; animation-duration: 1s;"></div>
<div class="star" style="top: 160%; left: 32%; animation-duration: 1.5s;"></div>
<div class="star" style="top: 180%; left: 86%; animation-duration: 2s;"></div>
<div class="star" style="top: 175%; left: 43%; animation-duration: 1.2s;"></div>
<div class="star" style="top: 125%; left: 27%; animation-duration: 1.8s;"></div>
<div class="star" style="top: 115%; left: 76%; animation-duration: 2.2s;"></div>

<div class="container" id="signUpSection">
    <div class="row align-items-center">
        <div class="col d-flex justify-content-center">
            <div class="card shadow-lg" style="width: 25rem;">
                <div class="card-body">
                    <h5 class="card-title mb-4 mt-2">회원가입</h5>
                    <h6 class="card-title mb-1 mt-4">기본정보</h6>
                    <form action="signup.do" method="post">
                        <button type="button" id="duplicateCheckButton" class="btn btn-transparent badge-hover">중복확인</button>
                        <ul class="list-group">
                            <input id="signUpEmail" name="signUpEmail" type="email" class="list-group-item"
                                   placeholder="이메일">
                            <input id="signUpPassword" name="signUpPassword" type="password" class="list-group-item"
                                   placeholder="비밀번호">
                            <input id="signUpName" name="signUpName" type="text" class="list-group-item"
                                   placeholder="이름">
                            <input id="signUpNickname" name="signUpNickname" type="text" class="list-group-item"
                                   placeholder="닉네임">
                            <%--              <input id="signUpLocation" name="signUpLocation" type="text" class="list-group-item" placeholder="사는지역">--%>
                        </ul>
                        <h6 class="card-title mb-1 mt-4">사는지역</h6>
                        <select id="signUpLocation" name="signUpLocation" class="form-control mb-4">
                            <option>강남구</option>
                            <option>강동구</option>
                            <option>강서구</option>
                            <option>관악구</option>
                            <option>구로구</option>
                            <option>금천구</option>
                            <option>동작구</option>
                            <option>서초구</option>
                            <option>송파구</option>
                            <option>양천구</option>
                            <option>영등포구</option>
                        </select>

                        <div class="d-grid gap-2 col-12 mx-auto">
                            <button id="signup-button" disabled="disabled" class="btn btn-outline-success"
                                    type="submit">가입하기
                            </button>
                            <div class="invalid-feedback">
                                이미 사용 중인 이메일입니다.
                            </div>
                        </div>
                    </form>
                </div>
                <div>
                    <ul class="find_wrap">
                        <li>
                            이미 회원이시라면 <a href="#loginSection" class="card-link singin">로그인 해주세요!</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>