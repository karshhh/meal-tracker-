package com.calorie.tracker.repository;

import com.calorie.tracker.entity.Meal;
import com.calorie.tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUserAndDate(User user, LocalDate date);
    List<Meal> findByUser(User user);
}
