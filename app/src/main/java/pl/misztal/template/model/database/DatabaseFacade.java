package pl.misztal.template.model.database;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;

@Singleton
public class DatabaseFacade {

    private final Realm realm;

    @Inject
    public DatabaseFacade() {
        RealmConfiguration config = RealmHelper.getDefaultRealmConfig();
        realm = Realm.getInstance(config);
    }


    // TODO remember about reactive model - return observables, or better Single
    // For void operations return Completable !

}
