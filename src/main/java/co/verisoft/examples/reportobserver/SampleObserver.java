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
package co.verisoft.examples.reportobserver;

import co.verisoft.fw.report.observer.BaseObserver;
import co.verisoft.fw.report.observer.ReportEntry;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * A sample observer. It holds a list of entries (string). Upon update invocation, it will add an entry to the list
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.5 (Jan 2023)
 */
public class SampleObserver extends BaseObserver {

    @Getter
    private final List<String> entries;


    public SampleObserver() {
        super();
        entries = new ArrayList<>();
    }


    @Override
    public void update(ReportEntry reportEntry) {
        entries.add("SampleObserver data is " + reportEntry.toString());
    }
}
