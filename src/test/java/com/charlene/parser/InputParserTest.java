package com.charlene.parser;

import com.charlene.entity.Extra;
import com.charlene.entity.Offering;
import com.charlene.util.InputFileReader;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class InputParserTest {

    private InputParser parser;

    @Before
    public void setUp() {
        parser = new InputParserImpl();
    }

    @Test
    public void parseInputOfferingsTest() throws IOException {
        List<String> inputList = InputFileReader
                .parseFile("src/test/resources/offerings_order_file.txt");
        List<Offering> offerings = parser.parseOfferings(inputList);


        Assert.assertThat(offerings,
                Matchers.contains(
                        HasPropertyWithValue.
                                hasProperty("price", CoreMatchers.is(4.40))


                )

        );
    }

    @Test
    public void parseInputExtrasTest() throws IOException {
        List<String> inputList = InputFileReader
                .parseFile("src/test/resources/extras_order_file.txt");
        List<Extra> extras = parser.parseExtras(inputList);


        Assert.assertThat(extras,
                Matchers.contains(
                        HasPropertyWithValue.
                                hasProperty("price", CoreMatchers.is(0.30))


                )

        );
    }

}
