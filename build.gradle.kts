plugins {
    java
    id ("org.springframework.boot").version("2.6.7")
    id("io.spring.dependency-management").version("1.0.11.RELEASE")
}

group = "me.zdziszkee"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.7")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}