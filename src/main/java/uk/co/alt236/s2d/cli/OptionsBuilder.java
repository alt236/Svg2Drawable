package uk.co.alt236.s2d.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import uk.co.alt236.s2d.Constants;
import uk.co.alt236.s2d.enums.ConverterName;
import uk.co.alt236.s2d.enums.IconType;
import uk.co.alt236.s2d.resources.S2DStrings;

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

    private final S2DStrings strings;

    public OptionsBuilder() {
        strings = new S2DStrings();
    }

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
        final String desc = strings.getString("cli_cmd_desc_overwrite");
        return Option.builder(ARG_OVERWRITE)
                .required(false)
                .desc(desc)
                .build();
    }

    private Option createIconTypeOption() {
        final String validArgs = getNiceEnumArgList(IconType.class);
        final String desc = strings.getString("cli_cmd_desc_icontype", validArgs);

        return Option.builder(ARG_ICON_TYPE)
                .hasArg()
                .required(true)
                .optionalArg(false)
                .desc(desc)
                .build();
    }

    private Option createOutputDirectoryOption() {
        final String desc = strings.getString("cli_cmd_desc_output");

        return Option.builder(ARG_OUTPUT)
                .hasArg()
                .required(true)
                .optionalArg(false)
                .desc(desc)
                .build();
    }

    private Option createConverterOption() {
        final String validArgs = getNiceEnumArgList(ConverterName.class);
        final String desc = strings.getString("cli_cmd_desc_converter",
                validArgs,
                toLower(Constants.DEFAULT_CONVERTER),
                toLower(ConverterName.INKSCAPE));

        return Option.builder(ARG_CONVERTER)
                .hasArg()
                .required(false)
                .optionalArg(true)
                .desc(desc)
                .build();
    }

    private Option createSvgOption() {
        final String desc = strings.getString("cli_cmd_desc_input");

        return Option.builder(ARG_INPUT)
                .hasArg()
                .required(true)
                .optionalArg(false)
                .desc(desc)
                .build();
    }
}
