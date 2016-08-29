/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.rackspace.clouddns.uk.features;

import org.jclouds.providers.internal.BaseProviderMetadataTest;
import org.jclouds.rackspace.clouddns.uk.CloudDNSUKProviderMetadata;
import org.jclouds.rackspace.clouddns.v1.CloudDNSApiMetadata;
import org.testng.annotations.Test;

@Test(groups = "unit", testName = "CloudDNSUKProviderTest")
public class CloudDNSUKProviderTest extends BaseProviderMetadataTest {

   public CloudDNSUKProviderTest() {
      super(new CloudDNSUKProviderMetadata(), new CloudDNSApiMetadata());
   }
}
