CREATE TABLE Product(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    price DOUBLE
) engine=InnoDB DEFAULT CHARSET = gbk;

CREATE TABLE Inventory(
    id INT ,
    count INT,
    lockedCount INT
) engine=InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE orderInfo(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    status VARCHAR(255),
    createTime VARCHAR(255),
    finishTime VARCHAR(255),
    paidTime VARCHAR(255),
    withdrawnTime VARCHAR(255),
    totalPrice DOUBLE,
    userId INT
) engine=InnoDB DEFAULT CHARSET = gbk;


CREATE TABLE Snapshot(
    snapId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    productId INT ,
    orderId INT,
    productName VARCHAR(255),
    productDescription VARCHAR(255),
    purchaseCount INT,
    purchasePrice DOUBLE
) engine=InnoDB DEFAULT CHARSET = gbk;

CREATE TABLE LogisticsRecord(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    orderId INT,
    logisticsStatus VARCHAR(255),
    outboundTime VARCHAR(255),
    signedTime VARCHAR(255),
    deliveryMan VARCHAR(255)
) engine=InnoDB DEFAULT CHARSET = gbk;


