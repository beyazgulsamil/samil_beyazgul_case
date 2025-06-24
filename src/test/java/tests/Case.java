package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.LandingPage;
import pages.OpenPositionPage;
import utilities.Utilities;

public class Case extends Utilities {

    LandingPage landingPage;
    CareersPage careersPage;
    OpenPositionPage openPositionPage;

    @BeforeMethod
    public void before() {
        initialize();
    }

    @Test
    public void test() throws InterruptedException {
        // Step 1 - Visit https://useinsider.com/ and check Insider home page is opened or not
        go("https://useinsider.com/");
        click(LandingPage.cookieAcceptButton, "Cookie Accept Button");
        landingPage = new LandingPage(driver);
        waitUntilVisible(LandingPage.insiderLogo,10,"Insider Logo");

        // Step 2 - Select the “Company” menu in the navigation bar, select “Careers” and check Career
        //page, its Locations, Teams, and Life at Insider blocks are open or not
        careersPage = new CareersPage(driver);
        click(LandingPage.navBarCompany, "Company button");
        clickText("Careers");
        waitUntilVisible(CareersPage.teamsArea,10,"Teams Area");
        waitUntilVisible(CareersPage.locationsArea,10,"Locations Area");
        waitUntilVisible(CareersPage.lifeAtInsiderArea,10,"Life at Insider Area");

        //Step 3 - Go to https://useinsider.com/careers/quality-assurance/, click “See all QA jobs”, filter
        //jobs by Location: “Istanbul, Turkiye”, and Department: “Quality Assurance”, check the
        //presence of the jobs list
        openPositionPage = new OpenPositionPage(driver);
        go("https://useinsider.com/careers/quality-assurance/");
        clickText("See all QA jobs");
        waitUntilVisible(OpenPositionPage.qaTitle,10,"QA Title");
        click(OpenPositionPage.filterByLocation, "Filter By Location");
        waitUntilVisible(OpenPositionPage.listLocation,10,"List Location");
        loopAndClick(OpenPositionPage.listLocation,"Istanbul, Turkiye");

        // Step 4 - Check that all jobs’ Position contains “Quality Assurance”, Department contains
        //“Quality Assurance”, and Location contains “Istanbul, Turkey”
        scroll(waitUntilVisible(OpenPositionPage.positionList,10,"Position List"));
        waitUntilVisible(OpenPositionPage.listItemTeam,10,"List Item Location");
        openPositionPage.verifyJobFilters("istanbul-turkiye", "qualityassurance");

        // Step 5 -Click the “View Role” button and check that this action redirects us to the Lever
        //Application form page
        click(mouseHover(OpenPositionPage.viewRoleButton,"View Role Button"),"View Role Button");
        switchToNewTab();
        verifyUrlContains("jobs.lever.co/useinsider");

    }


    @AfterMethod
    public void after() {
        kill();
    }
}
