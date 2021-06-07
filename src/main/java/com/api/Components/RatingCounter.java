package com.api.Components;

import com.api.Entities.Score;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class RatingCounter {
    public RatingCounter(){}

//    public float getRating(List<Integer> grades){
//        Optional<Integer> result = grades.stream().reduce((x, y) -> x+y);
//        return grades.size() == 0 ? 0 : result.get()/grades.size();
//    }

    public float getRating(List<Score> scores){
        Optional<Integer> result = scores.stream().map(Score::getScore).reduce((x, y) -> x+y);
        return scores.size() == 0 ? 0 : result.get()/scores.size();
    }
}