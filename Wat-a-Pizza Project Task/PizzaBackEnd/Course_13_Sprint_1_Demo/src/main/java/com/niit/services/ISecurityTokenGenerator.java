package com.niit.services;

import com.niit.domain.User;

import java.util.Map;

public interface ISecurityTokenGenerator {
    public Map<String,String> tokenGenerator(User user);
}
