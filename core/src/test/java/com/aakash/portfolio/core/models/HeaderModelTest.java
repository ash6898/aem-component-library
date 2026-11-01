package com.aakash.portfolio.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class HeaderModelTest {

    private final AemContext context = new AemContext();

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(HeaderModel.class);
    }

    @Test
    void readsAuthoredNavigation() {
        context.create().resource("/content/aakash-portfolio/home/jcr:content/header",
                "siteName", "Aakash Portfolio",
                "homePagePath", "/content/aakash-portfolio/home.html");
        context.create().resource("/content/aakash-portfolio/home/jcr:content/header/navItems/item1",
                "label", "About",
                "path", "/content/aakash-portfolio/home.html#about");
        context.create().resource("/content/aakash-portfolio/home/jcr:content/header/navItems/item2",
                "label", "Incomplete");
        context.create().resource("/content/aakash-portfolio/home/jcr:content/header/navItems/item3",
                "label", "Contact",
                "path", "/content/aakash-portfolio/home.html#contact");

        HeaderModel model = context.resourceResolver()
                .getResource("/content/aakash-portfolio/home/jcr:content/header")
                .adaptTo(HeaderModel.class);

        assertNotNull(model);
        assertEquals("Aakash Portfolio", model.getSiteName());
        assertEquals("/content/aakash-portfolio/home.html", model.getHomePagePath());
        assertEquals(2, model.getNavItems().size());
        assertEquals("About", model.getNavItems().get(0).getLabel());
        assertEquals("/content/aakash-portfolio/home.html#about", model.getNavItems().get(0).getPath());
        assertEquals("Contact", model.getNavItems().get(1).getLabel());
    }

    @Test
    void leavesNavigationEmptyWhenNothingIsAuthored() {
        context.create().resource("/content/aakash-portfolio/home/jcr:content/header");

        HeaderModel model = context.resourceResolver()
                .getResource("/content/aakash-portfolio/home/jcr:content/header")
                .adaptTo(HeaderModel.class);

        assertNotNull(model);
        assertTrue(model.getNavItems().isEmpty());
        assertEquals("Aakash Portfolio", model.getSiteName());
        assertEquals("/content/aakash-portfolio/home.html", model.getHomePagePath());
    }
}
