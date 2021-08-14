package android.eservices.beers.data.repository.beerdisplay.remote;
import android.eservices.beers.data.api.BeerDisplayService;
import android.eservices.beers.data.api.model.Beer;
import java.util.List;
import io.reactivex.Single;

public class BeerDisplayRemoteDataSource {

    private BeerDisplayService beerDisplayService;

    public BeerDisplayRemoteDataSource(BeerDisplayService beerDisplayService) {
        this.beerDisplayService = beerDisplayService;
    }

    /**
     * @return a beer from remote database through web service
     */
    public Single<List<Beer>> getBeers() {
        return beerDisplayService.getBeers();
    }

    /**
     * @param idBeer
     * @return a detail beer from remote database through web service
     */
    public Single<Beer> getBeer(String idBeer) {
        return beerDisplayService.getBeer(idBeer);
    }

}
