package blog;

import java.util.Arrays;
import java.util.Date;

public class Specifications {

//    private static final Specification trueSpecification = (blog) -> true;
    
    public static Specification withTitle(String title) {
        return entry -> entry.getTitle().equals(title);
    }

    public static Specification withTitleLike(String titlePart) {
        return entry -> entry.getTitle().contains(titlePart);
    }
    
    public static Specification publishedBefore(Date until) {
        return entry -> entry.getPublished().before(until);
    }

    public static Specification publishedAfter(Date since) {
        return entry -> entry.getPublished().after(since);
    }
    
    public static Specification withTag(String tag) {
        return entry -> entry.getTags().contains(tag);
    }
    
    public static Specification and(Specification... specifications) {
        return entry -> Arrays.asList(specifications)
                .stream()
                .map(x -> x.isSatisfiedBy(entry))
                .reduce(true, (a, b) -> a && b);
    }
    
    public static Specification or(Specification... specifications) {
        return entry -> Arrays.asList(specifications)
                .stream()
                .map(x -> x.isSatisfiedBy(entry))
                .reduce(false, (a, b) -> a || b);
    }
    
    public static Specification not(Specification specification) {
        return entry -> !specification.isSatisfiedBy(entry);
    }
    
}
