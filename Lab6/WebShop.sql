-- =============================================
-- XÓA CSDL CŨ (NẾU CÓ)
-- =============================================
DROP DATABASE IF EXISTS WebShop;
-- =============================================
-- TẠO CSDL MỚI
-- =============================================
CREATE DATABASE WebShop;
USE WebShop;

-- =============================================
-- BẢNG CATEGORIES
-- =============================================
CREATE TABLE Categories
(
    Id   VARCHAR(10)  NOT NULL PRIMARY KEY,
    Name VARCHAR(100) NOT NULL
);
-- =============================================
-- BẢNG PRODUCTS
-- =============================================
CREATE TABLE Products
(
    Id         INT AUTO_INCREMENT PRIMARY KEY,
    Name       VARCHAR(100) NOT NULL,
    Image      VARCHAR(255),
    Price      DECIMAL(18, 2),
    CreateDate DATE DEFAULT (CURRENT_DATE),
    Available  BIT  DEFAULT 1,
    CategoryId VARCHAR(10)  NOT NULL,
    FOREIGN KEY (CategoryId) REFERENCES Categories (Id)
);
-- =============================================
-- BẢNG ACCOUNTS
-- =============================================
CREATE TABLE Accounts
(
    Username  VARCHAR(50)  NOT NULL PRIMARY KEY,
    Password  VARCHAR(100) NOT NULL,
    Fullname  VARCHAR(100),
    Email     VARCHAR(100),
    Photo     VARCHAR(255),
    Activated BIT DEFAULT 1,
    Admin     BIT DEFAULT 0
);
-- =============================================
-- BẢNG ORDERS
-- =============================================
CREATE TABLE Orders
(
    Id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    Address    VARCHAR(255),
    CreateDate DATE DEFAULT (CURRENT_DATE),
    Username   VARCHAR(50) NOT NULL,
    FOREIGN KEY (Username) REFERENCES Accounts (Username)
);
-- =============================================
-- BẢNG ORDER DETAILS
-- =============================================
CREATE TABLE OrderDetails
(
    Id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    Price     DECIMAL(18, 2),
    Quantity  INT CHECK (Quantity > 0),
    ProductId INT    NOT NULL,
    OrderId   BIGINT NOT NULL,
    FOREIGN KEY (ProductId) REFERENCES Products (Id),
    FOREIGN KEY (OrderId) REFERENCES Orders (Id)
);
-- =============================================
-- DỮ LIỆU MẪU
-- =============================================

-- CATEGORIES
INSERT INTO Categories (Id, Name)
VALUES ('1000', 'Đồng hồ đeo tay'),
       ('1001', 'Máy tính xách tay'),
       ('1002', 'Máy ảnh'),
       ('1003', 'Điện thoại'),
       ('1004', 'Nước hoa'),
       ('1005', 'Nữ trang'),
       ('1006', 'Nón thời trang'),
       ('1007', 'Túi xách du lịch');

-- PRODUCTS
INSERT INTO Products (Name, Image, Price, Available, CategoryId)
VALUES ('Casio MTP-V002', 'watch1.jpg', 850000, 1, '1000'),
       ('Laptop Asus Vivobook', 'laptop1.jpg', 14500000, 1, '1001'),
       ('Canon EOS M50', 'camera1.jpg', 12500000, 1, '1002'),
       ('iPhone 15 Pro', 'phone1.jpg', 28900000, 1, '1003'),
       ('Nước hoa Dior Sauvage', 'perfume1.jpg', 3100000, 1, '1004'),
       ('Dây chuyền bạc nữ', 'jewel1.jpg', 450000, 1, '1005'),
       ('Nón lưỡi trai Adidas', 'cap1.jpg', 250000, 1, '1006'),
       ('Túi xách LV Mini', 'bag1.jpg', 9900000, 1, '1007');

-- ACCOUNTS
INSERT INTO Accounts (Username, Password, Fullname, Email, Photo, Activated, Admin)
VALUES ('admin', '123', 'Quản trị viên', 'admin@shop.vn', 'admin.jpg', 1, 1),
       ('user', '123', 'Người dùng', 'user@gmail.com', 'user.jpg', 1, 0),
       ('noob', '123', 'Noob', 'noob@gmail.com', 'noob.jpg', 1, 0);

-- ORDERS
INSERT INTO Orders (Address, Username)
VALUES ('343 BruhBruh, Q.6, TP.HCM', 'user'),
       ('325 Lmao, Q.12, TP.HCM', 'noob');

-- ORDER DETAILS
INSERT INTO OrderDetails (Price, Quantity, ProductId, OrderId)
VALUES (850000, 2, 1, 1),   -- 2 đồng hồ
       (28900000, 1, 4, 1), -- 1 iPhone
       (250000, 3, 7, 2); -- 3 nón Adidas

-- =============================================
-- KIỂM TRA DỮ LIỆU
-- =============================================

SELECT * FROM Products;
SELECT * FROM Accounts;
SELECT * FROM Orders;
SELECT * FROM OrderDetails;