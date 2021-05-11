package step3;

import step1.Rating;
import step2.FirstRatings;

import java.util.ArrayList;
import java.util.List;

public class ThirdRatings extends Object{
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }

    public ThirdRatings(String ratersFile) {
        super();
        Object firestRatings = new FirstRatings();
        myRaters = (ArrayList<Rater>) ((FirstRatings) firestRatings).loadRaters(ratersFile);
    }


    public int getRaterSize() {return myRaters.size();}

    private double getAverageByID(String id,int minRatings){
        int raters = 0;
        double ratings = 0.0;
        for(int i=0;i<myRaters.size();i++){
            Rater rater = (EfficientRater) myRaters.get(i);
            if(rater.hasRating(id)){
                raters ++;
                ratings += rater.getRating(id);
            }
        }
        if(raters >= minRatings)return ratings/raters;
        return 0.0;
    }

    public List getAverageRatings(int minRaters){
        List ratingList = new ArrayList<Rating>();
        List myMovies = MovieDatabase.filterBy(new TrueFilter());
        for(int i=0;i<myMovies.size();i++){
            String movieID = (String) myMovies.get(i);
            double avgRating = getAverageByID(movieID,minRaters);
            if(avgRating != 0.0) {
                Rating rating = new Rating(movieID, avgRating);
                ratingList.add(rating);
            }
        }
        return ratingList;
    }
    public List getAverageRatingsByFilter(int minRaters,Filter f){
        List ratingList = new ArrayList<Rating>();
        List myMovies = MovieDatabase.filterBy(f);
        for(int i=0;i<myMovies.size();i++){
            String movieID = (String) myMovies.get(i);
            double avgRating = getAverageByID(movieID,minRaters);
            if(avgRating != 0.0) {
                Rating rating = new Rating(movieID, avgRating);
                ratingList.add(rating);
            }
        }
        return ratingList;
    }

}
