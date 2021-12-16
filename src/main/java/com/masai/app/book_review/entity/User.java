package com.masai.app.book_review.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User
{
    @Id
    String userNameId;
    String publicName;
    String emailAddress;
    String passWord;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    List<FriendCircle> friendList = new ArrayList<>();

    public List<FriendCircle> getFriendList() {
        return friendList;
    }

    public void addFriendCircle(FriendCircle friendCircle) {
        this.friendList.add(friendCircle);
    }

    public void removeFriendCircle(FriendCircle friendCircle){
        this.friendList.remove(friendCircle);
    }
}


//{
//    "userNameId": "Rohan123",
//    "publicName": "Rohan",
//    "emailAddress": "rohan@gmail.com",
//    "passWord": 456
//}



