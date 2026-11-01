# AEM Component Library

Personal learning and portfolio project for an AEM Engineer job search. It is a small Adobe Experience Manager component library: seven HTL components, Sling Models where a component needs Java, one client library, and a multi-module Maven build (`core`, `ui.apps`, `ui.content`).

This is practice code, not a client delivery. Aakash Baskaran is preparing for the Adobe AD0-E123 certification (in progress).

## Modules

```
aem-component-library/
├── core/          OSGi bundle with the Sling Models
├── ui.apps/       HTL, Touch UI dialogs, client library, embedded bundle
├── ui.content/    Sample homepage and assets
└── pom.xml
```

CSS and JavaScript live in the client library under `ui.apps`.

| Module | Installed path | Package |
| --- | --- | --- |
| `ui.apps` | `/apps/component-library` | Mixed package: components, dialogs, the client library, and the core bundle embedded at `/apps/component-library/install`. |
| `ui.content` | `/content/aakash-portfolio` | Content package with the sample page and assets. |

Resource types use the application name, for example `component-library/components/header`. The sample site stays under `/content/aakash-portfolio`.

## Components

| Component | Resource type | Sling Model | Dialog |
| --- | --- | --- | --- |
| Page | `component-library/components/page` | — | Page title and description |
| Header | `component-library/components/header` | `HeaderModel` | Site name, home link, navigation multifield |
| Hero banner | `component-library/components/hero-banner` | `HeroBannerModel` | Text, actions, image |
| Section title | `component-library/components/section-title` | — | Title and subtitle |
| Project card | `component-library/components/project-card` | `ProjectCardModel` | Title, rich text, link, image |
| Contact form | `component-library/components/contact-form` | — | Endpoint, button label, success copy |
| Footer | `component-library/components/footer` | `FooterModel` | Copyright and social links |

The page component includes the client library category `component-library.site` (CSS in the head, JavaScript at the end of the body). Dialogs are Touch UI dialogs built with Granite and Coral.

## Prerequisites

- JDK 11 or newer. The modules compile with `--release 11`.
- Maven 3.6.3 or newer.
- AEM 6.5 author, only if you want to install the packages. The default build does not need a running instance.

## Build

```bash
mvn clean install
```

That compiles the models, runs the JUnit 5 tests (io.wcm AemContext), and builds both content packages. It does not call AEM.

FileVault prints one warning on `ui.apps`: package type `mixed` is legacy. The package is mixed because it installs both the application nodes and the OSGi bundle. An `application` package is not allowed to contain a bundle.

Packages:

- `ui.apps/target/aem-component-library.ui.apps-1.0.0-SNAPSHOT.zip`
- `ui.content/target/aem-component-library.ui.content-1.0.0-SNAPSHOT.zip`

## Deploy to a local author

Start AEM 6.5 author on port 4502, then:

```bash
mvn clean install -PautoInstallPackage
```

The profile installs both packages to `http://localhost:4502/crx/packmgr/service.jsp` as `admin` / `admin`. Override the target with:

```bash
mvn clean install -PautoInstallPackage -Daem.host=localhost -Daem.port=4502 -Dvault.user=admin -Dvault.password=admin
```

The command fails if author is not running. After a successful install:

- Components and the bundle: `/apps/component-library`
- Sample page: `/content/aakash-portfolio/home.html`

Install `ui.apps` before `ui.content`. The reactor order already does that.

## Experience described on the sample page

The homepage text is limited to paid work:

- Associate Software Developer (AEM Platform) at Accenture, client Chubb Insurance, January–October 2021, on AEM 6.x. Published and maintained 100+ pages, standardised reusable component templates (about 30% less authoring time), administered DAM workflows, and did cross-browser and WCAG 2.0 QA.
- Android Developer at Zoho, November 2021–December 2022.
- Nearly two years of industry experience in total.
- Adobe AD0-E123 is in progress.

## Author

**Aakash Baskaran**  
[LinkedIn](https://www.linkedin.com/in/aakashbaskaran/) · [GitHub](https://github.com/ash6898)
