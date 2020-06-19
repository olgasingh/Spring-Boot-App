package com.example.demo.Controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import com.example.demo.Models.Category;
import com.example.demo.Services.CategoryService;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/categories")
public class CategoryController {
    @Autowired
    private CategoryService crsv;

    @RequestMapping(path = "")
    public String getCategories(Model model) {
        List<Category> listCategories = crsv.listAll();
        model.addAttribute("listCategories", listCategories);
        return "categories";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category";
        }
        crsv.save(category);

        return "redirect:/categories";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") int id) {
        crsv.delete(id);
        return "redirect:/categories";
    }

    @RequestMapping("/{id}")
    public String showEditCategoryPage(Model model, @PathVariable(name = "id") int id) {
        Category category = null;
        if (id == 0) {
            category = new Category();
        } else {

            category = crsv.get(id);
        }
        model.addAttribute("category", category);
        return "category";
    }

    @RequestMapping(path = "/excel")
    public HttpEntity<byte[]> exportToExcel(Model model) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employee");

        List<Category> categories = crsv.listAll();
        for (int i = 0; i < categories.size(); i++) {
            Row headerRow = sheet.createRow(i);

            Cell cell = headerRow.createCell(0);
            cell.setCellValue(categories.get(i).getId());
            cell = headerRow.createCell(1);
            cell.setCellValue(categories.get(i).getName());
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        workbook.write(output);
        workbook.close();
        byte[] excelContent = output.toByteArray();
        output.flush();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders
                .setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        responseHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=cat.xlsx");
        return new HttpEntity<byte[]>(excelContent, responseHeaders);
    }

}