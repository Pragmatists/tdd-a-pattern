package blog;

import org.hibernate.criterion.Restrictions;

public class Specifications {

    public static Specification withTitle(String title) {
        
        return criteria -> criteria.add(Restrictions.eq("title", title));
    }

}
