package com.aakash.portfolio.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
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

    private List<NavItem> navItemList;
    private String debugInfo;

    @PostConstruct
    protected void init() {
        navItemList = new ArrayList<>();
        StringBuilder debug = new StringBuilder();

        if (resource != null) {
            debug.append("Resource path: ").append(resource.getPath()).append(" | ");
            Resource navItems = resource.getChild("navItems");
            if (navItems != null) {
                debug.append("navItems found | ");
                for (Resource child : navItems.getChildren()) {
                    String label = child.getValueMap().get("label", String.class);
                    String path = child.getValueMap().get("path", String.class);
                    debug.append("child: ").append(child.getName()).append(" ");
                    if (label != null && path != null) {
                        navItemList.add(new NavItem(label, path));
                    }
                }
            } else {
                debug.append("navItems NOT found");
            }
        } else {
            debug.append("resource is NULL");
        }

        debugInfo = debug.toString();

        // Fallback: if no items found, add defaults for debugging
        if (navItemList.isEmpty()) {
            navItemList.add(new NavItem("Default1", "/content/aakash-portfolio/about.html"));
            navItemList.add(new NavItem("Default2", "/content/aakash-portfolio/projects.html"));
            navItemList.add(new NavItem("Default3", "/content/aakash-portfolio/contact.html"));
        }
    }

    public String getDebugInfo() {
        return debugInfo;
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
