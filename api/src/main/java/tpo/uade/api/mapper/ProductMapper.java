package tpo.uade.api.mapper;

import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.model.ProductModel;

public interface ProductMapper {

    ProductDto mapFromDatabaseEntity (ProductModel productModel);

    ProductModel mapToDatabaseEntity (ProductDto productDto);
}
