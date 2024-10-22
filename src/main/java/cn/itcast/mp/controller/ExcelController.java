package cn.itcast.mp.controller;

/**
 * @author Administrator
 */

import cn.itcast.mp.pojo.User;
import cn.itcast.mp.services.impl.UserServicedd;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
@Slf4j
public class ExcelController {

    @Autowired
    private UserServicedd userServicedd;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || !file.getOriginalFilename().endsWith(".xlsx")) {
            return ResponseEntity.badRequest().body("请上传有效的 Excel 文件！");
        }
        try {
            log.info("获取文件名"+file.getName());
            List<User> userList = parseExcel(file);
            userServicedd.batchInsertUsers(userList);
            return ResponseEntity.ok("上传并解析成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(String.valueOf(e.getStackTrace()));
            log.error("错误信息"+e.getMessage());
            return ResponseEntity.status(500).body("文件解析失败: " + e.getMessage());
        }
    }

    private List<User> parseExcel(MultipartFile file) throws IOException {
        List<User> userList = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            // 跳过标题行
//            if (row.getRowNum() == 0) {
//                continue;
//            }
            User user = new User();
            String id=getCellValue(row.getCell(0));
            Double  idl=Double.valueOf(id);
            user.setId(idl.longValue());
            user.setUserName(getCellValue(row.getCell(1)));
            userList.add(user);
        }
        workbook.close(); // 关闭工作簿
        return userList;
    }
    private String getCellValue(Cell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                default:
                    return "";
            }
        }
        return "";
    }
}