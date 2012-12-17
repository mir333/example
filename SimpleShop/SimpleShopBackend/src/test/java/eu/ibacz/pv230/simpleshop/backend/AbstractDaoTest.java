package eu.ibacz.pv230.simpleshop.backend;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Miroslav Ligas
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/test-data-source.xml", "classpath:/META-INF/spring-backend.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public abstract class AbstractDaoTest {

    @Autowired
    protected ProductDAO productDAO;

}
