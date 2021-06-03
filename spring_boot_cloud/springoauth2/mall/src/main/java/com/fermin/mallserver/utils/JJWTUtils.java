package com.fermin.mallserver.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class JJWTUtils {

    /**
     * 默认过期时间 10s
     */
    private static Long DEFAULT_TTLMillis = 10 * 1000L;

    /**
     * 对称加密盐值
     */
    private static String PRIVATEKEY = "U2FsdGVkX1/tbhVf7Kzop78AJ3GcJWGsXrAxRtg/027Q5r8M8YyR7gAEVNZyoyEn";

    /**
     * jwt解密，需要密钥和token，如果解密失败，说明token无效
     *
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken)
                    .getBody();
            return claims;
        } catch (JwtException ex) {
            log.error(String.format("token 解析失败 token = %s, key = %s /n", jsonWebToken, base64Security), ex);
            return null;
        }
    }

    /**
     * 创建InnerToken
     *
     * @return
     */

    public static String createJWT(String issuer) {
        try {
            return createJWT(new HashMap<>(), issuer, DEFAULT_TTLMillis, PRIVATEKEY);
        } catch (Exception e) {
            log.error("创建token失败", e);
            return null;
        }
    }

    /**
     * 创建token
     *
     * @param map            主题，也差不多是个人的一些信息，为了好的移植，采用了map放个人信息，而没有采用JSON
     * @param issuer         签发人
     * @param TTLMillis      Token过期时间
     * @param base64Security 生成签名密钥
     * @return
     */
    public static String createJWT(Map map, String issuer, long TTLMillis, String base64Security) throws Exception {

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //生成签名密钥 就是一个base64加密后的字符串
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setClaims(map)//必须放最前面，不然后面设置的东西都会没有：如setExpiration会没有时间
                .setId(UUID.randomUUID().toString())
                .setIssuer(issuer)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey);  //设置签名使用的签名算法和签名使用的秘钥

        //添加Token过期时间
        if (TTLMillis >= 0) {
            // 过期时间
            long expMillis = nowMillis + TTLMillis;
            // 现在是什么时间
            Date exp = new Date(expMillis);
            // 系统时间之前的token都是不可以被承认的
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();

    }

//    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("dream", "fall in love with work");
//        try {
//            String token = JJWTUtils.createJWT(null, "test", 30 * 1000, PRIVATEKEY);
//
//            System.out.println(token);
//
//            JJWTUtils.parseJWT(token, PRIVATEKEY);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
