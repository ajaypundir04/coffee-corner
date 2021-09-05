package com.charlene.entity;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Size {
    small, medium, large, litre, not_applicable;

    private static final Map<String, Size> NAME_MAP = Stream.of(values())
            .collect(Collectors.toMap(Size::toString, Function.identity()));

    public static Size fromString(final String name) {
        return NAME_MAP.getOrDefault(name, not_applicable);
    }


}
