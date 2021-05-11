package step3;

import step1.Rating;

import java.util.Collections;
import java.util.List;

public class MovieRunnerWithFilters extends Object{

    public MovieRunnerWithFilters() {super();
    }

    public static void printAverageRatings(){
        //MovieDatabase.initialize("ratedmovies_short.csv");
        Object thirdRatings = new ThirdRatings();
        int raterNum = ((ThirdRatings) thirdRatings).getRaterSize();
        List avg = ((ThirdRatings) thirdRatings).getAverageRatings(35);
        Collections.sort(avg);
        System.out.println(MovieDatabase.size()+"  "+raterNum+"\nrated "+ avg.size());
        for(int i=0;i<avg.size();i++){
            Rating rating = (Rating) avg.get(i);
            String title = MovieDatabase.getTitle(rating.getItem());
            double value = rating.getValue();
            System.out.println(value+"  "+title);
        }
    }

    public static void printAverageRatingsByYear(){
        //MovieDatabase.initialize("ratedmovies_short.csv");
        Object thirdRatings = new ThirdRatings();
        int raterNum = ((ThirdRatings) thirdRatings).getRaterSize();
        Filter filter = new YearAfterFilter(2000);
        List avg = ((ThirdRatings) thirdRatings).getAverageRatingsByFilter(20,filter);
        Collections.sort(avg);
        System.out.println(MovieDatabase.size()+"  "+raterNum+"\nrated "+ avg.size());
        for(int i=0;i<avg.size();i++){
            Rating rating = (Rating) avg.get(i);
            String title = MovieDatabase.getTitle(rating.getItem());
            double value = rating.getValue();
            int year = MovieDatabase.getYear(rating.getItem());
            System.out.println(value+"  "+year+"  "+title);
        }
    }

    public static void printAverageRatingsByGenre(){
        //MovieDatabase.initialize("ratedmovies_short.csv");
        Object thirdRatings = new ThirdRatings();
        int raterNum = ((ThirdRatings) thirdRatings).getRaterSize();
        Filter filter = new GenreFilter("Comedy");
        List avg = ((ThirdRatings) thirdRatings).getAverageRatingsByFilter(20,filter);
        Collections.sort(avg);
        System.out.println(MovieDatabase.size()+"  "+raterNum+"\nrated "+ avg.size());
        for(int i=0;i<avg.size();i++){
            Rating rating = (Rating) avg.get(i);
            String title = MovieDatabase.getTitle(rating.getItem());
            double value = rating.getValue();
            String genres = MovieDatabase.getGenres(rating.getItem());
            System.out.println(value+"  "+title+"\n"+genres);
        }
    }

    public static void printAverageRatingsByMinutes(){
        //MovieDatabase.initialize("ratedmovies_short.csv");
        Object thirdRatings = new ThirdRatings();
        int raterNum = ((ThirdRatings) thirdRatings).getRaterSize();
        Filter filter = new MinutesFilter(105,135);
        List avg = ((ThirdRatings) thirdRatings).getAverageRatingsByFilter(5,filter);
        Collections.sort(avg);
        System.out.println(MovieDatabase.size()+"  "+raterNum+"\nrated "+ avg.size());
        for(int i=0;i<avg.size();i++){
            Rating rating = (Rating) avg.get(i);
            String title = MovieDatabase.getTitle(rating.getItem());
            double value = rating.getValue();
            int time = MovieDatabase.getMinutes(rating.getItem());
            System.out.println(value+"  Time  "+time+"  "+title);
        }
    }

    public static void printAverageRatingsByDirector() {
        //MovieDatabase.initialize("ratedmovies_short.csv");
        Object thirdRatings = new ThirdRatings();
        int raterNum = ((ThirdRatings) thirdRatings).getRaterSize();
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        Filter filter = new DirectorFilter(directors);
        List avg = ((ThirdRatings) thirdRatings).getAverageRatingsByFilter(4, filter);
        Collections.sort(avg);
        System.out.println(MovieDatabase.size() + "  " + raterNum + "\nrated " + avg.size());
        for (int i = 0; i < avg.size(); i++) {
            Rating rating = (Rating) avg.get(i);
            String title = MovieDatabase.getTitle(rating.getItem());
            double value = rating.getValue();
            String director = MovieDatabase.getDirector(rating.getItem());
            System.out.println(value + "  " + title + "\n" + director);
        }
    }

    public static void printAverageRatingsByYearAfterAndGenre(){
        //MovieDatabase.initialize("ratedmovies_short.csv");
        Object thirdRatings = new ThirdRatings();
        int raterNum = ((ThirdRatings) thirdRatings).getRaterSize();
        Filter filters = new AllFilters();
        Filter genre = new GenreFilter("Drama");
        Filter year = new YearAfterFilter(1990);
        ((AllFilters) filters).addFilter(genre);
        ((AllFilters) filters).addFilter(year);
        List avg = ((ThirdRatings) thirdRatings).getAverageRatingsByFilter(8,filters);
        Collections.sort(avg);
        System.out.println(MovieDatabase.size()+"  "+raterNum+"\nrated "+ avg.size());
        for(int i=0;i<avg.size();i++){
            Rating rating = (Rating) avg.get(i);
            String title = MovieDatabase.getTitle(rating.getItem());
            double value = rating.getValue();
            int mYear = MovieDatabase.getYear(rating.getItem());
            String genres = MovieDatabase.getGenres(rating.getItem());
            System.out.println(value+"  "+mYear+"  "+title+"\n"+genres);
        }
    }

    public static void printAverageRatingsByDirectorsAndMinutes(){
        //MovieDatabase.initialize("ratedmovies_short.csv");
        Object thirdRatings = new ThirdRatings();
        int raterNum = ((ThirdRatings) thirdRatings).getRaterSize();
        Filter filters = new AllFilters();
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        Filter dirFilter = new DirectorFilter(directors);
        Filter minutes = new MinutesFilter(90,180);
        ((AllFilters) filters).addFilter(dirFilter);
        ((AllFilters) filters).addFilter(minutes);
        List avg = ((ThirdRatings) thirdRatings).getAverageRatingsByFilter(3,filters);
        Collections.sort(avg);
        System.out.println(MovieDatabase.size()+"  "+raterNum+"\nrated "+ avg.size());
        for(int i=0;i<avg.size();i++){
            Rating rating = (Rating) avg.get(i);
            String title = MovieDatabase.getTitle(rating.getItem());
            double value = rating.getValue();
            int time = MovieDatabase.getMinutes(rating.getItem());
            String director = MovieDatabase.getDirector(rating.getItem());
            System.out.println(value+"  Time "+time+"  "+title+"\n"+director);
        }
    }

    public static void main(String[] args) {
        //printAverageRatings();
        //printAverageRatingsByYear();
        //printAverageRatingsByGenre();
        //printAverageRatingsByMinutes();
        //printAverageRatingsByDirector();
        //printAverageRatingsByYearAfterAndGenre();
        printAverageRatingsByDirectorsAndMinutes();
    }

}
