/*
 * Copyright (C) 2015 Antonio Leiva
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.antonioleiva.mikotlin.ui.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.view.View

inline public fun <reified T : Activity> Activity.navigate(id: String, sharedView: View? = null,
                                                           transitionName: String? = null) {
    val intent = Intent(this, T::class.java)
    intent.putExtra("id", id)

    var options: ActivityOptionsCompat? = null

    if (sharedView != null && transitionName != null) {
        options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, sharedView, transitionName)
    }

    ActivityCompat.startActivity(this, intent, options?.toBundle())
}

public fun Activity.getNavigationId(): String {
    val intent = intent
    return intent.getStringExtra("id")
}

public fun Context.getDimen(dimenRes: Int): Int {
    return resources.getDimensionPixelSize(dimenRes)
}

public fun Context.getAttrId(themeRes: Int, attrRes: Int): Int {
    val a = theme.obtainStyledAttributes(themeRes, intArrayOf(attrRes));
    val attributeResourceId = a.getResourceId(0, 0);
    a.recycle()
    return attributeResourceId
}

