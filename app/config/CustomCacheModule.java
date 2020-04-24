package config;

import cache.CustomDefaultSyncCacheApi;
import cache.CustomSyncCacheApi;
import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import play.cache.NamedCacheImpl;
import play.cache.SyncCacheApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomCacheModule extends AbstractModule {

    List<String> namedCaches = new ArrayList<String>(Arrays.asList("options-cache", "user-cache", "session-cache", "api-token-cache"));

    @Override
    protected void configure() {
        Provider<SyncCacheApi> syncCacheApi = getProvider(Key.get(SyncCacheApi.class, Names.named("custom-default")));
        bind(SyncCacheApi.class).toInstance(new CustomSyncCacheApi(syncCacheApi, "custom-default"));
        namedCaches.forEach(cache -> {
            bind(SyncCacheApi.class).annotatedWith(new NamedCacheImpl(cache))
                    .toInstance(new CustomDefaultSyncCacheApi(syncCacheApi, cache));
        });
    }
}
