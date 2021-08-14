package android.eservices.beers.presentation.beerdisplay.list;

import android.eservices.beers.data.api.model.Beer;

import java.util.List;

public class BeerListContract {

    public interface View {
        void displayBeers(List<Beer> beerItemViewModelList);
    }

    public interface Presenter {
        void searchBeers();

        void attachView(View view);

        void detachView();
    }
}
