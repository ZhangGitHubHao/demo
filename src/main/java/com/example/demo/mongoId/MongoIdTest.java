//package com.example.demo.mongoId;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * 测试类
// *
// * @author 860120014
// * @date 2021-07-21
// */
//public class MongoIdTest {
//    Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    public CarMongoRepository carMongoRepository;
//
//    public void mongodbIdTest(){
//        Car customer=new Car("宝马",500000d);
//        carMongoRepository.save(customer);
//        logger.info( "mongodbId:"+customer.getMongoId());
//    }
//}
