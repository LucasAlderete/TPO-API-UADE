package tpo.uade.api.service;

public interface INavigationService {
    void save(int userId, int productId);
    void delete(int userId, int productId);
    void deleteAll(int userId);
}
