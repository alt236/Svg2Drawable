package uk.co.alt236.s2d.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import uk.co.alt236.s2d.Constants;
import uk.co.alt236.s2d.enums.ConverterName;
import uk.co.alt236.s2d.enums.IconType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class OptionsBuilder {

    /*package*/ static final String ARG_ICON_TYPE = "icontype";
    /*package*/ static final String ARG_OUTPUT = "output";
    /*package*/ static final String ARG_CONVERTER = "converter";
    /*package*/ static final String ARG_INPUT = "input";
    /*package*/ static final String ARG_OVERWRITE = "overwrite";

    private static <E extends Enum<?>> String getNiceEnumArgList(final Class<E> enumClass) {

        final StringBuilder sb = new StringBuilder();

        final List<String> list = new ArrayList<>();
        for (final E enm : enumClass.getEnumConstants()) {
            list.add(enm.name().toLowerCase(Locale.US));
        }

        Collections.sort(list);

        for (final String string : list) {
            if (!(sb.length() == 0)) {
                sb.append(", ");
            }
            sb.append(string);
        }
        return sb.toString();
    }

    private static <E extends Enum<?>> String toLower(final E enm) {
        return enm.name().toLowerCase(Locale.US);
    }

    public Options compileOptions() {
        final Options options = new Options();

        options.addOption(createOverwriteOption());
        options.addOption(createSvgOption());
        options.addOption(createOutputDirectoryOption());
        options.addOption(createConverterOption());
        options.addOption(createIconTypeOption());

        return options;
    }

    private Option createOverwriteOption() {
        return Option.builder(ARG_OVERWRITE)
                .required(false)
                .desc("Overwrite existing files. If this is not set and ANY of the target files exist, the app will abort.")
                .build();
    }

    private Option createIconTypeOption() {
        final String validArgs = getNiceEnumArgList(IconType.class);
        return Option.builder(ARG_ICON_TYPE)
                .hasArg()
                .required(true)
                .optionalArg(false)
                .desc("The icon type to export. Must be one of: [" + validArgs + "] (case insensitive).")
                .build();
    }

    private Option createOutputDirectoryOption() {
        return Option.builder(ARG_OUTPUT)
                .hasArg()
                .required(true)
                .optionalArg(false)
                .desc("The output directory. It must exist and be writable")
                .build();
    }

    private Option createConverterOption() {
        final String validArgs = getNiceEnumArgList(ConverterName.class);

        return Option.builder(ARG_CONVERTER)
                .hasArg()
                .required(false)
                .optionalArg(true)
                .desc("The converter to use. Must be one of: [" + validArgs + "] (case insensitive)." +
                        " The default value is '" + toLower(Constants.DEFAULT_CONVERTER) + "'." +
                        " To use '" + toLower(ConverterName.INKSCAPE) + "', the inkscape binary must be in your PATH.")
                .build();
    }

    private Option createSvgOption() {
        return Option.builder(ARG_INPUT)
                .hasArg()
                .required(true)
                .optionalArg(false)
                .desc("The SVG input file. It must exist and be readable")
                .build();
    }
}
