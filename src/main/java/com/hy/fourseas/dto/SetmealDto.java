package com.hy.fourseas.dto;


import com.hy.fourseas.entity.Setmeal;
import com.hy.fourseas.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
