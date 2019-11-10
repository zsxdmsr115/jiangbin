INSERT INTO tbl_topic(title,content,publishtime,modifytime,uid,boardid)
VALUES('java good','�ܺ�',NOW(),NOW(),3,8);

INSERT INTO tbl_topic(title,content,publishtime,modifytime,uid,boardid)
VALUES('java very good','�ǳ���',NOW(),NOW(),3,8);


INSERT INTO tbl_reply (title,content,publishtime,modifytime,uid,topicid)
VALUES('java good reply','��ͬ�� java �ǳ���',NOW(),NOW(),3,1);

INSERT INTO tbl_reply (title,content,publishtime,modifytime,uid,topicid)
VALUES('java good reply','��ͬ�� java �ǳ���22',NOW(),NOW(),3,1);


INSERT INTO tbl_reply (title,content,publishtime,modifytime,uid,topicid)
VALUES('java very good reply','��ͬ�� java �ǳ���2',NOW(),NOW(),3,2);

 INSERT INTO tbl_reply (title,content,publishtime,modifytime,uid,topicid)
VALUES('java very good22 reply','��ͬ�� java22 �ǳ���2',NOW(),NOW(),3,2);
/*���ӻظ���*/
SELECT  topicid,COUNT(replyid) total
FROM tbl_reply GROUP BY topicid
/*��ҳ
 1:��������ʱ�������
*/
SELECT * FROM  tbl_topic

SELECT  boardid,MAX(modifytime)
  FROM tbl_topic
/*
2:��������˵����֣���ţ����ӱ��⣬���ӱ�� �����
*/
SELECT  tbl_topic.`topicid`, tbl_topic.`title` ,tbl_topic.`boardid` ,tbl_topic.`modifytime` AS modifytime ,tbl_user.`uid`, tbl_user.`uname` 
 FROM tbl_topic 
 LEFT JOIN tbl_user ON  tbl_topic.`uid`=tbl_user.`uid`
 
 /*
  �����������������ÿ������������Ϣ 
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
  ͳ�Ƹ�������������

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
 



