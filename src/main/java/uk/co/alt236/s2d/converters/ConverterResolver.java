package uk.co.alt236.s2d.converters;

import uk.co.alt236.s2d.enums.ConverterName;

public class ConverterResolver {

    public Converter resolve(final ConverterName converterName) {

        if (converterName == null) {
            throw new IllegalArgumentException("ConverterName cannot be null");
        }

        final Converter retVal;
        switch (converterName) {
            case INKSCAPE:
                retVal = new InkscapeConverter();
                break;
            case BATIK:
                retVal = new BatikConverter();
                break;
            default:
                throw new IllegalArgumentException("Unknown name " + converterName);
        }

        return retVal;
    }

}
