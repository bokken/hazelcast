/*
 * Copyright (c) 2008-2016, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.hotrestart;

/**
 * Empty implementation of HotRestartService to avoid null checks. This will provide default behaviour when hot restart is
 * not available or not enabled.
 */
public class NoOpHotRestartService implements HotRestartService {

    @Override
    public void backup() {

    }

    @Override
    public void backup(long backupSeq) {

    }

    @Override
    public BackupTaskStatus getBackupTaskStatus() {
        return new BackupTaskStatus(BackupTaskState.NOT_STARTED, 0, 0);
    }

    @Override
    public void interruptLocalBackupTask() {

    }

    @Override
    public void interruptBackupTask() {

    }
}
