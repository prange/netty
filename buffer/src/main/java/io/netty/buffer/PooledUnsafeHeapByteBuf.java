/*
 * Copyright 2015 The Netty Project
 *
 * The Netty Project licenses this file tothe License at:
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

import io.netty.util.Recycler;
import io.netty.util.Recycler.Handle;

final class PooledUnsafeHeapByteBuf extends PooledHeapByteBuf {

    private static final Recycler<PooledUnsafeHeapByteBuf> RECYCLER = new Recycler<PooledUnsafeHeapByteBuf>() {
        @Override
        protected PooledUnsafeHeapByteBuf newObject(Handle handle) {
            return new PooledUnsafeHeapByteBuf(handle, 0);
        }
    };

    static PooledUnsafeHeapByteBuf newUnsafeInstance(int maxCapacity) {
        PooledUnsafeHeapByteBuf buf = RECYCLER.get();
        buf.reuse(maxCapacity);
        return buf;
    }

    private PooledUnsafeHeapByteBuf(Handle recyclerHandle, int maxCapacity) {
        super(recyclerHandle, maxCapacity);
    }

    @Override
    protected byte _getByte(int index) {
        return UnsafeHeapByteBufUtil.getByte(memory, idx(index));
    }

    @Override
    protected short _getShort(int index) {
        return UnsafeHeapByteBufUtil.getShort(memory, idx(index));
    }

    @Override
    protected int _getUnsignedMedium(int index) {
        return UnsafeHeapByteBufUtil.getUnsignedMedium(memory, idx(index));
    }

    @Override
    protected int _getInt(int index) {
        return UnsafeHeapByteBufUtil.getInt(memory, idx(index));
    }

    @Override
    protected long _getLong(int index) {
        return UnsafeHeapByteBufUtil.getLong(memory, idx(index));
    }

    @Override
    protected void _setByte(int index, int value) {
        UnsafeHeapByteBufUtil.setByte(memory, idx(index), value);
    }

    @Override
    protected void _setShort(int index, int value) {
        UnsafeHeapByteBufUtil.setShort(memory, idx(index), value);
    }

    @Override
    protected void _setMedium(int index, int value) {
        UnsafeHeapByteBufUtil.setMedium(memory, idx(index), value);
    }

    @Override
    protected void _setInt(int index, int value) {
        UnsafeHeapByteBufUtil.setInt(memory, idx(index), value);
    }

    @Override
    protected void _setLong(int index, long value) {
        UnsafeHeapByteBufUtil.setLong(memory, idx(index), value);
    }

    @Override
    protected Recycler<?> recycler() {
        return RECYCLER;
    }
}
