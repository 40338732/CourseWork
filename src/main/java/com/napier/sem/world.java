package com.napier.sem;

//** PLEASE READ **//
// I have used the field names in the database for each variable except Name, Population and CountryCode
// These are now CountryName, CityName, CountryPopulation, CityPopulation, CityCountryCode and LanguageCountryCode.
// Also, not too sure if the data types are all correct...

public class world {


    //*** Country Table ***//
    /**
     * Country Code
     */

    public String Code;

    /**
     * Country Name
     */

    public String CountryName;

    /**
     * Country's Continent
     */

    public String Continent;

    /**
     * Country's Region
     */

    public String Region;

    /**
     * Country's Surface Area
     */

    public Float SurfaceArea;

    /**
     * Country's Independence Year
     */

    public String IndepYear;

    /**
     * Country's Population
     */

    public int CountryPopulation;

    /**
     * Country's Life Expectency
     */

    public Float LifeExpectency;

    /**
     * Country's Gross National Product
     */

    public Float GNP;

    /**
     * Country's Gross National Product something!
     */

    public Float GNPOld;

    /**
     * Country's Local Name
     */

    public String LocalName;

    /**
     * Country's Government Form
     */

    public String GovernmentForm;

    /**
     * Country's Head of State
     */

    public String HeadOfState;

    /**
     * Country's Capital
     */

    public int Capital;

    /**
     * Country's Code 2
     */

    public String Code2;



    //*** City Table ***//

    /**
     * City's ID
     */

    public int ID;

    /**
     * City's Name
     */

    public String CityName;

    /**
     * City's Country Code
     */

    public String CityCountryCode;

    /**
     * City's District
     */

    public String District;

    /**
     * City's Population
     */

    public int CityPopulation;





    //*** Country Language Table ***//

    /**
     * Country Language's Country Code
     */

    public String LanguageCountryCode;

    /**
     * Country Language's Language
     */

    public String Language;

    /**
     * Country Language's Is Official
     */

    public String IsOfficial;

    /**
     * Country Language's Percentage
     */

    public Float Percentage;



}
