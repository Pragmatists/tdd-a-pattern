package blog;

import org.junit.Ignore;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BlogTest {

    @Test
    @Ignore("Pending")
    public void shouldFindEntry_withTitle() throws Exception {
    }

    @Test
    @Ignore("Pending")
    public void shouldFindEntry_withTitleLike() throws Exception {
    }

    @Test
    @Ignore("Pending")
    public void shouldFindEntry_publishedBefore() throws Exception {
    }
    
    @Test
    @Ignore("Pending")
    public void shouldFindEntry_publishedAfter() throws Exception {
    }
    
    @Test
    @Ignore("Pending")
    public void shouldFindEntry_withTag() throws Exception {
    }

    @Test
    @Ignore("Pending")
    public void shouldFindEntry_and() throws Exception {
    }
    
    @Test
    @Ignore("Pending")
    public void shouldFindEntry_or() throws Exception {
    }
    
    @Test
    @Ignore("Pending")
    public void shouldFindEntry_not() throws Exception {
    }
    
    @Test
    @Ignore("Pending")
    public void shouldFindEntry_altogether() throws Exception {
    }
    
    // --
    
    private List<String> tags(String... tags) {
        return Arrays.asList(tags);
    }

    private Date date(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }
}
