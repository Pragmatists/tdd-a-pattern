package blog;

import static blog.Specifications.publishedAfter;
import static blog.Specifications.publishedBefore;
import static blog.Specifications.withTitle;
import static blog.Specifications.withTitleLike;
import static blog.Specifications.withTag;
import static blog.Specifications.and;
import static blog.Specifications.or;
import static blog.Specifications.not;
import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BlogTest {

    private Blog blog;
    
    private BlogEntry compositePatternExplained;
    private BlogEntry domainDrivenDesignPatterns;
    private BlogEntry howToTestDrivePattern;
    private BlogEntry junitTipsAndTricks;

    @Before
    public void setUp() throws Exception {

        blog = new Blog();
        
        compositePatternExplained = blog.post("Composite Pattern Explained", tags("DesignPatterns", "Composite"), date("2014-02-05"));
        domainDrivenDesignPatterns = blog.post("Domain-Driven Design Patterns", tags("DDD"), date("2014-04-15"));
        howToTestDrivePattern = blog.post("How to test-drive Pattern", tags("DesignPatterns", "TDD"), date("2014-08-10"));
        junitTipsAndTricks = blog.post("JUnit Tips & Tricks", tags("TDD", "JUnit"), date("2014-09-11"));
    }

    @Test
    public void shouldFindEntry_withTitle() throws Exception {

        // when:
        List<BlogEntry> entries = blog.search(withTitle("Composite Pattern Explained"));

        // then:
        assertThat(entries).containsOnly(compositePatternExplained);
    }

    @Test
    public void shouldFindEntry_withTitleLike() throws Exception {
        
        // when:
        List<BlogEntry> entries = blog.search(withTitleLike("Pattern"));
        
        // then:
        assertThat(entries).containsOnly(compositePatternExplained, domainDrivenDesignPatterns, howToTestDrivePattern);
    }
    
    @Test
    public void shouldFindEntry_publishedBefore() throws Exception {
        
        // when:
        List<BlogEntry> entries = blog.search(publishedBefore(date("2014-02-06")));
        
        // then:
        assertThat(entries).containsOnly(compositePatternExplained);
    }
    
    @Test
    public void shouldFindEntry_publishedAfter() throws Exception {
        
        // when:
        List<BlogEntry> entries = blog.search(publishedAfter(date("2014-09-10")));
        
        // then:
        assertThat(entries).containsOnly(junitTipsAndTricks);
    }
    
    @Test
    public void shouldFindEntry_withTag() throws Exception {
        
        // when:
        List<BlogEntry> entries = blog.search(withTag("TDD"));
        
        // then:
        assertThat(entries).containsOnly(howToTestDrivePattern, junitTipsAndTricks);
    }

    @Test
    public void shouldFindEntry_and() throws Exception {
        
        // when:
        List<BlogEntry> entries = blog.search(and(withTitleLike("Pattern"), withTag("TDD")));
        
        // then:
        assertThat(entries).containsOnly(howToTestDrivePattern);
    }
    
    @Test
    public void shouldFindEntry_or() throws Exception {
        
        // when:
        List<BlogEntry> entries = blog.search(or(publishedBefore(date("2014-02-06")), publishedAfter(date("2014-09-10"))));
        
        // then:
        assertThat(entries).containsOnly(compositePatternExplained, junitTipsAndTricks);
    }
    
    @Test
    public void shouldFindEntry_not() throws Exception {
        
        // when:
        List<BlogEntry> entries = blog.search(not(withTitleLike("Pattern")));
        
        // then:
        assertThat(entries).containsOnly(junitTipsAndTricks);
    }
    
    @Test
    public void shouldFindEntry_altogether() throws Exception {
        
        // when:
        List<BlogEntry> entries = blog.search(and(publishedBefore(date("2014-09-10")), withTag("DesignPatterns"), not(withTitleLike("Composite"))));
        
        // then:
        assertThat(entries).containsOnly(howToTestDrivePattern);
    }
    
    // --
    
    private List<String> tags(String... tags) {
        return Arrays.asList(tags);
    }

    private Date date(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }
}
