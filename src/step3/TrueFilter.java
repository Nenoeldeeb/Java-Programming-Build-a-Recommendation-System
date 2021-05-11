package step3;
public class TrueFilter extends Object implements Filter {

	public TrueFilter(){super();}

	@Override
	public boolean satisfies(String id) {
		return true;
	}

}
