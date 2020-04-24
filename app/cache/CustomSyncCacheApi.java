package cache;

import com.google.inject.Provider;
import play.cache.SyncCacheApi;

import java.util.Optional;
import java.util.concurrent.Callable;

public class CustomSyncCacheApi implements SyncCacheApi {

    private Provider<SyncCacheApi> cacheApiProvider;
    private SyncCacheApi cacheApi;

    private String prefix;

    private SyncCacheApi getCacheApi() {
        if (cacheApi == null) {
            cacheApi = cacheApiProvider.get();
        }
        return cacheApi;
    }

    public CustomSyncCacheApi(Provider<SyncCacheApi> provider, String prefix) {
        this.prefix = prefix;
        this.cacheApiProvider = provider;
    }


    @Override
    public <T> Optional<T> get(String key) {
        return Optional.empty();
    }

    @Override
    public <T> T getOrElseUpdate(String key, Callable<T> block, int expiration) {
        return this.getCacheApi().getOrElseUpdate(getKey(key), block, expiration);
    }

    @Override
    public <T> T getOrElseUpdate(String key, Callable<T> block) {
        return this.getCacheApi().getOrElseUpdate(getKey(key), block);
    }

    @Override
    public void set(String key, Object value, int expiration) {
        this.getCacheApi().set(getKey(key), value, expiration);
    }

    @Override
    public void set(String key, Object value) {
        this.getCacheApi().set(getKey(key), value);

    }

    @Override
    public void remove(String key) {
        this.getCacheApi().remove(getKey(key));
    }

    private String getKey(String cacheKey) {
        return "-test-prefix-" + ":" + this.prefix + ":" + cacheKey;
    }

}
