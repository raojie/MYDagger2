/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.raoj.dagger.presentation.internal.di.modules;

import android.content.Context;
import android.util.Log;

import com.raoj.dagger.data.cache.UserCache;
import com.raoj.dagger.data.cache.UserCacheImpl;
import com.raoj.dagger.data.executor.JobExecutor;
import com.raoj.dagger.data.repository.UserDataRepository;
import com.raoj.dagger.domain.executor.PostExecutionThread;
import com.raoj.dagger.domain.executor.ThreadExecutor;
import com.raoj.dagger.domain.repository.UserRepository;
import com.raoj.dagger.presentation.AndroidApplication;
import com.raoj.dagger.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;
    private static final String TAG = "AndroidApplication";
    public ApplicationModule(AndroidApplication application) {
        Log.d(TAG, "ApplicationModule");
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    UserCache provideUserCache(UserCacheImpl userCache) {
        return userCache;
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }
}