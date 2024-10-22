package cn.itcast.mp.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfToWordConverter {

    public static void main(String[] args) {
        String pdfFilePath = "input.pdf"; // 输入PDF文件路径
        String wordFilePath = "output.docx"; // 输出Word文件路径

        try {
            convertPdfToWord(pdfFilePath, wordFilePath);
            System.out.println("PDF转换为Word成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertPdfToWord(String pdfFilePath, String wordFilePath) throws IOException {
        // 加载PDF文档
        PDDocument document = PDDocument.load(new FileInputStream(pdfFilePath));
        PDFTextStripper pdfStripper = new PDFTextStripper();

        // 创建Word文档
        XWPFDocument wordDocument = new XWPFDocument();

        // 提取PDF文本并写入Word文档
        String pdfText = pdfStripper.getText(document);
        XWPFParagraph paragraph = wordDocument.createParagraph();
        paragraph.createRun().setText(pdfText);

        // 保存Word文档
        try (FileOutputStream out = new FileOutputStream(wordFilePath)) {
            wordDocument.write(out);
        }

        // 关闭文档
        document.close();
        wordDocument.close();
    }
}