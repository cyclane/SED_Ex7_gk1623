package fi.iki.jka;

public interface JPhotoShowFactory {
    JPhotoShow create(JPhotoCollection photos, int interval, JPhotoList list);
}
