package tpo.uade.api.mapper;

import org.springframework.stereotype.Service;
import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.model.CategoryModel;
import tpo.uade.api.model.ImagesModel;
import tpo.uade.api.model.ProductModel;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductMapperImpl implements ProductMapper{

    @Override
    public ProductDto mapFromDatabaseEntity(ProductModel productModel) {

        ProductDto dto = new ProductDto();
        dto.setName(productModel.getName());
        dto.setId(productModel.getId());
        dto.setSecureId(productModel.getSecureId());
        dto.setDescription(productModel.getDescription());
        dto.setPrice(productModel.getPrice());
        dto.setStock(productModel.getStock());
        dto.setAdditionalInformation(productModel.getAdditionalInformation());
        dto.setHighlighted(productModel.isHighlighted());
        List<String> imagesPaths = productModel.getUrlImageList().stream()
                .map(ImagesModel::getPath)
                .collect(Collectors.toList());
        dto.setImages(imagesPaths);
        dto.setCategoryName(productModel.getCategory().getName());

        return dto;
    }

    @Override
    public ProductModel mapToDatabaseEntity(ProductDto productDto) {
        ProductModel productModel = new ProductModel();
        productModel.setSecureId(productDto.getSecureId());
        productModel.setId(productDto.getId());
        productModel.setName(productDto.getName());
        productModel.setPrice(productDto.getPrice());
        productModel.setStock(productDto.getStock());
        productModel.setDescription(productDto.getDescription());
        productModel.setHighlighted(productDto.isHighlighted());
        productModel.setAdditionalInformation(productDto.getAdditionalInformation());
        if (Objects.isNull(productDto.getCategoryName())) {
            CategoryModel categoryModelDefault = new CategoryModel();
            categoryModelDefault.setId(3L);
            productModel.setCategory(categoryModelDefault);
        }
        List<ImagesModel> images = productDto.getImages().stream().map(imagen -> {
            ImagesModel model = new ImagesModel();
            model.setPath(imagen);
            model.setProduct(productModel);
            return  model;
        }).collect(Collectors.toList());
        productModel.setUrlImageList(images);
        return productModel;
    }
}
