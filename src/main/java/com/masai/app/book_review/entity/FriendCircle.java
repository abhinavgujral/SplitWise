package com.masai.app.book_review.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.HashMap;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FriendCircle
{
    @Id
    String fromFriendId;
    String toFriend;
    Boolean giver;
    Boolean taker; //this will never be required
    Integer amount;

    @ManyToOne
    @JsonBackReference
    private User user;

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



