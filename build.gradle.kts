plugins {
    java
    jacoco
    alias(libs.plugins.sonarqube)
}

group = "at.aau"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit)
    testRuntimeOnly(libs.junitEngine)
    implementation(libs.vavr)
    implementation(libs.log4j.core)
    implementation(libs.log4j.api)
    implementation(libs.log4j.slf4j)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

sonar {
    properties {
        property("sonar.projectKey", "SEII-Hexentanz_server")
        property("sonar.organization", "seii-hexentanz")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.java.coveragePlugin", "jacoco")
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "${project.projectDir}/build/reports/jacoco/jacocoTestReport/jacocoTestReport.xml"
        )
    }
}
