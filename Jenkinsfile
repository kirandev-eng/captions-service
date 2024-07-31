pipeline {
    agent any

    environment {
        mavenHome = tool 'jenkins-maven'
        mavenSettingsFile = 'C:/Users/Kiran/.m2/settings.xml' // Path to your settings.xml file
    }

    tools {
        jdk 'java-17'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    bat "${mavenHome}/bin/mvn clean install -DskipTests -s ${mavenSettingsFile}"
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    bat "${mavenHome}/bin/mvn test -s ${mavenSettingsFile}"
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    bat "${mavenHome}/bin/mvn jar:jar deploy:deploy -s ${mavenSettingsFile}"
                }
            }
        }
    }
}
