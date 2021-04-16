package Test;

import MethodsPage.BaseTest;
import MethodsPage.CaseMethods;
import org.testng.annotations.Test;

public class Tests extends BaseTest {

    CaseMethods caseMethods;

    @Test
    public void VerifyAuthor(){
        caseMethods = new CaseMethods(driver);
        caseMethods.verifyAuthor();
    }

    @Test
    public void VerifyImage(){
        caseMethods = new CaseMethods(driver);
        caseMethods.verifyImage();
    }



    @Test
    public void VerifyTitle(){
        caseMethods = new CaseMethods(driver);
        caseMethods.verifyTitle();
    }



    @Test
    public void VerifyLinks(){
        caseMethods = new CaseMethods(driver);
        caseMethods.verifyLinks();
    }
}
