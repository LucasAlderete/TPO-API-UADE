package tpo.uade.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.mapper.ProductMapper;
import tpo.uade.api.model.CategoryModel;
import tpo.uade.api.model.ProductModel;
import tpo.uade.api.repository.ProductRepository;
import tpo.uade.api.service.implementation.ProductService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllByCategory_WhenCalled_ReturnsGroupedProductsByCategory() {
        // Arrange
        final String CATEGORY_NAME = "ram";
        CategoryModel categoryModel = new CategoryModel(1, CATEGORY_NAME);

        final String CATEGORY_NAME_2 = "gpu";
        CategoryModel categoryModel2 = new CategoryModel(1, CATEGORY_NAME_2);

        ProductModel product1 = createProductModel(1, "Product1");
        ProductModel product2 = createProductModel(2, "Product2");
        ProductModel product3 = createProductModel(3, "Product3");

        product1.setCategory(categoryModel);
        product2.setCategory(categoryModel);
        product3.setCategory(categoryModel2);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2, product3));

        ProductDto dto1 = createProductDto(1, "Product1");
        ProductDto dto2 = createProductDto(2, "Product2");
        ProductDto dto3 = createProductDto(3, "Product3");

        dto1.setCategoryName(CATEGORY_NAME);
        dto2.setCategoryName(CATEGORY_NAME);
        dto3.setCategoryName(CATEGORY_NAME_2);

        when(productMapper.mapFromDatabaseEntity(product1)).thenReturn(dto1);
        when(productMapper.mapFromDatabaseEntity(product2)).thenReturn(dto2);
        when(productMapper.mapFromDatabaseEntity(product3)).thenReturn(dto3);

        // Act
        Map<String, List<ProductDto>> result = productService.getAllByCategory();

        // Assert
        assertEquals(2, result.size());
        assertEquals(Arrays.asList(dto1, dto2), result.get(CATEGORY_NAME));
        assertEquals(Collections.singletonList(dto3), result.get(CATEGORY_NAME_2));
    }

    @Test
    void getAll_WhenCalled_ReturnsAllProducts() {
        // Arrange
        ProductModel product1 = createProductModel(1, "Product1");
        ProductModel product2 = createProductModel(2, "Product2");

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        ProductDto dto1 = createProductDto(1, "Product1");
        ProductDto dto2 = createProductDto(2, "Product2");

        when(productMapper.mapFromDatabaseEntity(product1)).thenReturn(dto1);
        when(productMapper.mapFromDatabaseEntity(product2)).thenReturn(dto2);

        // Act
        List<ProductDto> result = productService.getAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
    }

    @Test
    void getByIds_WhenGivenProductIds_ReturnsMappedProductDtos() {
        // Arrange
        List<Integer> productIds = Arrays.asList(1, 2);
        ProductModel product1 = createProductModel(1, "Product1");
        ProductModel product2 = createProductModel(2, "Product2");

        when(productRepository.findByIdIn(productIds)).thenReturn(Arrays.asList(product1, product2));

        ProductDto dto1 = createProductDto(1, "Product1");
        ProductDto dto2 = createProductDto(2, "Product2");

        when(productMapper.mapFromDatabaseEntity(product1)).thenReturn(dto1);
        when(productMapper.mapFromDatabaseEntity(product2)).thenReturn(dto2);

        // Act
        List<ProductDto> result = productService.getByIds(productIds);

        // Assert
        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
    }

    private ProductModel createProductModel(int id, String name) {
        ProductModel model = new ProductModel();
        model.setId(id);
        model.setName(name);
        return model;
    }

    private ProductDto createProductDto(int id, String name) {
        ProductDto dto = new ProductDto();
        dto.setProductId(id);
        dto.setName(name);
        return dto;
    }


}
