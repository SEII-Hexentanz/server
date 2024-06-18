plugins {
    java
    jacoco
    alias(libs.plugins.sonarqube)
    alias(libs.plugins.shadow)
    alias(libs.plugins.jib)
}

group = "at.aau"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.vavr)
    implementation(libs.log4j.core)
    implementation(libs.log4j.api)
    implementation(libs.log4j.slf4j)
    implementation(project(":shared"))

    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.junit.api)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.junit)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required.set(true)
    }
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

jib {
    from {
        image = "eclipse-temurin:21-jre"
    }
    to {
        image = "hexentanz:$version"
    }
    container {
        ports = listOf("8080")
    }
}