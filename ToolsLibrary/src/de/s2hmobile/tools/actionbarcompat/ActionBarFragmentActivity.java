/* File modified by S2H Mobile.
 * 
 * Copyright 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.s2hmobile.tools.actionbarcompat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;

/**
 * A base activity that defers common functionality across app activities to an {@link
 * ActionBarHelper}. It extends {@link android.support.v4.app.FragmentActivity},
 * so derived acivtities can use fragments. Derived classes must call 
 * {@link ActionBarFragmentActivity#setUpActionBarHelper(Activity, boolean)} in their
 * onCreate() method to set up the actionbar helper.
 */
public abstract class ActionBarFragmentActivity extends FragmentActivity {
    
	private ActionBarHelper mActionBarHelper = null;

    /**
     * Sets up the actionbar helper.
     * @param activity the calling activity
     * @param isHomeStateful indicates the type of home item
     */
	protected void setUpActionBarHelper(Activity activity, boolean isHomeStateful) {
    	mActionBarHelper = ActionBarHelper.createInstance(activity, isHomeStateful);
    }    
    
    /**
     * Returns the {@link ActionBarHelper} for this activity.
     */
    protected ActionBarHelper getActionBarHelper() {
        return mActionBarHelper;
    }

    /**{@inheritDoc}*/
    @Override
    public MenuInflater getMenuInflater() {
        return mActionBarHelper.getMenuInflater(super.getMenuInflater());
    }

    /**{@inheritDoc}*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionBarHelper.onCreate(savedInstanceState);
    }

    /**{@inheritDoc}*/
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarHelper.onPostCreate(savedInstanceState);
    }

    /**
     * Base action bar-aware implementation for
     * {@link Activity#onCreateOptionsMenu(android.view.Menu)}.
     *
     * Note: marking menu items as invisible/visible is not currently supported.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean retValue = false;
        retValue |= mActionBarHelper.onCreateOptionsMenu(menu);
        retValue |= super.onCreateOptionsMenu(menu);
        return retValue;
    }

    /**{@inheritDoc}*/
    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        mActionBarHelper.onTitleChanged(title, color);
        super.onTitleChanged(title, color);
    }
}