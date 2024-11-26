$(() => {
  const contextPath = document.querySelector('meta[name="contextPath"]').getAttribute('content');
  //가제 관리/수정 페이지 조회
  // $("#store-management").on("click", viewStoreManagement)
  // function viewStoreManagement() {
  //   $.ajax({
  //     url: contextPath + "/ownerDashboard/storeManagement.do",
  //     type: "get",
  //     success: function (data) {
  //       $("#seller-sub-content").html(data)
  //     },
  //     error: function (err) {
  //       alert(err)
  //     }
  //   });
  // }

  $(".btn-close").on("click", refresh)

  function refresh() {
    setTimeout(function () {
      location.reload(); // 페이지 새로고침
    }, 500); // 1000ms = 1초
  }

  $("#store-management").on("click", moveStoreBoard);

  function moveStoreBoard() {
    location.href = contextPath + "/ownerDashboard/storeManagement.do"
  }

  $("#food-management").on("click", moveFoodBoard);

  function moveFoodBoard() {
    location.href = contextPath + "/ownerDashboard/foodManagement.do"
  }

  $(".sellerShop").on("click", moveMainBoard);

  function moveMainBoard() {
    location.href = contextPath + "/ownerDashboard/mainBoard.do"
  }

  $("#open-delete-modal").on("click", function () {
    let storeId = $(this).data("id");
    let storeName = $(this).data("name");

    $("#modal-store-delete-info").text("정말 '" + storeName + "'을 삭제하시겠습니까?");
    $("#confirm-delete").data("id", storeId)
  });

  $("#confirm-delete").on("click", deleteStore)

  function deleteStore() {
    let storeId = $(this).data("id");
    $.ajax({
      url: contextPath + "/deleteStore.do",
      type: "get",
      data: {"storeId": storeId},
      success: function () {
        location.href = contextPath + "/ownerDashboard/storeManagement.do"
      },
      error: function (err) {
        console.log(err)
      }
    });
  }

//버튼 -> 모달 -> 팝오버 데이터 전송
  document.querySelector("#foodSelectButton").addEventListener("show.bs.modal", function (event) {
    // 이벤트를 트리거한 요소 가져오기
    const button = event.relatedTarget;

    // data-id 속성 확인 및 가져오기
    if (button) {
      const storeId = button.getAttribute('data-id');
      if (storeId) {
        $("#food-insert-storeId").val(storeId);
      } else {
        console.error("data-id 속성이 없습니다.");
      }
    }
  });

// 모달 닫힘 이벤트에 backdrop 제거 코드 추가
  function closeModal() {
    $('#foodSelectButton').on('hidden.bs.modal', function () {
      console.log("모달 닫힘 - backdrop 제거 확인");
      $('.modal-backdrop').remove(); // backdrop 요소 제거
      $('body').removeClass('modal-open'); // 모달 열림 상태 제거
      $('body').css('overflow', 'auto'); // 스크롤 복구
    });
  }

//1. 음식등록
// 1-1. 팝오버 표시
  $('#food-insert-form').on('click', function (e) {
    e.preventDefault();
    const modalElement = $('#foodSelectButton');
    const popoverForm = $('#popoverInsertForm');
    // 팝오버 폼 표시
    popoverForm.removeClass('hidden');
  });

//1-2. 팝오버 닫기
  $('#close-popover').on('click', function () {
    $("#popoverInsertForm").addClass('hidden'); // 팝오버 숨기기
  });
  const popoverForm = document.getElementById('popoverInsertForm');
  const foodInsertButton = document.getElementById('food-insert-form');
  const cancelPopoverButton = document.getElementById('cancel-insert-Popover');
  const foodInsertForm = document.getElementById('foodInsertForm');

//1-3. 등록 버튼 클릭 시 팝오버 표시
  foodInsertButton.addEventListener('click', function () {
    popoverForm.classList.remove('hidden'); // 팝오버 표시
  });

//1-4. 취소 버튼 클릭 시 팝오버 숨김
  cancelPopoverButton.addEventListener('click', function () {
    popoverForm.classList.add('hidden'); // 팝오버 숨김
  });

//1-5. 음식 등록 로직
  $("#food-insert-button").off("click").on("click", function (e) {
    e.preventDefault();

    const formData = $("#foodInsertForm").serialize();
    const modalElement = $("#foodSelectButton");
    const modal = bootstrap.Modal.getOrCreateInstance(modalElement);

    // 버튼 비활성화
    $(this).attr("disabled", true);

    $.ajax({
      url: contextPath + "/insertFood.do",
      method: "get",
      data: formData,
      dataType: "html",
      success: function (html) {
        $("#foodSelectButton .modal-body").html(html);
        // setTimeout(function () {
        //   location.reload();
        // }, 300);

        setTimeout(function () {
          // modal.show();
        }, 1000);

      },
      error: function (err) {
        console.error("AJAX Error:", err);
        alert("음식을 등록하지 못했습니다.");
      },
      complete: function () {
        // 요청 완료 후 버튼 다시 활성화
        $("#food-insert-button").attr("disabled", false);
      }
    });
  });

// //3. 음식수정
// $(document).on("click", "#food-select-each-button", function () {
//   const foodId = $(this).data("id");
//   const foodName = $(this).data("name");
//   const foodPrice = $(this).data("price");
//   const foodMaxCookingTime = $(this).data("maxCookingTime");
//
//   console.log("음식 수정 데이터:", { foodId, foodName, foodPrice, foodMaxCookingTime });
//
//   // 수정 폼에 데이터 설정
//   $("#food-edit-id").val(foodId);
//   $("#food-edit-name").val(foodName);
//   $("#food-edit-price").val(foodPrice);
//   $("#food-edit-max-cooking-time").val(foodMaxCookingTime);
//
//   // 팝오버 표시
//   $("#popoverEditForm").removeClass("hidden");
// });
//
// // 수정 팝오버 닫기
// $("#cancel-edit-Popover").on("click", function () {
//   $("#popoverEditForm").addClass("hidden");
// });

// popoverForm = document.getElementById('popoverForm');
// foodInsertButton = document.getElementById('food-insert-form');
// cancelPopoverButton = document.getElementById('cancelPopover');
// foodInsertForm = document.getElementById('foodInsertForm');
//
// //1-3. 등록 버튼 클릭 시 팝오버 표시
// foodInsertButton.addEventListener('click', function () {
//   popoverForm.classList.remove('hidden'); // 팝오버 표시
// });
//
// //1-4. 취소 버튼 클릭 시 팝오버 숨김
// cancelPopoverButton.addEventListener('click', function () {
//   popoverForm.classList.add('hidden'); // 팝오버 숨김
// });
//
// //1-5. 음식 등록 로직
// 음식 수정 폼 제출
// $("#food-edit-button").on("click", function (e) {
//   e.preventDefault();
//   const formData = $("#foodEditForm").serialize();
//
//   console.log("수정 요청 데이터:", formData);
//
//   $.ajax({
//     url: contextPath + "/updateFood.do",
//     method: "POST", // 수정에는 POST 사용
//     data: formData,
//     success: function (response) {
//       console.log("수정 성공:", response);
//
//       // 팝오버 숨기기
//       $("#popoverEditForm").addClass("hidden");
//
//       // 음식 목록 새로고침 또는 UI 업데이트
//       $("#foodSelectButton .modal-body").html(response);
//     },
//     error: function (err) {
//       console.error("수정 실패:", err);
//     }
//   });
// });

//2. 음식조회 버튼
  $(".food-select").on("click", selectFoods);

  function selectFoods() {
    let storeId = $(this).data("id");
    let modalElement = $("#foodSelectButton");
    let modal = bootstrap.Modal.getOrCreateInstance(modalElement); // 기존 모달 인스턴스 가져오기

    $.ajax({
      url: contextPath + "/selectFood.do",
      method: "get",
      data: {"storeId": storeId},
      dataType: "html",
      success: function (html) {
        $("#foodSelectButton .modal-body").html(html)
        modal.show();
      },
      error: function (err) {
        console.log(err);
      }
    }).always(function () {
      closeModal();
    });
  }

//3. 음식 삭제버튼
  $(document).off("click", ".food-delete-button").on("click", ".food-delete-button", function () {
    const foodId = $(this).data("id"); // 삭제할 음식 ID
    const storeId = $(this).data("store-id"); // 현재 가게 ID 가져오기

    console.log(foodId);
    console.log(storeId);

    // AJAX 요청: 음식 삭제
    $.ajax({
      url: contextPath + "/deleteFood.do",
      type: "POST",
      data: {
        "foodId": foodId,
        "storeId": storeId
      },
      success: function (html) {
        // UI 업데이트: 음식 목록 새로고침
        const menuList = $(".menu-list");
        menuList.html(html);
        console.log("음식이 삭제되었습니다.");
        // location.reload();
        $.ajax({
          url: contextPath + "/selectFood.do",
          method: "get",
          data: {"storeId": storeId},
          dataType: "html",
          success: function (html) {
            $("#foodSelectButton .modal-body").html(html)
            modal.show();
          },
          error: function (err) {
            console.log(err);
          }
        }).always(function () {
          closeModal();
        });
      },
      error: function (err) {
        console.error("삭제 실패:", err);
      }
    });
  });

  $('#storeEditButton').on('hidden.bs.modal', function () {
    $("#store-edit-name").val(""); // 텍스트 필드 초기화
    $("#store-edit-area").val("").change();
    $("#store-edit-category").val("").change();
  });

  $("#refreshBoard").on("click", function () {
    location.href = contextPath + "/orderListUpdate.do";
  });
});
