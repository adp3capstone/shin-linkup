package com.ethan.adatingapp.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/*
 Institution.java
 Institution enum
 Author: Ethan Le Roux (222622172)
 Date: 11 May 2025
*/
public enum Institution {

    UNIVERSITY_OF_CAPE_TOWN("UCT"),
    UNIVERSITY_OF_THE_WITWATERSRAND("WITS"),
    UNIVERSITY_OF_JOHANNESBURG("UJ"),
    UNIVERSITY_OF_PRETORIA("UP"),
    UNIVERSITY_OF_KWAZULU_NATAL("UKZN"),
    STELLENBOSCH_UNIVERSITY("SUN"),
    RHODES_UNIVERSITY("RU"),
    NORTH_WEST_UNIVERSITY("NWU"),
    UNIVERSITY_OF_SOUTH_AFRICA("UNISA"),
    UNIVERSITY_OF_THE_WESTERN_CAPE("UWC"),
    NELSON_MANDELA_UNIVERSITY("NMU"),
    UNIVERSITY_OF_LIMPOPO("UL"),
    UNIVERSITY_OF_FORT_HARE("UFH"),
    UNIVERSITY_OF_THE_FREE_STATE("UFS"),
    MANGOSUTHU_UNIVERSITY_OF_TECHNOLOGY("MUT"),

    CAPE_PENINSULA_UNIVERSITY_OF_TECHNOLOGY("CPUT"),
    DURBAN_UNIVERSITY_OF_TECHNOLOGY("DUT"),
    TSHWANE_UNIVERSITY_OF_TECHNOLOGY("TUT"),
    VAAL_UNIVERSITY_OF_TECHNOLOGY("VUT"),

    FALSE_BAY_TVET_COLLEGE("FALSE_BAY_TVET"),
    NORTHLINK_COLLEGE("NORTHLINK"),
    COLLEGE_OF_CAPE_TOWN("CCT"),
    BOLAND_COLLEGE("BOLAND"),
    WEST_COAST_COLLEGE("WCC"),
    ELANGENI_TVET_COLLEGE("ELANGENI"),
    MAJUBA_TVET_COLLEGE("MAJUBA"),
    KING_HINTSA_TVET_COLLEGE("KING_HINTSA"),
    BUFFALO_CITY_TVET_COLLEGE("BUFFALO_CITY"),
    CENTRAL_JOHANNESBURG_TVET_COLLEGE("CENTRAL_JHB"),
    EKURHULENI_EAST_TVET_COLLEGE("EKURHULENI_EAST"),
    SOUTH_WEST_GAUTENG_TVET_COLLEGE("SWG_TVET"),
    LETABA_TVET_COLLEGE("LETABA"),
    MOPANI_SOUTH_EAST_TVET_COLLEGE("MOPANI_SE"),
    WATERBERG_TVET_COLLEGE("WATERBERG"),

    STADIO_HIGHER_EDUCATION("STADIO"),
    MILPARK_EDUCATION("MILPARK"),
    VEGA_SCHOOL("VEGA"),
    REGENT_BUSINESS_SCHOOL("REGENT"),
    BOSTON_CITY_CAMPUS("BOSTON"),
    DAMELIN_COLLEGE("DAMELIN"),
    CTI_EDUCATION_GROUP("CTI"),
    EDUCOR("EDUCOR"),
    AAA_SCHOOL_OF_ADVERTISING("AAA"),
    AFDA("AFDA"),
    INTERNATIONAL_HOTEL_SCHOOL("IHS"),
    IIE_MSA("IIE_MSA"),
    VARSITY_COLLEGE("VARSITY"),
    RICHFIELD_GRADUATE_INSTITUTE("RICHFIELD"),
    IMM_GRADUATE_SCHOOL("IMM"),
    SAHETI_HIGHER_EDUCATION("SAHETI"),
    LIMPOPO_ACADEMY_OF_AGRICULTURAL_ARTS("LAAA");

    private final String code;

    Institution(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    private static final Map<String, Institution> CODE_MAP = new HashMap<>();

    static {
        for (Institution i : Institution.values()) {
            CODE_MAP.put(i.code.toUpperCase(), i);
        }
    }

    @JsonCreator
    public static Institution fromCode(String code) {
        Institution institution = CODE_MAP.get(code.toUpperCase());
        if (institution == null) {
            throw new IllegalArgumentException("Invalid institution code: " + code);
        }
        return institution;
    }
}