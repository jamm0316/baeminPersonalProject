const contextPath = document.querySelector('meta[name="contextPath"]').getAttribute('content');
$("#moveToChicken").on("click", moveToChicken);
function moveToChicken() {
  let categoryId = $("#moveToChicken").val();
  console.log(categoryId);
  location.href = contextPath + "/util/detail/categorySelect.do?categoryId=" + categoryId
}

$("#moveToChinese").on("click", moveToChinese);
function moveToChinese() {
  let categoryId = $("#moveToChinese").val();
  console.log(categoryId);
  location.href = contextPath + "/util/detail/categorySelect.do?categoryId=" + categoryId
}

$("#moveToDonkazhu").on("click", moveToDonkazhu);
function moveToDonkazhu() {
  let categoryId = $("#moveToDonkazhu").val();
  console.log(categoryId);
  location.href = contextPath + "/util/detail/categorySelect.do?categoryId=" + categoryId
}

$("#moveToPizza").on("click", moveToPizza);
function moveToPizza() {
  let categoryId = $("#moveToPizza").val();
  console.log(categoryId);
  location.href = contextPath + "/util/detail/categorySelect.do?categoryId=" + categoryId
}

$("#moveToSushi").on("click", moveToSushi);
function moveToSushi() {
  let categoryId = $("#moveToSushi").val();
  console.log(categoryId);
  location.href = contextPath + "/util/detail/categorySelect.do?categoryId=" + categoryId
}

$("#moveToMela").on("click", moveToMela);
function moveToMela() {
  let categoryId = $("#moveToMela").val();
  console.log(categoryId);
  location.href = contextPath + "/util/detail/categorySelect.do?categoryId=" + categoryId
}

$("#moveToBeef").on("click", moveToBeef);
function moveToBeef() {
  let categoryId = $("#moveToBeef").val();
  console.log(categoryId);
  location.href = contextPath + "/util/detail/categorySelect.do?categoryId=" + categoryId
}

$("#moveToDessert").on("click", moveToDessert);
function moveToDessert() {
  let categoryId = $("#moveToDessert").val();
  console.log(categoryId);
  location.href = contextPath + "/util/detail/categorySelect.do?categoryId=" + categoryId
}

$("#moveToSideMeal").on("click", moveToSideMeal);
function moveToSideMeal() {
  let categoryId = $("#moveToSideMeal").val();
  console.log(categoryId);
  location.href = contextPath + "/util/detail/categorySelect.do?categoryId=" + categoryId
}

$("#moveToCoffee").on("click", moveToCoffee);
function moveToCoffee() {
  let categoryId = $("#moveToCoffee").val();
  console.log(categoryId);
  location.href = contextPath + "/util/detail/categorySelect.do?categoryId=" + categoryId
}

$("#moveOrderList").on("click", moveOrderList);
function moveOrderList() {
  location.href = contextPath + "/cart/order/orderList.do"
}

$("#moveDeliveryStatus").on("click", moveDeliveryStatus);
function moveDeliveryStatus() {
  location.href = contextPath + "/delivery/deliveryStatus.do";
}