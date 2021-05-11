package step3;
public class YearAfterFilter extends Object implements Filter {
	private int myYear;
	
	public YearAfterFilter(int year) {
		super();
		myYear = year;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getYear(id) >= myYear;
	}

}
