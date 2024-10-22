//package cn.itcast.mp.util;
//
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
//
//import java.io.File;
//
//public class ImageTextExtractor {
//    public static void main(String[] args) {
//        // 创建Tesseract实例
//        Tesseract tesseract = new Tesseract();
//
//        // 设置Tesseract的语言和数据路径
//        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata"); // 修改为您的tessdata路径
//        tesseract.setLanguage("eng"); // 设置语言为英语，您可以根据需要更改
//
//        try {
//            // 加载图片
//            File imageFile = new File("path/to/your/image.png"); // 修改为您的图片路径
//            // 提取文字
//            String result = tesseract.doOCR(imageFile);
//            System.out.println(result);
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        }
//    }
//}
