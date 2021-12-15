package com.masai.app.book_review.service;

import com.masai.app.book_review.entity.FriendCircle;
import com.masai.app.book_review.entity.User;
import com.masai.app.book_review.repository.FriendCircleRepository;
import com.masai.app.book_review.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FriendCircleService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendCircleRepository friendCircleRepository;


    public List<FriendCircle> getAllFriendCircles()
    {
        List<FriendCircle> friendCircleList = friendCircleRepository.findAll();
        return friendCircleList;
    }


    public FriendCircle getSingleFriendCircle(String userNameId)
    {
        Optional<FriendCircle> friendCircle = friendCircleRepository.findById(userNameId);

        if(friendCircle.isEmpty())
            return new FriendCircle();

        return  friendCircle.get();
    }


    public FriendCircle addFriendCircle(FriendCircle fromFriend)
    {
        FriendCircle friendCircle = friendCircleRepository.save(fromFriend);
        return friendCircle;
    }


    public String updateFriendCircle(FriendCircle friendCircle)
    {
        Optional<FriendCircle> friendCircle1 = friendCircleRepository.findById(friendCircle.getFromFriendId());

        if(friendCircle1.isEmpty())
            return "FriendList does not exist";

        friendCircle1.get().setToFriend(friendCircle.getToFriend());
        friendCircle1.get().setAmount(friendCircle.getAmount());
        friendCircle1.get().setGiver(friendCircle.getGiver());
        friendCircle1.get().setTaker(friendCircle.getTaker());

        friendCircleRepository.save(friendCircle1.get());
        return  "Info Updated.";
    }


    public String deleteFriendCircle(String friendCircleId)
    {
        FriendCircle friendCircle = friendCircleRepository.findById(friendCircleId).get();

        try
        {
            friendCircleRepository.delete(friendCircle);
            return "Deleted Successfully";
        }
        catch (Exception ex)
        {
            return "Delete Failed";
        }
    }

/*    @Transactional
    public void addFriendCircleListForUser(String userNameId, List<FriendCircle> friendCircleList)
    {
        User user = userRepository.findById(userNameId).get();  //handle user not found exception

        for(FriendCircle friendCircle: friendCircleList)
        {
            friendCircleRepository.save(friendCircle);
            friendCircle.setUser(user);
            user.addFriendCircle(friendCircle);

            friendCircleRepository.save(friendCircle);
        }

    }

    @Transactional
    public void addSingleFriendCircleForUser(String userNameId, FriendCircle friendCircle)
    {
        User user = userRepository.findById(userNameId).get(); //handle user not found exception

        friendCircleRepository.save(friendCircle);
        friendCircle.setUser(user);
        user.addFriendCircle(friendCircle);

        friendCircleRepository.save(friendCircle);
    }     */

    @Transactional
    public String addSingleFriendCircleForUserByIds(String userNameId, String friendCircleId)
    {
        System.out.println("userNameid is "+userNameId);
        System.out.println("frienCircleId is "+friendCircleId);

        User user = userRepository.findById(userNameId).get(); //handle user not found exception
        FriendCircle friendCircle = friendCircleRepository.findById(friendCircleId).get();

        friendCircleRepository.save(friendCircle);
        friendCircle.setUser(user);
        user.addFriendCircle(friendCircle);

        friendCircleRepository.save(friendCircle);

        return "Added to User";
    }

    public String modifyFriendCircleByUserId(String userNameId, String friendCircleId, Integer amount)
    {
        User user = userRepository.findById(userNameId).get(); //handle user not found exception
        FriendCircle friendCircle = friendCircleRepository.findById(friendCircleId).get();

        Integer currentAmount = friendCircle.getAmount();

        if(amount > currentAmount)
        {
            String msg = "Please enter an amount less than or equal to Rs. "+ currentAmount;
            return msg;
        }

        if(friendCircle.getGiver())
        {
            Integer remainingAmount = currentAmount - amount;
            friendCircle.setAmount(remainingAmount);

            if(remainingAmount == 0)
            {
                String msg = "Congrats! You have completely paid your part of the bill";
                friendCircleRepository.save(friendCircle);
                return msg;
            }
            else
            {
                String msg = "Rs. " + amount + "Paid Successfully. You still have to pay Rs. "+remainingAmount;
                friendCircleRepository.save(friendCircle);
                return msg;
            }
        }

        return "Done";
    }
}
