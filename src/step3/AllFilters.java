package step3;
import java.util.ArrayList;

public class AllFilters extends Object implements Filter {
    private ArrayList<Filter> filters;
    
    public AllFilters() {
        super();
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    @Override
    public boolean satisfies(String id) {
        for(Filter f : filters) {
            if (! f.satisfies(id)) {
                return false;
            }
        }
        
        return true;
    }

}
