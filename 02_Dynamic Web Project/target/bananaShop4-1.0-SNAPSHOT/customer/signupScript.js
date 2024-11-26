$(() => {
  function preventDefault(e) {
    e.preventDefault();
  }

  function disableScroll() {
    document.body.style.overflow = 'hidden'; // PC 스크롤 비활성화
    document.addEventListener('touchmove', preventDefault, {passive: false}); // 모바일 터치 스크롤 비활성화
  }

  const contextPath = document.querySelector('meta[name="contextPath"]').getAttribute('content');
  $('#confirm-button').on('click', function () {
    // Check which radio button is selected
    const customerSelected = $('#customer-member').is(':checked');
    const ownerSelected = $('#owner-member').is(':checked');

    if (customerSelected) {
      sessionStorage.removeItem("userId");
      $("#userId").val("");
      window.location.href = contextPath + '/customer/signup.do';
    }
    if (ownerSelected) {
      sessionStorage.removeItem("userId")
      $("#userId").val("");
      window.location.href = contextPath + '/owner/signup.do';
    }
  });

  $("#userId, #userPassword").on("keyup", activeLoginButton)

  function activeLoginButton() {
    let userIdLength = $("#userId").val().length;
    let userPassword = $("#userPassword").val().length;

    if (userIdLength > 0 && userPassword > 0) {
      $("#loginButton").removeAttr("disabled");
    } else {
      $("#loginButton").attr("disabled", "disabled");
    }
  }

  let isDuplicateChecked = false; // 중복 체크 여부
  let isDuplicate = false; // 중복 여부 결과

  $("#duplicateCheckButton").on("click", function () {
    let email = $("#signUpEmail").val();

    if (email.trim().length === 0) {
      alert("이메일을 입력하세요!");
      return;
    }

    $.ajax({
      url: contextPath + "/CheckDuplicate.do",
      method: "post",
      data: {
        email: email,
        authority: "customer"
      },
      success: function (response) {
        isDuplicateChecked = true; // 중복 체크 수행됨
        isDuplicate = response.isDuplicate;

        if (isDuplicate) {
          // 중복된 경우
          $("#duplicateCheckButton")
            .text("중복됨")
            .removeClass("btn-transparent")
            .removeClass("btn-successDup")
            .addClass("btn-dangerDup");
          // alert("이미 사용 중인 이메일입니다.");
        } else {
          // 중복되지 않은 경우
          $("#duplicateCheckButton")
            .text("확인완료")
            .removeClass("btn-transparent")
            .removeClass("btn-dangerDup")
            .addClass("btn-successDup");
        }

        // 중복 확인 후 버튼 상태 확인
        checkFieldsAndEnableSignupButton();
      },
      error: function (err) {
        console.log(err);
        alert("중복 확인 요청 중 오류가 발생했습니다.");
      },
    });
  });

// 모든 필드 및 중복 체크 상태 확인
  function checkFieldsAndEnableSignupButton() {
    let signUpEmailLength = $("#signUpEmail").val().trim().length;
    let signUpPasswordLength = $("#signUpPassword").val().trim().length;
    let signUpNameLength = $("#signUpName").val().trim().length;
    let signUpNicknameLength = $("#signUpNickname").val().trim().length;

    // 모든 필드가 채워져 있고 중복 체크가 완료되었으며, 중복이 없는 경우에만 활성화
    if (
      signUpEmailLength > 0 &&
      signUpPasswordLength > 0 &&
      signUpNameLength > 0 &&
      signUpNicknameLength > 0 &&
      isDuplicateChecked &&
      !isDuplicate
    ) {
      $("#signup-button").removeAttr("disabled");
    } else {
      $("#signup-button").attr("disabled", "disabled");
    }
  }

// 입력 필드 변경 시 상태 확인
  $("#signUpEmail, #signUpPassword, #signUpName, #signUpNickname").on(
    "keyup",
    checkFieldsAndEnableSignupButton
  );

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

  // $("#signUpEmail").on("keyup", isEmptyFocusRing)
  //
  // function isEmptyFocusRing() {
  //   let userEmailVal = $("#signUpEmail").val();
  //
  //   if (!(userEmailVal.contain("@") && userEmailVal.contain("."))) {
  //     $("#signUpEmail").attr("input.list-group-item:focus:outline", "red")
  //   }
  // }

  $(".find_password").on("click", function (event) {
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

  // $('#signUpEmail').on("keyup", emailValidationAjax)
  //
  // function emailValidationAjax() {
  //   let email = $(this).val();
  //
  //   if (email.length > 0) {
  //     $.ajax({
  //       url: contextPath + "/check-email",
  //       method: "post",
  //       data: {email: email},
  //       success: function (response) {
  //         if (response.isDuplicate) {
  //           $("#signUpEmail").addClass('is-invalid');
  //           $("#signup-button").prop('disabled', "disabled");
  //         } else {
  //           $("#signUpEmail").removeClass('is-invalid');
  //           $("#signup-button").prop('disabled', "");
  //         }
  //       },
  //       error: function () {
  //         console.log(err)
  //       }
  //     });
  //   } else {
  //     $("#signUpEmail").removeClass('is-invalid');
  //     $("#signup-button").prop('disabled', false);
  //   }
  // }
});