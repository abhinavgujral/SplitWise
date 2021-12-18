package com.masai.app.book_review.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionHistory
{
    @Id
    @GeneratedValue
    Integer TransactionHistoryId;
    String toFriend;
    String fromFriend;
    Integer Amount;
    Date date;

}
