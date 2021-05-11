package step3;

public class GenreFilter extends Object implements Filter{

    private String movieGenre;

    public GenreFilter(String genre) {
        super();
        movieGenre = genre;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(movieGenre);
    }
}
