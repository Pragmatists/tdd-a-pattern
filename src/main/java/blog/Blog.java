package blog;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

public class Blog {

    private Session session;

    public Blog(Session session) {
        this.session = session;
    }

    @SuppressWarnings("unchecked")
    public List<BlogEntry> search(Specification specification){

        Criteria criteria = session.createCriteria(BlogEntry.class);
        
        specification.applyOnCriteria(criteria);
        
        return criteria.list();
    }

    public BlogEntry post(String title, List<String> tags, Date published) {

        BlogEntry entry = new BlogEntry(title, tags, published);
        
        session.persist(entry);
        session.flush();

        session.clear();
            
        return entry;
    }
    
}
