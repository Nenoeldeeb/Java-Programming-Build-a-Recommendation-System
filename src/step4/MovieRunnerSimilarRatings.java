package step4;

import step1.Rating;
import step3.*;

import java.util.Collections;
import java.util.List;

public class MovieRunnerSimilarRatings extends Object{



    public MovieRunnerSimilarRatings() {super();
    }

    public static void printAverageRatings(){
        //MovieDatabase.initialize("ratedmovies_short.csv");
        Object fourthRatings = new FourthRatings();
        List avg = ((FourthRatings) fourthRatings).getAverageRatings(35);
        Collections.sort(avg);
        System.out.println(MovieDatabase.size()+"  "+RaterDatabase.size()+"\nrated "+ avg.size());
        for(int i=0;i<avg.size();i++){
            Rating rating = (Rating) avg.get(i);
            String title = MovieDatabase.getTitle(rating.getItem());
            double value = rating.getValue();
            System.out.println(value+"  "+title);
        }
    }

    public static void printSimilarRatings(){
        //RaterDatabase.initialize("quiz4raters.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        Object fourthRatings = new FourthRatings();
        List avg = ((FourthRatings) fourthRatings).getSimilarRatings("71",20,5);
        System.out.println(MovieDatabase.size()+"  "+RaterDatabase.size()+"\nrated "+ avg.size());
        for(int i=0;i<avg.size();i++){
            Rating rating = (Rating) avg.get(i);
            String title = MovieDatabase.getTitle(rating.getItem());
            double value = rating.getValue();
            System.out.println(value+"  "+title);
        }
    }

    public static void printAverageRatingsByYearAfterAndGenre(){
        //MovieDatabase.initialize("ratedmovies_short.csv");
        Object fourthRatings = new FourthRatings();
        Filter filters = new AllFilters();
        Filter genre = new GenreFilter("Drama");
        Filter year = new YearAfterFilter(1990);
        ((AllFilters) filters).addFilter(genre);
        ((AllFilters) filters).addFilter(year);
        List avg = ((FourthRatings) fourthRatings).getAverageRatingsByFilter(8,filters);
        Collections.sort(avg);
        System.out.println(MovieDatabase.size()+"  "+RaterDatabase.size()+"\nrated "+ avg.size());
        for(int i=0;i<avg.size();i++){
            Rating rating = (Rating) avg.get(i);
            String title = MovieDatabase.getTitle(rating.getItem());
            double value = rating.getValue();
            int mYear = MovieDatabase.getYear(rating.getItem());
            String genres = MovieDatabase.getGenres(rating.getItem());
            System.out.println(value+"  "+mYear+"  "+title+"\n"+genres);
        }
    }

    public static void printSimilarRatingsByGenre(){
        //MovieDatabase.initialize("ratedmovies_short.csv");
        Object fourthRatings = new FourthRatings();
        Filter filter = new GenreFilter("Mystery");
        List avg = ((FourthRatings) fourthRatings).getSimilarRatingsByFilter("964",20,5,filter);
        System.out.println(MovieDatabase.size()+"  "+RaterDatabase.size()+"\nrated "+ avg.size());
        for(int i=0;i<avg.size();i++){
            Rating rating = (Rating) avg.get(i);
            String title = MovieDatabase.getTitle(rating.getItem());
            double value = rating.getValue();
            String genres = MovieDatabase.getGenres(rating.getItem());
            System.out.println(value+"  "+title+"\n"+genres);
        }
    }

    public static void printAverageRatingsByDirector() {
        //MovieDatabase.initialize("ratedmovies_short.csv");
        Object fourthRatings = new FourthRatings();
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        Filter filter = new DirectorFilter(directors);
        List avg = ((FourthRatings) fourthRatings).getSimilarRatingsByFilter("120",10,2,filter);
        System.out.println(MovieDatabase.size() + "  " + RaterDatabase.size() + "\nrated " + avg.size());
        for (int i = 0; i < avg.size(); i++) {
            Rating rating = (Rating) avg.get(i);
            String title = MovieDatabase.getTitle(rating.getItem());
            double value = rating.getValue();
            String director = MovieDatabase.getDirector(rating.getItem());
            System.out.println(value + "  " + title + "\n" + director);
        }
    }

    public static void printSimilarRatingsByGenreAndMinutes() {
        //MovieDatabase.initialize("ratedmovies_short.csv");
        Object fourthRatings = new FourthRatings();
        Filter genre = new GenreFilter("Drama");
        Filter minutes = new MinutesFilter(80, 160);
        Filter filter = new AllFilters();
        ((AllFilters) filter).addFilter(genre);
        ((AllFilters) filter).addFilter(minutes);
        List avg = ((FourthRatings) fourthRatings).getSimilarRatingsByFilter("168", 10, 3, genre);
        System.out.println(MovieDatabase.size() + "  " + RaterDatabase.size() + "\nrated " + avg.size());
        for (int i = 0; i < avg.size(); i++) {
            Rating rating = (Rating) avg.get(i);
            String title = MovieDatabase.getTitle(rating.getItem());
            double value = rating.getValue();
            int time = MovieDatabase.getMinutes(rating.getItem());
            String genres = MovieDatabase.getGenres(rating.getItem());
            System.out.println(value + "  Time " + time + "  " + title + "\n" + genres);
        }
    }

        public static void printSimilarRatingsByYearAfterAndMinutes() {
            //MovieDatabase.initialize("ratedmovies_short.csv");
            Object fourthRatings = new FourthRatings();
            Filter filters = new AllFilters();
            Filter mintues = new MinutesFilter(70,200);
            Filter year = new YearAfterFilter(1975);
            ((AllFilters) filters).addFilter(mintues);
            ((AllFilters) filters).addFilter(year);
            List avg = ((FourthRatings) fourthRatings).getSimilarRatingsByFilter("314",10,5,filters);
            System.out.println(MovieDatabase.size() + "  " + RaterDatabase.size() + "\nrated " + avg.size());
            for (int i = 0; i < avg.size(); i++) {
                Rating rating = (Rating) avg.get(i);
                String title = MovieDatabase.getTitle(rating.getItem());
                double value = rating.getValue();
                int mYear = MovieDatabase.getYear(rating.getItem());
                int time = MovieDatabase.getMinutes(rating.getItem());
                System.out.println(value + "  " + mYear + "  time "+time+"  " + title);
            }
        }


    public static void main(String[] args) {
        //printAverageRatings();
        //printAverageRatingsByYearAfterAndGenre();
        //printSimilarRatings();
         //printSimilarRatingsByGenre();
        //printAverageRatingsByDirector();
        //printSimilarRatingsByGenreAndMinutes();
        printSimilarRatingsByYearAfterAndMinutes();
    }

}
