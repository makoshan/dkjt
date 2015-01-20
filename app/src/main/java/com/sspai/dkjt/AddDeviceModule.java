package com.sspai.dkjt;

import dagger.Module;
import dagger.Provides;
import com.sspai.dkjt.model.Device;

import java.util.HashSet;
import java.util.Set;

import static dagger.Provides.Type.SET;
import static dagger.Provides.Type.SET_VALUES;

@Module(library = true)
public class AddDeviceModule {
    @Provides(type = SET_VALUES)
    Set<Device> provideEmptyDevices() {
        return new HashSet<>(); // Empty set to ensure the Set is initialized.
    }

    @Provides(type = SET)
    Device provideNexus9() {
        return new Device.Builder().setId("nexus_9").setName("Nexus 9").setCategory("google").setPortOffset(347, 513).setPortSize(1536, 2048).setRealSize(1536, 2048).addProductId("volantis").build();
    }

    @Provides(type = SET)
    Device provideNexus72013() {
        return new Device.Builder().setId("nexus_7_2013").setName("Nexus 7 (2013)").setCategory("google").setPortOffset(244, 326).setPortSize(800, 1280).setRealSize(1200, 1920).addProductId("razor").addProductId("razorg").build();
    }

    @Provides(type = SET)
    Device provideNexus6() {
        return new Device.Builder().setId("nexus_6").setName("Nexus 6").setCategory("google").setPortOffset(245, 366).setPortSize(1080, 1920).setRealSize(1440, 2560).addProductId("shamu").build();
    }

    @Provides(type = SET)
    Device provideNexus5() {
        return new Device.Builder().setId("nexus_5").setName("Nexus 5").setCategory("google").setPortOffset(306, 436).setPortSize(1080, 1920).setRealSize(1080, 1920).addProductId("hammerhead").build();
    }

    @Provides(type = SET)
    Device provideNexus4() {
        return new Device.Builder().setId("nexus_4").setName("Nexus 4").setCategory("google").setPortOffset(213, 350).setPortSize(768, 1280).setRealSize(768, 1280).addProductId("occam").build();
    }

    @Provides(type = SET)
    Device provideGalaxyNexus() {
        return new Device.Builder().setId("galaxy_nexus").setName("Galaxy Nexus").setCategory("google").setPortOffset(216, 353).setPortSize(720, 1280).setRealSize(720, 1280).addProductId("takju").addProductId("yakju").addProductId("mysid").addProductId("mysidspr").build();
    }

    @Provides(type = SET)
    Device provideSamsungS5w() {
        return new Device.Builder().setId("samsung_s5w").setName("Galaxy S5").setCategory("samsung").setPortOffset(308, 477).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideSamsungNote3b() {
        return new Device.Builder().setId("samsung_note3b").setName("Galaxy Note3").setCategory("samsung").setPortOffset(259, 337).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideHtcM7() {
        return new Device.Builder().setId("htc_m7").setName("HTC M7").setCategory("htc").setPortOffset(308, 399).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideHtcM8() {
        return new Device.Builder().setId("htc_m8").setName("HTC M8").setCategory("htc").setPortOffset(308, 425).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideSonyZ3b() {
        return new Device.Builder().setId("sony_z3b").setName("Z3 Black").setCategory("Sony").setPortOffset(306, 436).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideSonyZ3w() {
        return new Device.Builder().setId("sony_z3w").setName("Z3 White").setCategory("Sony").setPortOffset(306, 436).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideSonyZ3c() {
        return new Device.Builder().setId("sony_z3c_green").setName("Z3 Compact").setCategory("Sony").setPortOffset(268, 367).setPortSize(720, 1280).setRealSize(720, 1280).build();
    }

    @Provides(type = SET)
    Device provideSonyZ2b() {
        return new Device.Builder().setId("sony_z2b").setName("Z2 Black").setCategory("Sony").setPortOffset(306, 436).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideSonyZ2w() {
        return new Device.Builder().setId("sony_z2w").setName("Z2 White").setCategory("Sony").setPortOffset(306, 436).setPortSize(1080, 1920).setRealSize(1920, 1080).build();
    }

    @Provides(type = SET)
    Device provideSonyZ1() {
        return new Device.Builder().setId("sony_z1").setName("Xperia Z1").setCategory("Sony").setPortOffset(221, 340).setPortSize(720, 1280).setRealSize(1920, 1080).build();
    }

    @Provides(type = SET)
    Device provideLGG2() {
        return new Device.Builder().setId("lg_g2").setName("LG G2").setCategory("lge").setPortOffset(308, 477).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideHuaweiP7B() {
        return new Device.Builder().setId("huawei_p7b").setName("P7 Black").setCategory("Huawei").setPortOffset(268, 457).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideHuaweiP7W() {
        return new Device.Builder().setId("huawei_p7").setName("P7 White").setCategory("Huawei").setPortOffset(268, 457).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideNubiaZ7() {
        return new Device.Builder().setId("nubia_z7").setName("Nubia Z7").setCategory("nubia").setPortOffset(228, 383).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideNubiaX6() {
        return new Device.Builder().setId("nubia_x6").setName("Nubia X6").setCategory("nubia").setPortOffset(228, 383).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideNubiaZ5s() {
        return new Device.Builder().setId("nubia_z5s").setName("Nubia Z5S").setCategory("nubia").setPortOffset(228, 383).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideNubiaZ5sMini() {
        return new Device.Builder().setId("nubia_z5smini").setName("Nubia Z5S mini").setCategory("nubia").setPortOffset(123, 278).setPortSize(720, 1080).setRealSize(720, 1080).build();
    }

    @Provides(type = SET)
    Device provideXiaomiMI4b() {
        return new Device.Builder().setId("xiaomi_mi4b").setName("Mi4 Black").setCategory("Xiaomi").setPortOffset(302, 430).setPortSize(1080, 1920).setRealSize(1080, 1920).addProductId("cancro").addProductId("pisces").build();
    }

    @Provides(type = SET)
    Device provideXiaomiMI4w() {
        return new Device.Builder().setId("xiaomi_mi4w").setName("Mi4 White").setCategory("Xiaomi").setPortOffset(306, 436).setPortSize(1080, 1920).setRealSize(1080, 1920).addProductId("cancro").addProductId("pisces").build();
    }

    @Provides(type = SET)
    Device provideXiaomiNote() {
        return new Device.Builder().setId("xiaomi_note").setName("Redmi Note").setCategory("Xiaomi").setPortOffset(213, 353).setPortSize(720, 1280).setRealSize(720, 1280).addProductId("lcsh92_wet_jb9").addProductId("armani").addProductId("dior").build();
    }

    @Provides(type = SET)
    Device provideXiaomiMI2s() {
        return new Device.Builder().setId("xiaomi_mi2s").setName("Mi2 Black").setCategory("Xiaomi").setPortOffset(216, 353).setPortSize(720, 1280).setRealSize(720, 1280).addProductId("taurus").addProductId("aries").build();
    }

    @Provides(type = SET)
    Device provideMeizuMX4Pro() {
        return new Device.Builder().setId("meizu_mx4pro").setName("MX4 Pro Black").setCategory("Meizu").setPortOffset(305, 515).setPortSize(1536, 2560).setRealSize(1536, 2560).build();
    }

    @Provides(type = SET)
    Device provideMeizuMX4ProW() {
        return new Device.Builder().setId("meizu_mx4prow").setName("MX4 Pro White").setCategory("Meizu").setPortOffset(305, 515).setPortSize(1536, 2560).setRealSize(1536, 2560).build();
    }

    @Provides(type = SET)
    Device provideMeizuMX4() {
        return new Device.Builder().setId("meizu_mx4b").setName("MX4 Black").setCategory("Meizu").setPortOffset(268, 472).setPortSize(1152, 1920).setRealSize(1152, 1920).addProductId("meizu_mx4").build();
    }

    @Provides(type = SET)
    Device provideMeizuMX4w() {
        return new Device.Builder().setId("meizu_mx4w").setName("MX4 White").setCategory("Meizu").setPortOffset(268, 472).setPortSize(1152, 1920).setRealSize(1152, 1920).addProductId("meizu_mx4").build();
    }

    @Provides(type = SET)
    Device provideMeilanNote() {
        return new Device.Builder().setId("meilan_note").setName("Meilan Note").setCategory("Meizu").setPortOffset(306, 436).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideMeizuMX3b() {
        return new Device.Builder().setId("meizu_mx3b").setName("MX3 Black").setCategory("Meizu").setPortOffset(310, 497).setPortSize(1080, 1800).setRealSize(1800, 1080).addProductId("meizu_mx3").build();
    }

    @Provides(type = SET)
    Device provideMeizuMX3w() {
        return new Device.Builder().setId("meizu_mx3w").setName("MX3 White").setCategory("Meizu").setPortOffset(310, 497).setPortSize(1080, 1800).setRealSize(1800, 1080).addProductId("meizu_mx3").build();
    }

    @Provides(type = SET)
    Device provideMeizuMX2b() {
        return new Device.Builder().setId("meizu_mx2b").setName("MX2 Black").setCategory("Meizu").setPortOffset(228, 326).setPortSize(800, 1280).setRealSize(1280, 800).addProductId("meizu_mx2").build();
    }

    @Provides(type = SET)
    Device provideMeizuMX2w() {
        return new Device.Builder().setId("meizu_mx2w").setName("MX2 White").setCategory("Meizu").setPortOffset(228, 326).setPortSize(800, 1280).setRealSize(1280, 800).addProductId("meizu_mx2").build();
    }

    @Provides(type = SET)
    Device provideOnePlusOne() {
        return new Device.Builder().setId("oneplus_one").setName("OnePlus One").setCategory("ONEPLUS").setPortOffset(305, 431).setPortSize(1080, 1920).setRealSize(1080, 1920).addProductId("bacon").build();
    }

    @Provides(type = SET)
    Device provideSmartisanT1() {
        return new Device.Builder().setId("chuizi_t1").setName("Smartisan T1").setCategory("smartisan").setPortOffset(307, 436).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideSmartisanT1w() {
        return new Device.Builder().setId("chuizi_t1w").setName("Smartisan T1").setCategory("smartisan").setPortOffset(307, 436).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }
    @Provides(type = SET)
    Device provideIphone6() {
        return new Device.Builder().setId("iphone_6").setName("iPhone 6").setCategory("other").setPortOffset(166, 337).setPortSize(720, 1280).setRealSize(1080, 1920).build();
    }

    @Provides(type = SET)
    Device provideNokia930() {
        return new Device.Builder().setId("nokia_930").setName("Lumia 930").setCategory("other").setPortOffset(308, 401).setPortSize(1080, 1920).setRealSize(1080, 1920).build();
    }


}
