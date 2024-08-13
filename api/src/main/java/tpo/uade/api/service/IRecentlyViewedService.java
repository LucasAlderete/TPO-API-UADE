package tpo.uade.api.service;

import java.util.List;

import tpo.uade.api.model.RecentlyViewedModel;

public interface IRecentlyViewedService {
    public List<RecentlyViewedModel> getAllByUser(int userId, int limit);
    public void save(String userId, int productId);
}
