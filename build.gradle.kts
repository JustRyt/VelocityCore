plugins {
    id("java")
    id("io.freefair.lombok") version "9.2.0"
    id("com.gradleup.shadow") version "9.3.1"
}

group = "net.justryt.vcore"
version = "1.0.0"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.5.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.5.0-SNAPSHOT")
    implementation("solar.squares:pixel-width-utils:1.1.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks.shadowJar {
    archiveClassifier.set("")
    minimize()
}