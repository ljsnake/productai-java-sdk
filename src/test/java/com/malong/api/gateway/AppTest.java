package com.malong.api.gateway;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.malong.api.gateway.JavaApi;

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
        try {
            String[] args = new String[0];
            JavaApi.main(args);
            assertTrue( true );
        } catch (Exception e) {
            System.out.print(e.getMessage());
            assertTrue( false );
        }
    }
}
