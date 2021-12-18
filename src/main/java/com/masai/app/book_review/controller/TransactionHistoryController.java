package com.masai.app.book_review.controller;

import com.masai.app.book_review.entity.TransactionHistory;
import com.masai.app.book_review.entity.User;
import com.masai.app.book_review.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionHistoryController
{
    @Autowired
    TransactionHistoryService transactionHistoryService;

    @PostMapping("/history/addrecord")
    public TransactionHistory addRecord(@RequestBody TransactionHistory record)
    {
        TransactionHistory transactionHistory = transactionHistoryService.addRecord(record);
        return transactionHistory;
    }

    @GetMapping("/history/getfullhistory")
    public String getFullTransactionHistoryByUserId(String userNameId)
    {
        String entireHistory = transactionHistoryService.getFullTransactionHistoryByUserId(userNameId);
        return entireHistory;
    }

    @GetMapping("/history/betweentwousers/{user1}/{user2}")
    public String getTransactionHistoryAmongTwoUsers(@PathVariable("user1") String userOne,
                                                     @PathVariable("user2") String userTwo)
    {
        String betweenHistory = transactionHistoryService.getTransactionHistoryAmongTwoUsers(userOne, userTwo);
        return  betweenHistory;
    }

}
