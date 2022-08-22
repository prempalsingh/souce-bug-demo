plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.2.1")
}

gradlePlugin {
    plugins {
        create("CustomPlugin") {
            id = "com.example.custom"
            implementationClass = "CustomPlugin"
            version = "1.0.0"
        }
    }
}