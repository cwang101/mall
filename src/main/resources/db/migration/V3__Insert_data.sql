
insert into product(name,description,price) values("test666","test",1000);
insert into product(name,description,price) values("方便面","康师傅",5);
insert into product(name,description,price) values("农夫山泉","矿泉水",3);
insert into product(name,description,price) values("三颗松鼠大礼包","七种零食包装",60);
insert into product(name,description,price) values("飘柔洗护套装","滋润秀发",110);

insert into inventory(id,count,lockedCount) values(1,113,107);
insert into inventory(id,count,lockedCount) values(2,100,86);
insert into inventory(id,count,lockedCount) values(3,100,85);
insert into inventory(id,count,lockedCount) values(4,123,28);
insert into inventory(id,count,lockedCount) values(5,145,58);


insert into orderInfo (createTime, finishTime, paidTime, status, totalPrice, withdrawnTime,userId) values ("2018-4-13", "2018-4-20", "2018-4-14", "finished", 230, "",1);
insert into orderInfo (createTime, finishTime, paidTime, status, totalPrice, withdrawnTime,userId) values ("2018-4-13", "", "", "unpaid", 230, "",1);
insert into orderInfo (createTime, finishTime, paidTime, status, totalPrice, withdrawnTime,userId) values ("2018-4-13", "", "", "unpaid", 230, "",1);
insert into orderInfo (createTime, finishTime, paidTime, status, totalPrice, withdrawnTime,userId) values ("2018-4-13", "", "", "unpaid", 230, "",1);
insert into orderInfo (createTime, finishTime, paidTime, status, totalPrice, withdrawnTime,userId) values ("2018-4-13", "", "", "unpaid", 230, "",1);
insert into orderInfo (createTime, finishTime, paidTime, status, totalPrice, withdrawnTime,userId) values ("2018-4-13", "", "", "unpaid", 230, "",1);


insert into snapshot(orderId,productId,productName,productDescription,purchasePrice,purchaseCount) values(1,4,"三颗松鼠大礼包","七种零食包装",60,2),(1,5,"飘柔洗护套装","滋润秀发",110,1);

insert into logisticsRecord(logisticsStatus,outboundTime,signedTime,deliveryMan,orderId) values("readyToShip","","","李师傅",1);