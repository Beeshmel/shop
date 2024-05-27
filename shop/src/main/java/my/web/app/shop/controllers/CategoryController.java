package my.web.app.shop.controllers;

import my.web.app.shop.models.Category;
import my.web.app.shop.models.Good;
import my.web.app.shop.repo.CategoryRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    private CategoryRepository catRepository;

    @GetMapping("/categories")
    public String showGoods(Model model){
        Iterable<Category> categories = catRepository.findAll(); //получаем все записи из таблицы товаров
        model.addAttribute("categories", categories);
        return "categories";
    }
    @PostMapping("/category/add") //добавлять новые категории товаров
    public String getGood(
            @RequestParam String name,
            @RequestParam String info,
            Model model
    ){
        Category category = new Category(name,info);
        catRepository.save(category); // выполняется операция вставки
        return "redirect:/goods";
    }
}
