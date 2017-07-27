package gsunis.bigdata.common.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述 ：token工具类
 *
 * @author : maozebing
 * @version : v1.00
 * @CreationDate : 16-6-12 下午13:50
 * @Description :
 * @update : 修改人，修改时间，修改内容
 * @see :[相关类/方法]
 */
public class JWTUtil {

    private static final String SECRET="hnlwzxxm";
    private static final String ISSUER="www.gsunis.net";

    public static String createToken(String userName) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Calendar calendar = Calendar.getInstance();
        Date iat=calendar.getTime();

        String token = JWT.create()
                .withHeader(map)//header
                .withIssuer(ISSUER)
                .withAudience(userName)
                .withIssuedAt(iat)
                .sign(Algorithm.HMAC256(SECRET));//加密
        return token;
    }

    public static boolean verifyToken(String token) {
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            String iss=jwt.getClaims().get("iss").asString();
            if (ISSUER.equals(iss)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

}
