# Absen App — Build APK Otomatis (Tanpa Install Apapun di Laptop)

## Cara paling gampang: GitHub Actions (gratis, jalan di cloud)

1. Buat akun GitHub kalau belum punya: https://github.com/signup
2. Buat repository baru (public atau private, bebas), misalnya nama `absen-app`.
3. Upload SEMUA isi folder ini (kecuali `absen-app-starter.zip` sendiri) ke
   repo tersebut. Cara termudah tanpa command line:
   - Buka repo di github.com -> tombol "Add file" -> "Upload files"
   - Drag semua file & folder (termasuk folder `.github`, `android-snippets`,
     `ios-snippets`, `www`) ke situ, lalu klik Commit changes.
4. Begitu ke-upload, buka tab Actions di repo tersebut. Workflow
   "Build Android APK" akan otomatis jalan (build memakan waktu +-3-5 menit).
5. Kalau sudah selesai (centang hijau), klik run tersebut -> scroll ke
   bagian Artifacts -> download `absen-app-debug-apk.zip`.
6. Extract zip itu -> dapat file `app-debug.apk` -- ini file yang tinggal
   kamu kirim ke HP Android dan install langsung (aktifkan dulu
   "Izinkan install dari sumber tidak dikenal" di HP).

Setiap kali kamu push perubahan ke repo (misal ganti URL atau ganti nama
app), APK baru otomatis ke-build lagi -- tidak perlu setup ulang.

## Soal iOS (IPA)

Ini bukan soal alat, tapi aturan Apple: file iOS tidak bisa dibuat "siap
install" tanpa Apple ID/sertifikat developer, sekalipun dibuild di cloud.
Nggak ada CI/robot manapun yang bisa melewati ini. Tiga opsi realistis:

| Opsi | Butuh apa | Hasil |
|---|---|---|
| Xcode di Mac + Apple ID gratis | Pinjam/punya Mac | App terinstall di iPhone-mu langsung via kabel, tapi expired tiap 7 hari |
| Apple Developer Program ($99/thn) + Codemagic | Kartu kredit, akun Apple Developer | IPA ad-hoc yang tahan lama, bisa dibagi ke beberapa iPhone (daftarkan UDID) |
| Layanan webview-to-app instan (Median.co, Appilix, dll) | Bayar per app biasanya | Mereka urus build & signing iOS-nya, kamu tinggal terima file/link install |

Kalau kamu memang butuh versi iOS jalan cepat tanpa ribet sertifikat,
opsi paling praktis buat pemula biasanya nomor 3.

## Isi folder ini
- `package.json`, `capacitor.config.json`, `www/` -- project inti,
  mengarah ke https://senop333.github.io/absen/
- `.github/workflows/build-android-apk.yml` -- robot build otomatis
- `android-snippets/` -- kode izin kamera Android (dipakai otomatis oleh workflow)
- `ios-snippets/` -- kode izin kamera iOS (dipakai kalau nanti kamu lanjut ke Xcode/Codemagic)
