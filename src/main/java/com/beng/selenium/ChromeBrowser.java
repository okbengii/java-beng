package com.beng.selenium;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * chrome浏览器版本与chromeDriver驱动包版本是要注意的，以下摘抄自网络，出处：http://blog.csdn.net/huilan_same/article/details/51896672
 * 下载地址：http://chromedriver.storage.googleapis.com/index.html
 * 下载地址：http://npm.taobao.org/mirrors/chromedriver/2.42/* 将 background.js 和
 * manifest.json 文件打包成 zip ，注意一定到在两个文件的当前目录下进行打包
 * 
 * @author apple
 */
public class ChromeBrowser {

    public static void main(String[] args) {
        ChromeOptions co = new ChromeOptions();
        co.addExtensions(new File("/Users/apple/project/xpi/proxy/test.zip")); // 将proxy的信息添加到ChromeOptions中
        System.setProperty("webdriver.chrome.driver", "/Users/apple/app/chrome-driver/chromedriver");// chromedriver服务地址
        WebDriver driver = new ChromeDriver(co);
        driver.get("https://www.baidu.com");
        try {
            WebElement input = driver.findElement(By.id("kw"));
            input.sendKeys("ip");
            WebElement oneClick = driver.findElement(By.id("su"));
            oneClick.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(driver.getTitle());
        // driver.quit();// 退出浏览器
    }
}
