package tpo.uade.api.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tpo.uade.api.dto.ProductDto;
import tpo.uade.api.mapper.ProductMapper;
import tpo.uade.api.model.ProductModel;
import tpo.uade.api.model.UserModel;
import tpo.uade.api.repository.ProductRepository;
import tpo.uade.api.repository.UserRepository;
import tpo.uade.api.service.IFavoriteService;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteService implements IFavoriteService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public boolean add(Long productId) {
        UserModel userLogged = userService.getUserModelByUsername();

        ProductModel product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return false;
        }

        if (!userLogged.getFavoriteProducts().contains(product)) {
            userLogged.getFavoriteProducts().add(product);
            userRepository.save(userLogged);
        }
        return true;
    }

    @Override
    public boolean remove(Long productId) {
        UserModel userLogged = userService.getUserModelByUsername();

        ProductModel product = productRepository.findById(productId).orElse(null);
        if (product == null || !userLogged.getFavoriteProducts().contains(product)) {
            return false;
        }

        userLogged.getFavoriteProducts().remove(product);
        userRepository.save(userLogged);
        return true;
    }

    @Override
    public List<ProductDto> getByUserLogged() {
        UserModel userLogged = userService.getUserModelByUsername();
        List<ProductModel> favoritesPorductsModelList = userLogged.getFavoriteProducts();
        return favoritesPorductsModelList.stream()
                .map(productMapper::mapFromDatabaseEntity)
                .toList();
    }
}
