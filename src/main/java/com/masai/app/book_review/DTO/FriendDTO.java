package com.masai.app.book_review.DTO;

import com.masai.app.book_review.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendDTO {
    Integer friendCircleId;
    String fromFriendId;
    String toFriend;
    Boolean giver;
    Boolean taker;
    Integer amount;
    private User user;
}
