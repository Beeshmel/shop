package my.web.app.shop.controllers;

import my.web.app.shop.models.Good;
import my.web.app.shop.repo.IGoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodController {
    @Autowired
    private IGoodRepository goodRepository;

    @GetMapping ("/form")
    public String showForm(){
        return "form";
    }
    @GetMapping("/goods") //вывод всей категории товаров
    public String showGoods(Model model){
        Iterable<Good> goods = goodRepository.findAll(); //получаем все записи из таблицы товаров
        model.addAttribute("goods", goods);
        return "goods";
    }

    @PostMapping("/good/add") //создавать товары
    public String getGood(
            @RequestParam int categId,
            @RequestParam String name,
            @RequestParam String info,
            @RequestParam int price,
            @RequestParam int num,
            Model model
    ){
    Good good = new Good(categId,name,info,price,num);
    goodRepository.save(good); // выполняется операция вставки
        return "redirect:/goods";
    }

    @GetMapping("/good/{id}")
    public String showCard(@PathVariable(value = "id") long id, Model model){
        var good = goodRepository.findById(id).get();
        model.addAttribute("good", good);
        return "good";
    }

    @GetMapping("/good/{categId}") //вывод по категории товаров
    public String showCard(@PathVariable(value = "categId") int categId, Model model){
        List <Good> goods= goodRepository.findAllByCategId(categId);
        model.addAttribute("goods", goods);
        return "goods";
    }

    @GetMapping("/good/delete/{id}")
    public String deleteGood(@PathVariable(value = "id") long id, Model model){
        goodRepository.deleteById(id);
        return "redirect:/goods";
    }

    @GetMapping("/good/edit/{id}")
    public String editGood(@PathVariable(value = "id") long id, Model model){
        Good good = goodRepository.findById(id).get();
        model.addAttribute("good", good);
        return "form_edit";
    }

    @PostMapping("/good/edit/{id}") //добавление количества товаров или изменения его цены
    public String updateGood(@PathVariable(value = "id") long id,
                             @RequestParam String name,
                             @RequestParam String info,
                             @RequestParam int price,
                             @RequestParam int num
                             ){
        Good good = goodRepository.findById(id).get();
        good.setName(name);
        good.setInfo(info);
        good.setPrice(price);
        good.setNum(num);
        goodRepository.save(good);
        return "redirect:/goods";
    }
}
