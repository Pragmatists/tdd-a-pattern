package blog;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class Specifications {

    private static final Specification trueSpecification = new Specification() {

        @Override
        public void applyOnCriteria(Criteria criteria) {
        }

    };
    
    public static Specification withTitle(String title) {
        
        return new Specification() {

            @Override
            public void applyOnCriteria(Criteria criteria) {
                criteria.add(Restrictions.eq("title", title));
            }
        };
    }

    public static Specification withTitleLike(String titlePart) {
        return trueSpecification;
    }
    
    public static Specification publishedBefore(Date until) {
        return trueSpecification;
    }

    public static Specification publishedAfter(Date since) {
        return trueSpecification;
    }
    
    public static Specification withTag(String tag) {
        return trueSpecification;
    }
    
    public static Specification and(Specification... specifications) {
        return trueSpecification;
    }
    
    public static Specification or(Specification... specifications) {
        return trueSpecification;
    }
    
    public static Specification not(Specification specification) {
        return trueSpecification;
    }
    
}
