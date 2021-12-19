package com.masai.app.book_review.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.HashMap;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FriendCircle
{
    @Id
    @GeneratedValue
    Integer friendCircleId;
    String fromFriendId;
    String toFriend;
    Boolean giver;
    Boolean taker; //useless
    Integer amount;

    @ManyToOne
    @JsonBackReference
    private User user;


    public void setUser(User user) {
        this.user = user;
    }
}

//
//   {
//        "fromFriendId": "fromName123",
//        "toFriend": "Modify123",
//        "giver": "true",
//        "taker": "true",
//        "amount": 100
//   }
//



