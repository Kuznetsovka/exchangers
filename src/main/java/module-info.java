module com.systemair.exchangers {
    requires org.seleniumhq.selenium.api;
    requires org.seleniumhq.selenium.support;
    requires lombok;
    requires log4j;

    exports com.systemair.exchangers;
    exports com.systemair.exchangers.domain.exchangers;
    exports com.systemair.exchangers.domain;
    exports com.systemair.exchangers.service;
}