/*
 * Copyright © 2016 Tinkoff Bank
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.tinkoff.decoro;

import android.os.Parcel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import ru.tinkoff.decoro.slots.PredefinedSlots;
import ru.tinkoff.decoro.slots.Slot;

/**
 * @author Mikhail Artemev
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class MaskDescriptorTest {

    @Test
    public void parcelable() {
        MaskDescriptor before = new MaskDescriptor()
                .setForbidInputWhenFilled(true)
                .setHideHardcodedHead(true)
                .setRawMask("___-___")
                .setSlots(new Slot[]{PredefinedSlots.digit()})
                .setTerminated(false);

        Parcel parcel = Parcel.obtain();
        before.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);

        MaskDescriptor after = MaskDescriptor.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(before, after);

        parcel.recycle();
    }

}
