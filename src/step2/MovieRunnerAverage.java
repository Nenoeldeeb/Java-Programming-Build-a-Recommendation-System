package step2;

import step1.Rating;

import java.util.Collections;
import java.util.List;

public class MovieRunnerAverage extends Object{
    public MovieRunnerAverage() {super();}

    public static void printAverageRatings(){
        Object secondRatings = new SecondRatings();
        int movieNum = ((SecondRatings) secondRatings).getMovieSize();
        int raterNum = ((SecondRatings) secondRatings).getRaterSize();
        List avg = ((SecondRatings) secondRatings).getAverageRatings(12);
        Collections.sort(avg);
        System.out.println(movieNum+" "+raterNum+"\nrated "+ avg.size());
        for(int i=0;i<avg.size();i++){
            Rating rating = (Rating) avg.get(i);
            String title = ((SecondRatings) secondRatings).getTitle(rating.getItem());
            double value = rating.getValue();
            System.out.println(value+"  "+title);
        }
    }

    public static void getAverageRatingOneMovie(){
        SecondRatings secondRatings = new SecondRatings("data/quiz2movies.csv","data/quiz2raters.csv");
        String id = secondRatings.getID("Inside Llewyn Davis");
        List list = secondRatings.getAverageRatings(1);
        for(int i=0;i<list.size();i++){
            Rating rating = (Rating) list.get(i);
            if(rating.getItem().equals(id)){
                System.out.println("\n"+rating.getValue()+"  "+secondRatings.getTitle(id));
            }
        }
    }

    public static void main(String[] args) {
        printAverageRatings();
        getAverageRatingOneMovie();
    }
}
