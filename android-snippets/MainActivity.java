// Ganti isi android/app/src/main/java/.../MainActivity.java dengan ini.
// Capacitor versi baru (5/6) sebenarnya sudah auto-handle izin kamera untuk
// getUserMedia SELAMA android.permission.CAMERA ada di manifest dan user
// sudah accept runtime permission. Kode di bawah ini jadi fallback eksplisit
// kalau ternyata prompt kamera tidak muncul otomatis.

package com.senop333.absen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

    private static final int CAMERA_PERMISSION_CODE = 1001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Minta izin kamera Android di awal (runtime permission)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }

        // Jembatani permintaan getUserMedia() dari halaman web ke izin native
        this.bridge.getWebView().setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                runOnUiThread(() -> {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                            == PackageManager.PERMISSION_GRANTED) {
                        request.grant(request.getResources());
                    } else {
                        request.deny();
                    }
                });
            }
        });
    }
}
