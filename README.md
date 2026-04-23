# AEM Component Library

Reusable AEM components built with Apache Sling, HTL (Sightly), and Sling Models.

## Project Structure

```
aem-component-library/
├── core/                        # Java Sling Models & OSGi services
│   └── src/main/java/
├── ui.apps/                     # Component definitions (HTL, dialogs, clientlibs)
│   └── src/main/content/
│       └── jcr_root/apps/component-library/components/
├── ui.content/                  # Sample content for components
│   └── src/main/content/
├── ui.frontend/                 # CSS / JS source (webpack)
│   └── src/main/webpack/
└── pom.xml
```

## Tech Stack

- **AEM 6.5** / Apache Sling
- **HTL (Sightly)** — templating
- **Sling Models** — Java backend models
- **OSGi** — service & component configuration
- **Maven** — build & deployment

## Components

| Component | Description | Sling Model |
|-----------|-------------|-------------|
| `page` | Base page component — wraps all portfolio pages | — |
| `header` | Site header with logo and navigation links | `HeaderModel.java` |
| `hero-banner` | Full-width hero section with name, title, and CTA | `HeroBannerModel.java` |
| `section-title` | Reusable section heading / subtitle block | — |
| `project-card` | Card displaying project image, title, description, and links | `ProjectCardModel.java` |
| `contact-form` | Contact form with client-side validation | — |
| `footer` | Site footer with social links and copyright | `FooterModel.java` |

## Getting Started

### Prerequisites
- AEM 6.5 running on `localhost:4502`
- Java 11
- Maven 3.6+

### Build & Deploy

```bash
# Build all modules
mvn clean install

# Deploy to local AEM author
mvn clean install -PautoInstallSinglePackage
```

## Author

**Aakash Baskaran**  
[LinkedIn](https://www.linkedin.com/in/aakashbaskaran/) · [GitHub](https://github.com/ash6898)
