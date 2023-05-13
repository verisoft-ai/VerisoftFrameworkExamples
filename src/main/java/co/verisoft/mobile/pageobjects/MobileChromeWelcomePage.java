/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package co.verisoft.mobile.pageobjects;

import co.verisoft.fw.pages.MobileBasePage;
import co.verisoft.fw.utils.Waits;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MobileChromeWelcomePage extends MobileBasePage {

    @AndroidFindBy(id = "com.android.chrome:id/terms_accept")
    @iOSXCUITFindBy(id = "SOME IOS ID")
    private WebElement accept;


    public MobileChromeWelcomePage(WebDriver driver) {
        super(driver);
    }


    @Override
    public boolean isOnPage() {
        return Waits.visibilityOf(driver, timeOut, accept).isDisplayed();
    }


    public MobileChromeTurnOnSyncPage accept(){
        this.accept.click();
        return new MobileChromeTurnOnSyncPage(driver);
    }
}
