package com.bbshop.service;

import com.bbshop.entity.Account;

public interface BaseService {
    public String sendEmailCode(String email, boolean hasUser,String role);

    public String checkCode(String email,String code,String role);

    public Account login(Account account);

    public void updatePassword(Account account);

    public void register(Account account);
}
