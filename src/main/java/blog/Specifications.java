package blog;

public class Specifications {

    public static Specification withTitle(String title) {
        return entry -> true;
    }

}
