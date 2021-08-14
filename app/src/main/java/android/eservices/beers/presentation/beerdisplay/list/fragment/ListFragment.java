package android.eservices.beers.presentation.beerdisplay.list.fragment;

import android.eservices.beers.R;
import android.eservices.beers.data.api.model.Beer;
import android.eservices.beers.data.di.DependencyInjection;
import android.eservices.beers.presentation.beerdisplay.list.BeerListContract;
import android.eservices.beers.presentation.beerdisplay.list.BeerListPresenter;
import android.eservices.beers.presentation.beerdisplay.list.adapter.BeerAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements BeerListContract.View {
    public static String TAB_NAME = "Beers";
    private View rootView;
    BeerListContract.Presenter beerListPresenter;
    private RecyclerView recyclerView;
    private BeerAdapter beerAdapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
    List<Beer> beers = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;

    public ListFragment() {
    }

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    public void displaySnackBar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    public void changeLayout() {
        if (layoutManager instanceof GridLayoutManager) {
            layoutManager = new LinearLayoutManager(getContext());
        } else {
            layoutManager = new GridLayoutManager(getContext(), 2);
        }
        recyclerView.setLayoutManager(layoutManager);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        beerAdapter = new BeerAdapter();
        recyclerView.setAdapter(beerAdapter);
        beerListPresenter = new BeerListPresenter(DependencyInjection.getBeerDisplayRepository());
        beerListPresenter.attachView(this);
        coordinatorLayout = rootView.findViewById(R.id.coordinator_layout);
        setupRecyclerView();

    }

    public void setupRecyclerView() {
        recyclerView.setLayoutManager(layoutManager);
        beerListPresenter.searchBeers();
    }

    @Override
    public void displayBeers(List<Beer> beerItemViewModelList) {
        beers = beerItemViewModelList;
        beerAdapter.bindViewModels(beerItemViewModelList);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        beerListPresenter.detachView();
    }
}