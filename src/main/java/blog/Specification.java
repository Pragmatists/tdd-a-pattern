package blog;

import org.hibernate.Criteria;


public interface Specification {

    public void applyOnCriteria(Criteria criteria);
    
}
