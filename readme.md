<div>
<img src="https://github.com/user-attachments/assets/47ff9d4d-0a45-4ee4-957d-f4b8c975dbc1" width="50" height="50" alt="배달의민족로고"/>  
<span><h1>배달의 민족 배달서비스</h1></span>
</div>
바로 주문, 배로 배달, 현황 확인까지 한번에!
Project Introduce in Canava

<h2>Authors</h2>


<h2>Tech Stack</h2>
<p>Client: Javascript, HTML, CSS</p>
<p></p>Server: Java, JSP</p>
<p></p>Database: Oracle</p>

<h2>Development Environment</h2>
<code>IDE</code> IntelliJ Altimate
<code>JDK</code> 21
<code>OS</code> mac

<h2>Archetecture</h2>

<h2>Database</h2>

<h2>Directory Structure</h2>
<h3>java</h3>
<pre>
java
├── DBUtil
│   ├── DBUtil.java
│   └── ParseUtil.java
├── com
│   └── delivery
│       ├── controller
│       │   ├── EmailCheckServlet.java
│       │   ├── FindPasswordServlet.java
│       │   ├── category
│       │   │   ├── CategoryServlet.java
│       │   │   ├── SelectCategoryServlet.java
│       │   │   └── SelectFoodsServlet.java
│       │   ├── customer
│       │   │   └── CustomerSignupServlet.java
│       │   ├── deliveryStatus
│       │   │   └── DeliveryStatusServlet.java
│       │   ├── food
│       │   │   ├── DeleteFoodServlet.java
│       │   │   ├── FoodManagementServlet.java
│       │   │   ├── InsertFoodServlet.java
│       │   │   ├── SelectFoodServlet.java
│       │   │   └── UpdateFoodServlet.java
│       │   ├── oderItem
│       │   │   ├── InsertItemServlet.java
│       │   │   ├── OrderListUpdateServlet.java
│       │   │   ├── SelectOrderItems.java
│       │   │   ├── TempInsertItemServlet.java
│       │   │   └── UpdateOrderItemServlet.java
│       │   ├── owner
│       │   │   ├── OwnerDashBoardServlet.java
│       │   │   └── OwnerSignupServlet.java
│       │   └── store
│       │       ├── DeleteStoreServlet.java
│       │       ├── InsertStoreServlet.java
│       │       ├── StoreManagementServlet.java
│       │       └── UpdateStoreServlet.java
│       └── filter
│           └── EncodingFilter.java
├── contorller
│   ├── CustoemrController.java
│   ├── FoodController.java
│   ├── OrderItemController.java
│   ├── OwnerController.java
│   └── StoreController.java
├── customer
│   ├── CustomerRepository.java
│   └── CustomerService.java
├── entity
│   ├── Customer.java
│   ├── CustomerDTO.java
│   ├── Food.java
│   ├── FoodDTO.java
│   ├── Location.java
│   ├── OrderItem.java
│   ├── OrderItemDTO.java
│   ├── OrdersDTO.java
│   ├── Owner.java
│   ├── OwnerDTO.java
│   ├── Store.java
│   └── StoreDTO.java
├── food
│   ├── FoodRepository.java
│   └── FoodService.java
├── orderitem
│   ├── OrderItemRepository.java
│   └── OrderItemService.java
├── owner
│   ├── OwnerRepository.java
│   └── OwnerService.java
└── store
    ├── StoreRepository.java
    └── StoreService.java
</pre>

<h3>web</h3>
<pre>
webapp
├── META-INF
│   └── context.xml
├── WEB-INF
│   └── web.xml
├── cart
│   └── order
│       ├── orderList.jsp
│       ├── orderListScript.js
│       └── orderStyle.css
├── customer
│   ├── findPassword.jsp
│   ├── resultPassword.jsp
│   ├── signup.jsp
│   ├── signupScript.js
│   └── signupStyle.css
├── delivery
│   ├── deliveryStatus.jsp
│   └── deliveryStatusStyle.css
├── index.jsp
├── lib
│   └── ojdbc8.jar
├── owner
│   ├── findPassword.jsp
│   ├── resultPassword.jsp
│   ├── signup.jsp
│   ├── signupScript.js
│   └── signupStyle.css
├── ownerDashboard
│   ├── foodListFragment.jsp
│   ├── foodManagement.jsp
│   ├── maindashBoard.jsp
│   ├── maindashBoardScript.js
│   ├── maindashBoardStyle.css
│   ├── storeManagement.jsp
│   └── storefoodScript.js
├── projectImg
│   ├── beef_ani.gif
│   ├── beef_state.png
│   ├── chicken_ani.gif
│   ├── chicken_state.png
│   ├── chinese_ani.gif
│   ├── chinese_state.png
│   ├── coffee_ani.gif
│   ├── coffee_state.png
│   ├── dessert_ani.gif
│   ├── dessert_state.png
│   ├── donkazhu_ani.gif
│   ├── donkazhu_state.png
│   ├── icon_round.png
│   ├── logo.png
│   ├── mela_ani.gif
│   ├── mela_state.png
│   ├── pizza_ani.gif
│   ├── pizza_state.png
│   ├── side_meal_ani.gif
│   ├── side_meal_state.png
│   ├── sushi_ani.gif
│   └── sushi_state.png
└── util
    ├── category.jsp
    ├── categoryScript.js
    ├── detail
    │   ├── beef.jsp
    │   ├── categoryStyle.css
    │   ├── chicken.jsp
    │   ├── chinese.jsp
    │   ├── coffee.jsp
    │   ├── dessert.jsp
    │   ├── donkazhu.jsp
    │   ├── mainCategoryScript.js
    │   ├── mela.jsp
    │   ├── pizza.jsp
    │   ├── sideMeal.jsp
    │   └── sushi.jsp
    ├── findPassword.jsp
    ├── header.jsp
    ├── mainCategoryStyle.css
    ├── resultPassword.jsp
    └── updateStyle.css
</pre>
