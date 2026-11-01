package com.aakash.portfolio.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class HeroBannerModelTest {

    private final AemContext context = new AemContext();

    @BeforeEach
    void setUp() {
        context.addModelsForClasses(HeroBannerModel.class);
    }

    @Test
    void readsAuthoredHeroFields() {
        context.create().resource("/content/aakash-portfolio/home/jcr:content/hero-banner",
                "jobTitle", "Associate Software Developer",
                "name", "Aakash Baskaran",
                "description", "Nearly two years of industry experience.",
                "button1Text", "Projects",
                "button1Path", "/content/aakash-portfolio/home.html#projects",
                "button2Text", "LinkedIn",
                "button2Path", "https://www.linkedin.com/in/aakashbaskaran/",
                "imagePath", "/content/aakash-portfolio/assets/images/Illustration.png",
                "styleClass", "about-section");

        HeroBannerModel model = context.resourceResolver()
                .getResource("/content/aakash-portfolio/home/jcr:content/hero-banner")
                .adaptTo(HeroBannerModel.class);

        assertNotNull(model);
        assertEquals("Associate Software Developer", model.getJobTitle());
        assertEquals("Aakash Baskaran", model.getName());
        assertEquals("Nearly two years of industry experience.", model.getDescription());
        assertEquals("Projects", model.getButton1Text());
        assertEquals("/content/aakash-portfolio/home.html#projects", model.getButton1Path());
        assertEquals("LinkedIn", model.getButton2Text());
        assertEquals("https://www.linkedin.com/in/aakashbaskaran/", model.getButton2Path());
        assertEquals("/content/aakash-portfolio/assets/images/Illustration.png", model.getImagePath());
        assertEquals("about-section", model.getStyleClass());
    }

    @Test
    void leavesOptionalFieldsNullWhenTheyAreNotAuthored() {
        context.create().resource("/content/aakash-portfolio/home/jcr:content/hero-banner");

        HeroBannerModel model = context.resourceResolver()
                .getResource("/content/aakash-portfolio/home/jcr:content/hero-banner")
                .adaptTo(HeroBannerModel.class);

        assertNotNull(model);
        assertNull(model.getJobTitle());
        assertNull(model.getImagePath());
        assertNull(model.getButton1Download());
    }
}
