package com.masai.app.book_review.Exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String msz){
        super(msz);
    }
}
