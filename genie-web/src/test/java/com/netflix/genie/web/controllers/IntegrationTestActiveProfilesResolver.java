/*
 *
 *  Copyright 2016 Netflix, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */
package com.netflix.genie.web.controllers;

import org.springframework.test.context.ActiveProfilesResolver;

/**
 * A class to switch the active profiles for integration tests based on environment variables.
 *
 * @author tgianos
 * @since 3.0.0
 */
public class IntegrationTestActiveProfilesResolver implements ActiveProfilesResolver {

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] resolve(final Class<?> testClass) {
        final boolean isCI = Boolean.valueOf(System.getProperty("CI", "false"));
        final boolean isTravis = Boolean.valueOf(System.getProperty("TRAVIS", "false"));
        final boolean isContinuousIntegration = Boolean.valueOf(System.getProperty("CONTINUOUS_INTEGRATION", "false"));

        final String[] activeProfiles;
        if (isCI || isTravis || isContinuousIntegration) {
            activeProfiles = new String[]{"ci"};
        } else {
            activeProfiles = new String[]{"integration"};
        }

        return activeProfiles;
    }
}
