package com.masai.app.book_review.Exception;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ExceptionResponse {
    Date timeStamp;
    String message;
    String details;
}
