package android.eservices.beers.data.repository.beerdisplay.mapper;
import android.eservices.beers.data.api.model.Beer;
import android.eservices.beers.data.entity.BeerEntity;

public class BeerToBeerEntityMapper {
    public BeerEntity map(Beer beer) {
        BeerEntity beerEntity = new BeerEntity();

        beerEntity.setId(beer.getId());
        beerEntity.setName(beer.getName());
        beerEntity.setTagline(beer.getTagline());
        beerEntity.setFirst_brewed(beer.getFirst_brewed());
        beerEntity.setDescription(beer.getDescription());
        beerEntity.setImage_url(beer.getImage_url());
        return beerEntity;
    }
}
