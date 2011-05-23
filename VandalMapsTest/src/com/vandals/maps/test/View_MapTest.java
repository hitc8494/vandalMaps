package com.vandals.maps.test;

import com.vandals.maps.View_map;

import android.test.ActivityInstrumentationTestCase2;

//slight sqlite testing here as well

public class View_MapTest extends ActivityInstrumentationTestCase2<View_map> {
    public View_MapTest() {
        super("com.vandals.maps",View_map.class);
    }
    
    @Override
    protected void setUp() throws Exception{
        super.setUp();
    }
}
