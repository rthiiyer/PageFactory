package com.axone.devintest.AxoneAutomation.selenium.constants;

import java.io.File;

public interface IGlobalConstants {

    public static final String WINDOWS_OS = "Windows";

    public static final String LINUX_OS = "linux";

    /** //src//main//resources// */
    public static final String GLOBAL_MAIN_RESOURCES_FOLDER = File.separator + "src" + File.separator + "main"
	    + File.separator + "resources" + File.separator;

    /** //src//test//resources// */
    public static final String GLOBAL_TEST_RESOURCES_FOLDER = File.separator + "src" + File.separator + "test"
	    + File.separator + "resources" + File.separator;

    /** //src//test//resources//data//. */
    public static final String GLOBAL_DATA_FOLDER = GLOBAL_TEST_RESOURCES_FOLDER + File.separator + "data"
	    + File.separator;

    /** //src//test//resources//screenshots// */
    public static final String GLOBAL_SCREENSHOTS_FOLDER = GLOBAL_TEST_RESOURCES_FOLDER + File.separator + "screenshots"
	    + File.separator;

    /** //src//test//resources//reports// */
    public static final String REPORT_FILE_PATH = GLOBAL_TEST_RESOURCES_FOLDER + File.separator + "reports"
	    + File.separator;

    /** SELENIUM VERSION USED */
    public static final String SELENIUM_VERSION = "3.13.0";

    /** TEST ENVIRONMENT USED */
    public static final String ENVIRONMENT = "QA";

    /** //src//test//resources//data//environment.properties */
    public String ENVIRONMENT_PROPERTIES_PATH = GLOBAL_TEST_RESOURCES_FOLDER + "data" + File.separator
	    + "environment.properties";

    /** //src//test//resources//data//ios_capabilities.properties */
    public static final String IOS_CAPABILITY_PROPERTIES_PATH = GLOBAL_DATA_FOLDER + File.separator
	    + "ios_capabilities.properties";

    /** //src//test//resources//data//android_capabilities.properties */
    public static final String ANDROID_CAPABILITY_PROPERTIES_PATH = GLOBAL_DATA_FOLDER + File.separator
	    + "android_capabilities.properties";

    /** //src//test//resources//data//ipad_capabilities.properties */
    public static final String IPAD_CAPABILITY_PROPERTIES_PATH = GLOBAL_DATA_FOLDER + File.separator
	    + "ipad_capabilities.properties";

    /** //src//test//resources//drivers// */
    public static final String GLOBAL_DRIVERS_FOLDER = GLOBAL_TEST_RESOURCES_FOLDER + File.separator + "drivers"
	    + File.separator;

    /** //src//test//resources//drivers//geckodriver.exe */
    public static final String GECKO_DRIVER_PATH = GLOBAL_DRIVERS_FOLDER + File.separator + "geckodriver.exe";

    /** //src//test//resources//drivers//linux//geckodriver */
    public static final String GECKO_DRIVER_LINUX_PATH = GLOBAL_DRIVERS_FOLDER + File.separator + LINUX_OS
	    + "geckodriver";

    /** //src//test//resources//drivers//chromedriver.exe */
    public static final String CHROME_DRIVER_PATH = GLOBAL_DRIVERS_FOLDER + File.separator + "chromedriver.exe";

    /** //src//test//resources//drivers//linux//chromedriver */
    public static final String CHROME_LINUX_DRIVER_PATH = GLOBAL_DRIVERS_FOLDER + File.separator + LINUX_OS
	    + File.separator + "chromedriver";

    /** //src//test//resources//drivers//IEDriverServer.exe */
    public static final String IE_DRIVER_PATH = GLOBAL_DRIVERS_FOLDER + File.separator + "IEDriverServer.exe";

    public static final String EDGE_DRIVER_PATH = GLOBAL_DRIVERS_FOLDER + File.separator + "MicrosoftWebDriver.exe";


}
