package step3;

public class DirectorFilter extends Object implements Filter{


    private String[] directors;

    public DirectorFilter(String directors) {
        super();
        this.directors = directors.split(",");
    }

    @Override
    public boolean satisfies(String id) {
        for(String dir : directors) {
            if (MovieDatabase.getDirector(id).contains(dir))
                return true;
        }
        return false;
    }
}
