package tpo.uade.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.model.ProductModel;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto mapFromDatabaseEntity (ProductModel productModel);

    ProductModel mapToDatabaseEntity (ProductDto productDto);
}
