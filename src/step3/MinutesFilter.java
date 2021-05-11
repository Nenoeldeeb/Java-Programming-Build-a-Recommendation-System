package step3;

public class MinutesFilter extends Object implements Filter{

    private int minTime;
    private int maxTime;

    public MinutesFilter(int minTime, int maxTime) {
        super();
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= minTime
                && MovieDatabase.getMinutes(id) <= maxTime;
    }
}
