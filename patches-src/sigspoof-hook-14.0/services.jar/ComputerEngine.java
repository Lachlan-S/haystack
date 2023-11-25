/*
 * Copyright (C) 2015 Marvin W <https://github.com/mar-v-in>
 * Copyright (C) 2016-2019 Lanchon <https://github.com/Lanchon>
 *
 * Based on Marvin's work:
 *
 *      https://gerrit.omnirom.org/#/c/14898/
 *      https://gerrit.omnirom.org/#/c/14899/
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

package com.android.server.pm;

import android.content.Context;
import android.content.pm.PackageInfo;
//<14.0// import com.android.server.pm.parsing.pkg.AndroidPackage;
/*>14.0*/ import com.android.server.pm.pkg.AndroidPackage;
import android.content.pm.PackageManager;

import com.android.server.pm.pkg.PackageStateInternal;

import lanchon.dexpatcher.annotation.*;

@DexEdit(contentOnly = true)
public class ComputerEngine {

	@DexIgnore
	/* final */ Context mContext;

	@DexWrap
	public final PackageInfo generatePackageInfo(PackageStateInternal ps, long flags, int userId) {
	    PackageInfo pi = generatePackageInfo(ps, flags | PackageManager.GET_PERMISSIONS, userId);
	    if (ps != null && pi != null) {
		AndroidPackage pp = ps.getPkg();
		if (pp != null) pi = GeneratePackageInfoHook.hook(pi, mContext, pp, flags, userId);
		if ((flags & PackageManager.GET_PERMISSIONS) == 0) {
		    // maybe not necessary but let's keep API compatibile
		    pi.permissions = null;
		    pi.requestedPermissions = null;
		    pi.requestedPermissionsFlags = null;
		}
	    }
	    return pi;
	}
}
