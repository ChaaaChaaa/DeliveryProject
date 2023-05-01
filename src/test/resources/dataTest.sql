-- User 데이터 추가
INSERT INTO user (userName, password, nickname, grade, phoneNumber, role, createdAt)
VALUES ('test', '1234', 'chacha', 'NORMAL', '11111111111', 'CUSTOMER', NOW());

-- Delivery 데이터 추가
INSERT INTO Delivery (deliveryId) VALUES (1);


-- Food 데이터 추가
INSERT INTO Food (name, price, description)
VALUES ('라면', 1000, '맛있는 라면');

-- Store 데이터 추가
INSERT INTO Store (storeName, category, rating, reviewCount)
VALUES ('차차네', 'noodle', 5.0, 100);

-- StoreMenu 데이터 추가
INSERT INTO StoreMenu (foodId, storeId)
VALUES (
           (SELECT foodId FROM Food WHERE name='라면'),
           (SELECT storeId FROM Store WHERE storeName='차차네')
       );

-- Food 데이터 추가
INSERT INTO Food (name, price, description)
VALUES ('신라면', 2000, '매콤한 라면');

-- User 데이터 추가
INSERT INTO User (userName, password, nickname, phoneNumber, role)
VALUES ('mandu', 'testtesttest', 'mandu', '22222222222', 'CUSTOMER');

-- Store 데이터 추가
INSERT INTO Store (storeName, category, rating, reviewCount)
VALUES ('만두네', 'dumpling', 4.5, 200);

-- Food 데이터 업데이트
UPDATE Food SET price=2000, description='매콤한 라면' WHERE name='신라면';

-- Store 데이터 업데이트
UPDATE Store SET category='dumpling', storeName='만두네', rating=4.5, reviewCount=200 WHERE storeName='차차네';

