package android.eservices.beers.data.repository.beerdisplay;
import android.eservices.beers.data.api.model.Beer;
import java.util.List;
import io.reactivex.Single;

public interface BeerDisplayRepository {
    Single<List<Beer>> getBeers();
}
