
create table tbl_user(
	uid int primary key auto_increment,
	uname varchar(20),
	upass varchar(100),
	head varchar(100),
	regtime datetime,
	gender int
)
delete from tbl_user;
select * from tbl_user
insert into tbl_user (uname,upass,head,regtime,gender) values('a','0cc175b9c0f1b6a831c399e269772661','1.gif',now(),1);
insert into tbl_user (uname,upass,head,regtime,gender) values('b','0cc175b9c0f1b6a831c399e269772661','2.gif',now(),1);
--版块
create table tbl_board(
	boardid int primary key auto_increment,
	boardname varchar(50),
	parentid int
);

select * from tbl_board order by parentid

insert into tbl_board(boardname,parentid) values('.net',0);
insert into tbl_board(boardname,parentid) values('java',0);
insert into tbl_board(boardname,parentid) values('db',0);
insert into tbl_board(boardname,parentid) values('project',0);


insert into tbl_board(boardname,parentid) values('ado.net',1);
insert into tbl_board(boardname,parentid) values('asp.net',1);
insert into tbl_board(boardname,parentid) values('vb.net',1);


insert into tbl_board(boardname,parentid) values('jsp',2);
insert into tbl_board(boardname,parentid) values('struts',2);
insert into tbl_board(boardname,parentid) values('hibernate',2);


insert into tbl_board(boardname,parentid) values('sql',3);
insert into tbl_board(boardname,parentid) values('oracle',3);
insert into tbl_board(boardname,parentid) values('mysql',3);

--主题帖
create table tbl_topic(
	topicid int primary key auto_increment,
	title varchar(50),
	content varchar(1000),
	publishtime datetime,
	modifytime datetime,
	uid int,
	boardid int
);

insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) values("jsp  good"," good,i agree",now(),now(),1,8);
insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) values("jsp is very good","very good,i agree",now(),now(),1,8);


select * from tbl_topic


alter table tbl_topic
   add constraint FK_topic_uid
      foreign key(uid) references tbl_user(uid)
      
alter table tbl_topic
   add constraint FK_topic_boardid
     foreign key(boardid) references tbl_board(boardid)
    
---回复表	
create table tbl_reply(
	replyid int primary key auto_increment,
	title varchar(50),
	content varchar(1000),
	publishtime datetime,
	modifytime datetime,
	uid int,
	topicid int
);

alter table tbl_reply
   add constraint FK_reply_uid
      foreign key(uid) references tbl_user(uid);
      
alter table tbl_reply
	add constraint FK_reply_topicid
	   foreign key(topicid) references tbl_topic(topicid);
	   
	   
	   
  
	   
--首页用查询语句	   
select a.boardid,boardname,parentid,total, topicid,title,modifytime,uname
from
(
	select tbl_board.boardid,boardname,parentid , count( topicid ) as total
	from tbl_board
	left join tbl_topic
	on tbl_board.boardid=tbl_topic.boardid
	group by tbl_board.boardid,boardname,parentid 
) a
left join 
(
	select topicid,title,a.modifytime,uname,a.boardid
	from
	(	select topicid, title, modifytime, uname, boardid
		from tbl_topic
		left join tbl_user
		on tbl_topic.uid=tbl_user.uid
        ) a,
		(
			select boardid,max(modifytime) as modifytime
			from tbl_topic
			group by boardid
		) b
	where  a.boardid=b.boardid and a.modifytime=b.modifytime
)b
on a.boardid=b.boardid



-------列出版块下分页显示的主题的sql的语句
--案例需求:   求出  8号版块下  第一页的主贴的信息  (注:每页2条)
select a.topicid,title,content,publishtime,modifytime,uid,uname,boardid, total
from
(select topicid,title,content,publishtime,modifytime,  tbl_user.uid,  uname,boardid
from tbl_topic
inner join tbl_user
on tbl_topic.uid=tbl_user.uid
where boardid=8
order by modifytime desc
limit 0,2) a
left join 
(select topicid, count(*) as total from tbl_reply
group by topicid) b
on a.topicid=b.topicid

