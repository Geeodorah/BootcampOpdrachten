using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Firefox;

namespace UnitTestProject1
{
    [TestClass]
    public class Login
    {
        IWebDriver driver;
        readonly string url = "https://techblog.polteq.com/testshop/index.php";

        [TestInitialize]
        public void TestSetup()
        {
            driver = new FirefoxDriver();
            driver.Navigate().GoToUrl(url);
            driver.FindElement(By.ClassName("login")).Click();
            driver.FindElement(By.Id("email")).SendKeys("ben.brugman@polteq.com");
            driver.FindElement(By.Id("passwd")).SendKeys("polteq");
            driver.FindElement(By.Id("SubmitLogin")).Click();
        }

        [TestMethod]
        public void LoginSuccesfull()
        {
            String partToCheck = driver.FindElement(By.CssSelector(".breadcrumb")).Text;
            Assert.AreEqual("> My account", partToCheck);
        }

        [TestMethod]
        public void TestTwo()
        {
            IWebElement buttonToValidate = driver.FindElement(By.CssSelector("a.logout"));
            Boolean buttonValidation = buttonToValidate.Displayed && buttonToValidate.Enabled;
            Assert.IsTrue(buttonValidation);
        }

        [TestCleanup]
        public void Cleanup()
        {
            driver.Quit();
        }
    }
}
