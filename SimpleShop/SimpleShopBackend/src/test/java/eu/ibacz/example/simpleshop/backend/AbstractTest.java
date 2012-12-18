package eu.ibacz.example.simpleshop.backend;

import eu.ibacz.example.simpleshop.backend.dao.ProductDAO;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author František Hartman <frantisek.hartman@ibacz.eu>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/test-data-source.xml", "classpath:/META-INF/spring-backend.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public abstract class AbstractTest {

    @Autowired
    protected ProductDAO productDAO;

}
