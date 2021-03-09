package org.example;

import org.example.generated.model.User;
import org.example.generated.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public Long addUser(String name, Integer age) {
        // 如果 name = 'zhh', age = 18
        // 对应的sql是: insert into user(name, age) values('zhh', 18);
        return new User().name.set(name).age.set(age).insert(); // 链式调用，insert()返回新增记录的rowId
    }


    @Override
    public User findByName(String name) {
        // 如果 name = 'zhh'
        // 对应的sql是: select * from user where name = 'zhh' limit 1
        return User.dao.where().name.eq(name).findOne();
    }
}
