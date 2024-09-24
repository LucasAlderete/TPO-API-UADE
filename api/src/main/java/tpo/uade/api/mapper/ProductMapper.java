package tpo.uade.api.mapper;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.model.ProductModel;

public interface ProductMapper {

    ProductModel mapToDatabaseEntity (ProductDto productDto);
    ProductDto mapFromDatabaseEntity (ProductModel productModel);
}
