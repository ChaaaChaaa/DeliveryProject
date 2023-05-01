## DB ERD
***
![image](https://user-images.githubusercontent.com/56629324/235487969-d862585b-c6ef-4cef-9d0f-1323bc9a70d2.png)

## URL
***
|제목|내용|설명|
|------|---|---|
|Auth|||
|POST|/auth/signup|회원 가입|
|POST|/auth/login|로그인|
|POST|/auth/logout|로그아웃|
|Food|||
|POST|/foods/save|가게 음식 저장|
|GET|/foods/{id}|가게 음식 조회|
|GET|/foods/search|가게 음식 이름으로 조회|
|PUT|/foods/{id}|가게 음식 업데이트|
|DELETE|/foods/{id}|가게 음식 삭제|
|Store|||
|POST|/stores/save|가게 저장|
|GET|/stores/{storeId}|가게 조회|
|GET|/stores/search|가게 이름으로 조회|
|PUT|/stores/{storeId}|가게 정보 업데이트|
|DELETE|/stores/{storeId}|가게 삭제|
|StoreMenu|||
|POST|/storeMenus/save|가게 메뉴 저장|
|GET|/storeMenus/{storeMenuId}|가게 메뉴 조회|
|PUT|/storeMenus/{storeMenuId}/updateFood|가게 음식 업데이트|
|PUT|/storeMenus/{storeMenuId}/updateStore|가게 업데이트|
|DELETE|/storeMenus/{storeMenuId}|가게 메뉴 삭제|
|OrderItem|||
|GET|/order-items/{orderItemId}|주문항목 조회||
|Order|||
|POST|/order-list/save|주문서 저장|
|GET|/order-list/{orderListId}|주문서 조회|
|GET|/order-list/search/user/{orderListId}|유저 id로 주문서 조회|
|GET|/order-list/search/delivery/{orderListId}|배달 id로 주문서 조회|
|User|||
|POST|/users/save|유저 저장|
|GET|/users/{userId}|유저 id로 조회|
|GET|/users/search/nickname/{nickName}|유저 닉네임으로 조회|
|PUT|/users/{userId}|유저 업데이트|
|DELETE|/users/{userId}|유저 삭제|
|Review|||
|POST|/reviews/save|리뷰 저장|
|GET|/reviews/search/{reviewId}|리뷰 조회|
|DELETE|/reviews/{reviewId}|리뷰 삭제|
|Delivery|||
|POST|/deliveries/save|배달 정보 저장|
|GET|/deliveries/search/{deliveryId}|배달 정보 조회|



## 프로젝트를 통해 해결한 궁금증과 문제들
***
:bulb:[게시판 Repository 을 만들면서 QnA(JpaRepository,Builder,@Modifying,@Transactional)](https://peonyf.tistory.com/entry/Spring-%EA%B2%8C%EC%8B%9C%ED%8C%90-Repository-%EC%9D%84-%EB%A7%8C%EB%93%A4%EB%A9%B4%EC%84%9C-QnA)

:bulb:[게시판 Controller을 만들면서 QnA(RequestBody,REST API,Annotation)](https://peonyf.tistory.com/entry/Spring-%EA%B2%8C%EC%8B%9C%ED%8C%90-Controller%EC%9D%84-%EB%A7%8C%EB%93%A4%EB%A9%B4%EC%84%9C-QnA)

:bulb:[게시판 Service을 만들면서 QnA](https://peonyf.tistory.com/entry/Spring-%EA%B2%8C%EC%8B%9C%ED%8C%90-Service%EC%9D%84-%EB%A7%8C%EB%93%A4%EB%A9%B4%EC%84%9C-QnA)

:bulb:[게시판 Domain을 만들면서 QnA](https://peonyf.tistory.com/entry/Spring-%EA%B2%8C%EC%8B%9C%ED%8C%90-Domain%EC%9D%84-%EB%A7%8C%EB%93%A4%EB%A9%B4%EC%84%9C-QnA)

:bulb:[게시판 TDD를 만들면서 QnA](https://peonyf.tistory.com/entry/Spring-%EA%B2%8C%EC%8B%9C%ED%8C%90-TestCase%EB%A5%BC-%EB%A7%8C%EB%93%A4%EB%A9%B4%EC%84%9C-QnA)
