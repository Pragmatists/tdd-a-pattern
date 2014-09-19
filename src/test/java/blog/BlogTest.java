package blog;

import static blog.Specifications.and;
import static blog.Specifications.not;
import static blog.Specifications.or;
import static blog.Specifications.publishedAfter;
import static blog.Specifications.publishedBefore;
import static blog.Specifications.withTag;
import static blog.Specifications.withTitle;
import static blog.Specifications.withTitleLike;
import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BlogTest {

    private Blog blog;
    
    private BlogEntry compositePatternExplained;
    private BlogEntry domainDrivenDesignPatterns;
    private BlogEntry howToTestDrivePattern;
    private BlogEntry junitTipsAndTricks;

    private Session session;

    private SessionFactory factory;

    @Before
    public void setUp() throws Exception {

        setupHibernate();
        blog = new Blog(session);
        
        compositePatternExplained = blog.post("Composite Pattern Explained", tags("DesignPatterns", "Composite"), date("2014-02-05"));
        domainDrivenDesignPatterns = blog.post("Domain-Driven Design Patterns", tags("DDD"), date("2014-04-15"));
        howToTestDrivePattern = blog.post("How to test-drive Pattern", tags("DesignPatterns", "TDD"), date("2014-08-10"));
        junitTipsAndTricks = blog.post("JUnit Tips & Tricks", tags("TDD", "JUnit"), date("2014-09-11"));
    }

    @After
    public void tearDown(){
        session.close();
        factory.close();
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
    
    private void setupHibernate() {

        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(BlogEntry.class);
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        cfg.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.setProperty("hibernate.format_sql", "true");
        cfg.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        cfg.setProperty("hibernate.connection.username", "sa");
        cfg.setProperty("hibernate.connection.password", "");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        factory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build());
        session = factory.openSession();
    }

}
