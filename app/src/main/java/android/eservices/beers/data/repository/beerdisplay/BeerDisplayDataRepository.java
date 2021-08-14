package android.eservices.beers.data.repository.beerdisplay;
import android.eservices.beers.data.api.model.Beer;
import android.eservices.beers.data.repository.beerdisplay.mapper.BeerToBeerEntityMapper;
import android.eservices.beers.data.repository.beerdisplay.remote.BeerDisplayRemoteDataSource;
import java.util.List;
import io.reactivex.Single;

public class BeerDisplayDataRepository implements BeerDisplayRepository{


    private BeerDisplayRemoteDataSource beerDisplayRemoteDataSource;
    private BeerToBeerEntityMapper beerToBeerEntityMapper;

    public BeerDisplayDataRepository(BeerDisplayRemoteDataSource beerDisplayRemoteDataSource,
                                    BeerToBeerEntityMapper beerToBeerEntityMapper) {
        this.beerDisplayRemoteDataSource = beerDisplayRemoteDataSource;
        this.beerToBeerEntityMapper = beerToBeerEntityMapper;
    }

    @Override
    public Single<List<Beer>> getBeers() {
        return beerDisplayRemoteDataSource.getBeers();
    }
}
