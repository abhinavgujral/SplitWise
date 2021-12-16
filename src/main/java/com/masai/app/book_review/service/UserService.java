package com.masai.app.book_review.service;

import com.masai.app.book_review.entity.User;
import com.masai.app.book_review.repository.FriendCircleRepository;
import com.masai.app.book_review.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendCircleRepository friendCircleRepository;


    public List<User> getAllUsers()
    {
        List<User> userInfo = userRepository.findAll();
        return userInfo;
    }


    public User getSingleUser(String userNameId)
    {
        Optional<User> user = userRepository.findById(userNameId);

        if(user.isEmpty())
            return new User();

        return user.get();
    }


    public User addUser(User user)
    {
        User user1 = userRepository.save(user);
        return user1;
    }

    public String updateUser(User user)
    {
        Optional<User> user1 = userRepository.findById(user.getUserNameId());

        if(user1.isEmpty())
            return "No User Found"; // error

        user1.get().setEmailAddress(user.getEmailAddress());
        user1.get().setPassWord(user.getPassWord());
        user1.get().setPublicName(user.getPublicName());
        //try to change the userNameId by first checking that it is unique and not already exists in the repo.

        userRepository.save(user1.get());
        return  "Info Updated";
    }


    public String deleteUser(String userId)
    {
        User user = userRepository.findById(userId).get();

        try
        {
            userRepository.delete(user);
            return "Deleted Successfully";
        }
        catch (Exception ex)
        {
            return "Delete Failed"; // error
        }
    }
}
