package android.eservices.beers.presentation.beerdisplay.list;

import android.eservices.beers.data.api.model.Beer;
import android.eservices.beers.data.repository.beerdisplay.BeerDisplayRepository;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BeerListPresenter implements BeerListContract.Presenter {

    private BeerDisplayRepository beerDisplayRepository;
    private BeerListContract.View view;
    private CompositeDisposable compositeDisposable;


    public BeerListPresenter(BeerDisplayRepository beerDisplayRepository) {
        this.beerDisplayRepository = beerDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void searchBeers() {
        compositeDisposable.clear();
        compositeDisposable.add(beerDisplayRepository.getBeers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Beer>>() {

                    @Override
                    public void onSuccess(List<Beer> beers) {
                        // work with the resulting todo
                        view.displayBeers(beers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        System.out.println(e.toString());
                    }
                }));

    }
    
    @Override
    public void attachView(BeerListContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;
    }




}
