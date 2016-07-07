package uk.co.alt236.s2d.resources;

import uk.co.alt236.s2d.Constants;

import java.util.Enumeration;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class S2DStrings extends ResourceBundle {
    private final PropertyResourceBundle bundle;

    public S2DStrings() {
        bundle = (PropertyResourceBundle) ResourceBundle.getBundle(Constants.STRING_PROPERTIES);
    }

    @Override
    protected Object handleGetObject(String s) {
        return bundle.handleGetObject(s);
    }

    @Override
    public Enumeration<String> getKeys() {
        return bundle.getKeys();
    }

    public String getString(String s, final Object... values) {
        final String string = getString(s);

        return String.format(getLocale(), string, values);
    }
}
