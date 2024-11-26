$(document).ready(function () {
  const contextPath = document.querySelector('meta[name="contextPath"]').getAttribute('content');

  // Store item click
  $(".store-item").on("click", function () {
    const storeId = $(this).data("store-id");

    // Show menu section
    $(".menu-section").removeClass("hidden");
    $(".menu-section").css("display", "block"); // 명시적으로 display 설정

    // Set store name in menu section
    $("#store-name").text(`음식 메뉴`);
    // Fetch food items via AJAX (example request)
    $.ajax({
      url: contextPath + `/util/detail/foodSelect.do`,
      method: "GET",
      data: { storeId },
      dataType: "json",
      success: function (data) {
        console.log("recieve:" + data);
        const menuList = $(".menu-list");
        menuList.empty(); // 기존 메뉴 리스트 초기화

        if (data.length > 0) {
          // 데이터가 있을 경우 추가
          let menuItem = ""
          data.forEach(food => {
            console.log(food.name);
            menuItem += `
              <li class="menu-item">
                <div class="food-info">
                  <div class="food-name">${food.name}</div>
                  <div class="food-price">₩${food.price}</div>
                </div>
                <button class="btn btn-primary add-to-cart" data-id="${food.foodId}">장바구니 담기</button>
              </li>`;
          });
          console.log(menuItem);
          menuList.append(menuItem);
        } else {
          // 데이터가 없을 경우 메시지 표시
          menuList.html("<p>메뉴가 없습니다.</p>");
        }
      },
      error: function (err) {
        console.error("AJAX Error:", err);
        alert("메뉴를 불러오지 못했습니다.");
      }
    });
  });

  let selectedFoodId = null;

  // 장바구니 담기 버튼 클릭 이벤트
  $(document).on("click", ".add-to-cart", function () {
    selectedFoodId = $(this).data("id"); // 선택된 음식 ID 저장
    console.log(selectedFoodId);
    $("#quantityModal").modal("show"); // 모달 표시
  });

  // 모달에서 추가 버튼 클릭 이벤트
  $("#addToCartConfirm").on("click", function () {
    const quantity = $("#foodQuantity").val(); // 수량 가져오기

    if (quantity < 1) {
      alert("수량은 1 이상이어야 합니다.");
      return;
    }
    console.log(quantity);
    console.log(selectedFoodId);

    // AJAX 요청으로 서버에 데이터 전송 (예: 장바구니에 추가)
    $.ajax({
      url: contextPath +`/cart/addItem.do`,
      method: "POST",
      data: {
        foodId: selectedFoodId,
        quantity: quantity
      },
      success: function (response) {
        $("#quantityModal").modal("hide"); // 모달 닫기
      },
      error: function (err) {
        console.error("AJAX Error:", err);
        alert("장바구니 추가에 실패했습니다.");
      }
    });
  });
});