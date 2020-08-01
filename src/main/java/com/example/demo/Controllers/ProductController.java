package com.example.demo.Controllers;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.Models.Category;
import com.example.demo.Models.Product;
import com.example.demo.Services.CategoryService;
import com.example.demo.Services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    private ProductService  prsv;

    @Autowired
    private CategoryService ctsv;
    
    @RequestMapping(path="/products")
    public String getProducts(Model model,
    @RequestParam(value="pageNo", defaultValue="0") Integer pageNo,
    @RequestParam(value="pageSize", defaultValue="10") Integer pageSize,
    @RequestParam(value="orderBy", defaultValue="name") String orderBy,
    @RequestParam(value="orderDirection", defaultValue="ASC") String orderDirection){
        //List<Product> listProducts = prsv.listAll(pageNo,pageSize,orderBy,orderDirection);
        List<Product> listProducts = prsv.findByCriteria("cc");
    model.addAttribute("listProducts", listProducts);
        return "products";
    }

    
    @RequestMapping(value = "/products/save", method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product";
        }
        prsv.save(product);
     
    return "redirect:/products";
    }
    @RequestMapping("/products/delete/{id}")
public String deleteProduct(@PathVariable(name = "id") int id) {
    prsv.delete(id);
    return "redirect:/products";       
}

    @RequestMapping("/products/{id}")
    public String showEditProductPage(Model model,@PathVariable(name = "id") int id) {
        Product product = null;
        if (id == 0) {
            product = new Product();
        } else {

     product = prsv.get(id);
        }
        model.addAttribute("product", product);
        List<Category> listCategories=ctsv.listAll();
        model.addAttribute("listCategories", listCategories);

        return "product";
    }
}