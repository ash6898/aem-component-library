package com.aakash.portfolio.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { Resource.class,
        SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeroBannerModel {

    @SlingObject
    private Resource resource;

    @ValueMapValue
    private String jobTitle;

    @ValueMapValue
    private String name;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String button1Text;

    @ValueMapValue
    private String button2Text;

    @ValueMapValue
    private String button1Path;

    @ValueMapValue
    private String button2Path;

    @ValueMapValue
    private String button1Download;

    @ValueMapValue
    private String imagePath;

    @ValueMapValue
    private String styleClass;

    // Getters
    public String getJobTitle() {
        return jobTitle;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getButton1Text() {
        return button1Text;
    }

    public String getButton2Text() {
        return button2Text;
    }

    public String getButton1Path() {
        return button1Path;
    }

    public String getButton2Path() {
        return button2Path;
    }

    public String getButton1Download() {
        return button1Download;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getStyleClass() {
        return styleClass;
    }
}
