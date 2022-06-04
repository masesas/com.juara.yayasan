package com.juara.yayasan.Pdf;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.juara.yayasan.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class PDFUtility {
    private static final String TAG = PDFUtility.class.getSimpleName();
    private static Font FONT_TITLE = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static Font FONT_SUBTITLE = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

    private static Font FONT_CELL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    private static Font FONT_COLUMN = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);

    public interface OnDocumentClose {
        void onStartGenerate();

        void onPDFDocumentClose(File file);
    }

    private Context context;
    private OnDocumentClose mCallback;
    private Document document;

    private String filePath;
    private Boolean isPortrait = false;

    private List<String> columTable;
    private List<String[]> dataTable;

    private String title;

    public PDFUtility(Context context, String filePath) {
        this.context = context;
        this.filePath = filePath;
    }

    private void initDoc() {
        File file = new File(filePath);
        try {
            if (file.exists()) {
                file.delete();
                Thread.sleep(50);
            }
            document = new Document();
            document.setMargins(24f, 24f, 32f, 32f);
            document.setPageSize(isPortrait ? PageSize.A4 : PageSize.A4.rotate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFinishGenerate(OnDocumentClose onDocumentClose) {
        this.mCallback = onDocumentClose;
    }

    public void setTableList(List<String[]> dataTable) {
        this.dataTable = dataTable;
    }

    public void setColumTable(List<String> columTable) {
        this.columTable = columTable;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void generateTable() {
        try {
            if (columTable == null) {
                throw new Exception("Column Kosong");
            }
            if (dataTable == null) {
                throw new Exception("Data Kosong");
            }
            if (title == null) {
                title = "Laporan";
            }

            if (mCallback != null) {
                mCallback.onStartGenerate();
            }

            initDoc();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            pdfWriter.setFullCompression();
            pdfWriter.setPageEvent(new PageNumeration());

            document.open();
            //setMetaData();
            addHeader();
            addEmptyLine(document, 1);
            setSubtitle();
            addEmptyLine(document, 1);
            createDataTable();
            addEmptyLine(document, 2);
            document.close();

            pdfWriter.close();

            if (mCallback != null) {
                mCallback.onPDFDocumentClose(new File(filePath));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("error_lap", ex.getLocalizedMessage());
        }
    }


    private void addEmptyLine(Document document, int number) throws DocumentException {
        for (int i = 0; i < number; i++) {
            document.add(new Paragraph(" "));
        }
    }

    private void setMetaData() {
        document.addCreationDate();
        //document.add(new Meta("",""));
        document.addAuthor("jul");
        document.addCreator("jul");
        document.addHeader("DEVELOPER", "jul");
    }

    private void addHeader() {
        try {
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 7, 2});
            table.getDefaultCell().setBorder(PdfPCell.BOTTOM);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell;
            {
                /*LEFT TOP LOGO*/
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.yayasan_2);
                Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                Image logo = Image.getInstance(stream.toByteArray());
                logo.setWidthPercentage(80);
                logo.scaleToFit(105, 55);

                cell = new PdfPCell();
                cell.setHorizontalAlignment(Element.ALIGN_TOP);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setUseAscender(true);
                cell.setBorder(PdfPCell.BOTTOM);
                cell.setPadding(2f);
                cell.addElement(logo);
                table.addCell(cell);
            }

            {
                /*MIDDLE TEXT*/
                cell = new PdfPCell();
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(PdfPCell.BOTTOM);
                cell.setPadding(10f);
                cell.setUseAscender(true);

                Paragraph title = new Paragraph("Yayasan Nurul Awaliyah", FONT_TITLE);
                title.setAlignment(Element.ALIGN_CENTER);
                cell.addElement(title);

                Paragraph address = new Paragraph("Jl. Kayu Besar RT. 005/011, Kel. Cengkareng Timur, Kec. Cengkareng, Jakarta Barat", FONT_CELL);
                address.setAlignment(Element.ALIGN_CENTER);
                cell.addElement(address);

                table.addCell(cell);
            }
            /* RIGHT TOP LOGO*/
            {
                cell = new PdfPCell();
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(PdfPCell.BOTTOM);
                cell.setPadding(10f);
                cell.setUseAscender(true);
                table.addCell(cell);
              /*  PdfPTable logoTable = new PdfPTable(1);
                logoTable.setWidthPercentage(100);
                logoTable.getDefaultCell().setBorder(PdfPCell.BOTTOM);
                logoTable.setHorizontalAlignment(Element.ALIGN_CENTER);
                logoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);

                PdfPCell logoCell = new PdfPCell();
                logoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                logoCell.setBorder(PdfPCell.BOTTOM);
                logoTable.addCell(logoCell);

//                logoCell = new PdfPCell(new Phrase("Logo Text", FONT_CELL));
//                logoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//                logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                logoCell.setBorder(PdfPCell.NO_BORDER);
//                logoCell.setPadding(4f);
//                logoTable.addCell(logoCell);*/

              /*  cell = new PdfPCell(logoTable);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setUseAscender(true);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setPadding(2f);
              ;*/
            }

            //Paragraph paragraph=new Paragraph("",new Font(Font.FontFamily.TIMES_ROMAN, 2.0f, Font.NORMAL, BaseColor.BLACK));
            //paragraph.add(table);
            //document.add(paragraph);
            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSubtitle() throws Exception {
        PdfPTable subTitleTable = new PdfPTable(1);
        subTitleTable.setWidthPercentage(100);
        subTitleTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
        subTitleTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell subTitleCell = new PdfPCell();
        subTitleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        subTitleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        subTitleCell.setBorder(PdfPCell.NO_BORDER);

        Paragraph subTitle = new Paragraph("Laporan " + this.title, FONT_TITLE);
        subTitle.setAlignment(Element.ALIGN_CENTER);
        subTitleCell.addElement(subTitle);
        subTitleTable.addCell(subTitleCell);

        document.add(subTitleTable);
    }

    private void createDataTable() throws Exception {
        float[] widhts = new float[columTable.size()];
        for (int i = 0; i < columTable.size(); i++) {
            widhts[i] = 1f;
        }

        PdfPTable table1 = new PdfPTable(columTable.size());
        table1.setWidthPercentage(100);
        table1.setWidths(widhts);
        table1.setHeaderRows(1);
        table1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
        table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell cell;
        {
            if (columTable.size() > 0) {
                for (int i = 0; i < columTable.size(); i++) {
                    cell = new PdfPCell(new Phrase(columTable.get(i), FONT_COLUMN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setPadding(4f);
                    table1.addCell(cell);
                }
            } else {
                throw new Exception("Column Table Kosong");
            }
        }

        //document.add(table1);

        float top_bottom_Padding = 8f;
        float left_right_Padding = 4f;
        boolean alternate = false;

        BaseColor lt_gray = new BaseColor(221, 221, 221); //#DDDDDD
        BaseColor cell_color;

        for (int i = 0; i < dataTable.size(); i++) {
            cell_color = alternate ? lt_gray : BaseColor.WHITE;
            String[] temp = dataTable.get(i);
            for (int j = 0; j < temp.length; j++) {
                String var = temp[j];
                cell = new PdfPCell(new Phrase(temp[j], FONT_CELL));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setPaddingLeft(left_right_Padding);
                cell.setPaddingRight(left_right_Padding);
                cell.setPaddingTop(top_bottom_Padding);
                cell.setPaddingBottom(top_bottom_Padding);
                cell.setBackgroundColor(cell_color);
                table1.addCell(cell);

                alternate = !alternate;
            }
        }

        document.add(table1);
    }

    private void createSignBox() throws DocumentException {
        PdfPTable outerTable = new PdfPTable(1);
        outerTable.setWidthPercentage(100);
        outerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        PdfPTable innerTable = new PdfPTable(2);
        {
            innerTable.setWidthPercentage(100);
            innerTable.setWidths(new float[]{1, 1});
            innerTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

            //ROW-1 : EMPTY SPACE
            PdfPCell cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setFixedHeight(60);
            innerTable.addCell(cell);

            //ROW-2 : EMPTY SPACE
            cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setFixedHeight(60);
            innerTable.addCell(cell);

            //ROW-3 : Content Left Aligned
            cell = new PdfPCell();
            Paragraph temp = new Paragraph(new Phrase("Signature of Supervisor", FONT_SUBTITLE));
            cell.addElement(temp);

            temp = new Paragraph(new Phrase("( RAVEESH G S )", FONT_SUBTITLE));
            temp.setPaddingTop(4f);
            temp.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(temp);

            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPadding(4f);
            innerTable.addCell(cell);

            //ROW-4 : Content Right Aligned
            cell = new PdfPCell(new Phrase("Signature of Staff ", FONT_SUBTITLE));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setPadding(4f);
            innerTable.addCell(cell);
        }

        PdfPCell signRow = new PdfPCell(innerTable);
        signRow.setHorizontalAlignment(Element.ALIGN_LEFT);
        signRow.setBorder(PdfPCell.NO_BORDER);
        signRow.setPadding(4f);

        outerTable.addCell(signRow);

        document.add(outerTable);
    }

    private static Image getImage(byte[] imageByte, boolean isTintingRequired) throws Exception {
        Paint paint = new Paint();
        if (isTintingRequired) {
            paint.setColorFilter(new PorterDuffColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN));
        }
        Bitmap input = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
        Bitmap output = Bitmap.createBitmap(input.getWidth(), input.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawBitmap(input, 0, 0, paint);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        output.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image image = Image.getInstance(stream.toByteArray());
        image.setWidthPercentage(80);
        return image;
    }

    private static Image getBarcodeImage(PdfWriter pdfWriter, String barcodeText) {
        Barcode128 barcode = new Barcode128();
        //barcode.setBaseline(-1); //BARCODE TEXT ABOVE
        barcode.setFont(null);
        barcode.setCode(barcodeText);
        barcode.setCodeType(Barcode128.CODE128);
        barcode.setTextAlignment(Element.ALIGN_BASELINE);
        return barcode.createImageWithBarcode(pdfWriter.getDirectContent(), BaseColor.BLACK, null);
    }

}
