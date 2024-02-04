package io.github.devclubspb.parking.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CarClass {

    A("Mini cars"),
    B("Small cars"),
    C("Medium cars"),
    D("Larger cars"),
    E("Executive cars"),
    F("Luxury cars"),
    J("Sport utility"),
    M("Multi purpose cars"),
    S("Sport coupe"),
    ;

    private final String name;

}
