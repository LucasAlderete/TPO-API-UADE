package tpo.uade.api.service;

import java.util.List;

import tpo.uade.api.model.FeaturedProductModel;

public interface IFeaturedProductService {
    public List<FeaturedProductModel> getAll();
    public FeaturedProductModel getById(int productId);
    public void save();
    public void update();
    public void delete();
}
