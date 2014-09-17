package blog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class BlogEntry {

    private String title;
    private List<String> tags;
    private Date published;

    public BlogEntry(String title, List<String> tags, Date published) {
        this.title = title;
        this.tags = tags;
        this.published = published;
    }
    
    public String getTitle() {
        return title;
    }
    
    public List<String> getTags() {
        return tags;
    }
    
    public Date getPublished() {
        return published;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj); 
    }
    
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
    @Override
    public String toString() {
        return String.format("'%s' @%s %s\n", title, new SimpleDateFormat("yyyy-MM-dd").format(published), printTags());
    }

    private String printTags() {
        return tags.stream().map(tag -> "#" + tag).collect(Collectors.joining(" "));
    }
}
