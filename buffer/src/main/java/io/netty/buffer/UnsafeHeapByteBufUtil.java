/*
 * Copyright 2015 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.buffer;

import io.netty.util.internal.PlatformDependent;

final class UnsafeHeapByteBufUtil {
    private static final boolean NATIVE_ORDER = PlatformDependent.isNativeOrder();

    static byte getByte(byte[] array, int index) {
        return PlatformDependent.getByte(array, index);
    }

    static short getShort(byte[] array, int index) {
        short v = PlatformDependent.getShort(array, index);
        return NATIVE_ORDER? v : Short.reverseBytes(v);
    }

    static int getUnsignedMedium(byte[] array, int index) {
        return  (PlatformDependent.getByte(array, index) & 0xff) << 16 |
                (PlatformDependent.getByte(array, index + 1) & 0xff) <<  8 |
                PlatformDependent.getByte(array, index + 2) & 0xff;
    }

    static int getInt(byte[] array, int index) {
        int v = PlatformDependent.getInt(array, index);
        return NATIVE_ORDER? v : Integer.reverseBytes(v);
    }

    static long getLong(byte[] array, int index) {
        long v =  PlatformDependent.getLong(array, index);
        return NATIVE_ORDER? v : Long.reverseBytes(v);
    }

    static void setByte(byte[] array, int index, int value) {
        PlatformDependent.putByte(array, index, (byte) value);
    }

    static void setShort(byte[] array, int index, int value) {
        PlatformDependent.putShort(array, index, NATIVE_ORDER? (short) value : Short.reverseBytes((short) value));
    }

    static void setMedium(byte[] array, int index, int   value) {
        PlatformDependent.putByte(array, index, (byte) (value >>> 16));
        PlatformDependent.putByte(array, index + 1, (byte) (value >>> 8));
        PlatformDependent.putByte(array, index + 2, (byte) value);
    }

    static void setInt(byte[] array, int index, int   value) {
        PlatformDependent.putInt(array, index, NATIVE_ORDER? value : Integer.reverseBytes(value));
    }

    static void setLong(byte[] array, int index, long  value) {
        PlatformDependent.putLong(array, index, NATIVE_ORDER ? value : Long.reverseBytes(value));
    }

    private UnsafeHeapByteBufUtil() { }
}
