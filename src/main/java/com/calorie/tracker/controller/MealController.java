package com.calorie.tracker.controller;

import com.calorie.tracker.entity.Meal;
import com.calorie.tracker.entity.User;
import com.calorie.tracker.repository.MealRepository;
import com.calorie.tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/meals")
public class MealController {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add")
    public String addMealForm(Model model) {
        model.addAttribute("meal", new Meal());
        return "add-meal";
    }

    @PostMapping("/add")
    public String addMeal(@ModelAttribute Meal meal,
                          @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        meal.setUser(user);
        meal.setDate(LocalDate.now());
        mealRepository.save(meal);
        return "redirect:/meals";
    }

    @GetMapping
    public String viewMeals(Model model,
                            @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<Meal> meals = mealRepository.findByUser(user);
        model.addAttribute("meals", meals);
        return "meal-list";
    }

    @GetMapping("/stats")
    public String calorieStats(Model model,
                               @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<Meal> meals = mealRepository.findByUser(user);
        model.addAttribute("meals", meals);
        return "calorie-stats";
    }
}
