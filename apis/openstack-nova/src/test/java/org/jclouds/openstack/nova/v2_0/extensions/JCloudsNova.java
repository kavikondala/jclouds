package org.jclouds.openstack.nova.v2_0.extensions;

import com.google.common.collect.ImmutableSet;
import com.google.common.io.Closeables;
import com.google.common.base.Optional;
import com.google.inject.Module;
import org.jclouds.ContextBuilder;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.jclouds.openstack.nova.v2_0.NovaApi;
import org.jclouds.openstack.nova.v2_0.domain.Console;
import org.jclouds.openstack.nova.v2_0.domain.Server;
import org.jclouds.openstack.nova.v2_0.features.ServerApi;
import org.jclouds.openstack.nova.v2_0.extensions.ConsolesApi;

import java.io.Closeable;
import java.io.IOException;
import java.util.Set;

public class JCloudsNova {
    private final NovaApi novaApi;
    private final Set<String> regions;

    public static void main(String[] args) throws IOException {
        JCloudsNova jcloudsNova = new JCloudsNova();

        try {
            jcloudsNova.listServers();
            jcloudsNova.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jcloudsNova.close();
        }
    }

    public JCloudsNova() {
    
    /* if you want http debug information of jclouds enable the bellow module and add this 
       module to ContextBuilder.*/

//      Iterable<Module> modules = ImmutableSet.<Module>of(new SLF4JLoggingModule());
        String provider = "openstack-nova";
        String identity = "demo:demo"; // tenantName:userName
        String credential = "user123#";

        novaApi = ContextBuilder.newBuilder(provider)
                .endpoint("http://10.2.195.117:5000/v2.0/")
                .credentials(identity, credential)
//              .modules(modules)
                .buildApi(NovaApi.class);
        regions = novaApi.getConfiguredRegions();
    }

    private void listServers() {

        for (String region : regions) {

            ServerApi serverApi = novaApi.getServerApi(region);
            System.out.println("\r\n[" + region + "]  Start\r\n");

            for (Server server : serverApi.listInDetail().concat()) {


		Optional<? extends ConsolesApi> apiOption = novaApi.getConsolesApi(region);

		if (!apiOption.isPresent()) {
			System.err.println("Consoles extension not present in server");
			continue;
		}
    
                ConsolesApi api= apiOption.get();
      
                Console console = api.getConsole(server.getId(),Console.Type.NOVNC);
                String Status = server.getStatus().toString(); 
                System.out.println("Name    :" + server.getName().toString());
                System.out.println("Status  :" + Status);
                System.out.println("UUId    :" + server.getId());
                System.out.println("Console :" + console.getUrl());
          /*      if( Status == "SHUTOFF"){
                      serverApi.start(server.getId());
                }
                else{
                     serverApi.stop(server.getId());
                }  */
                System.out.println("\r\n");
            
            }
            System.out.println("\r\n[" + region + "]  End\r\n");
        }
    }

    public void close() throws IOException {
        Closeables.close(novaApi, true);
    }
}
