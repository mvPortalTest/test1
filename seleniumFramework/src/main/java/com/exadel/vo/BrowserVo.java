package com.exadel.vo;

/**
 * This value object represents a browser's details.
 *
 */

public class BrowserVo {


    /* ----- CONSTANTS ----- */
    private static final long serialVersionUID = 2928222348725848901L;

    /* ----- FIELDS ----- */
    private final String name;
    private final String version;
    private final String platform;


    /* ----- CONSTRUCTORS ----- */

    /**
     * Default constructor.
     *
     * @param name     The name of the browser
     * @param version  The version of the browser
     * @param platform The platform of the browser
     */
    public BrowserVo(String name, String version, String platform) {
        super();
        this.name = name;
        this.version = version;
        this.platform = platform;
    }


    /* ----- GETTERS  / SETTERS ----- */

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Gets the platform.
     *
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }
}
