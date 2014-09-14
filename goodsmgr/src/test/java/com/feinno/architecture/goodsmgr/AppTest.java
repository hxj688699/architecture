package com.feinno.architecture.goodsmgr;

import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.beanutils.BeanMap;

import com.feinno.architecture.goodsmgr.vo.GoodsModel;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    public static void main(String[] args) {
		GoodsModel m = new GoodsModel();
		m.setUuid(10);
		m.setName("name");
		m.setDescription("test");
		m.setImgPath("d:/");
		
		Map map = new BeanMap(m);
	}
}
