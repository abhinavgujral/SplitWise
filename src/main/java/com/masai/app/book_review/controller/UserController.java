package com.masai.app.book_review.controller;

import com.masai.app.book_review.DTO.UserDTO;
import com.masai.app.book_review.entity.User;
import com.masai.app.book_review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController
{
    @Autowired
    UserService userService;

    @GetMapping("/userservice/users")
    public List<UserDTO> getAllUsers()
    {
      return  userService.getAllUsers();
    }

    @GetMapping("/userservice/users/{id}")
    public UserDTO getSingleUser(@PathVariable("id") String userNameId)
    {
        UserDTO userDTO = userService.getSingleUser(userNameId);
        return userDTO;
    }

    @PostMapping("/userservice/users")
    public UserDTO addUser(@RequestBody User user)
    {
        UserDTO user1 = userService.addUser(user);
        return user1;
    }

// In Postman use following FORMAT to add user
//  {
//    "userId": 111,
//        "name" : "let us C",
//        "user" : "Yashvant",
//        "publication" : "B2B",
//        "category" : "Computer User",
//        "pages": 500,
//        "price" : 560
//  }


    @PutMapping("userservice/users/id")
    public String updateUser(@RequestBody UserDTO userDTO)
    {
        String user1 = userService.updateUser(userDTO);
        return user1;
    }

    @DeleteMapping("/userservice/users/{id}")  //Remember {} around id when @PathVariable is used
    public String deleteUser(@PathVariable("id") String UserId)
    {
        String msg = userService.deleteUser(UserId);
        return msg;
    }

}