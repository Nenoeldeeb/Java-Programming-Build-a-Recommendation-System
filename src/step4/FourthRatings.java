package step4;

import step1.Rating;
import step3.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FourthRatings extends Object{

    public FourthRatings() {super();
    }

    private double getAverageByID(String id,int minRatings){
        int raters = 0;
        double ratings = 0.0;
        List myRaters = RaterDatabase.getRaters();
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
    public List getAverageRatingsByFilter(int minRaters, Filter f){
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

    private double dotProduct(Rater me,Rater r){
        double answer = 0.0;
        List<String> movies = me.getItemsRated();
        for(String movie : movies){
            if(r.hasRating(movie)){
                double rRating = r.getRating(movie)-5;
                double meRating = me.getRating(movie)-5;
                answer += rRating * meRating;
            }
        }
        return answer;
    }

    private List getSimilarities(String id){
        List list = new ArrayList<Rating>();
        List<Rater> raters =  RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        for(Rater rater : raters){
            if(!rater.getID().equals(id)){
                double value = dotProduct(me,rater);
                if(value > 0.0){
                    Rating rating = new Rating(rater.getID(),value);
                    list.add(rating);
                }
            }
        }
        Collections.sort(list,Collections.reverseOrder());
        return list;
    }

    public List getSimilarRatings(String id,int numSimilarRaters,int minRaters){
        return getSimilarRatingsByFilter(id,numSimilarRaters,minRaters,new TrueFilter());
    }

    public List getSimilarRatingsByFilter(String id,int numSimilarRaters,int minRaters,Filter f){
        List ratingList = new ArrayList<Rating>();
        List<Rating> similarities = getSimilarities(id);
        List<String> movies = MovieDatabase.filterBy(f);
        if(similarities.size() < numSimilarRaters) {
            numSimilarRaters = similarities.size();
        }
        for(String movie : movies) {
            int ratersCount = 0;
            double avg = 0.0;
            for (int i = 0; i < numSimilarRaters; i++) {
                Rating rating = similarities.get(i);
                Rater rater = RaterDatabase.getRater(rating.getItem());
                if(rater.hasRating(movie)){
                    ratersCount ++;
                    avg += rater.getRating(movie)*rating.getValue();
                }
            }
            if(ratersCount >= minRaters){
                Rating weightedAvg = new Rating(movie,avg/ratersCount);
                ratingList.add(weightedAvg);
            }
        }
        Collections.sort(ratingList,Collections.reverseOrder());
        return ratingList;
    }

}
