package com.vandals.maps.test;

import com.vandals.maps.BuildingOpenHelper;
import com.vandals.maps.CheckBoxCursorAdapter;
import com.vandals.maps.Initiate;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//also tests BuildingOpenHelper and CheckBoxCursorAdaptor

public class InitiateTest extends ActivityInstrumentationTestCase2<Initiate> {
    private Initiate mActivity;
    private View aView;
    private View mView;
    private TextView mViewTest;
    private View listButton;
    BuildingOpenHelper db;
    CheckBoxCursorAdapter cursor;
    
    private String resourceString;

    public InitiateTest() {
        super("com.vandals.maps", Initiate.class);
      }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();
       // aView = (TextView) mActivity.findViewById(com.vandals.maps.R.id.textview);
        mView = (Button) mActivity.findViewById(com.vandals.maps.R.id.MapButton);
        mViewTest = (TextView) mActivity.findViewById(com.vandals.maps.R.id.MapButton);
        resourceString = mActivity.getString(com.vandals.maps.R.string.map);
    }
    
    public void testPreconditions() {
        assertNotNull(mView);
    }
    
    public void testText() {
        assertEquals(resourceString,(String) mViewTest.getText());    
    }
    
        
    
}
