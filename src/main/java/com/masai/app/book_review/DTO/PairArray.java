package com.masai.app.book_review.DTO;



import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PairArray{

    List<Pair> pairList = new ArrayList<>();

    public PairArray(List<Pair> pairList) {
        this.pairList = pairList;
    }

    public PairArray() {

    }
}


