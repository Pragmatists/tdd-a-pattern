package blog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static blog.Specifications.withTitle;
import static org.assertj.core.api.Assertions.assertThat;

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
