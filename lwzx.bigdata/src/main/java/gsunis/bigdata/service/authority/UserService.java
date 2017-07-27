package gsunis.bigdata.service.authority;


import gsunis.bigdata.common.dbentity.authority.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

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
public interface UserService {
    UserEntity findByNameAndPassword(String name, String password);
    List<UserEntity> listUser();
    void saveUser(UserEntity userEntity);
    void deleteUser(long id);
    Page<UserEntity> pageUser(String name,int page, int size);
    UserEntity findById(long id);
}
