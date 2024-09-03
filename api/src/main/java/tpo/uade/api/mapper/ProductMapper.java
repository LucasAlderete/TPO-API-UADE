package tpo.uade.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.model.ProductModel;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductModel mapToDatabaseEntity (ProductDto productDto);
    ProductDto mapFromDatabaseEntity (ProductModel productModel);
}
