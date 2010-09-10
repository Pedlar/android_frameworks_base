/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.content.res;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class ThemeIcons {

	private static Context mContext;
	public ThemeIcons(Context context) {
		mContext = context;
	}

	public static int ResourceID(String iconName, int rDefault) {
		String pkg = "com.cyanogenmod.cmthemeone";
		PackageManager pm = mContext.getPackageManager();
		Resources mR = null;
		int rId = 0;
		try {
			mR = pm.getResourcesForApplication(pkg);
		} catch (NameNotFoundException e) {
        	}
		if(mR != null) {
			try {
				rId = mR.getIdentifier(iconName, "drawable", pkg);
			} catch (NotFoundException e) {
			}
		}
		return(rId == 0 ? rDefault : rId);
	}

	public static String getPackage() {
		String pkg = "com.cyanogenmod.cmthemeone";
		PackageManager pm = mContext.getPackageManager();
                Resources mR = null;
		try {
                        mR = pm.getResourcesForApplication(pkg);
			return pkg;
                } catch (NameNotFoundException e) {
			return null;
                }
	}

	public static String getPackage(String basePkg, int iconId) {
		String pkg = "com.cyanogenmod.cmthemeone";
                PackageManager pm = mContext.getPackageManager();
                Resources mR = null;
		Resources mBaseR = null;
		int rId = 0;
                try {
                        mR = pm.getResourcesForApplication(pkg);
			mBaseR = pm.getResourcesForApplication(basePkg);
                } catch (NameNotFoundException e) {
			return basePkg;
                }
		if(mR != null && mBaseR != null) {
			try {
				String iconName = mBaseR.getResourceEntryName(iconId);
				rId = mR.getIdentifier(iconName, "drawable", pkg);
				Log.i("ThemeIcons", "Checking, String: "+iconName+" Got: "+rId);
			} catch (NotFoundException e) {
				rId = 0;
				return basePkg;
			}
		}
		return(rId == 0 ? basePkg : pkg);
	}

}
