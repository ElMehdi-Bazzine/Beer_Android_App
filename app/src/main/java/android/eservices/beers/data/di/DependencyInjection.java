package android.eservices.beers.data.di;
import android.content.Context;
import android.eservices.beers.data.api.BeerDisplayService;
import android.eservices.beers.data.db.BeerDatabase;
import android.eservices.beers.data.repository.beerdisplay.BeerDisplayDataRepository;
import android.eservices.beers.data.repository.beerdisplay.BeerDisplayRepository;
import android.eservices.beers.data.repository.beerdisplay.mapper.BeerToBeerEntityMapper;
import android.eservices.beers.data.repository.beerdisplay.remote.BeerDisplayRemoteDataSource;
import androidx.room.Room;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DependencyInjection {
    private static BeerDisplayService beerDisplayService;
    private static BeerDatabase beerDatabase;
    private static BeerDisplayRepository beerDisplayRepository;
    private static Retrofit retrofit;
    private static Gson gson;
    private static Context appContext;

    public static BeerDisplayRepository getBeerDisplayRepository() {
        if (beerDisplayRepository == null) {
            beerDisplayRepository = new BeerDisplayDataRepository(
                    new BeerDisplayRemoteDataSource(getBeerDisplayService()),
                    new BeerToBeerEntityMapper()
            );
        }
        return beerDisplayRepository;
    }

    public static BeerDisplayService getBeerDisplayService() {
        if (beerDisplayService == null) {
            beerDisplayService = getRetrofit().create(BeerDisplayService.class);
        }
        return beerDisplayService;
    }


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.punkapi.com/v2/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        appContext = context;
    }

    public static BeerDatabase getBeerDatabase() {
        if (beerDatabase == null) {
            beerDatabase = Room.databaseBuilder(appContext,
                    BeerDatabase.class, "beer-database").build();
        }
        return beerDatabase;
    }
}
