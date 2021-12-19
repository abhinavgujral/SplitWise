package com.masai.app.book_review.controller;

import com.masai.app.book_review.DTO.FriendDTO;
import com.masai.app.book_review.DTO.Pair;
import com.masai.app.book_review.entity.FriendCircle;
import com.masai.app.book_review.service.FriendCircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendCircleController
{
    @Autowired
    FriendCircleService friendCircleService;

    @GetMapping("/friendservice/friends")
    public List<FriendDTO> getAllFriendCircles()
    {
        List<FriendDTO> friendCircleInfo = friendCircleService.getAllFriendCircles();
        return friendCircleInfo;
    }

  /*  @GetMapping("/friendservice/friends/{id}")
    public FriendCircle getSingleFriendCircle(@PathVariable("id") String fromFriendId)
    {
        FriendCircle fromFriendId1 = friendCircleService.getSingleFriendCircle(fromFriendId);
        return fromFriendId1;
    } */


    @PostMapping("/friendservice/friends")
    public FriendCircle addFriendCircle(@RequestBody FriendCircle fromFriendId)
    {
        FriendCircle friendCirclei = friendCircleService.addFriendCircle(fromFriendId);
        return friendCirclei;
    }

    @PostMapping("/friendservice/friends/{userId}/{fcId}")  //new method1
    public String addSingleFriendCircleForUserByIds(@PathVariable("userId") String userId,
                                                          @PathVariable("fcId") String friendId)
    {
        String msg = friendCircleService.addSingleFriendCircleForUserByIds(userId, friendId);
        return msg;
    }
    //Divide share
    @PostMapping("user/{username}/bill/{bill}/friends/{num}/divide/{choice}")
    public String addcontribution( @PathVariable("bill") Integer bill, @PathVariable("num") Integer num, @PathVariable("choice") Character choice, @RequestBody List<Pair> pairArray){
          // return " success";
        return friendCircleService.addcontribution(bill,num,choice,pairArray);
    }



    @PutMapping("/friendservice/friends/{userId}/{fcId}/{amount}")  //new method2
    public String modifyFriendCircleByUserId(@PathVariable("userId") String userId,
                                             @PathVariable("fcId") String friendId,
                                             @PathVariable("amount") Integer amount)
    {
        String msg = friendCircleService.modifyFriendCircleByUserId(userId, friendId, amount);
        return msg;
    }

    @GetMapping("/friendservice/payees/friends/getpayees/{fcId}")  //new method3
    public String getListOfPayees(@PathVariable("fcId") String friendId)
    {
        String msg = friendCircleService.getListOfPayees(friendId);
        return msg;
    }

    @GetMapping("/friendservice/payors/friends/getpayors/{fcId}")  //new method3
    public List<FriendCircle> getListOfPayors(@PathVariable("fcId") String friendId)
    {
        List<FriendCircle> friendList = friendCircleService.getListOfPayors(friendId);
        return friendList;
    }

  /*  @PutMapping("friendservice/friends/id")
    public String updateFriendCircle(@RequestBody FriendCircle friendCircle)
    {
        String fromFriendId1 = friendCircleService.updateFriendCircle(friendCircle);
        return fromFriendId1;
    }  */

 /*   @DeleteMapping("/friendservice/friends/{id}")  //Remember {} around id when @PathVariable is used
    public String deleteFriendCircle(@PathVariable("id") String FriendCircleId)
    {
        String msg = friendCircleService.deleteFriendCircle(FriendCircleId);
        return msg;
    }  */


}
