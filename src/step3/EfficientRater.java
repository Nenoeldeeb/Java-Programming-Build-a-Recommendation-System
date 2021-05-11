package step3;

import step1.Rating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EfficientRater extends Object implements  Rater {
    private String myID;
    private Map<String,Rating> myRatings;

    public EfficientRater(String id) {
        super();
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {
            if (myRatings.containsKey(item)){
                return true;
            }

        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
            if (myRatings.containsKey(item)){
                return myRatings.get(item).getValue();
            }

        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String movie : myRatings.keySet()){
            list.add(movie);
        }

        return list;
    }
}
