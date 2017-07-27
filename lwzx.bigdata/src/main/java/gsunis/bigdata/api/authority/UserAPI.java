package gsunis.bigdata.api.authority;

import gsunis.bigdata.common.dbentity.authority.UserEntity;
import gsunis.bigdata.common.utility.EnumClass;
import gsunis.bigdata.common.utility.JWTUtil;
import gsunis.bigdata.common.viewentity.authority.ViewCookieUserEntity;
import gsunis.bigdata.service.authority.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Hashtable;

/**
 * 描述 ：
 *
 * @author : maozebing
 * @version : v1.00
 * @CreationDate : 2017/1/6 20:55
 * @Description :
 * @update : 修改人，修改时间，修改内容
 * @see :[相关类/方法]
 */
@Component
@Path("user")
public class UserAPI {

    @Autowired
    private UserService userService;

    @Autowired
    HttpServletRequest request;

    private Logger logger = LoggerFactory.getLogger(UserAPI.class);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @Path("/login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("username") String username,
                          @QueryParam("password") String password) {
        try {
            UserEntity userEntity = userService.findByNameAndPassword(username, password);
            if (userEntity != null) {
                ViewCookieUserEntity cookieUserEntity = new ViewCookieUserEntity();
                cookieUserEntity.setId(userEntity.getId());
                cookieUserEntity.setName(userEntity.getName());
                String token = JWTUtil.createToken(userEntity.getName());
                cookieUserEntity.setToken(token);
                return Response.ok(cookieUserEntity).build();
            } else {
                return Response.status(EnumClass.HttpStatusCode.未授权.getValue()).entity("工号或密码错误！").build();
            }
        } catch (Exception e) {
            return Response.status(EnumClass.HttpStatusCode.未授权.getValue()).entity("工号或密码错误！").build();
        }
    }

    /**
     * 根据用户id获取用户信息
     *
     * @return
     */
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        try {
            UserEntity userEntity=userService.findById(id);
            return Response.ok(userEntity).build();
        } catch (Exception e) {
            return Response.status(EnumClass.HttpStatusCode.服务器错误.getValue()).entity(EnumClass.HttpStatusCode.服务器错误).build();
        }
    }

    /**
     * 获取所有用户
     *
     * @return
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUser() {
        try {
            return Response.ok(userService.listUser()).build();
        } catch (Exception e) {
            return Response.status(EnumClass.HttpStatusCode.服务器错误.getValue()).entity(EnumClass.HttpStatusCode.服务器错误).build();
        }
    }

    @Path("/pagination")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response paginationUser(@DefaultValue("") @QueryParam("name") String name,
                                   @QueryParam("page") int page,
                                   @QueryParam("size") int size){
        Page<UserEntity> userEntities=userService.pageUser(name,page-1,size);
        Hashtable table=new Hashtable();
        table.put("total",userEntities.getTotalElements());
        table.put("data",userEntities.getContent());

        return Response.ok(table).build();
    }

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTest(UserEntity userEntity){
        userService.saveUser(userEntity);
        return Response.ok().build();
    }

    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTest(@PathParam("id") long id){
        userService.deleteUser(id);
        return Response.ok().build();
    }

    /**
     * 退出登录
     *
     * @return
     */
    @Path("/loginout")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity loginOut() {
        return ResponseEntity.ok().build();
    }

}
