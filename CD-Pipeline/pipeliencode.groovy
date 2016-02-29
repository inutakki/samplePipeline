def configLoader;
    
    node{
        stage 'setUp'
           configLoader = fileLoader.fromGit('CD_ConfigProperties', 
                    'https://github.com/inutakki/webtest.git', 'master', null, '')
                    
        stage 'checkout'
       
           git ([url:'https://github.com/inutakki/webtest.git'])
           echo "${ws}/config.xml"
           
        stage 'BuildStep'
        
            def mvnHome=tool 'M3'
            echo "${env.M2_HOME}"
            echo "${createBuildString(configLoader)}"
    }
        
            
      
    
def createBuildString(ConfigLoaderObject){
    
def buildStep = "\${mvnHome}/bin/mvn -X clean deploy" +
                    " -Pci -s "+ConfigLoaderObject.getSettingsFilePath()+
                    " -DartifactoryURL=" +ConfigLoaderObject.getURL() +
                    " -DartifactoryPublishUser=" + ConfigLoaderObject.getPublishUser() +
                    " -DartifactoryPublishPassword="+ ConfigLoaderObject.getPublishUser() +
                    " -DreleaseNumber=" + ConfigLoaderObject.getReleaseNumber() + "\"";
    echo "${buildStep}";
    return buildStep;


    
}

