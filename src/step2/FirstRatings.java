package step2;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import step1.Movie;
import step3.EfficientRater;
import step3.Rater;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FirstRatings extends Object{
    public FirstRatings() {super();}

    public List loadMovies(String filename){
        List result = new ArrayList<Movie>();
        try {
            BufferedReader file = Files.newBufferedReader(Paths.get(filename));
            CSVParser parser = new CSVParser(file, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : parser) {
                Movie movie = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"),
                        record.get("director"), record.get("country"), record.get("poster"), record.get("minutes"));
                result.add(movie);
            }
        }
        catch (IOException e){e.printStackTrace();}
        return (ArrayList<Movie>)result;
    }
    public List loadRaters(String filename){
        List raters = new ArrayList<Rater>();
        try {
            BufferedReader file = Files.newBufferedReader(Paths.get(filename));
            CSVParser parser = new CSVParser(file, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : parser) {
                String raterID = record.get("rater_id");
                String movieID = record.get("movie_id");
                double rating = Double.parseDouble(record.get("rating"));
                int index = -1;
                if (raters.isEmpty()) {
                    Rater rater = new EfficientRater(raterID);
                    rater.addRating(movieID, rating);
                    raters.add(rater);
                }
                for (int i = 0; i < raters.size(); i++) {
                    Rater rater = ((Rater) raters.get(i));
                    if (rater.getID().equals(raterID)) {
                        index = i;
                    }
                }
                if (index == -1) {
                    Rater rater = new EfficientRater(raterID);
                    rater.addRating(movieID, rating);
                    raters.add(rater);
                } else {
                    Rater rater = ((Rater) raters.get(index));
                    if (!rater.hasRating(movieID)) {
                        rater.addRating(movieID, rating);
                        raters.set(index, rater);
                    }
                }
            }
        }
        catch (IOException e){e.printStackTrace();}
        return (ArrayList<Rater>)raters;
    }
    public void testLoadMovies() {
            List list = loadMovies("data/ratedmoviesfull.csv");
            int greaterMin = 0;
            int numGen = 0;
            Map dir = new HashMap<String ,Integer>();
            for(int i=0;i<list.size();i++){
                Movie m = (Movie)list.get(i);
                if(m.getMinutes() > 150){
                    greaterMin ++;
                }
                if(m.getGenres().contains("Comedy")){
                    numGen ++;
                }
                String dire = m.getDirector();
                if(!dir.containsKey(dire)){
                    dir.put(dire,1);
                }
                else{int num =(int) dir.get(dire);dir.put(dire,num+1);}
                //System.out.println(m);
            }
            int maxDir = (int)Collections.max(dir.values());
            List mostMo = new ArrayList<String>();
            for(Object st : dir.keySet()){
                st = (String)st;
                if((int)(dir.get(st))==maxDir){mostMo.add(st);}
            }
            System.out.println(list.size()+"\nGenres "+numGen+"\tminutes "+greaterMin+"\ndires "+maxDir+"\t"+mostMo);
    }
    public void testLoadRaters(){
            List list = loadRaters("data/ratings.csv");
            int numRatings = 0;
            int maxRatings = 0;
            Map maxRaters = new HashMap<String,List>();
            List movies = new ArrayList<String>();
            List diffMovies = new ArrayList<String>();
            for(int i=0;i<list.size();i++){
                Rater rater = (Rater)list.get(i);
                if(rater.getID().equals("193")){numRatings = rater.numRatings();}
                if(rater.numRatings() > maxRatings){maxRatings = rater.numRatings();}
                List moviesList = rater.getItemsRated();
                for(int l=0;l<moviesList.size();l++){
                    String movie = (String) moviesList.get(l);
                    if(movie.equals("1798709")){
                        movies.add(rater.getID());
                    }
                    if(!diffMovies.contains(movie)){diffMovies.add(movie);}
                }
                //System.out.println(rater);
            }
            for(int i=0;i<list.size();i++){
                Rater rater = (Rater) list.get(i);
                if(rater.numRatings() == maxRatings){
                    List maxList =rater.getItemsRated();
                    maxRaters.put(rater.getID(),maxList);
                }
            }
            System.out.println(list.size()+"\nNumber of Ratings "+numRatings+"\nMax Rated "+
                    maxRatings+"\nmaxNum "+maxRaters.size()+" "+maxRaters+"\nnumber of max movies rated "
            + movies.size()+"\nnumber of unique movies rated "+diffMovies.size());
    }
}
