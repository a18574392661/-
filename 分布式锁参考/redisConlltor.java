package com.itheima.controller;

import com.itheima.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by 我自己 on 2020-03-20.
 */
@Controller
public class redisConlltor {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    /*
        synchronizedt同步锁只能解决单机版的项目 集群无法解决
        用setnx或者redission 或者zookeeper

     */


    @ResponseBody
    @RequestMapping("/test1")
    public  void  q(){
      boolean b=  redisUtil.setIfAbsent("nx","nx2",60);
        System.out.println(b);
    }
    @ResponseBody
    @RequestMapping("test")
    public  Object test(){
        String key="count";
        String nxKey="nxKey";

        //减少库存的业务

        String token= UUID.randomUUID().toString();
        boolean nx=redisUtil.setIfAbsent(nxKey,"nx"+token,10);
        if (!nx){
            return test();//进行自悬
        }

        String count=redisUtil.get(key)+"";
        try {
           if (StringUtils.isNotBlank(count)){
               //多线程情况下去拿锁 redis是单线程 请求放队列里面 一个个执行
               //拿到锁 如果拿到了就代表缓存没数据 可以操作业务逻辑
               int sum=Integer.parseInt(count);
               if (sum>0){
                   sum--;
                   System.out.println("购买成功数量减少"+sum);
                   redisUtil.set("count",sum);
               }else {
                   System.out.println("没有库存了");
               }

           }else {
               System.out.println("没有库存为null");
           }


       }catch (Exception e){
           e.printStackTrace();
       }finally {
            //释放锁
           //其实小公司没必要了
           //最好判断一下自己的 键盘 能在特别高的并发下的安全问题
           if (("nx"+token).equals(redisUtil.get(nxKey))){
               redisUtil.del(nxKey);
           }



       }


        return 0;


    }

    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping("testRedisson")
    @ResponseBody
    public  void testRedisson(){
        String key="count";
        String nxKey="nxKey";

        RLock rLock=redissonClient.getLock(nxKey);
        try {

      boolean sucess=   rLock.tryLock(10, TimeUnit.SECONDS);
      if (sucess==true){
          String count=redisUtil.get(key)+"";
          if (StringUtils.isNotBlank(count)){
              //多线程情况下去拿锁 redis是单线程 请求放队列里面 一个个执行
              //拿到锁 如果拿到了就代表缓存没数据 可以操作业务逻辑
              int sum=Integer.parseInt(count);
              if (sum>0){
                  sum--;
                  System.out.println("购买成功数量减少"+sum);
                  redisUtil.set("count",sum);
              }else {
                  System.out.println("没有库存了");
              }

          }else {
              System.out.println("没有库存为null");
          }


      }


        }
        catch (Exception e){

            e.printStackTrace();
        }
        finally {
            rLock.unlock();
        }



    }
}
