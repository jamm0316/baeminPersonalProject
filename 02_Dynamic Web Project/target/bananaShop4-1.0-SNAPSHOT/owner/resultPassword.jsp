<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}"></c:set>
<!doctype html>
<html lang="en">
<head>
    <%--metaData--%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="contextPath" content="${pageContext.request.contextPath}">

    <%--font--%>
    <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>

    <%--css--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">

    <%--script--%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.js"></script>

    <title>비밀스럽게 찾아드립니다.</title>
    <script>
      $(() => {
        function preventDefault(e) {
          e.preventDefault();
        }

        const contextPath = document.querySelector('meta[name="contextPath"]').getAttribute('content');

        function disableScroll() {
          document.body.style.overflow = 'hidden'; // PC 스크롤 비활성화
          document.addEventListener('touchmove', preventDefault, {passive: false}); // 모바일 터치 스크롤 비활성화
        }

        $("a[href='#signUpSection']").on("click", function (event) {
          event.preventDefault();

          $("html, body").animate({
            scrollTop: $("#signUpSection").offset().top,
          }, 100); // 1.5초 애니메이션
        });

        $("a[href='#loginSection']").on("click", function (event) {
          event.preventDefault();

          $("html, body").animate({
            scrollTop: $("#loginSection").offset().top,
          }, 100); // 1.5초 애니메이션
        });

        $("#signUpEmail").on("keyup", isEmptyFocusRing)

        function isEmptyFocusRing() {
          let userEmailVal = $("#signUpEmail").val();

          if (!(userEmailVal.contain("@") && userEmailVal.contain("."))) {
            $("#signUpEmail").attr("input.list-group-item:focus:outline", "red")
          }
        }

        $(".transition-link").on("click", function (event) {
          event.preventDefault(); // 기본 동작(페이지 이동)을 막음

          const targetUrl = $(this).attr("href"); // 링크의 href 속성에서 URL 가져오기

          // 페이드 아웃 효과
          $("body").fadeOut(1000, function () {
            window.location.href = targetUrl; // 페이지 이동
          });
        });

        // 페이지 로드 시 페이드 인 효과
        $("body").hide().fadeIn(1000);

        setTimeout(() => {
          const alertElement = document.querySelector("#alert");
          const cardElement = document.querySelector(".card"); // 로그인 카드 선택
          const badgeElement = document.querySelector(".badge"); // 로그인 카드 선택

          if (alertElement != null) {
            alertElement.classList.remove("show");
            alertElement.classList.add("fade");

            // 500ms 후 alert 제거
            setTimeout(() => {
              alertElement.remove();

              // 로그인 카드 원래 위치로 복귀
              if (cardElement != null) {
                cardElement.classList.remove("shifted");
                badgeElement.classList.remove("shifted");
              }
            }, 300);
          }
        }, 3000);

        $("#goToLogin").on("click", function () {
          window.location.href = contextPath + "/owner/signup.do"
        });

        $("#goToSignUp").on("click", function () {
          window.location.href = contextPath + "/owner/signup.do#signUpSection"
        });

        $("#reFindPass").on("click", function () {
          window.location.href = contextPath + "/check/findPass.do?authority=owner"
        });
      });
    </script>

    <style>
      * {
        font-family: 'Spoqa Han Sans Neo', 'sans-serif';
      }

      body {
        /*overflow: hidden; !* 스크롤 비활성화 *!*/
        background: linear-gradient(135deg, #1d006b, #3a0091, #0a0023);
        background-size: cover;
        background-attachment: fixed;
        color: #ffffff;
        touch-action: manipulation; /* 확대 및 추가 제스처 비활성화 */
      }

      @keyframes scrollBackground {
        0% {
          background-position: 0% 0%;
        }
        100% {
          background-position: 100% 100%;
        }
      }

      /* 별 스타일 */
      .star {
        position: absolute;
        width: 2px;
        height: 2px;
        background: white;
        border-radius: 50%;
        opacity: 0.8;
        animation: twinkling 2s infinite ease-in-out, flying 5s linear infinite;
        box-shadow: 0 0 6px 2px rgba(255, 255, 255, 0.5);
      }

      /* 꼬리를 표현하기 위한 애니메이션 */
      @keyframes twinkling {
        0%, 100% {
          opacity: 0.8;
        }
        50% {
          opacity: 0.2;
        }
      }

      /* 별들이 앞으로 날아오는 효과 */
      @keyframes flying {
        from {
          transform: translateZ(0) scale(1);
          opacity: 1;
        }

        to {
          transform: translateZ(500px) scale(2); /* 점점 커지면서 다가오는 효과 */
          opacity: 0;
        }
      }

      /* Glassmorphism 카드 */
      .container {
        display: grid;
        height: 100vh;
      }

      .card {
        background: rgba(255, 255, 255, 0.2);
        border-radius: 15px;
        backdrop-filter: blur(15px);
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        border: 1px solid rgba(255, 255, 255, 0.3);
      }

      .card-title, .card-link, button {
        color: #ffffff;
      }

      label {
        color: dimgray;
      }

      .find_wrap li {
        font-size: small;
        list-style-type: none;
      }

      .card-link:hover {
        color: greenyellow;
      }

      .btn-outline-success {
        height: 50px;
        border-color: greenyellow;
        color: #ffffff;
      }

      .btn-outline-success:hover {
        background-color: #a6fa23;
        color: black;
      }

      .find_wrap li {
        margin-bottom: 10px; /* 각 항목 간격 추가 */
      }

      /* 공통 스타일 */
      h6 {
        font-size: 24px;
        font-weight: bold;
        text-align: center;
        animation: galaxy-wave 4s infinite linear, wave-motion 2.5s infinite ease-in-out;
        background-image: linear-gradient(90deg, indianred, indigo, royalblue, indigo);
        background-size: 400%;
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
      }

      /* 색상 변화 애니메이션 */
      @keyframes galaxy-wave {
        0% {
          background-position: 0%;
        }
        100% {
          background-position: 100%;
        }
      }
    </style>
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
            <div class="card shadow-lg" style="width: 25rem;">
                <div class="card-body">
                    <c:if test="${found}">
                        <h5 class="card-title mb-4 mt-2">${alertMessage}</h5>
                        <p>
                        <button class="btn btn-primary" type="button" data-bs-toggle="collapse"
                                data-bs-target="#passHeat" aria-expanded="false"
                                aria-controls="passHeat">
                            비밀스럽게 확인하기
                        </button>
                    </p>
                    <div style="min-height: 120px;">
                        <div class="collapse collapse-horizontal" id="passHeat">
                            <div class="card card-body" style="width: 365px;">
                                <h6>${userId}</h6>
                                <h6>${userPassword}</h6>
                                <div class="d-grid gap-2 col-12 mx-auto">
                                    <button id="goToLogin" class="btn btn-outline-success" type="button">로그인 하러가기
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                        <c:remove var="userId" scope="session"/>
                        <c:remove var="userPassword" scope="session"/>
                        <c:remove var="found" scope="session"/>
                        <c:remove var="notFound" scope="session"/>
                        <c:remove var="alertMessage" scope="session"/>
                    </c:if>
                    <c:if test="${notFound}">
                        <h5 class="card-title mb-4 mt-2">${alertMessage}</h5>
                        <p>
                            <button class="btn btn-primary" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#passMiss" aria-expanded="false"
                                    aria-controls="passMiss">
                                그러나 이런방법이 있답니다!
                            </button>
                        </p>
                        <div style="min-height: 120px;">
                            <div class="collapse collapse-horizontal" id="passMiss">
                                <div class="card card-body" style="width: 365px;">
                                    <button id="goToSignUp" class="btn btn-outline-success mb-4" type="button">회원가입 하러가기
                                    </button>
                                    <button id="reFindPass" class="btn btn-outline-success" type="button">다시 찾아보기
                                    </button>
                                </div>
                            </div>
                        </div>
                        <c:remove var="found" scope="session"/>
                        <c:remove var="notFound" scope="session"/>
                        <c:remove var="alertMessage" scope="session"/>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>