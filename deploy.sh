projectPath=/home/enikyasta/Documentos/NetBeansProjects/greenSoftware/
war=/home/enikyasta/Documentos/NetBeansProjects/greenSoftware/target/greenSoftware.war
glassFishServer=/home/enikyasta/Descargas/glassfish7/glassfish/bin/asadmin;
domainOneLogs=/home/enikyasta/Descargas/glassfish7/glassfish/domains/domain_one/logs/server.log;
app="testingTest";

cleanLogs(){
    echo "" > ${domainOneLogs};
}

mavenBuild(){
    mvn clean;
    mvn install;
}

redeploy(){
    mavenBuild
    bash ${glassFishServer} redeploy --name greenSoftware ${war};
    echo "Succes";
    cleanLogs;
    echo "Logs cleaned";
}

redeploy
