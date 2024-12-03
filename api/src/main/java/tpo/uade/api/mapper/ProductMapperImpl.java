package tpo.uade.api.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.model.CategoryModel;
import tpo.uade.api.model.ImagesModel;
import tpo.uade.api.model.ProductModel;
import tpo.uade.api.repository.CategoryRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductMapperImpl implements ProductMapper{

    private final CategoryRepository categoryRepository;

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
        productModel.setUrlImage(productDto.getUrlImage());
        List<ImagesModel> imagesList = productDto.getImages().stream()
                .map(url -> {
                    ImagesModel image = new ImagesModel();
                    image.setPath(url);  // Asigna la URL a la propiedad 'path'
                    image.setProduct(productModel);  // Asigna el ProductModel
                    return image;
                })
                .toList();
        productModel.setUrlImageList(imagesList);
        CategoryModel category = categoryRepository.findByName(productDto.getCategoryName())
                .orElseThrow(() -> new NoSuchElementException("Category not found"));
        productModel.setCategory(category);

        return productModel;
    }
}
