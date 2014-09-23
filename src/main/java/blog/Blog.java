package blog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Blog {

    private List<BlogEntry> entries = new ArrayList<>();

    public List<BlogEntry> search(Specification specification) {
        return Collections.emptyList();
    }

    public BlogEntry post(String title, List<String> tags, Date published) {
        BlogEntry entry = new BlogEntry(title, tags, published);
        entries.add(entry);
        return entry;
    }
    
}
