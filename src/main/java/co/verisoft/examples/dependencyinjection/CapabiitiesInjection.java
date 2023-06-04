/*
 * (C) Copyright 2023 VeriSoft (http://www.verisoft.co)
 *
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
package co.verisoft.examples.dependencyinjection;


import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


/**
 * This is a classic IoC Bean using spring depndency injection mechanism.
 * Note! We use the IoC mechanism only, and do NOT import spring boot dependency.
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since May 2023
 */
@Configuration
@ComponentScan("co.verisoft.examples")
public class CapabiitiesInjection {


    @Bean("chrome")
    @Primary
    public Capabilities getChromeCapabilities() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");


        return options;
    }

    @Bean("edge")
    public Capabilities getEdgeCapabilities(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--no-sandbox");
        return options;
    }

    @Bean("firefox")
    public Capabilities getFirefoxCapabilities(){
        return new FirefoxOptions();
    }
}
