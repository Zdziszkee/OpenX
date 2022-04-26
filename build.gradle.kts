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
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}