package cn.itcast.mp.util;



import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordToPdfConverter {

    public static void main(String[] args) {
        String wordFilePath = "input.docx"; // 输入Word文件路径
        String pdfFilePath = "output.pdf"; // 输出PDF文件路径

        try {
            convertWordToPdf(wordFilePath, pdfFilePath);
            System.out.println("Word转换为PDF成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertWordToPdf(String wordFilePath, String pdfFilePath) throws IOException {
        // 加载Word文档
        XWPFDocument document = new XWPFDocument(new FileInputStream(wordFilePath));

        // 创建HTML内容
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><body>");
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            htmlContent.append("<p>").append(paragraph.getText()).append("</p>");
        }
        htmlContent.append("</body></html>");

        // 使用OpenHTMLtoPDF将HTML转换为PDF
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(htmlContent.toString(), null);
        try (
                FileOutputStream outputStream = new FileOutputStream(pdfFilePath)
        ) {
            builder.toStream(outputStream);
        }

        // 关闭文档
        document.close();
    }
}