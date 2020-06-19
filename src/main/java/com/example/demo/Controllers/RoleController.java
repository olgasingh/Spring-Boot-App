package com.example.demo.Controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.example.demo.Models.Role;
import com.example.demo.Services.RoleService;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/roles")
public class RoleController {
    @Autowired
    private RoleService rsv;

    @RequestMapping(path = "")
    public String getRoles(Model model) {
        List<Role> listRoles = rsv.listAll();
        model.addAttribute("listRoles", listRoles);
        return "roles";
    } 
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveRole(@ModelAttribute("role") Role role) {
        rsv.save(role);

        return "redirect:/roles";
    }

    @RequestMapping("/delete/{id}")
    public String deleteRole(@PathVariable(name = "id") int id) {
        rsv.delete(id);
        return "redirect:/roles";
    }

    @RequestMapping("/{id}")
    public String showEditRolePage(Model model, @PathVariable(name = "id") int id) {
        Role role = null;
        if (id == 0) {
            role = new Role();
        } else {
         role = rsv.get(id);
        }
        model.addAttribute("role", role);
        return "role";
    }
    @RequestMapping(path="/excel")
    public HttpEntity<byte[]> exportToExcel(Model model) throws IOException{
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employee");



        List<Role> roles=rsv.listAll();
        for(int i=0;i<roles.size();i++){
            Row headerRow = sheet.createRow(i);

            Cell cell = headerRow.createCell(0);
            cell.setCellValue(roles.get(i).getId());
             cell = headerRow.createCell(1);
            cell.setCellValue(roles.get(i).getName());
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        workbook.write(output);
        workbook.close();
        byte[] excelContent = output.toByteArray();
        output.flush();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        responseHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=cat.xlsx");
        return new HttpEntity<byte[]>(excelContent, responseHeaders);
    }


}