def configLoader;
    
    node{
        stage 'setUp'
           configLoader = fileLoader.fromGit('CD_ConfigProperties', 
                    'https://github.com/inutakki/webtest.git', 'master', '08cfb8a6-8848-4530-90c0-8b6be0588179', '')
                    
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

