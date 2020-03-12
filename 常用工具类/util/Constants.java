package com.atguigu.gmall.util;

public class Constants {
/*
 * sku查询信息 包括图片的redis键声称规则
 */
	public static final String Sku="Sku:";
	public static final String info=":info";
	public static final String Lock=":lock";
	public static final int ReditDeTime=3000;
	public static final String Spu="Spu:";
	public static final String AttrAndValue=":AttrAndValue";
	//sku的spu商品销售属性 选中sku的属性
	
	//cookie保存购物车
	public static final String CookieSkuListName="SkuCookieList";
	
	//redis保存购物车的名字 
	public static final String CacheSkuListName="CacheCookieList:";
	
	//用户直接登陆成功跳转的路径
		public static final String LOGINSUCESSU_STRING="http://search.gmall.com:8083/index";
		//用户保存到cookie的token
		public static final String UserCookieToken = "UserCookieToken";
		
		//去登陆页面 
		public static final String ToLoginUrl="http://passport.gmall.com:8085/index.html?toUrl=";
		//用户保存的key 
		public static final String UserKey="UserKeyUserName:";
		//JWY加密的key
		public static final String JwyKey = "TZW2020210";
	
		//第三方登录授权码
		public static final String App_Key = "1865865070";
		//第三方登录授权码
		public static final String App_Secret = "000a6dd5a642e74a41d656599a24e27f";
		public static final String OrderKey = "UserSubmitOrderCode:";
		//拼接某个用户的订单 
		public static final String UserOrderKey = "UserOrderKey:";
		
		public static final String OrderKeyByid = "OrderKeyByid:";
		
		//用户地址key
		public static String userAddrKey="UserAddrKey:";
		
		//测试
		public static final String UAll = "UAll";
		
}
