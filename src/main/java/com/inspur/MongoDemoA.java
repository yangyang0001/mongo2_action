package com.inspur;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * User: YANG
 * Date: 2019/7/2-22:39
 * Description: No Description
 * 强烈记住MongoDB只能做这些东西,因为Mongo不是MySQL, 不是Oracle
 */
public class MongoDemoA {

	public static void main(String[] args) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("192.168.120.150" , 27017);
		DB db = mongoClient.getDB("yang");
		//进行用户名和密码的授权认证
		db.authenticate("root", "123456".toCharArray());

		for (String collectionName : db.getCollectionNames()) {
			System.out.println("collectionName ------------->:" + collectionName);
		}

		//首先得到一个集合
		DBCollection dbCollection = db.getCollection("mongo2_coll");

		//**********************数据的插入**********************
//		for(int i = 10; i < 50; i++){
//			BasicDBObject basicDBObject = new BasicDBObject();
//			basicDBObject.append("deptno", 1000 + i + "");
//			basicDBObject.append("dname", "技术部-" + i);
//			basicDBObject.append("location", "北京-" + i);
//			//插入数据
//			dbCollection.insert(basicDBObject);
//		}
		System.out.println("----------------------------------------------------------------------------------");

		//**********************数据全部查询********************
		DBCursor cursor = dbCollection.find();
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			System.out.println(obj.toString());
		}

		System.out.println("----------------------------------------------------------------------------------");
		//分页查询:
		//设置分页条件
//		DBObject queryObject = new BasicDBObject("deptno", new BasicDBObject("$gte", "1003").append("$lte", "1008"));

		//范围查询
//		DBObject queryINObject = new BasicDBObject("deptno", new BasicDBObject("$in", new String[] {"1000", "1002", "1004", "1006"}));

		//模糊查询
//		DBObject queryMHObject = new BasicDBObject("deptno", new BasicDBObject("$regex", Pattern.compile("5")));
//		DBCursor pageCursor = dbCollection.find(queryMHObject).skip(0).limit(5);

//		DBCursor pageCursor = dbCollection.find().skip(0).limit(5);
//		while(pageCursor.hasNext()){
//			DBObject obj = pageCursor.next();
//			System.out.println(obj.toString());
//		}

		//**********************数据的修改**********************
		//修改条件
//		DBObject sourceObject = new BasicDBObject("deptno", "1000");
//		DBObject sourceObject = new BasicDBObject("deptno", new BasicDBObject("$gte", "1000").append("$lte", "1002"));

		//必须写全了因为这里没有设置修改器$set,否则覆盖了 类似与修改多行
//		DBObject destinObject = new BasicDBObject("deptno", "1000").append("dname", "修改后的部门名称").append("location", "北京-0");
		//修改一个字段的数据的操作
//		DBObject destinObject = new BasicDBObject("$set", new BasicDBObject("dname" , "修改后的部门名称00000001"));
		//修改多个字段的数据
		DBObject destinObject = new BasicDBObject("$set", new BasicDBObject("dname" , "修改后的部门名称00000000").append("location", "北京-00000000"));

		//修改单行
//		dbCollection.update(sourceObject, destinObject);
		//修改多行的操作
//		dbCollection.updateMulti(sourceObject, destinObject);


		//**********************数据的删除**********************
		DBObject sourceObject = new BasicDBObject("deptno", new BasicDBObject("$gte", "1000").append("$lte", "1002"));
		dbCollection.remove(sourceObject);

		mongoClient.close();
	}


}
