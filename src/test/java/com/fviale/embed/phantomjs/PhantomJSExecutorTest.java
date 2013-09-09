/**
 * Copyright (C) 2013
 *   Facundo Viale <fviale@despegar.com>
 *
 * with contributions from
 * 	Facundo Viale (Jarlakxen@github)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fviale.embed.phantomjs;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fviale.embed.phantomjs.Configuration;
import com.fviale.embed.phantomjs.PhantomJSExecutor;

public class PhantomJSExecutorTest {

    private static String DEFAULT_TEST_JS = "console.log('TEST1');phantom.exit();";

    private static File test1 = new File(System.getProperty("java.io.tmpdir") + "/embedphantomjs.test1.js");

    @BeforeClass
    public static void setUpClass() throws IOException {
        test1.createNewFile();
        FileUtils.write(test1, DEFAULT_TEST_JS);
    }

    @AfterClass
    public static void tearDownClass() throws IOException {
        test1.delete();
    }

    @Test
    public void testWithFile() {
        PhantomJSExecutor ex = new PhantomJSExecutor(new Configuration().setCheckNativeInstallation(false));
        assertEquals("TEST1\n", ex.execute(test1));
    }

    @Test
    public void testWithString() {
        PhantomJSExecutor ex = new PhantomJSExecutor(new Configuration().setCheckNativeInstallation(false));
        assertEquals("TEST1\n", ex.execute(DEFAULT_TEST_JS));
    }
}
