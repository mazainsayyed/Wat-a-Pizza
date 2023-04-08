package com.niit.services;

import com.niit.domain.User;

import java.util.List;

public interface InterService {
    public User addData(User user);
    public List<User> getAllData();
    public  String DeleteData(String email);
    public  User Update(User user);
    public  List <User> getAllUserByFirstName(String firstName);
    public User loginCheck(String email, String password);
}
