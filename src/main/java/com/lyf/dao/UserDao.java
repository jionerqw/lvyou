package com.lyf.dao;

import com.lyf.domain.User;

public interface UserDao {
    User findByName(String name);

    void save(User user);

    User findByUserName(String username);

    int updateStatus(String activeCode);
}
