package testng_junit;


import org.testng.annotations.*;

public class test2 {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("this is beforeClass...");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("this is afterClass...");
    }

    @BeforeMethod
    public void setUp() throws Exception {
        System.out.println("this is before...");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        System.out.println("this is after");
    }

    @Test
    public void test1() {
        System.out.println("this is test1...");
    }

    @Test
    public void test2(){
        System.out.println("this is test2...");
    }
}

