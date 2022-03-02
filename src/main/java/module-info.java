module com.systemair.exchangers {
    requires org.seleniumhq.selenium.edge_driver;
    requires org.seleniumhq.selenium.chrome_driver;
    requires lombok;
    requires org.seleniumhq.selenium.support;
    requires log4j;

    exports com.systemair.exchangers;
    exports com.systemair.exchangers.domain.exchangers;
    exports com.systemair.exchangers.domain;
    exports com.systemair.exchangers.service;
}