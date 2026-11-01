package com.aakash.portfolio.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { Resource.class,
        SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterModel {

    @SlingObject
    private Resource resource;

    @ValueMapValue
    private String footerText;

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
            String iconPath = child.getValueMap().get("iconPath", String.class);
            if (label != null && path != null) {
                navItemList.add(new NavItem(label, path, iconPath));
            }
        }
    }

    public String getFooterText() {
        return footerText != null ? footerText : "© 2026 Aakash Portfolio";
    }

    public List<NavItem> getNavItems() {
        return navItemList != null ? navItemList : Collections.emptyList();
    }

    public static class NavItem {
        private final String label;
        private final String path;
        private final String iconPath;

        public NavItem(String label, String path, String iconPath) {
            this.label = label;
            this.path = path;
            this.iconPath = iconPath;
        }

        public String getLabel() {
            return label;
        }

        public String getPath() {
            return path;
        }

        public String getIconPath() {
            return iconPath;
        }
    }
}
