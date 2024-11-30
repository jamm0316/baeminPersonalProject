<img src="https://github.com/user-attachments/assets/47ff9d4d-0a45-4ee4-957d-f4b8c975dbc1" width="50px" height="50px"> &nbsp;
배달의 민족 배달서비스
===
바로 주문, 배로 배달, 현황 확인까지 한번에!
Project Introduce in Canava

## Contents
> [!NOTE]
> 1. [Authors](#authors)
> 2. [Tech Stack](#tech-stack)
> 3. [Development Environment](#development-environment)
> 4. [Archetecture](#archetecture)
> 5. [Database](#database)
> 6. [Directory Structure](#directory-structure)  
>     - [java](#java)
>     - [web](#web)
> 7. [API Reference](#api-reference)  
>     - [SignUp](#signup)

## Authors

## Tech Stack
Client: Javascript, HTML, CSS
Server: Java, JSP
Database: Oracle

## Development Environment
`IDE` IntelliJ Altimate
`JDK` 21
`OS` mac

## Archetecture

## Database

## Directory Structure
### java
```
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
```

### web
```
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
```

## API Reference
### SignUp
**customer Signup Page**
```
  GET /customer/signup.do
```

**owner Signup Page**
```
  GET /owner/signup.do
```
