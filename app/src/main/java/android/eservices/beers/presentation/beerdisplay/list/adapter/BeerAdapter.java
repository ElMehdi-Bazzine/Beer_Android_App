package android.eservices.beers.presentation.beerdisplay.list.adapter;
import android.content.Context;
import android.content.Intent;
import android.eservices.beers.presentation.beerdisplay.beer_activity;
import android.eservices.beers.R;
import android.eservices.beers.data.api.model.Beer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.List;
import java.util.ArrayList;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {
    public static class BeerViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnailImageView;
        private View v;
        public TextView nameTextView;
        public TextView tagLineTextView;
        CardView cardView;

        public BeerViewHolder(View v) {
            super(v);
            nameTextView = v.findViewById(R.id.name_text_view);
            tagLineTextView = v.findViewById(R.id.tagline);
            thumbnailImageView = v.findViewById(R.id.thumbnail_image_view);
            cardView = v.findViewById(R.id.card_view_id);
            this.v = v;
        }


        void bind(Beer beerItemViewModel) {
            nameTextView.setText(beerItemViewModel.getName());
            tagLineTextView.setText(beerItemViewModel.getTagline());
            String urlPoster = beerItemViewModel.getImage_url();

            if(urlPoster!= null) {
                Glide.with(v.getContext())
                        .load(Uri.parse(urlPoster))
                        .into(thumbnailImageView);
            }
       }

    }

    private List<Beer> beerItemViewModelList;
    private Context context;


    public BeerAdapter() {
        beerItemViewModelList = new ArrayList<>();
    }

    public void bindViewModels(List<Beer> beerItemViewModelList) {
        this.beerItemViewModelList.clear();
        this.beerItemViewModelList.addAll(beerItemViewModelList);
        notifyDataSetChanged();
    }

    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beer_list_content, parent, false);
        BeerViewHolder beerViewHolder = new BeerViewHolder(v);
        return beerViewHolder;
    }


    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) {
        holder.bind(beerItemViewModelList.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, beer_activity.class);
                intent.putExtra("name",beerItemViewModelList.get(position).getName());
                intent.putExtra("tagline",beerItemViewModelList.get(position).getTagline());
                intent.putExtra("first_brewed",beerItemViewModelList.get(position).getFirst_brewed());
                intent.putExtra("description",beerItemViewModelList.get(position).getDescription());
                intent.putExtra("thumbnail",beerItemViewModelList.get(position).getImage_url());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beerItemViewModelList.size();
    }

}
