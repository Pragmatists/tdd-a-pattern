package blog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Blog {

    private List<BlogEntry> entries = new ArrayList<BlogEntry>();

    public List<BlogEntry> search(Specification specification){

        return entries.stream().filter(blog -> specification.isSatisfiedBy(blog)).collect(Collectors.toList());
    }

    public BlogEntry post(String title, List<String> tags, Date published) {
        
        BlogEntry entry = new BlogEntry(title, tags, published);
        entries.add(entry);
        return entry;
    }
    
}
