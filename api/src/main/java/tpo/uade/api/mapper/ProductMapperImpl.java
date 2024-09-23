package tpo.uade.api.mapper;

import org.springframework.stereotype.Service;
import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.model.ProductModel;

@Service
public class ProductMapperImpl implements ProductMapper{

    @Override
    public ProductDto mapFromDatabaseEntity(ProductModel productModel) {
        return new ProductDto(productModel.getId(),
                productModel.getName(),
                productModel.getPrice(),
                productModel.getCategory().getName(),
                productModel.getUrlImage(),
                productModel.getDescription(),
                productModel.getAdditionalInformation(),
                productModel.getStock(),
                productModel.isHighlighted()
        );
    }

    @Override
    public ProductModel mapToDatabaseEntity(ProductDto productDto) {
        ProductModel productModel = new ProductModel();
        productModel.setId(productDto.getProductId());
        productModel.setName(productDto.getName());
        productModel.setPrice(productDto.getPrice());
        productModel.setStock(productDto.getStock());
        productModel.setDescription(productDto.getDescription());
        productModel.setHighlighted(productDto.isHighlighted());
        return productModel;
    }
}
