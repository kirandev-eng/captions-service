pipeline {
    agent any

    environment {
        mavenHome = tool 'jenkins-maven'
        mavenSettingsFile = 'C:/Users/Kiran/.m2/settings.xml' // Path to your settings.xml file
        REPO = 'xtp-data-service'
    }

    tools {
        jdk 'java-17'
    }

    stages {
        stage('Build') {
            steps {
                dir(REPO) {
                    script {
                        bat "${mavenHome}/bin/mvn clean install -DskipTests -s ${mavenSettingsFile}"
                    }
                }
            }
        }

        stage('Test') {
            steps {
			 dir(REPO) {
                script {
                    bat "${mavenHome}/bin/mvn test -s ${mavenSettingsFile}"
                }
				
			}
            }
        }

        stage('Deploy') {
            steps {
			 dir(REPO) {
                script {
                    bat "${mavenHome}/bin/mvn jar:jar deploy:deploy -s ${mavenSettingsFile}"
                }
            }
			}
        }
    }
}
