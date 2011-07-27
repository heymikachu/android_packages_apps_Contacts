/*
 * Copyright (C) 2010 The Android Open Source Project
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
package com.android.contacts.tests.mocks;

import com.android.contacts.model.AccountType;
import com.android.contacts.model.AccountTypeManager;
import com.android.contacts.model.AccountWithDataSet;
import com.google.android.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * A mock {@link AccountTypeManager} class.
 */
public class MockAccountTypeManager extends AccountTypeManager {

    private final AccountType[] mTypes;
    private AccountWithDataSet[] mAccounts;

    public MockAccountTypeManager(AccountType[] types, AccountWithDataSet[] accounts) {
        this.mTypes = types;
        this.mAccounts = accounts;
    }

    @Override
    public AccountType getAccountType(String accountType, String dataSet) {
        for (AccountType type : mTypes) {
            if (accountType.equals(type.accountType)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public List<AccountWithDataSet> getAccounts(boolean writableOnly) {
        return Arrays.asList(mAccounts);
    }

    @Override
    public Map<String, AccountType> getInvitableAccountTypes() {
        return Maps.newHashMap(); // Always returns empty
    }
}
