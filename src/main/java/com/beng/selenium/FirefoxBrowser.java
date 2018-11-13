package com.beng.selenium;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxBrowser {

    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox-bin");
        // 设置代理
        FirefoxProfile profile = new FirefoxProfile();

        // add new header
        File file1 = new File("/Users/apple/project/xpi/modify_headers-0.7.1.1-fx.xpi");
        profile.addExtension(file1);

        profile.setPreference("extensions.modify_headers.currentVersion", "0.7.1.1-fx");
        profile.setPreference("modifyheaders.confi g.active", true);
        profile.setPreference("modifyheaders.headers.count", 1);
        profile.setPreference("modifyheaders.headers.action0", "Add");
        profile.setPreference("modifyheaders.headers.name0", "Proxy-Switch-Ip");
        profile.setPreference("modifyheaders.headers.value0", "yes");
        profile.setPreference("modifyheaders.headers.enabled0", true);

        // add proxy
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.http", "localhost");
        profile.setPreference("network.proxy.http_port", 8080);
        profile.setPreference("network.proxy.ssl", "localhost");
        profile.setPreference("network.proxy.ssl_port", 8080);
        profile.setPreference("network.proxy.ftp", "localhost");
        profile.setPreference("network.proxy.ftp_port", 8080);
        profile.setPreference("network.proxy.socks", "localhost");
        profile.setPreference("network.proxy.socks_port", 8080);
        profile.setPreference("network.proxy.no_proxies_on", "localhost, 127.0.0.1");
        profile.setPreference("network.proxy.autoconfig_url", "/Users/apple/project/xpi/proxy/proxy.pac");

        File file2 = new File("/Users/apple/project/xpi/close_proxy_authentication-1.1.xpi");
        profile.addExtension(file2);
        String credentials;
        credentials = "username:password";
        try {
            byte[] credentials_ascii = credentials.getBytes("ascii");
            byte[] encoded = Base64.getEncoder().encode(credentials_ascii);
            credentials = new String(encoded, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        profile.setPreference("extensions.closeproxyauth.authtoken", credentials);
        WebDriver webDriver = new FirefoxDriver(profile);
        webDriver.get("https://www.baidu.com");
        WebElement input = webDriver.findElement(By.id("kw"));
        input.sendKeys("ip");
        WebElement oneClick = webDriver.findElement(By.id("su"));
        oneClick.click();
    }
}
