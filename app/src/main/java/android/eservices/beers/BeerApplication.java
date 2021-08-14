package android.eservices.beers;
import android.app.Application;
import android.eservices.beers.data.di.DependencyInjection;
import com.facebook.stetho.Stetho;

public class BeerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        DependencyInjection.setContext(this);
    }
}
