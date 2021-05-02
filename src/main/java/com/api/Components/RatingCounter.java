package com.api.Components;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class RatingCounter {
    public RatingCounter(){}

    public float getRating(List<Integer> grades){
        Optional<Integer> result = grades.stream().reduce((x, y) -> x+y);
        return grades.size() == 0 ? 0 : result.get()/grades.size();
    }
}
