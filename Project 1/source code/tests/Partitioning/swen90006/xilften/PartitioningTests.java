package swen90006.xilften;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PartitioningTests
{
    protected Xilften xilften;
    public String username;
    public String password;
    public double expected_cost;
    public double test_cost;

    public PartitioningTests() {
    }

    //Any method annotated with "@Before" will be executed before each test,
    //allowing the tester to set up some shared resources.
    @Before public void setUp()
    {
        xilften = new Xilften();
    }

    //Any method annotated with "@After" will be executed after each test,
    //allowing the tester to release any shared resources used in the setup.
    @After public void tearDown() {
    }


    /**
     * *************** Test method Register ***************
     */

    /*EC1_I for method Register*/
    @Test(expected = DuplicateUserException.class)
    public void duplicateUsername()
            throws Throwable
    {
        username = "0aAZ";
        password = "p";

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        assertTrue("Username is not existed", xilften.isUser(username));
        xilften.register(username, password);
    }

    /*EC2_I for method Register*/
    @Test(expected = InvalidUsernameException.class)
    public void lowerMinLengthUsername()
            throws Throwable
    {
        username = "0aA";
        password = "p";

        assertFalse("Username is existed", xilften.isUser(username));
        xilften.register(username, password);
    }

    /*EC3_I for method Register*/
    @Test(expected = InvalidUsernameException.class)
    public void illegalCharacterUsername()
            throws Throwable
    {
        username = "/0aA";
        password = "p";

        xilften.register(username, password);
        assertFalse("Username is existed", xilften.isUser(username));
    }

    /*EC4_V for method Register*/
    @Test
    public void usContains_AZ()
            throws Throwable
    {
        username = "ABYZ";
        password = "";

        xilften.register(username, password);
        assertTrue("username can not contain A~Z", xilften.isUser(username));
    }

    /*EC5_V for method Register*/
    @Test
    public void usContains_az()
            throws Throwable
    {
        username = "abyz";
        password = "p";

        xilften.register(username, password);
        assertTrue("username can not contain a~z", xilften.isUser(username));
    }

    /*EC6_V for method Register*/
    @Test
    public void usContains_09()
            throws Throwable
    {
        username = "0189";
        password = "pw";

        xilften.register(username, password);
        assertTrue("username can not contain 0~9", xilften.isUser(username));
    }

    /*EC7_V for method Register*/
    @Test
    public void usContains_AZ_az()
            throws Throwable
    {
        username = "AZaz";
        password = "pw";

        xilften.register(username, password);
        assertTrue("username can not contain a~z && A~Z", xilften.isUser(username));
    }

    /*EC8_V for method Register*/
    @Test
    public void usContains_AZ_09()
            throws Throwable
    {
        username = "09AZ";
        password = "pw";

        xilften.register(username, password);
        assertTrue("username can not contain A~Z && 0~9", xilften.isUser(username));
    }

    /*EC9_V for method Register*/
    @Test
    public void usContains_az_09()
            throws Throwable
    {
        username = "09az";
        password = "pw";

        xilften.register(username, password);
        assertTrue("username can not contain a~z && 0~9", xilften.isUser(username));
    }

    /*EC10_V for method Register*/
    @Test
    public void usContains_AZ_az_09()
            throws Throwable
    {
        username = "09AZaz";
        password = "pw";

        xilften.register(username, password);
        assertTrue("username can not contain A~Z && 0~9 && a~z", xilften.isUser(username));
    }

    /**
     * *************** Test method Rent ***************
     */

    /*EC1_I for method Rent*/
    @Test(expected = NoSuchUserException.class)
    public void usernameNotExists()
            throws Throwable
    {
        username = "0aAZ";
        password = "p";
        final Date currentDate = new Date(29, 2, 2000);
        final Date returnDate = new Date(29, 2, 2000);

        assertFalse("username: 0aAZ is existed", xilften.isUser(username));
        xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
    }

    /*EC2_I for method Rent*/
    @Test(expected = IncorrectPasswordException.class)
    public void pwdNotMatchUsername()
            throws Throwable
    {
        username = "0aAZ";
        password = "p";
        final Date currentDate = new Date(28, 2, 2020);
        final Date returnDate = new Date(29, 2, 2020);
        String test_pwd = "pwd";

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        xilften.rent(username, test_pwd, currentDate, returnDate, Xilften.StreamType.SD);
    }

    /*EC3_V for method Rent*/
    @Test
    public void SD_PeriodLess5_SameMonth()
            throws Throwable
    {
        username = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        password = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final Date currentDate = new Date(22, 2, 2000);
        final Date returnDate = new Date(29, 2, 2000);
        expected_cost = 4.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC4_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C1_R2()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(28, 1, 2020);
        final Date returnDate = new Date(4, 2, 2020);
        expected_cost = 4.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC5_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C2_R3_nonleap()
            throws Throwable
    {
        username = "09azAZ";
        password = " ";
        final Date currentDate = new Date(28, 2, 2018);
        final Date returnDate = new Date(7, 3, 2018);
        expected_cost = 4.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC6_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C2_R3_leap()
            throws Throwable
    {
        username = "09azAZ";
        password = "az";
        final Date currentDate = new Date(22, 2, 2100);
        final Date returnDate = new Date(1, 3, 2100);
        expected_cost = 4.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC7_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C3_R4()
            throws Throwable
    {
        username = "09azAZ";
        password = "AZ";
        final Date currentDate = new Date(28,3,2020);
        final Date returnDate = new Date(6,4,2020);
        expected_cost = 4.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC8_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C4_R5()
            throws Throwable
    {
        username = "09azAZ";
        password = "09";
        final Date currentDate = new Date(28,4,2000);
        final Date returnDate = new Date(5,5,2000);
        expected_cost = 4.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC9_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C5_R6()
            throws Throwable
    {
        username = "09azAZ";
        password = "p";
        final Date currentDate = new Date(28,5,2020);
        final Date returnDate = new Date(4,6,2020);
        expected_cost = 4.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC10_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C6_R7()
            throws Throwable
    {
        username = "09azAZ";
        password = "p";
        final Date currentDate = new Date(28,6,2000);
        final Date returnDate = new Date(5,7,2000);
        expected_cost = 4.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC11_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C7_R8()
            throws Throwable
    {
        username = "09azAZ";
        password = "p";
        final Date currentDate = new Date(28,7,2020);
        final Date returnDate = new Date(4,8,2020);
        expected_cost = 4.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC12_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C8_R9()
            throws Throwable
    {
        username = "09azAZ";
        password = "p";
        final Date currentDate = new Date(28,8,2000);
        final Date returnDate = new Date(4,9,2000);
        expected_cost = 4.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC13_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C9_R10()
            throws Throwable
    {
        username = "09azAZ";
        password = "p";
        final Date currentDate = new Date(28,9,2020);
        final Date returnDate = new Date(5,10,2020);
        expected_cost = 4.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC14_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C10_R11()
            throws Throwable
    {
        username = "09azAZ";
        password = "p";
        final Date currentDate = new Date(28,10,2000);
        final Date returnDate = new Date(6,11,2000);
        expected_cost = 4.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC15_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C11_R12()
            throws Throwable
    {
        username = "09azAZ";
        password = "p";
        final Date currentDate = new Date(28,11,2020);
        final Date returnDate = new Date(7,12,2020);
        expected_cost = 4.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC16_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C12_R1()
            throws Throwable
    {
        username = "09azAZ";
        password = "p";
        final Date currentDate = new Date(28,12,2019);
        final Date returnDate = new Date(6,1,2020);
        expected_cost = 4.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC17_V for method Rent*/
    @Test
    public void SD_PeriodBetween_5_20()
            throws Throwable
    {
        username = "09azAZ";
        password = "p";
        final Date currentDate = new Date(31,1,2020);
        final Date returnDate = new Date(29,2,2020);
        expected_cost = 5.5;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC18_V for method Rent*/
    @Test
    public void SD_PeriodMore_20()
            throws Throwable
    {
        username = "09azAZ";
        password = "p";
        final Date currentDate = new Date(28, 2, 2017);
        final Date returnDate = new Date(29, 2, 2028);
        expected_cost = 5.5;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC19_V for method Rent*/
    @Test
    public void HD_PeriodLess_5()
            throws Throwable
    {
        username = "09azAZ";
        password = "p";
        final Date currentDate = new Date(22, 2, 2100);
        final Date returnDate = new Date(1, 3, 2100);
        expected_cost = 5.0;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.HD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC20_V for method Rent*/
    @Test
    public void HD_PeriodBetween_5_20()
            throws Throwable
    {
        username = "09azAZ";
        password = "p";
        final Date currentDate = new Date(28,2,2000);
        final Date returnDate = new Date(27,3,2000);
        expected_cost = 6.5;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.HD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC21_V for method Rent*/
    @Test
    public void HD_PeriodMore_20()
            throws Throwable
    {
        username = "09azAZ";
        password = "p";
        final Date currentDate = new Date(29, 1, 2018);
        final Date returnDate = new Date(29, 2, 2020);
        expected_cost = 6.5;

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.HD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

}
