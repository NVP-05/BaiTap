package com.data.javarest04.controller;

import com.data.javarest04.entity.FoodItem;
import com.data.javarest04.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/foodItems")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping
    public String getAllFoodItems(Model model,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String category) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<FoodItem> foodItems;

        if ((name != null && !name.isEmpty()) || (category != null && !category.isEmpty())) {
            foodItems = foodItemService.findByName(name, category, pageable);
        } else {
            foodItems = foodItemService.getAll(pageable);
        }

        model.addAttribute("foodPage", foodItems);
        model.addAttribute("currentPage", page);
        model.addAttribute("lastPage", foodItems.isLast());
        model.addAttribute("name", name);
        model.addAttribute("category", category);
        return "foodList";
    }

    @GetMapping("/add")
    public String newFoodItem(Model model) {
        model.addAttribute("food", new FoodItem());
        return "foodForm";
    }

    @PostMapping("/add")
    public String addFoodItem(@ModelAttribute("food") FoodItem food) {
        foodItemService.add(food);
        return "redirect:/foodItems";
    }

    @GetMapping("/edit/{id}")
    public String editFoodItem(@PathVariable Long id, Model model) {
        model.addAttribute("food", foodItemService.findById(id));
        return "foodForm";
    }

    @PostMapping("/edit")
    public String updateFoodItem(@RequestParam Long id, @ModelAttribute("food") FoodItem food) {
        food.setId(id);
        foodItemService.add(food);
        return "redirect:/foodItems";
    }

    @PostMapping("/delete/{id}")
    public String deleteFoodItem(@PathVariable Long id) {
        foodItemService.delete(id);
        return "redirect:/foodItems";
    }

    @GetMapping("/detail/{id}")
    public String detailFoodItem(@PathVariable Long id, Model model) {
        model.addAttribute("food", foodItemService.findById(id));
        return "redirect:/foodItems";
    }
}
