package com.charlene.parser;



import com.charlene.entity.Extra;
import com.charlene.entity.Offering;

import java.util.List;

/**
 * @author Ajay Singh Pundir
 * It will parse the input file
 */
public interface InputParser {

    List<Offering> parseOfferings(List<String> inputList);
    List<Extra> parseExtras(List<String> inputList);

}
