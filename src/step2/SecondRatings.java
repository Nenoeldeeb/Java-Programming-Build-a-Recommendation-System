package step2;
/**
 * Write a description of week2.SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import step1.Movie;
import step1.Rating;
import step3.Rater;

import java.util.ArrayList;
import java.util.List;

public class SecondRatings extends Object {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }

    public SecondRatings(String moviesFile, String ratersFile) {
        super();
        Object firestRatings = new FirstRatings();
        myMovies = (ArrayList<Movie>) ((FirstRatings) firestRatings).loadMovies(moviesFile);
        myRaters = (ArrayList<Rater>) ((FirstRatings) firestRatings).loadRaters(ratersFile);
    }

    public int getMovieSize() {return myMovies.size();}

    public int getRaterSize() {return myRaters.size();}

    private double getAverageByID(String id,int minRatings){
        int raters = 0;
        double ratings = 0.0;
        for(int i=0;i<myRaters.size();i++){
            Rater rater = (Rater) myRaters.get(i);
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
        for(int i=0;i<myMovies.size();i++){
            Movie movie = (Movie) myMovies.get(i);
            String movieID = movie.getID();
            double avgRating = getAverageByID(movieID,minRaters);
            if(avgRating != 0.0) {
                Rating rating = new Rating(movieID, avgRating);
                ratingList.add(rating);
            }
        }
        return ratingList;
    }

    public String getTitle(String id){
        String title = "This ID was not found!";
        for(int i=0;i<myMovies.size();i++){
            Movie movie = (Movie) myMovies.get(i);
            if(movie.getID().equals(id)){title = movie.getTitle();}
        }
        return title;
    }

    public String getID(String title){
        String id = "There is no title\nPlease make sure Your are spelled It correctly";
        for(int i=0;i<myMovies.size();i++){
            Movie movie = myMovies.get(i);
            if(movie.getTitle().equals(title)){id = movie.getID();}
        }
        return id;
    }

}
