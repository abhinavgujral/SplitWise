package com.masai.app.book_review.service;

import com.masai.app.book_review.DTO.UserDTO;
import com.masai.app.book_review.entity.User;
import com.masai.app.book_review.modelmapper.ModelMapperClass;
import com.masai.app.book_review.repository.FriendCircleRepository;
import com.masai.app.book_review.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendCircleRepository friendCircleRepository;

    @Autowired
    ModelMapperClass modelMapper;

    public List<UserDTO> getAllUsers()
    {
        List<User> userInfo = userRepository.findAll();

        List<UserDTO> alluserDTO = modelMapper.modelMapper().map(userInfo, new TypeToken<List<UserDTO>>() {}.getType());
        return alluserDTO;
    }


    public UserDTO getSingleUser(String userNameId)
    {
        Optional<User> user = userRepository.findById(userNameId);

           if(user.isEmpty())
            return new UserDTO(); //throw Exception UserNotFound**

        UserDTO userDTO= new UserDTO();
        modelMapper.modelMapper().map(user.get(),userDTO);


        return userDTO ;
    }

    public String verifyLogin(String userNameId, String passWord) //new
    {
        Optional<User> user1 = userRepository.findById(userNameId);

        if(user1.isEmpty())
            return "No User Exists"; //error

        User user = user1.get();

        if(user.getPassWord().equals(passWord))
            return "Successfully Logged in";
        else
            return "Wrong PassWord";
    }
    
    public UserDTO addUser(User user)
    {
        Optional<User> userOptional = userRepository.findById(user.getUserNameId());

        if(userOptional.isPresent())
            return new UserDTO(); // error "Error! UserName already exists. Enter other userName";
        
        User user1 = userRepository.save(user);   // check unique email address Exception
        UserDTO userDTO= new UserDTO();
        modelMapper.modelMapper().map(user1,userDTO);

        return userDTO;
    }

    public String updateUser(UserDTO userDTO)
    {
        User user = new User();
        modelMapper.modelMapper().map(userDTO,user);
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
