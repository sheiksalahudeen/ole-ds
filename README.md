Building the application to set up solr home directory:

1. mvn clean install -DskipTests=true -Denv=local [This copies the configuration files to the location set in config.properties for solr.solr.home]


VM Options

-Xms512m -Xmx5g -XX:MaxPermSize=512m (Optional or set it as per your hardware)

-Dsolr.solr.home = [Specify your solr user home directory]; This should be whatever you set in the config.properties for solr.solr.home
 
-Denv=local [This is needed]
 
 
 Starting jetty server:
 
 1. jetty:run followed by the VM Options mentioned above.