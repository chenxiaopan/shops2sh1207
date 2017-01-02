
/*============================*/ 
/* Table: 用户表结构 		      */
/*============================*/
create table user
(
   /* 用户编号,自动增长 */
   id                  number primary key not null ,
   /* 用户登录名 */
   login               varchar2(20),
   /* 用户真实姓名 */
   name                varchar2(20),
   /* 用户登录密码 */
   pass                varchar2(20),
   /* 用户性别 */
   sex                 varchar2(20),
   /* 用户电话 */
   phone               varchar2(20),
   /* 用户Email */
   email               varchar2(20)
);


-------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------
/*============================*/ 
/* Table: 用户表结构 		      */
/*============================*/
    create table "CXP"."USERS"(
        "USERID" NUMBER not null,
       "UNAME" NVARCHAR2,
       "UPWD" NVARCHAR2,
       "USEX" NVARCHAR2,
       "UBIRTH" DATE,
       "UTEL" NVARCHAR2,
       "UADD" NVARCHAR2,
       "UTYPE" NUMBER(2),
        constraint "SYS_C0012124" primary key ("USERID")
    );

    create unique index "CXP"."SYS_C0012124" on "CXP"."USERS"("USERID");



/*============================*/
/*      Table：管理员表结构                       */
/*============================*/
create table account
(
	/* 管理员编号，自动增长 */
	id number(10) primary key not null ,
	/* 管理员登录名 */
	login varchar(20),
	/* 管理员姓名 */
	name varchar(20),
	/* 管理员密码 */
	pass varchar(20)
);


/*=============================*/
/*     Table：商品类别表结构                      */
/*============================*/
create table category
(
   /* 类别编号，自动增长 */
   id  number primary key not null,
   /* 类别名称 */
   type varchar(20),
   /* 类别是否为热点类别，热点类别才有可能显示在首页*/
   hot  number(2) ,
   /* 外键，此类别由哪位管理员管理 */
   aid number(10) ,
   
   constraint aid_FK foreign key(aid) references account(id)
);

/*=============================*/
/* Table: 商品表结构	 		   */
/*=============================*/
create table product
(
   /* 商品编号,自动增长 */
   id                  number(10) primary key not null ,
   /* 商品名称 */
   name                varchar2(50),
   /* 商品价格 */
   price               number(8,2),
   /* 商品图片 */
   pic                 varchar2(300),
   /* 商品简单介绍 */
   remark              varchar2(50),
   /* 商品详细介绍 */
   xremark             varchar2(100),
   /* 商品生产日期 */
   pdate                Date default CURRENT_TIMESTAMP,
   /* 是否为推荐商品,推荐商品才有可能显示在商城首页 */
   commend             number(2),
   /* 是否为有效商品,有效商品才有可能显示在商城首页 */
   open                number(2),
   /* 商品所在的类别编号*/
   cid                  number,
   constraint cid_FK foreign key(cid) references category(id)
);


/*=============================*/
/* Table: 订单状态表结构 		       */
/*=============================*/
create table status
(
   /* 状态编号,自动增长 */
   id                  number primary key not null ,
   /* 订单状态 */
   status               varchar2(10)
);

/*=============================*/
/* Table: 购物车（订单）表结构		   */
/*=============================*/
create table forder
(
   /* 订单编号,自动增长 */
   id                  number primary key not null ,
   /* 收件人名字 */
   name                varchar2(20),
   /* 收件人电话 */
   phone               varchar2(20),
   /* 配送信息 */
   remark              varchar2(20),
   /* 下单日期 */
   fdate                Date default CURRENT_TIMESTAMP,
   /* 订单总金额 */
   total               number(8,2),
   /* 收件人邮编 */
   post                varchar2(20),
    /* 收件人邮编 */
   address             varchar2(200),
   /* 订单状态 */
   sid                 number ,
   /* 会员编号 */
   userid                 number,
   constraint sid_FK foreign key(sid) references status(id),
   constraint uid_FK foreign key(userid) references users(userid)
);


/*=============================*/
/* Table: 购物项表结构 		       */
/*=============================*/

create table sorder
(
   /* 购物项编号,自动增长 */
   id                  number primary key not null ,
   /* 被购买商品的名称 */
   name                varchar2(20),
   /* 购买时商品的价格 */
   price               number(8,2),
   /* 购买的数量 */
   numbers              number not null,
   /* 所属商品编号 */
   pid                  number,
   /* 此订单项,所属的订单编号 */
   fid                  number,
   constraint pid_FK foreign key(pid) references product(id),
   constraint fid_FK foreign key(fid) references forder(id)
);


create sequence seq_aid start with 1000;
create sequence seq_cid start with 1000;
create sequence seq_pid start with 1000;
create sequence seq_sid start with 0 minvalue 0;
create sequence seq_fid start with 201605000;
create sequence seq_sorid start with 1000;

select seq_fid.nextval from dual;

/*插入测试用例*/
insert into account values(seq_aid.nextval,'admin','管理员','admin');
insert into account values(seq_aid.nextval,'cxp','管理员','123');
insert into account values(seq_aid.nextval,'user','客服A','user');

   /* 1表示为热点类别*/
INSERT INTO category  VALUES (seq_cid.nextval,'男士休闲',1,1001);
INSERT INTO category  VALUES (seq_cid.nextval,'女士休闲',1,1001);
INSERT INTO category  VALUES (seq_cid.nextval,'儿童休闲',0,1002);
INSERT INTO category  VALUES (seq_cid.nextval,'老人休闲',0,1002);

/* 商品测试用例 */
INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'圣得西服',2999.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1001);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'罗蒙西服',1999.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1001);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'衫衫西服',3999.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1001);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'金利来西服',4999.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1001);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'韩版女装',199.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1002);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'雪地靴',299.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1002);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'欧版女装',3999.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1002);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'女款手套',4999.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1002);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'佳能单反机',3998.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1003);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'金士顿优盘',299.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1003);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'日立硬盘',599.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1003);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'大水牛机箱',399.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1003);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'电脑桌',150.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1004);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'老板椅',199.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1004);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'空调',3599.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1004);

INSERT INTO product (id,name,price,pic,remark,xremark,commend,open,cid) VALUES 
(seq_pid.nextval,'电视',699.00,'test.jpg','这里是简单介绍','这里是详细介绍',1,1,1004);



/*插入状态测试用例*/
INSERT INTO status VALUES (seq_sid.nextval,'未支付');
INSERT INTO status VALUES (seq_sid.nextval,'已支付');
INSERT INTO status VALUES (seq_sid.nextval,'配送中');
INSERT INTO status VALUES (seq_sid.nextval,'订单完成');

/*插入购物车测试用例*/
INSERT INTO forder (id,name,phone,remark,fdate,total,address,post,userid) VALUES (seq_fid.nextval,'bb','123','备注','12-12月-2016',200.3,'广州天河区','1000',1003);
INSERT INTO forder (id,name,phone,remark,fdate,total,address,post,userid) VALUES (seq_fid.nextval,'bb','123','备注',default,200.3,'上海嘉定区','1000',1004);
INSERT INTO forder (id,name,phone,remark,fdate,total,address,post,userid) VALUES (seq_fid.nextval,'bb','123','备注',default,200.3,'上海宝山区','1000',1007);

/*插入购物车项测试用例*/
INSERT INTO sorder (id,name,price,numbers,pid,fid) VALUES (seq_sorid.nextval,'空调',200,3,1015,201605001);
INSERT INTO sorder (id,name,price,numbers,pid,fid) VALUES (seq_sorid.nextval,'电视',0.3,5,1016,201605001);
INSERT INTO sorder (id,name,price,numbers,pid,fid) VALUES (seq_sorid.nextval,'杉杉西服',0.3,7,1003,201605002);
INSERT INTO sorder (id,name,price,numbers,pid,fid) VALUES (seq_sorid.nextval,'圣德西服',0.3,12,1001,201605002);
INSERT INTO sorder (id,name,price,numbers,pid,fid) VALUES (seq_sorid.nextval,'韩版女装',0.3,20,1005,201605002);
INSERT INTO sorder (id,name,price,numbers,pid,fid) VALUES (seq_sorid.nextval,'雪地靴',0.3,10,1006,201605002);
INSERT INTO sorder (id,name,price,numbers,pid,fid) VALUES (seq_sorid.nextval,'欧版女装',0.3,9,1007,201605002);

SELECT * FROM account;
SELECT * FROM category;
SELECT * FROM product;
SELECT * FROM users;
SELECT * FROM status;
SELECT * FROM forder;
SELECT * FROM sorder;

select * from product where id=1006;






select numbers "销量",p.name "商品名" from sorder s,product p where s.pid=p.id;

select* from product p,category c where p.cid=c.id and p.commend=1 and p.open=1 and c.id=1001 order by p.pdate desc

select category0_.ID as ID1_1_, category0_.AID as AID2_1_, category0_.TYPE as TYPE3_1_, category0_.HOT as HOT4_1_ from CXP.CATEGORY category0_ where category0_.HOT=1

select * from ( select category0_.ID as ID1_1_0_, account1_.ID as ID1_0_1_, category0_.AID as AID2_1_0_, category0_.TYPE as TYPE3_1_0_, category0_.HOT as HOT4_1_0_, account1_.LOGIN as LOGIN2_0_1_, account1_.NAME as NAME3_0_1_, account1_.PASS as PASS4_0_1_ from CXP.CATEGORY category0_ 
left outer join CXP.ACCOUNT account1_ on category0_.AID=account1_.ID where category0_.TYPE like '%%' ) where rownum <= 5;

select count(1) as col_0_0_ from CXP.CATEGORY category0_ where category0_.TYPE like '%%';


 select categories0_.AID as AID2_0_0_, categories0_.ID as ID1_1_0_, categories0_.ID as ID1_1_1_, categories0_.AID as AID2_1_1_, categories0_.TYPE as TYPE3_1_1_, categories0_.HOT as HOT4_1_1_ from CXP.CATEGORY categories0_ where categories0_.AID=1001;

select sorders0_.PID as PID3_3_0_, sorders0_.ID as ID1_4_0_, sorders0_.ID as ID1_4_1_, sorders0_.FID as FID2_4_1_, sorders0_.PID as PID3_4_1_, sorders0_.NAME as NAME4_4_1_, sorders0_.PRICE as PRICE5_4_1_, sorders0_.NUMBERS as NUMBERS6_4_1_ from CXP.SORDER sorders0_ where sorders0_.PID=1001


select product0_.ID as ID1_3_0_, product0_.CID as CID2_3_0_, product0_.NAME as NAME3_3_0_, product0_.PRICE as PRICE4_3_0_, product0_.PIC as PIC5_3_0_, product0_.REMARK as REMARK6_3_0_, product0_.XREMARK as XREMARK7_3_0_, product0_.PDATE as PDATE8_3_0_, product0_.COMMEND as COMMEND9_3_0_, product0_.OPEN as OPEN10_3_0_, category1_.ID as ID1_1_1_, category1_.AID as AID2_1_1_, category1_.TYPE as TYPE3_1_1_, category1_.HOT as HOT4_1_1_ 
from CXP.PRODUCT product0_ 
left outer join CXP.CATEGORY category1_ on product0_.CID=category1_.ID 
where product0_.ID=1001;


 select sorder0_.NUMBERS as col_0_0_ from CXP.SORDER sorder0_, CXP.PRODUCT product1_ where sorder0_.PID=product1_.ID and product1_.COMMEND=1;


select s.name,sum(s.numbers)  from sorder s,product p where s.pid=p.id  group by p.id,s.name;


select * from ( select sorder0_.NAME as col_0_0_, sum(sorder0_.NUMBERS) as col_1_0_ from CXP.SORDER sorder0_ group by sorder0_.PID , sorder0_.NAME ) where rownum <= 5;

 select category0_.ID as ID1_1_0_, category0_.AID as AID2_1_0_, category0_.TYPE as TYPE3_1_0_, category0_.HOT as HOT4_1_0_ from CXP.CATEGORY category0_ where category0_.ID=1001;


 select category0_.ID as ID1_1_0_, category0_.AID as AID2_1_0_, category0_.TYPE as TYPE3_1_0_, category0_.HOT as HOT4_1_0_ from CXP.CATEGORY category0_ where category0_.ID=1007;

