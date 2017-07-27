package gsunis.bigdata.service.authority;

import gsunis.bigdata.common.dbentity.authority.UserEntity;
import gsunis.bigdata.dao.authority.UserDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserEntity findByNameAndPassword(String name, String password) {
        return userDao.findByNameAndPassword(name, password);
    }

    @Override
    public List<UserEntity> listUser() {
        return userDao.findAll();
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userDao.save(userEntity);
    }

    @Override
    public void deleteUser(long id) {
        userDao.delete(id);
    }

    @Override
    public Page<UserEntity> pageUser(String name,int page, int size) {
        PageRequest pageRequest=new PageRequest(page,size);
        Page<UserEntity> userEntities = userDao.findAll(new Specification<UserEntity>(){
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if(!"".equals(name)){
                    list.add(cb.like(root.get("name").as(String.class), "%" + name + "%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        },pageRequest);
        return userEntities;
    }

    @Override
    public UserEntity findById(long id) {
        return userDao.findOne(id);
    }
}
