/*
 * (C) Copyright 2025 VeriSoft (http://www.verisoft.ai)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
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
package co.verisoft.examples;

import co.verisoft.fw.asserts.Asserts;
import co.verisoft.fw.store.StoreManager;
import co.verisoft.fw.store.StoreType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for co.verisoft.fw.store
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.2 (Jan 2022)
 */
@Execution(ExecutionMode.CONCURRENT)
public class StoreTest extends BaseTest{

    @Test
    public void shouldSaveAndRetrieveValueFromGlobalStore() {
        String name = "Nir";
        StoreManager.getStore(StoreType.GLOBAL).putValueInStore("NIR", name);
        String receivedName = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore("NIR");

        // Note!! Verisoft Assert
        Asserts.assertEquals(name, receivedName, "Should have retrieved from store");
    }


    @Test
    public void shouldSaveAndRetrieveValueFromLocalStore() {
        String name = "Irit";
        StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("IRIT", name);
        String receivedName = StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("IRIT");

        // Note!! Verisoft Assert
        Asserts.assertEquals(name, receivedName, "Should have retrieved from store");
    }

    @Test
    public void shouldUseOBject() {
        Object key = new Object();
        String name = "Nir";
        StoreManager.getStore(StoreType.GLOBAL).putValueInStore(key, name);
        String receivedName = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore(key);

        // Note!! Verisoft Assert
        Asserts.assertEquals(name, receivedName, "Should have retrieved from store");
    }

    @Test
    public void shouldCreateUniqueStore() {
        String key = "key";
        StoreManager.getStore("unique").putValueInStore(key, "value");
        String receivedNameGlobal = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore(key);
        String receivedNameLocal = StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore(key);

        // Note!! Verisoft Assert
        Asserts.assertNull(receivedNameGlobal, "Should not be retrieved from store");
        Asserts.assertNull(receivedNameLocal, "Should not be retrieved from store");
    }


    @Test
    public void shouldNotUse2OBject() {
        Object key1 = new Object();
        Object key2 = new Object();
        String name = "Nir";
        StoreManager.getStore(StoreType.GLOBAL).putValueInStore(key1, name);
        String receivedName = StoreManager.getStore(StoreType.GLOBAL).getValueFromStore(key2);

        // Note!! Verisoft Assert
        Asserts.assertNull(receivedName, "Should have retrieved from store");
    }

    @Test
    public void shouldReplaceValueInStore() {

        StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("key", "val1");
        StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("key", "val2");

        String receivedName = StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("key");

        // Note!! Verisoft Assert
        Asserts.assertEquals(receivedName, "val2", "Store should replace the original value");
    }

    @Test
    public void shouldNotNeedToCast(){
        List<Integer> list = new ArrayList<>();
        list.add(3);

        StoreManager.getStore(StoreType.LOCAL_THREAD).putValueInStore("integerList", list);


        List<Integer> receivedList = StoreManager.getStore(StoreType.LOCAL_THREAD).getValueFromStore("integerList");
        Asserts.assertEquals(receivedList.size(), 1, "should be only one value");
    }
}
