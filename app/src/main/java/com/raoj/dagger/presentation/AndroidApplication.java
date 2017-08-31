/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.raoj.dagger.presentation;

import android.app.Application;
import android.util.Log;

import com.raoj.dagger.BuildConfig;
import com.raoj.dagger.presentation.internal.di.components.ApplicationComponent;
import com.raoj.dagger.presentation.internal.di.components.DaggerApplicationComponent;
import com.raoj.dagger.presentation.internal.di.modules.ApplicationModule;

/**
 * Android Main Application
 */
public class AndroidApplication extends Application {

    private static final String TAG = "AndroidApplication";

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        this.initializeInjector();
        this.initializeLeakDetection();
    }

    private void initializeInjector() {
        Log.d(TAG, "initializeInjector");
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        Log.d(TAG, "AndroidApplication-getApplicationComponent");
        return this.applicationComponent;
    }

    private void initializeLeakDetection() {
        Log.d(TAG, "initializeLeakDetection");
        if (BuildConfig.DEBUG) {
//            LeakCanary.install(this);
        }
    }
}