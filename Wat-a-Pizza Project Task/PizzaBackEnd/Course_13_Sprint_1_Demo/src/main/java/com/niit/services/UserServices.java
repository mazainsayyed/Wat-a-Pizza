package com.niit.services;

import com.niit.domain.User;
//import com.niit.proxy.UserProxy;
import com.niit.repositery.UserRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServices implements  InterService{
    UserRepositery userRepositery;

@Autowired
    public UserServices(UserRepositery userRepositery) {
        this.userRepositery = userRepositery;
    }

    @Override
    public User addData(User user) {
      if (userRepositery.findById(user.getEmail()).isEmpty())
      {
          return userRepositery.save(user);
      }
      return null;
    }

    @Override
    public List<User> getAllData() {
        return userRepositery.findAll();
    }

    @Override
    public String DeleteData(String email) {
       if (userRepositery.findById(email).isEmpty())
       {
           return  "Data Not Available";
       }
       userRepositery.deleteById(email);
       return "Data delete Successfully";
    }

    @Override
    public User Update(User user) {
      if (userRepositery.findById(user.getEmail()).isEmpty())
      {
          return  null;
      }
      User tempUser=userRepositery.findById(user.getEmail()).get();

      tempUser.setFirstName(user.getFirstName());
      tempUser.setLastName(user.getLastName());
      tempUser.setPassword(user.getPassword());

      return  userRepositery.save(tempUser);

    }

    @Override
    public List<User> getAllUserByFirstName(String firstName) {
        return userRepositery.findByfirstName(firstName);
    }

    @Override
    public User loginCheck(String email, String password) {
     if (userRepositery.findById(email).isPresent())
     {
         //fetch user object by email id
         User result=userRepositery.findById(email).get();
         if (result.getPassword().equals(password))
         {
             return result;
         }
         else
         {
             return null;
         }
     }
     else
     {
         return null;
     }
    }
}
