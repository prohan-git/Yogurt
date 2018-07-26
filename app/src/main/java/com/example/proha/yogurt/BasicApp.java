/*
 * Copyright 2017, The Android Open Source Project
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

package com.example.proha.yogurt;

import android.app.Application;

import com.example.proha.yogurt.db.AppDatabase;

/**
 * Android Application class. Used for accessing singletons.
 */
public class BasicApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    public AppDatabase getDatabase() {
        return AppDatabase.Companion.getInstance(this);
    }
}
