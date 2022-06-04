package com.juara.yayasan.Laporan;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.MANAGE_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

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
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/laporan.pdf";
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
                    && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, MANAGE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
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
        if (isAllowStoragePermission()) {
            ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE
                    , READ_EXTERNAL_STORAGE, CAMERA}, 1000);
        } else {
            // showWarning("Izinkan Akses Berkas di Pengaturan");
        }
    }
}
