INSERT INTO tbl_topic(title,content,publishtime,modifytime,uid,boardid)
VALUES('java good','很好',NOW(),NOW(),3,8);

INSERT INTO tbl_topic(title,content,publishtime,modifytime,uid,boardid)
VALUES('java very good','非常好',NOW(),NOW(),3,8);


INSERT INTO tbl_reply (title,content,publishtime,modifytime,uid,topicid)
VALUES('java good reply','我同意 java 非常好',NOW(),NOW(),3,1);

INSERT INTO tbl_reply (title,content,publishtime,modifytime,uid,topicid)
VALUES('java good reply','我同意 java 非常好22',NOW(),NOW(),3,1);


INSERT INTO tbl_reply (title,content,publishtime,modifytime,uid,topicid)
VALUES('java very good reply','我同意 java 非常好2',NOW(),NOW(),3,2);

 INSERT INTO tbl_reply (title,content,publishtime,modifytime,uid,topicid)
VALUES('java very good22 reply','我同意 java22 非常好2',NOW(),NOW(),3,2);
/*帖子回复数*/
SELECT  topicid,COUNT(replyid) total
FROM tbl_reply GROUP BY topicid
/*首页
 1:查出最后发帖时间的帖子
*/
SELECT * FROM  tbl_topic

SELECT  boardid,MAX(modifytime)
  FROM tbl_topic
/*
2:查出发帖人的名字，编号，帖子标题，帖子编号 板块编号
*/
SELECT  tbl_topic.`topicid`, tbl_topic.`title` ,tbl_topic.`boardid` ,tbl_topic.`modifytime` AS modifytime ,tbl_user.`uid`, tbl_user.`uname` 
 FROM tbl_topic 
 LEFT JOIN tbl_user ON  tbl_topic.`uid`=tbl_user.`uid`
 
 /*
  将上面两部整合求出每个板块的帖子信息 
 */
 SELECT topicid,title,b.modifytime AS modifytime,uid,uname,b.boardid  FROM
	(
		SELECT  tbl_topic.`topicid`, tbl_topic.`title` ,tbl_topic.`boardid` ,tbl_topic.`modifytime` AS modifytime ,tbl_user.`uid`, tbl_user.`uname` 
		FROM tbl_topic 
		LEFT JOIN tbl_user ON  tbl_topic.`uid`=tbl_user.`uid`
	 ) a,
        
        (
		SELECT  boardid,MAX(modifytime) AS modifytime
		FROM tbl_topic
	) b
 WHERE a.boardid=b.boardid AND a.modifytime=b.modifytime

/*
  统计各个板块的帖子数

*/

SELECT tbl_board.boardid,tbl_board.boardname,tbl_board.parentid,COUNT(tbl_topic.`topicid`) total
FROM tbl_board
LEFT JOIN tbl_topic ON tbl_board.`boardid`= tbl_topic.`boardid`
GROUP BY boardid,boardname,parentid



SELECT b.boardid boardid,boardname,parentid, total ,topicid,title,modifytime,uid,uname
  FROM
  (
	SELECT topicid,title,b.modifytime AS modifytime,uid,uname,b.boardid  FROM
	(
		SELECT  tbl_topic.`topicid`, tbl_topic.`title` ,tbl_topic.`boardid` ,tbl_topic.`modifytime` AS modifytime ,tbl_user.`uid`, tbl_user.`uname` 
		FROM tbl_topic 
		LEFT JOIN tbl_user ON  tbl_topic.`uid`=tbl_user.`uid`
	 ) a,
        
        (
		SELECT  boardid,MAX(modifytime) AS modifytime
		FROM tbl_topic
	) b
	WHERE a.boardid=b.boardid AND a.modifytime=b.modifytime
  
  )a
RIGHT JOIN
  (
  
	SELECT tbl_board.boardid,tbl_board.boardname,tbl_board.parentid,COUNT(tbl_topic.`topicid`) total
	FROM tbl_board
	LEFT JOIN tbl_topic ON tbl_board.`boardid`= tbl_topic.`boardid`
	GROUP BY boardid,boardname,parentid


  
  )b
ON a.boardid=b.boardid;
 



