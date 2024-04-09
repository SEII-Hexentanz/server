plugins {
    id("java")
    `maven-publish`
}

group = "at.aau"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "at.aau"
            artifactId = "hexentanz-shared"
            version = version

            from(components["java"])
        }
    }
}