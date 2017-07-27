package gsunis.bigdata.dao.authority;

import gsunis.bigdata.common.dbentity.authority.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by mzb on 2017/6/20.
 */
public interface UserDao extends JpaSpecificationExecutor<UserEntity>,JpaRepository<UserEntity,Long> {
    UserEntity findByNameAndPassword(String name, String password);
}
