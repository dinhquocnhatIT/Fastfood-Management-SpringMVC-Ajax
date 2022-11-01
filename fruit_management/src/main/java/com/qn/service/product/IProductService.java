package com.qn.service.product;

import com.qn.model.Product;
import com.qn.model.dto.ProductDTO;
import com.qn.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IProductService extends IGeneralService<Product> {
    List<ProductDTO> findAllProductDTO();

    Optional<ProductDTO> findProductDTOById(Long id);

    void deleteProductById(Long id);

    void softDelete(Product product);

    List<ProductDTO> findProductByValue(String query);
}
