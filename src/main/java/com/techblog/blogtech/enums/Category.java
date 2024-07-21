package com.techblog.blogtech.enums;

import lombok.Getter;

@Getter
public enum Category {

    SOFTWARE_ENGINEERING("Software Engineering"),
    WEB_DEVELOPMENT("Web Development"),
    MOBILE_DEVELOPMENT("Mobile Development"),
    DATA_SCIENCE("Data Science"),
    MACHINE_LEARNING("Machine Learning"),
    ARTIFICIAL_INTELLIGENCE("Artificial Intelligence"),
    CLOUD_COMPUTING("Cloud Computing"),
    CYBER_SECURITY("Cyber Security"),
    BLOCKCHAIN("Blockchain"),
    INTERNET_OF_THINGS("Internet of Things"),
    TECH_NEWS("Tech News"),
    STARTUPS("Startups"),
    TECH_REVIEWS("Tech Reviews"),
    PROGRAMMING_LANGUAGES("Programming Languages"),
    OPERATING_SYSTEMS("Operating Systems"),
    TECH_TRENDS("Tech Trends"),
    GADGETS("Gadgets"),
    ROBOTICS("Robotics"),
    VIRTUAL_REALITY("Virtual Reality"),
    AUGMENTED_REALITY("Augmented Reality");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

}
