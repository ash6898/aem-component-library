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
class FooterModelTest {

    private final AemContext context = new AemContext();

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(FooterModel.class);
    }

    @Test
    void readsFooterTextAndSkipsIncompleteLinks() {
        context.create().resource("/content/aakash-portfolio/home/jcr:content/footer",
                "footerText", "© 2026 Aakash Portfolio");
        context.create().resource("/content/aakash-portfolio/home/jcr:content/footer/navItems/item1",
                "label", "LinkedIn",
                "path", "https://www.linkedin.com/in/aakashbaskaran/",
                "iconPath", "/content/aakash-portfolio/assets/icons/linkedin.svg");
        context.create().resource("/content/aakash-portfolio/home/jcr:content/footer/navItems/item2",
                "label", "Missing path");

        FooterModel model = context.resourceResolver()
                .getResource("/content/aakash-portfolio/home/jcr:content/footer")
                .adaptTo(FooterModel.class);

        assertNotNull(model);
        assertEquals("© 2026 Aakash Portfolio", model.getFooterText());
        assertEquals(1, model.getNavItems().size());
        assertEquals("LinkedIn", model.getNavItems().get(0).getLabel());
        assertEquals("https://www.linkedin.com/in/aakashbaskaran/", model.getNavItems().get(0).getPath());
        assertEquals("/content/aakash-portfolio/assets/icons/linkedin.svg", model.getNavItems().get(0).getIconPath());
    }

    @Test
    void usesCurrentYearCopyrightWhenTextIsMissing() {
        context.create().resource("/content/aakash-portfolio/home/jcr:content/footer");

        FooterModel model = context.resourceResolver()
                .getResource("/content/aakash-portfolio/home/jcr:content/footer")
                .adaptTo(FooterModel.class);

        assertNotNull(model);
        assertEquals("© 2026 Aakash Portfolio", model.getFooterText());
        assertTrue(model.getNavItems().isEmpty());
    }
}
