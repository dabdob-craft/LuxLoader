plugins {
    id 'java'
}

group = 'net.lux'
version = '1.0.0'

repositories {
    mavenCentral()
    maven { url 'https://libraries.minecraft.net/' }
}

dependencies {
    implementation 'org.ow2.asm:asm:9.5'
    implementation 'org.ow2.asm:asm-tree:9.5'
    
    implementation 'com.google.code.gson:gson:2.10.1'
}

jar {
    manifest {
        attributes(
            'Main-Class': 'net.lux.launcher.LuxLauncher',
            'Premain-Class': 'net.lux.launcher.LuxLauncher', // لتشغيل اللودر كـ Java Agent
            'Can-Retransform-Classes': 'true'
        )
    }
}
