package com.bbshop.service;

import com.bbshop.entity.Account;
import com.bbshop.entity.User;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


public interface UserService {
    public void add(User user);

    public void deleteById(Integer id);



    public void deleteBatch(List<Integer> ids);
    public void updateById(User user,String code,Boolean emailState);

    public int updateByEmail(User user); //根据邮箱修改密码

    public User selectById(Integer id);

    public List<User> selectAll(User user);

    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize);


}
