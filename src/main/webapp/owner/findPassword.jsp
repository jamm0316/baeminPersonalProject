<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}"></c:set>
<!doctype html>
<html lang="en">
<head>
  <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
  <title>Bootstrap Example</title>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.js"></script>
  <script>
    $(() => {
      function preventDefault(e) {
        e.preventDefault();
      }

      function disableScroll() {
        document.body.style.overflow = 'hidden'; // PC 스크롤 비활성화
        document.addEventListener('touchmove', preventDefault, {passive: false}); // 모바일 터치 스크롤 비활성화
      }

      $("#userId, #userName").on("keyup", activeLoginButton)

      function activeLoginButton() {
        let userIdLength = $("#userId").val().length;
        let userNameLength = $("#userName").val().length;

        if (userIdLength > 0 && userNameLength > 0) {
          $("#findPassword").removeAttr("disabled");
        } else {
          $("#findPassword").attr("disabled", "disabled");
        }
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

    .form-check-label {
      color: whitesmoke;
    }

    .find_wrap {
      display: flex;
      justify-content: center;
      gap: 10px;
      color: lightgray;
      padding-left: 0px;
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

    .card-link {
      font-size: small;
      color: lightsteelblue;
    }

    #others {
      text-align: center; /* 부모 요소의 텍스트 정렬 */
    }

    .find_wrap {
      display: inline-block;
      text-align: center; /* 텍스트를 가운데 정렬 */
      list-style: none; /* 불릿 제거 */
      padding: 0; /* 기본 패딩 제거 */
    }

    .find_wrap li {
      margin-bottom: 10px; /* 각 항목 간격 추가 */
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
          <h5 class="card-title mb-4 mt-2">회원정보를 찾아드릴게요!</h5>

          <form action="${path}/check/findPass.do" method="post">
            <div class="form-floating mb-3">
              <input id="userId" name="userId" type="email" class="form-control" placeholder="이메일">
              <label for="userId">이메일</label>
            </div>

            <div class="form-floating mb-3">
              <input id="userName" name="userName" type="text" class="form-control"
                     placeholder="이름">
              <label for="userName">이름</label>
            </div>

            <div class="d-grid gap-2 col-12 mx-auto">
              <input hidden="hidden" name="authority" value="owner"/>
              <button id="findPassword" class="btn btn-outline-success" disabled="disabled" type="submit">비밀번호 찾기
              </button>
            </div>
          </form>
        </div>
        <div id="others">
          <ul class="find_wrap">
            <li>
              이미 회원이시라면<a href="../owner/signup.do" class="card-link singin transition-link">로그인 해주세요!</a>
            </li>
            <li>
              아직회원이 아니시라면<a href="../owner/signup.do#signUpSection" class="card-link singup transition-link">우리의
              멤버가
              되어주세요!</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>