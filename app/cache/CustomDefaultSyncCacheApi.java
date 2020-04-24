package cache;

import com.google.inject.Provider;
import play.cache.SyncCacheApi;

public class CustomDefaultSyncCacheApi extends CustomSyncCacheApi {

    public CustomDefaultSyncCacheApi(Provider<SyncCacheApi> cacheApiProvider, String prefix) {
        super(cacheApiProvider, prefix);
    }
}
