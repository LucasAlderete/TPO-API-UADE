package tpo.uade.api.service;

public interface IFavoriteService {
    boolean add(int productId, long user_id);
    boolean remove(int productId, long user_id);
}
