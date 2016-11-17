package com.exadel.enumeration;

/**
 * This represents a page and it's URL location relative to the host.
 *
 */
public enum PageLocation {

    EXADEL_HOME("/", "Home Page"),

    CONTACT_US("/contact-info/", "Contact Us"),
    PRODUCT_DEVELOPMENT("/product-development/", "Product Development"),
    APPLICATION_DEVELOPMENT("/application-development-maintenance/", "Application Development");

    private final String url;
    private final String name;

    /* ----- CONSTRUCTORS ----- */

    /**
     * @description Instantiates a new page location.
     *
     * @param url  the url;
     * @param name the name
     */
    private PageLocation(String url, String name) {
        this.url = url;
        this.name = name;
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}