package com.axone.devintest.AxoneAutomation.selenium.constants;

/**
 * interface which provides the wait time constants to be used in the framework
 * 
 * @author Karthik.Gandhinathan
 *
 */
public interface IWaitTimeConstants {
    /** Global int wait one seconds (minimum). */
    public static final int GLOBAL_WAIT_TIME_MIN = 1;
    /** Global int wait 5 seconds. */
    public static final int GLOBAL_WAIT_TIME = 10;
    /** Global int timeout which is the timeout in the wait.until() methods. */
    public static final int GLOBAL_SYNCHRONISATION_TIMEOUT = 30;
    /** Global wait time for the maximum wait time in seconds. */
    public static final int GLOBAL_WAIT_TIME_MAX = 70;

    /** Global minimum wait time in seconds to pause. */
    public static final int GLOBAL_MAX_WAIT_TIME_SEC = 180;

    /** MILLI-SECONDS (MS). */

    /** Global minimum wait time in ms to pause (tenth of a second). */
    public static final int GLOBAL_MINIMUM_WAIT_TIME_MS = 100;
    /** Global wait time in ms to pause a second. */
    public static final int GLOBAL_WAIT_TIME_HALF_SEC_IN_MS = 500;
    /** Global wait time in ms to pause a second. */
    public static final int GLOBAL_WAIT_TIME_ONE_SEC_IN_MS = 1000;
    /** Global wait time in ms to pause a second. */
    public static final int GLOBAL_WAIT_TIME_TWO_SEC_IN_MS = 2000;
    /** Global wait time in MS. */
    public static final int GLOBAL_WAIT_TIME_MS = 5000;
    /** Global wait time in ms to pause 10 second. */
    public static final int GLOBAL_WAIT_TIME_TEN_SEC_IN_MS = 10000;
    /** Global wait time in ms to pause 30 second. */
    public static final int GLOBAL_WAIT_TIME_THIRTY_SEC_IN_MS = 30000;
    /** Global wait time for the maximum wait time in seconds. */
    public static final int GLOBAL_WAIT_TIME_MAX_MS = 60000;
    /** Global HUD Wait time in ms. **/
    public static final int GLOBAL_WAIT_TIME_HUD_MS = 1500;

}
