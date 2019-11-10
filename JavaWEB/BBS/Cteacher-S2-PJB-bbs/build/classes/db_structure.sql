drop table tbl_reply;
drop table tbl_topic;
drop table tbl_board;
drop table tbl_user;
drop sequence seq_tbl_board;

create table tbl_user(
  userid varchar2(36) primary key,
  uname varchar2(20),
  upass varchar2(100),
  head varchar2(100),
  regtime date,
  gender int
);

insert into tbl_user values( '1','a','0cc175b9c0f1b6a831c399e269772661','1.gif',sysdate,1);
select * from tbl_user;

create sequence seq_tbl_board start with 1;

create table tbl_board(
  boardid int primary key ,
  boardname varchar(50),
  parentid int
);

insert into tbl_board values(seq_tbl_board.nextval,'.net版块',0);
insert into tbl_board values(seq_tbl_board.nextval,'java版块',0);
insert into tbl_board values(seq_tbl_board.nextval,'数据库版块',0);
insert into tbl_board values(seq_tbl_board.nextval,'软件工程版块',0);


insert into tbl_board values(seq_tbl_board.nextval,'ado.net',1);
insert into tbl_board values(seq_tbl_board.nextval,'asp.net',1);
insert into tbl_board values(seq_tbl_board.nextval,'vb.net',1);


insert into tbl_board values(seq_tbl_board.nextval,'jsp',2);
insert into tbl_board values(seq_tbl_board.nextval,'struts',2);
insert into tbl_board values(seq_tbl_board.nextval,'hibernate',2);


insert into tbl_board values(seq_tbl_board.nextval,'sql',3);
insert into tbl_board values(seq_tbl_board.nextval,'oracle',3);
insert into tbl_board values(seq_tbl_board.nextval,'mysql',3);

insert into tbl_board values( seq_tbl_board.nextval,'其它', 0);

insert into tbl_board values( seq_tbl_board.nextval, '面試', 15 );


select boardid, boardname, parentid from tbl_board;


create table tbl_topic(
  topicid varchar2(40) primary key ,
  title varchar2(50),
  content varchar2(1000),
  publishtime date,
  modifytime date,
  userid varchar2(40),
  boardid int
);

alter table tbl_topic
   add constraint FK_topic_uid
      foreign key(userid) references tbl_user(userid);
      
alter table tbl_topic
   add constraint FK_topic_boardid
     foreign key(boardid) references tbl_board(boardid);
     
     
insert into tbl_topic values( '1','.net版块第一条数据','aaa',sysdate,sysdate, '1',6);
insert into tbl_topic values( '2','.net版块第2条数据','aaa',sysdate,sysdate, '1',6);
insert into tbl_topic values( '3','.net版块第3条数据','aaa',sysdate,sysdate, '1',6);
insert into tbl_topic values( '4','.net版块第4条数据','aaa',sysdate,sysdate, '1',6);
insert into tbl_topic values( '5','.net版块第5条数据','aaa',sysdate,sysdate, '1',6);
insert into tbl_topic values( '6','.net版块第6条数据','aaa',sysdate,sysdate, '1',6);

select * from tbl_topic;
     
create table tbl_reply(
  replyid varchar2(40) primary key ,
  title varchar2(50),
  content varchar2(1000),
  publishtime date,
  modifytime date,
  userid varchar2(40),
  topicid varchar2(40)
);

alter table tbl_reply
   add constraint FK_reply_uid
      foreign key(userid) references tbl_user(userid);
      
alter table tbl_reply
  add constraint FK_reply_topicid
     foreign key(topicid) references tbl_topic(topicid);
     
commit;

