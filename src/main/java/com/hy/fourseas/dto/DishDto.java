package com.hy.fourseas.dto;

import com.hy.fourseas.entity.Dish;
import com.hy.fourseas.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
