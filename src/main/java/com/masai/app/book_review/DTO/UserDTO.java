package com.masai.app.book_review.DTO;

import com.masai.app.book_review.entity.FriendCircle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    String userNameId;
    String publicName;
    String emailAddress;
    String passWord;
    List<FriendCircle> friendList = new ArrayList<>();
}
