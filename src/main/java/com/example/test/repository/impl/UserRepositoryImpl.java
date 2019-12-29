package com.example.test.repository.impl;

import com.mongodb.client.result.UpdateResult;
import com.example.test.repository.UserRepository;
import com.example.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public User findUserByUserName(String userName) {
        Query query = new Query(Criteria.where("userName").is(userName));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public long updateUser(User user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
        // 更新查询返回结果集的第一条
        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
        // 更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        return result.getMatchedCount();
    }

    @Override
    public void deleteUserById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, User.class);
    }
}