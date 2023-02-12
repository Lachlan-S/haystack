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

/*<4.0*/ package com.android.server;
//>4.0// package com.android.server.pm;

import android.content.Context;
import android.content.pm.PackageInfo;
/*<11.0*/ import android.content.pm.PackageParser;
//>11.0// import com.android.server.pm.parsing.pkg.AndroidPackage;
import android.content.pm.PackageManager;

/*<4.0*/ import com.android.server.pm.GeneratePackageInfoHookAccessor;

//>7.0// import com.android.server.pm.PackageSetting;

import lanchon.dexpatcher.annotation.*;

/*<12.0*/ @DexEdit(contentOnly = true)
//>12.0// @DexIgnore
public class PackageManagerService /* extends IPackageManager.Stub */ {

    //>12.0// @DexEdit(contentOnly = true)
    //>12.0// protected static class ComputerEngine {

    @DexIgnore /* final */ Context mContext;

    @DexWrap
    /*<4.1*/ PackageInfo generatePackageInfo(PackageParser.Package p, int flags) {
    //>4.1// /*<7.0*/ PackageInfo generatePackageInfo(PackageParser.Package p, int flags, int userId) {
    //>7.0// /*<12.0*/ private PackageInfo generatePackageInfo(PackageSetting p, int flags, int userId) {
    //>12.0// public final PackageInfo generatePackageInfo(PackageSetting p, int flags, int userId) {
        /*<4.1*/ PackageInfo pi = generatePackageInfo(p, flags);
        //>4.1// PackageInfo pi = generatePackageInfo(p, flags | PackageManager.GET_PERMISSIONS, userId);
        if (p != null && pi != null) {
            /*<4.0*/ pi = GeneratePackageInfoHookAccessor.hook(pi, mContext, p, flags, -1);
            //>4.0// /*<4.1*/ pi = GeneratePackageInfoHook.hook(pi, mContext, p, flags, -1);
            //>4.1// /*<7.0*/ pi = GeneratePackageInfoHook.hook(pi, mContext, p, flags, userId);
            //>7.0// if (p.pkg != null) pi = GeneratePackageInfoHook.hook(pi, mContext, p.pkg, flags, userId);
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
//>12.0// }
