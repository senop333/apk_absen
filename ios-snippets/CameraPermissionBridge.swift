// Buat file baru: ios/App/App/CameraPermissionBridge.swift
// Lalu di ios/App/App/AppDelegate.swift, di bagian window rootViewController
// setup, arahkan bridge WKUIDelegate ke class ini (lihat catatan di bawah).

import WebKit

@available(iOS 15.0, *)
class CameraPermissionBridge: NSObject, WKUIDelegate {
    func webView(_ webView: WKWebView,
                 requestMediaCapturePermissionFor origin: WKSecurityOrigin,
                 initiatedByFrame frame: WKFrameInfo,
                 type: WKMediaCaptureType,
                 decisionHandler: @escaping (WKPermissionDecision) -> Void) {
        // Otomatis izinkan kamera untuk domain absen ini.
        // Bisa dipersempit dengan cek origin.host == "senop333.github.io"
        decisionHandler(.grant)
    }
}

/*
 CATATAN INTEGRASI (Capacitor 5/6):
 Capacitor's CAPBridgeViewController sudah punya webView bawaan. Cara paling
 gampang: di ios/App/App/AppDelegate.swift, method
 applicationDidBecomeActive atau applicationDidFinishLaunching, ambil
 reference ke bridge view controller lalu set:

    if let bridgeVC = window?.rootViewController as? CAPBridgeViewController {
        if #available(iOS 15.0, *) {
            bridgeVC.webView?.uiDelegate = CameraPermissionBridge()
        }
    }

 Kalau target minimum iOS < 15, getUserMedia di WKWebView TIDAK didukung
 sama sekali oleh Apple — itu batas dari WebKit sendiri, bukan dari kode ini.
*/
