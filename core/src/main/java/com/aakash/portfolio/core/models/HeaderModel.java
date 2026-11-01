package com.aakash.portfolio.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Model(
    adaptables = {Resource.class, SlingHttpServletRequest.class},
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HeaderModel {

    @SlingObject
    private Resource resource;

    @ValueMapValue
    private String siteName;

    @ValueMapValue
    private String homePagePath;

    private List<NavItem> navItemList = Collections.emptyList();

    @PostConstruct
    protected void init() {
        navItemList = new ArrayList<>();
        if (resource == null) {
            return;
        }
        Resource navItems = resource.getChild("navItems");
        if (navItems == null) {
            return;
        }
        for (Resource child : navItems.getChildren()) {
            String label = child.getValueMap().get("label", String.class);
            String path = child.getValueMap().get("path", String.class);
            if (label != null && path != null) {
                navItemList.add(new NavItem(label, path));
            }
        }
    }

    public String getSiteName() {
        return siteName != null ? siteName : "Aakash Portfolio";
    }

    public String getHomePagePath() {
        return homePagePath != null ? homePagePath : "/content/aakash-portfolio/home.html";
    }

    public List<NavItem> getNavItems() {
        return navItemList != null ? navItemList : Collections.emptyList();
    }

    public static class NavItem {
        private final String label;
        private final String path;

        public NavItem(String label, String path) {
            this.label = label;
            this.path = path;
        }

        public String getLabel() {
            return label;
        }

        public String getPath() {
            return path;
        }
    }
}
