package com.vandals.maps.test;

import com.vandals.maps.About;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

//here for thoroughness

public class AboutTest extends ActivityInstrumentationTestCase2<About> {

    
    private About aboutActivity;
    private View tView;
    private String tResource;
    
    
    public AboutTest() {
          super("com.vandals.maps",About.class);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        aboutActivity = this.getActivity();
        tView = (TextView) aboutActivity.findViewById(com.vandals.maps.R.id.about_content);
        tResource = aboutActivity.getString(com.vandals.maps.R.string.about_text);
    }
    
    public void testPreconditions() {
        assertNotNull(aboutActivity);
    }
    
    public void testText() {
        assertEquals(tResource, (String) ((TextView) tView).getText());
    }
}
