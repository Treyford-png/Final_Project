import org.gradle.kotlin.dsl.implementation

plugins {
    java
    id("org.openjfx.javafxplugin") version "0.1.0"
    application
}


javafx {
    version = "21"
    modules = listOf("javafx.controls", "javafx.fxml")
}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.openjfx:javafx-controls:21")
    implementation("org.openjfx:javafx-fxml:21")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("bsu.edu.cs.cs222.games.liberty_bell.RunLibertyBell")
}

tasks.withType<JavaExec> {
    jvmArgs("--enable-native-access=javafx.graphics")
}
