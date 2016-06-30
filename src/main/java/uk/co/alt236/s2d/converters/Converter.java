package uk.co.alt236.s2d.converters;

import uk.co.alt236.s2d.enums.ConverterName;
import uk.co.alt236.s2d.outputpayload.OutputPayload;

import java.util.List;

public interface Converter {
    void convert(List<OutputPayload> outputPayloadList) throws Exception;

    ConverterName getName();
}
