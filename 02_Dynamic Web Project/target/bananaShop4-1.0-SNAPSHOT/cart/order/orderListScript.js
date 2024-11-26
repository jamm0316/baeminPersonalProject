const contextPath = document.querySelector('meta[name="contextPath"]').getAttribute('content');
$("#order-confirm").on("click", function () {
  location.href = contextPath + "/cart/order/insertOrders.do"
});

// function confirmOrders() {
//   const orderId = $(this).data("order-id");
//
//   $.ajax({
//     url: contextPath + "/confirmOrder.do",
//     method: "POST",
//     data: {orderId},
//     success: function () {
//       alert("주문이 확인되었습니다.");
//       // 최신 주문 목록 다시 불러오기
//       updateOrderStatus();
//     },
//     error: function (err) {
//       console.error("주문 확인 실패:", err);
//     }
//   });
// }

function updateOrderStatus() {
  $.ajax({
    url: contextPath + "/orderListUpdate.do",
    method: "GET",
    data: {orderList: "${orderList}"},
    dataType: "json",
    success: function (data) {
      const orderStatus = $("#order-status .panel-list");
      orderStatus.empty();

      if (data.length > 0) {
        data.forEach(order => {
          const orderHtml = `
              <li>
                <div class="order-info">
                  <div class="order-summary">
                    <span class="order-info-title">
                      <em>신규주문</em>
                    </span>
                    <span class="number-area">
                      <a>${data.length}</a>
                      <span>건</span>
                    </span>
                  </div>
                  <div class="each-order">
                    <span class="each-info">${order.storeName}, ${order.foodName}, ${order.location}</span>
                    <button class="btn btn-success order-confirm" data-order-id="${order.id}">주문확인</button>
                  </div>
                </div>
              </li>`;
          orderStatus.append(orderHtml);
        });
      } else {
        orderStatus.html("<p>현재 주문이 없습니다.</p>");
      }
    },
    error: function (err) {
      console.error("주문 데이터를 가져오는 데 실패했습니다:", err);
    }
  });
}

// "주문하기" 버튼 클릭 시 주문 목록 업데이트
$("#order-confirm").on("click", function () {
  updateOrderStatus();
});

// 페이지 로드 시 최신 주문 상태 업데이트
updateOrderStatus();