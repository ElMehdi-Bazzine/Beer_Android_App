package android.eservices.beers.data.api;
import android.eservices.beers.data.api.model.Beer;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BeerDisplayService {


    /**
     * @return
     */
    @GET("beers?page=6&per_page=20")
    Single<List<Beer>> getBeers();


    /**
     * @param id  concerns a particular Beer
     * @return a Beer with all information
     */
    @GET("beers/{id}")
    Single<Beer> getBeer(@Path("id") String id);

}
