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

package com.hazelcast.map.impl.operation;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.Data;
import com.hazelcast.spi.PartitionAwareOperation;
import com.hazelcast.spi.impl.MutatingOperation;

import java.io.IOException;

import static com.hazelcast.map.impl.recordstore.RecordStore.DEFAULT_TTL;

public abstract class MutatingKeyBasedMapOperation extends MapOperation
        implements PartitionAwareOperation, MutatingOperation {

    protected Data dataKey;
    protected long threadId;
    protected Object dataValue;
    protected long ttl = DEFAULT_TTL;

    public MutatingKeyBasedMapOperation() {
    }

    public MutatingKeyBasedMapOperation(String name, Data dataKey) {
        super(name);
        this.dataKey = dataKey;
    }

    protected MutatingKeyBasedMapOperation(String name, Data dataKey, Object dataValue) {
        super(name);
        this.dataKey = dataKey;
        this.dataValue = dataValue;
    }

    protected MutatingKeyBasedMapOperation(String name, Data dataKey, long ttl) {
        super(name);
        this.dataKey = dataKey;
        this.ttl = ttl;
    }

    protected MutatingKeyBasedMapOperation(String name, Data dataKey, Object dataValue, long ttl) {
        super(name);
        this.dataKey = dataKey;
        this.dataValue = dataValue;
        this.ttl = ttl;
    }

    public final Data getKey() {
        return dataKey;
    }

    @Override
    public final long getThreadId() {
        return threadId;
    }

    @Override
    public final void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public final Data getValue() {
        return getNodeEngine().toData(dataValue);
    }

    public final long getTtl() {
        return ttl;
    }

    @Override
    protected void writeInternal(ObjectDataOutput out) throws IOException {
        out.writeUTF(name);
        out.writeData(dataKey);
        out.writeLong(threadId);
        out.writeData(getValue());
        out.writeLong(ttl);
    }

    @Override
    protected void readInternal(ObjectDataInput in) throws IOException {
        name = in.readUTF();
        dataKey = in.readData();
        threadId = in.readLong();
        dataValue = in.readData();
        ttl = in.readLong();
    }
}
