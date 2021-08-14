package android.eservices.beers.presentation.beerdisplay;
import android.content.Intent;
import android.eservices.beers.R;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class beer_activity extends AppCompatActivity {

    private ImageView thumbnailImageView;
    private TextView nameTextView;
    private TextView taglineTextView;
    private TextView first_brewedTextView;
    private TextView descriptionTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_activity);

        Intent intent = getIntent();

        nameTextView = findViewById(R.id.name);
        taglineTextView = findViewById(R.id.tagline);
        first_brewedTextView = findViewById(R.id.first_brewed);
        descriptionTextView = findViewById(R.id.description);
        thumbnailImageView = findViewById(R.id.image);


        String name = intent.getExtras().getString("name");
        String tagline = intent.getExtras().getString("tagline");
        String first_brewed = intent.getExtras().getString("first_brewed");
        String description = intent.getExtras().getString("description");
        String image_url = intent.getExtras().getString("thumbnail");

        nameTextView.setText(name);
        taglineTextView.setText(tagline);
        first_brewedTextView.setText(first_brewed);
        descriptionTextView.setText(Html.fromHtml(description));
        if(image_url!= null) {
            Glide.with(nameTextView.getContext())
                .load(Uri.parse(image_url))
                .into(thumbnailImageView);
        }
    }

}
