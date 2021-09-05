package com.charlene.parser;


import com.charlene.entity.Extra;
import com.charlene.entity.Offering;
import com.charlene.entity.Size;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ajay Singh Pundir
 * Used for parsing the input strings.
 */
public class InputParserImpl implements InputParser {


    @Override
    public List<Offering> parseOfferings(List<String> inputList) {
        return inputList.stream().map(
                line -> {
                    String[] input = line.split("  ");
                    return new Offering(input[0], input[1],
                            Size.fromString(input[2]), Integer.parseInt(input[3]),
                            Double.parseDouble(input[4])
                    );
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<Extra> parseExtras(List<String> inputList) {
        return inputList.stream().map(
                line -> {
                    String[] input = line.split("  ");
                    return new Extra(input[0], Integer.parseInt(input[1]),
                            Double.parseDouble(input[2]));
                }
        ).collect(Collectors.toList());
    }
}
