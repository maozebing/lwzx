package gsunis.info.common.utility;

/**
 * 描述 ：
 *
 * @author : maozebing
 * @version : v1.00
 * @CreationDate : 2017/4/12 16:18
 * @Description :
 * @update : 修改人，修改时间，修改内容
 * @see :[相关类/方法]
 */
public class EnumClass {

    /**
     * 响应码
     */
    public enum HttpStatusCode {
        成功(200),
        参数错误(400),
        未授权(401),
        业务异常(402),
        验证失败(404),
        服务器错误(500);
        private int value;

        private HttpStatusCode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
