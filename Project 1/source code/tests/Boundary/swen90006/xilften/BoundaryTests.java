package swen90006.xilften;

import java.util.List;
import java.util.ArrayList;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;

import org.junit.*;
import static org.junit.Assert.*;


//By extending PartitioningTests, we inherit tests from the script
public class BoundaryTests
    extends swen90006.xilften.PartitioningTests
{
    /**
     * *************** Test method Register ***************
     */

    /*Test case for method Register:
    EC1_I - on point,
    EC2_I - on point,

    Test case for method Rent:
    EC1_I - off point,
    EC2_I - off point;
    */
    @Test
    public void usernameNotRegistered()
            throws Throwable
    {
        username = "0aAZ";
        password = "p";

        if(!xilften.isUser(username)) {
            xilften.register(username, password);
        }
        assertTrue("Username is not existed", xilften.isUser(username));
    }

    /*EC4_V for method Register*/
    @Test(expected = InvalidUsernameException.class)
    public void usContains_AZ_off_points()
            throws Throwable
    {
        username = "@@[[";
        password = "pw";

        xilften.register(username, password);
        assertFalse("username can be @@[[", xilften.isUser(username));
    }

    /*EC5_V for method Register*/
    @Test(expected = InvalidUsernameException.class)
    public void usContains_az_off_points()
            throws Throwable
    {
        username = "``{{";
        password = "pw";

        xilften.register(username, password);
        assertFalse("username can be ``{{", xilften.isUser(username));
    }

    /*EC6_V for method Register*/
    @Test(expected = InvalidUsernameException.class)
    public void usContains_09_off_points()
            throws Throwable
    {
        username = "//::";
        password = "pw";

        xilften.register(username, password);
        assertFalse("username can be //::", xilften.isUser(username));
    }

    /*EC7_V for method Register*/
    @Test(expected = InvalidUsernameException.class)
    public void usContains_AZ_az_off_point_1()
            throws Throwable
    {
        username = "@@@@";
        password = "pw";

        xilften.register(username, password);
        assertFalse("username can be @@@@", xilften.isUser(username));
    }

    /*EC7_V for method Register*/
    @Test(expected = InvalidUsernameException.class)
    public void usContains_AZ_az_off_point_2()
            throws Throwable
    {
        username = "[[``";
        password = "pw";

        xilften.register(username, password);
        assertFalse("username can be [[``", xilften.isUser(username));
    }

    /*EC7_V for method Register*/
    @Test(expected = InvalidUsernameException.class)
    public void usContains_AZ_az_off_point_3()
            throws Throwable
    {
        username = "{{{{";
        password = "pw";

        xilften.register(username, password);
        assertFalse("username can be {{{{", xilften.isUser(username));
    }

    /*EC8_V for method Register*/
    @Test(expected = InvalidUsernameException.class)
    public void usContains_AZ_09_off_point_1()
            throws Throwable
    {
        username = "////";
        password = "pw";

        xilften.register(username, password);
        assertFalse("username can be ////", xilften.isUser(username));
    }

    /*EC8_V for method Register*/
    @Test(expected = InvalidUsernameException.class)
    public void usContains_AZ_09_off_point_2()
            throws Throwable
    {
        username = ": :@@";
        password = "pw";

        xilften.register(username, password);
        assertFalse("username can be : :@@", xilften.isUser(username));
    }

    /*EC8_V for method Register*/
    @Test(expected = InvalidUsernameException.class)
    public void usContains_AZ_09_off_point_3()
            throws Throwable
    {
        username = "[[[[";
        password = "pw";

        xilften.register(username, password);
        assertFalse("username can be [[[[", xilften.isUser(username));
    }

    /*EC9_V for method Register*/
    @Test(expected = InvalidUsernameException.class)
    public void usContains_az_09_off_point()
            throws Throwable
    {
        username = "``::";
        password = "pw";

        xilften.register(username, password);
        assertFalse("username can be ``::", xilften.isUser(username));
    }


    /**
     * *************** Test method Rent ***************
     */

    /*EC3_V for method Rent*/
    @Test
    public void SD_PeriodLess5_SameMonth_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(20,2,2019);
        final Date returnDate = new Date(28,2,2019);
        expected_cost = 4.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC4_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C1_R2_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(28,1, 2019);
        final Date returnDate = new Date(5,2, 2019);
        expected_cost = 4.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC5_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C2_R3_nonleap_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "p";
        final Date currentDate = new Date(28,2, 2019);
        final Date returnDate = new Date(8,3, 2019);
        expected_cost = 4.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC6_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C2_R3_leap_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(22,2, 2016);
        final Date returnDate = new Date(1, 3, 2016);
        expected_cost = 4.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC7_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C3_R4_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(28,3, 2018);
        final Date returnDate = new Date(5,4, 2018);
        expected_cost = 4.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC8_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C4_R5_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(28,4, 2019);
        final Date returnDate = new Date(7,5, 2019);
        expected_cost = 4.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC9_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C5_R6_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(28,5, 2018);
        final Date returnDate = new Date(5,6, 2018);
        expected_cost = 4.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC10_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C6_R7_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(28,6, 2019);
        final Date returnDate = new Date(8,7, 2019);
        expected_cost = 4.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC11_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C7_R8_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(28,7, 2018);
        final Date returnDate = new Date(7,8, 2018);
        expected_cost = 4.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC12_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C8_R9_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(28,8, 2019);
        final Date returnDate = new Date(5,9, 2019);
        expected_cost = 4.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC13_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C9_R10_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(28,9, 2018);
        final Date returnDate = new Date(8,10, 2018);
        expected_cost = 4.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC14_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C10_R11_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(28,10, 2019);
        final Date returnDate = new Date(5,11, 2019);
        expected_cost = 4.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC15_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C11_R12_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(28,11, 2018);
        final Date returnDate = new Date(6,12, 2018);
        expected_cost = 4.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC16_V for method Rent*/
    @Test
    public void SD_PeriodLess5_C12_R1_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(28,12, 2018);
        final Date returnDate = new Date(7,1, 2019);
        expected_cost = 4.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC17_V, EC18_V for method Rent*/
    @Test
    public void SD_PeriodBetween_5_20_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(1,3,2018);
        final Date returnDate = new Date(31,3,2018);
        expected_cost = 5.5;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.SD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC19_V for method Rent*/
    @Test
    public void HD_PeriodLess_5_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(22, 2, 2016);
        final Date returnDate = new Date(1, 3, 2016);
        expected_cost = 5.1;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.HD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    /*EC20_V, EC21_V for method Rent*/
    @Test
    public void HD_PeriodBetween_5_20_off_point()
            throws Throwable
    {
        username = "09azAZ";
        password = "";
        final Date currentDate = new Date(2,8, 2018);
        final Date returnDate = new Date(31,8, 2018);
        expected_cost = 6.5;

        xilften.register(username, password);
        test_cost = xilften.rent(username, password, currentDate, returnDate, Xilften.StreamType.HD);
        assertEquals(expected_cost,test_cost, 0.000000000001);
    }

    @Test
    public void addtestq() throws DuplicateUserException, InvalidUsernameException, NoSuchUserException, IncorrectPasswordException {
        // include a message for better feedback
        xilften.register("abcd", "password1");

        Date currentDate = new Date(20, 11, 2020);
        Date returnDate = new Date(10, 12, 2020);

        double r = xilften.rent("abcd", "password1", currentDate, returnDate, Xilften.StreamType.SD);
        // the assertTrue method is used to check whether something holds.
        assertTrue(r == 4.9);
    }

    @Test
    public void addtest2() throws DuplicateUserException, InvalidUsernameException, NoSuchUserException, IncorrectPasswordException {
        // include a message for better feedback
        xilften.register("abcd", "password1");

        Date currentDate = new Date(15, 3, 2019);
        Date returnDate = new Date(10, 4, 2019);

        double r = xilften.rent("abcd", "password1", currentDate, returnDate, Xilften.StreamType.HD);
        // the assertTrue method is used to check whether something holds.
        assertTrue(r == 6.3);
    }

    @Test
    public void addtest3() throws DuplicateUserException, InvalidUsernameException, NoSuchUserException, IncorrectPasswordException {
        // include a message for better feedback
        xilften.register("abcd", "password1");

        Date currentDate = new Date(20, 2, 2000);
        Date returnDate = new Date(10, 3, 2000);

        double r = xilften.rent("abcd", "password1", currentDate, returnDate, Xilften.StreamType.SD);
        // the assertTrue method is used to check whether something holds.
        assertTrue(r == 4.9);
    }

}
