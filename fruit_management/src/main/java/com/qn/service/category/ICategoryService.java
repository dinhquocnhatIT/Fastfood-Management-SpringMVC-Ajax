package com.qn.service.category;

import com.qn.model.Category;
import com.qn.service.IGeneralService;

public interface ICategoryService extends IGeneralService<Category> {
    void softDelete(Category category);

    Category findCatetoryByName(String name);
}
