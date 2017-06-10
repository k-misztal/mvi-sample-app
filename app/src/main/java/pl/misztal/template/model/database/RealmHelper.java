package pl.misztal.template.model.database;

import io.realm.RealmConfiguration;

final class RealmHelper {
    public static final String DATABASE_NAME = "db.realm";
    public static final long DATABASE_VERSION = 1;

    public static RealmConfiguration getDefaultRealmConfig() {
        return new RealmConfiguration.Builder()
                .name(DATABASE_NAME)
                .schemaVersion(DATABASE_VERSION)
                .build();
    }
}
