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

  // todo: 음식지우기
  // $("#open-delete-modal").on("click", function () {
  //   let storeId = $(this).data("id");
  //   let storeName = $(this).data("name");
  //
  //   $("#modal-store-delete-info").text("정말 '" + storeName + "'을 삭제하시겠습니까?");
  //   $("#confirm-delete").data("id", storeId)
  // });
  //
  // $("#confirm-delete").on("click", deleteStore)
  //
  // function deleteStore() {
  //   let storeId = $(this).data("id");
  //   $.ajax({
  //     url: contextPath + "/deleteStore.do",
  //     type: "get",
  //     data: {"storeId": storeId},
  //     success: function () {
  //       location.href = contextPath + "/ownerDashboard/storeManagement.do"
  //     },
  //     error: function (err) {
  //       console.log(err)
  //     }
  //   });
  // }


  //여기 스크립트 있었음. storefoodScript//////////////////////////////////////

  // 새로고침 버튼 클릭 시 주문 목록 가져오기
  $('#refreshBoard').on('click', function () {
    $.ajax({
      url: contextPath + '/orderListUpdate.do',
      method: 'GET',
      dataType: 'json',
      success: function (data) {
        console.log('AJAX 요청 성공:', data);
        console.log('받은 데이터 길이:', data.length);

        const orderList = $('#order-list');
        orderList.empty(); // 기존 목록 초기화

        if (data.length > 0) {
          data.forEach((order, index) => {
            console.log(`데이터 ${index + 1}:`, order);
            const orderItem = `
              <div class="order-item">
                <div class="order-item-info">
                  <span><strong>가게:</strong> ${order.storeName}</span>
                  <span><strong>음식:</strong> ${order.foodName}</span>
                  <span><strong>개수:</strong> ${order.quntity}</span>
                  <span><strong>예상 조리시간:</strong> ${order.deliveryTime}분</span>
                  <span><strong>가격:</strong> ₩${order.price}</span>
                  <span><strong>주문 상태:</strong> ${order.status}</span>
                </div>
                <div class="order-actions">
                  <button class="btn btn-success order-confirm" data-id="${order.id}" value="주문확인">주문확인</button>
                  <button class="btn btn-warning order-delivering" data-id="${order.id}" value="배달중">배달중</button>
                  <button class="btn btn-info order-complete" data-id="${order.id}" value="배달완료">배달완료</button>
                </div>
              </div>`;
            orderList.append(orderItem);
          });
        } else {
          orderList.html('<p>주문이 없습니다.</p>');
        }
      },
      error: function (err) {
        console.error('주문 목록 가져오기 실패:', err);
        alert('주문 목록을 가져오지 못했습니다.');
      }
    });
  });

  $('#order-list').on('click', 'button', function () {
    const orderId = $(this).data('id'); // 버튼에 있는 데이터 속성에서 주문 ID 가져오기
    const status = $(this).val();      // 버튼의 값에서 상태 가져오기

    // AJAX 요청 보내기
    $.ajax({
      url: contextPath + '/ownerDashboard/updateOrder.do', // 서버 서블릿 URL
      method: 'GET',
      data: { id: orderId, status: status }, // 전달할 데이터
      success: function (response) {
        $('#refreshBoard').click(); // 기존 새로고침 버튼 클릭 이벤트 호출
      },
      error: function (err) {
      }
    });
  });

  // 새로고침 버튼 자동 트리거 (페이지 로드 시 주문 목록 출력)
  $(document).ready(function () {
    $('#refreshBoard').trigger('click');
  });

  // 각 버튼 이벤트 처리
  $(document).on('click', '.order-confirm, .order-delivering, .order-complete', function () {
    const orderId = $(this).data('order-id');
    const action = $(this).text(); // 버튼 텍스트로 액션 구분
    console.log(`주문 ID: ${orderId}, 액션: ${action}`);

    $.ajax({
      url: contextPath + '/ownerDashboard/updateOrder.do',
      method: 'POST',
      data: {
        orderId: orderId,
        status: action
      },
      success: function () {
        alert(`주문 상태가 '${action}'로 업데이트되었습니다.`);
        $('#refreshBoard').trigger('click'); // 상태 변경 후 새로고침
      },
      error: function (err) {
        console.error('주문 상태 업데이트 실패:', err);
        alert('주문 상태 업데이트에 실패했습니다.');
      }
    });
  });

})
;