package com.juara.yayasan.Laporan;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.MANAGE_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.juara.yayasan.BaseActivity;
import com.juara.yayasan.Pdf.PDFUtility;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class BaseLaporanActivity extends BaseActivity {

    private PDFUtility pdfUtility;
    private ProgressDialog mProgressDialog;

    protected String setFileName() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString() + "/laporan.pdf";
    }

    protected void initPdfUtil(List<String[]> dataList, List<String> dataColumn, String title) {
        String path = setFileName();
        try {
            pdfUtility = new PDFUtility(this, path);
            pdfUtility.setTitle(title);
            pdfUtility.onFinishGenerate(new PDFUtility.OnDocumentClose() {
                @Override
                public void onStartGenerate() {
                    mProgressDialog.show();
                }

                @Override
                public void onPDFDocumentClose(File file) {
                    mProgressDialog.dismiss();
                    showInfoDialog("Konfirmasi", "Buka File Hasil Generate", (dialog, which) -> openPdf(file), null);
                }

                @Override
                public void onFail(String message) {
                    mProgressDialog.dismiss();
                    showError("Error " + message);
                }
            });
            pdfUtility.setColumTable(dataColumn);
            pdfUtility.setTableList(dataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void generatePDF() {
        try {
            pdfUtility.generateTable();
        } catch (Exception e) {
            e.printStackTrace();
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            showError("Error Generated PDF");
        }
    }

    protected void openPdf(File file) {
        if (file.exists()) {
            openGeneratedPDF(file);
        } else {
            showError("Gagal Membuka File PDF");
        }
    }

    protected boolean isAllowStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
        }

        return true;
    }

    protected void initProgress() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Generated Pdf");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
    }

    protected void reqPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            if (isAllowStoragePermission()) {
                ActivityCompat.requestPermissions(this, new String[]{
                                WRITE_EXTERNAL_STORAGE,
                                READ_EXTERNAL_STORAGE
                        },
                        1000);
            } else {
                showWarning("Izinkan Akses Berkas di Pengaturan");
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    protected void moveSettings() {
        showInfoDialog("Akses Aplikasi", "Ijinkan Aplikasi Untuk Mengakses Berkas", (dialog, which) -> {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            Uri uri = Uri.fromParts("package", BaseLaporanActivity.this.getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
        }, (dialog, which) -> {
            showWarning("PDF Tidak Bisa di Generated, Jika Izin Tidak di Berikan!");
            dialog.dismiss();
        });
    }

    protected void generatePdf() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                generatePDF();
            } else {
                moveSettings();
            }
        } else {
            if (isAllowStoragePermission()) {
                generatePDF();
            } else {
                showInfoDialog("Akses Aplikasi", "Ijinkan Aplikasi Untuk Mengakses Berkas",
                        (dialog, which) -> startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0),
                        (dialog, which) -> {
                            showWarning("PDF Tidak Bisa di Generated, Jika Izin Tidak di Berikan!");
                            dialog.dismiss();
                        });

            }
        }
    }

}
