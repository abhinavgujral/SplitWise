package com.masai.app.book_review.service;

import com.masai.app.book_review.Exception.UserNotFound;
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


 /*   public FriendCircle getSingleFriendCircle(String userNameId)
    {
        List<FriendCircle> friendCircleList = friendCircleRepository.findAll();

        Optional<FriendCircle> friendCircle = friendCircleRepository.findById(userNameId);

        if(friendCircle.isEmpty())
            return new FriendCircle();

        return  friendCircle.get();
    }  */


    public FriendCircle addFriendCircle(FriendCircle fromFriend)
    {
        FriendCircle friendCircle = friendCircleRepository.save(fromFriend);
        return friendCircle;
    }


/*    public String updateFriendCircle(FriendCircle friendCircle)
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
    }   */


 /*   public String deleteFriendCircle(String friendCircleId)
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
    }  */

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

        //handle user not found exception
         if(userRepository.findById(userNameId).isPresent())
         {
         User user = userRepository.findById(userNameId).get();

        List<FriendCircle> friendCircleList = friendCircleRepository.findAll();

        for(FriendCircle friendCircle: friendCircleList)
        {
            if( (friendCircle.getFromFriendId().equals(friendCircleId)) && (friendCircle.getToFriend().equals(userNameId)) )
            {
                System.out.println("Found 1 entry");
                System.out.println(friendCircle);
            //    friendCircleRepository.save(friendCircle);
                friendCircle.setUser(user);
                user.addFriendCircle(friendCircle);

                friendCircleRepository.save(friendCircle);
                System.out.println("friend Circle user is -> ");
                System.out.println(friendCircle.getUser());
                System.out.println(user.getFriendList().get(0));
                System.out.println("Done till this");
                return "Added to User";
            }
        }
   //     FriendCircle friendCircle = friendCircleRepository.findById(friendCircleId).get();
             }
                 throw new UserNotFound("-------User Not Found---");
    }

    public String modifyFriendCircleByUserId(String userNameId, String friendCircleId, Integer amount)
    {
        //handle user not found exception
        //User user = userRepository.findById(userNameId).get();
        if(userRepository.findById(userNameId).isPresent())
        {
        List<FriendCircle> friendCircleList = friendCircleRepository.findAll();

        for(FriendCircle friendCircle: friendCircleList) {
            if ((friendCircle.getFromFriendId().equals(friendCircleId)) && (friendCircle.getToFriend().equals(userNameId))) {
                Integer currentAmount = friendCircle.getAmount();

                if (amount > currentAmount) {
                    String msg = "Please enter an amount less than or equal to Rs. " + currentAmount;
                    return msg;
                }

                if (friendCircle.getGiver()) {
                    Integer remainingAmount = currentAmount - amount;
                    friendCircle.setAmount(remainingAmount);

                    if (remainingAmount == 0) {
                        String msg = "Congrats! You have completely paid your part of the bill";
                        friendCircleRepository.save(friendCircle);
                        return msg;
                    } else {
                        String msg = "Rs. " + amount + "Paid Successfully. You still have to pay Rs. " + remainingAmount;
                        friendCircleRepository.save(friendCircle);
                        return msg;
                    }
                }
            }
        }

        }

    //    FriendCircle friendCircle = friendCircleRepository.findById(friendCircleId).get();
        throw new UserNotFound("-------User Not Found---");
    }

     public String getListOfPayees(String friendId)
    {
        String msg = "";
        int j = 0;
        boolean found = false;
        List<FriendCircle> friendCircleList = friendCircleRepository.findAll();

        for(FriendCircle friendCircle: friendCircleList)
        {
            if(friendCircle.getFromFriendId().equals(friendId))
            {
                found = true;
                ++j;
                //replace below with StringBuilder class
                msg += j + " )   " + friendCircle.getFromFriendId() + " has to give Rs. "+ friendCircle.getAmount() + " to "+ friendCircle.getUser().getPublicName() + " ( userName = " + friendCircle.getUser().getUserNameId() + " )   ";
            }
        }

        if(found)
            return msg;
        else
            return "You dont have to give any money to anybody";



    /*    List<FriendCircle> friendCircleList = friendCircleRepository.findAll();
        String msg = "";
        int i = 0;
        for(FriendCircle friendCircle: friendCircleList)
        {
            ++i;
            User user = friendCircle.getUser();
            msg  +=  i + ")  +  " + friendId + " has to give Rs "+ friendCircle.getAmount() + " to "+ user.getPublicName();
        }
        return msg;   */

    }

    @Transactional
    public void test()
    {
        System.out.println("In test : ");

        System.out.println("For Rahul =>");
        System.out.println(userRepository.findById("Rahul123").get().getFriendList());

        System.out.println("For Bagul =>");
        System.out.println(userRepository.findById("Bagul123").get().getFriendList());

    }
}
