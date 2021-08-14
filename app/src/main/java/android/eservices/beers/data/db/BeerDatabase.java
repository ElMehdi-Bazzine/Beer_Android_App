package android.eservices.beers.data.db;
import android.eservices.beers.data.entity.BeerEntity;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {BeerEntity.class}, version = 1, exportSchema = false)

public abstract class BeerDatabase extends RoomDatabase {
}