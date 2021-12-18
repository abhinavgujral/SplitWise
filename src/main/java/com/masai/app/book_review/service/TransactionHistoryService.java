package com.masai.app.book_review.service;

import com.masai.app.book_review.entity.TransactionHistory;
import com.masai.app.book_review.entity.User;
import com.masai.app.book_review.repository.TransactionHistoryRepository;
import com.masai.app.book_review.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryService
{
    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    UserRepository userRepository;

    public TransactionHistory addRecord(TransactionHistory record)
    {
       TransactionHistory transactionHistory = transactionHistoryRepository.save(record);
       return transactionHistory;
    }


    public String getFullTransactionHistoryByUserId(String userNameId)
    {
        if(userRepository.findById(userNameId).isEmpty())
            return "Invalid UserName!";  // error

        List<TransactionHistory> transactionHistoryList = transactionHistoryRepository.findAll();
        String msg1 = "Money Recieved : \n";
        String msg2 = "Money Sent : \n";
        boolean found1 = false;
        boolean found2 = false;

        int p = 0;
        int q = 0;

        for(TransactionHistory record: transactionHistoryList)
        {
            if( (record.getToFriend().equals(userNameId)) )
            {
                if(!found1)
                    found1 = true;
                p++;
                msg1 += p+") " + record.getToFriend() + " recieved Rs. " + record.getAmount() + " from " + record.getFromFriend()  + " on " + record.getDate() + ". \n";
            }
            else if(record.getFromFriend().equals(userNameId))
            {
                if(!found2)
                    found2 = true;

                q++;
                msg2 += q + ") " + record.getFromFriend() + " paid Rs. " + record.getAmount() + " to " + record.getToFriend() + " on " + record.getDate() + ". \n";
            }
        }

        if(found1 && !found2)
            return msg1;

        if(!found1 && found2)
            return msg2;

        if(found1 && found2)
        {
            String msg3 = msg1 + "\n" + msg2;
            return msg3;
        }

        return "No Transaction found for " + userNameId;
    }


    public String getTransactionHistoryAmongTwoUsers(String userOne, String userTwo) //userTwo is mostly friendCrcleId
    {
        List<TransactionHistory> transactionHistoryList = transactionHistoryRepository.findAll();
        String msg1 = "Money Sent : \n";
        String msg2 = "Money Recieved: \n";
        boolean found1 = false;
        boolean found2 = false;

        int p = 0;
        int q = 0;

        for(TransactionHistory record: transactionHistoryList)
        {
            if( (record.getFromFriend().equals(userOne))  &&  (record.getToFriend().equals(userTwo)) )
            {
                if(!found1)
                    found1 = true;

                p++;
                msg1 += p+") " +record.getFromFriend() + " paid Rs. " + record.getAmount() + " to " + record.getToFriend() + " on " + record.getDate() + ". \n";
            }
            else if( (record.getToFriend().equals(userOne))  &&  (record.getFromFriend().equals(userTwo)) )
            {
                if(!found2)
                    found2 = true;

                q++;
                msg2 += q+") " +record.getToFriend() + " recieved Rs. " + record.getAmount() + " from " + record.getFromFriend() + " on " + record.getDate() + ". \n";
            }
        }

        if(found1 && !found2)
            return msg1;

        if(!found1 && found2)
            return msg2;

        if(found1 && found2)
        {
            String msg3 = msg1 + "\n" + msg2;
            return msg3;
        }

        return "No Transaction is done so far between " + userOne +" and " + userTwo;
    }

}
