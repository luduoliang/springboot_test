package com.springboot_test.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Date;


/**
 * jwt token生成、验证类
 * @author Administrator
 *
 */
public class JwtUtils {

	//秘钥
    private static String secret="secret";
	
    /**
     签发对象：这个用户的id
     签发时间：现在
     有效时间：30分钟
     载荷内容：暂时设计为：这个人的名字，这个人的昵称
     加密密钥：这个人的id加上一串字符串
     */
    public static String createToken(String userId) {
    	System.out.println(secret);
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.YEAR, 10);
        Date expiresDate = nowTime.getTime();

        return JWT.create().withSubject(userId)
        		.withIssuer("PDD")
                .withIssuedAt(new Date())
                .withExpiresAt(expiresDate)
                .withClaim("id", userId)
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 检验合法性，其中secret参数就应该传入的是用户的id
     * @param token
     * @throws TokenUnavailable
     */
    public static void verifyToken(String token) throws Exception {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            //效验失败
            //这里抛出的异常是我自定义的一个异常，你也可以写成别的
            throw e;
        }
    }

    /**
    * 获取签发对象
    */
    public static String getAudience(String token) throws Exception {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            //这里是token解析失败
            throw j;
        }
        return audience;
    }


    /**
    * 通过载荷名字获取载荷的值
    */
    public static Claim getClaimByName(String token, String name){
        return JWT.decode(token).getClaim(name);
    }
    
    /**
     * 通过载荷名字获取载荷的值
     */
     public static String getClaimValueByName(String token, String name){
         return JWT.decode(token).getClaim(name).asString();
     }
     
     /**
      * 通过载荷名字获取载荷的值
      */
      public static String getSubjectValue(String token){
          return JWT.decode(token).getSubject();
      }
}