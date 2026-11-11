# Kurt Lock — devam planı

Bu depo ReChronoRain/HyperCeiler fork'u. Hedef: HyperOS için Good Lock tarzı, tamamen Türkçe,
kişisel kullanım uygulaması. Adı **Kurt Lock** olacak. Paket adı (`com.sevtinge.hyperceiler`)
değişmeyecek; Vector kapsamı ve üstüne kurulum bozulmasın.

## Cihaz
- Xiaomi 17T Pro (warhol), HyperOS 3.0.311.0 Global, Android 16, KernelSU Next + Vector (LSPosed).
- Android 16'da SystemUI kancaları `libhook/app/SystemUI/SystemUIB.java` içinden yüklenir
  (`SystemUIV` Android 15 içindir). Bir ayarı öne çıkarmadan önce kancasının SystemUIB'de
  kayıtlı olduğunu doğrula.

## Yapılanlar
- Türkçe çeviri tamamlandı ve baştan gözden geçirildi (`values-tr-rTR`, `strings_tr_extra.xml`).
- Derleme: `.github/workflows/tr_build.yml` (ubuntu, canary, sabit anahtar `tr1action.jks`),
  her push'ta `tr-N` etiketiyle Release'e APK koyar. Yerelde derleme denenmedi.
- İmza uyarısı afişi kaldırıldı, ipuçları dosyası yeniden yazıldı.
- `ChargingCVP.kt` içinde `a ?: b.findMethod{}` öncelik hatası düzeltildi; ancak bu kanca
  SystemUIB'de kayıtlı değil ve `LockScreenSettings` onu HyperOS 3'te "desteklenmiyor" olarak
  işaretliyor. Yani kilit ekranı şarj bilgisi bu telefonda çalışmıyor; öne çıkarma.

## Bilinen sorun
- Kontrol merkezi bir kez soluk gri oldu (bulanıklık gitti). Kapsamlar tek başına bozmuyor
  (Sistem Çerçevesi + Sistem Arayüzü + eklenti ile normal). Neden, kullanıcının açtığı bir
  kontrol merkezi ayarıydı; hangisi olduğu bilinmiyor. Bulanıklık/doku/renk ayarlarını
  kartlara koyma ya da "riskli" diye işaretle.

## Sıradaki iş
1. **Ad:** `library/core/.../values/strings_app.xml` `app_name` ve libhook `library_app_name`
   → "Kurt Lock". Türkçe metinlerdeki "HyperCeiler" geçişlerini ek uyumuyla değiştir.
2. **Yeni ana sayfa (ilk sekme):** en düşük riskli yol, `SettingsPageFragment` +
   `SettingsFragment` örneğindeki gibi bir tercih sayfası:
   - `KurtPageFragment` (fan.appcompat.app.Fragment, `fragment_settings` düzeni) içine
     `KurtFragment` (app `BasePreferenceFragment`, `getPreferenceScreenResId()` → `prefs_kurt`).
   - `prefs_kurt.xml`: simge + başlık + açıklamalı satırlar: Kilit ekranı, Durum çubuğu,
     Kontrol merkezi, Ana ekran, Son uygulamalar, Hareketler, Ses.
     `android:fragment` ile mevcut sayfalara (`hooker.systemui.LockScreenSettings`,
     `StatusBarSettings`, `ControlCenterSettings`, `NavigationSettings` ...) ya da
     `com.sevtinge.hyperceiler.prefs.XmlPreference` + `app:inflatedXml` ile seçilmiş XML'lere git.
   - `HomePageActivity.onPreferenceStartFragment` içindeki `instanceof` koşuluna `KurtFragment` ekle.
   - `rebuildContentPages()` ve `bottom_nav_menu.xml`: sıra Kurt Lock · Tümü (eski liste) ·
     Ayarlar · Hakkında.
3. **Seçilmiş sayfalar:** her kart için 8-10 ayarlık XML; öğeleri mevcut XML'lerden aynen
   kopyala (anahtarlar aynı kalmalı), sonuna "Tüm ayarlar" bağlantısı koy.
4. **Yeni özellikler:** uygulama başına ses, ses tuşuyla şarkı değiştirme, kilit ekranı kısayolları.

## Kullanıcı tercihleri
- Türkçe, sade anlatım; APK'yı Releases'ten indirip elle kurar.
- Ayar yüklemek için: JSON yedek dosyası → uygulamada Ayarlar → Modül ayarlarını geri yükle.
