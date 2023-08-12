package home.gyak.resource_handling;

import java.net.URL;

public class Locator {
    public URL getUrl(String filePath) {
        return this.getClass().getResource(filePath);
    }
}