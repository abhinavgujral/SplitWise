package com.masai.app.book_review.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pair {

    String username;
    int contribution;

    public Pair() {
    }

    public Pair(String username, int contribution) {
        this.username = username;
        this.contribution = contribution;
    }
}
