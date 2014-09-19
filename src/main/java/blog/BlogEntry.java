package blog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class BlogEntry {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    private String title;
    
    @ElementCollection
    private List<String> tags = new ArrayList<String>();
    @Temporal(TemporalType.DATE)
    private Date published;

    protected BlogEntry() {
    }
    
    public BlogEntry(String title, List<String> tags, Date published) {
        this();
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
        
        if(!(obj instanceof BlogEntry)){
            return false;
        }
        
        BlogEntry other = (BlogEntry) obj;
        
        return this.id == other.id 
                && this.title.equals(other.title)
                && this.published.equals(other.published)
                && this.tags.size() == other.tags.size()                
                && this.tags.containsAll(other.tags);
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
