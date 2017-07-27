package gsunis.bigdata.common.exception;

/**
 * 描述 ：业务异常类
 *
 * @author : maozebing
 * @version : v1.00
 * @CreationDate : 2016/6/13 下午13:50
 * @Description :
 * @update : 修改人，修改时间，修改内容
 * @see :[相关类/方法]
 */
public class BusinessException extends  Exception{

    public BusinessException()
    {
        super();
    }

    public BusinessException(String message)
    {
        super(message);
    }

    public BusinessException(String message, Exception exception)
    {
        super(message,exception);
    }
}
