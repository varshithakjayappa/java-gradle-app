pipeline {
    agent {
       dockerContainer 'varshithakj/java-app:v1'
    }

    tools {
        gradle 'gradle'
        jdk "jdk17"
    }
    environment{
        VERSION = "${env.BUILD_ID}"
    }
    stages {
        stage('Clean workspace') {
            steps {
                cleanWs()
            }
        }
        stage('checkout git') {
            steps {
                git branch: 'main', url: 'https://github.com/varshithakjayappa/java-gradle-app.git'
            }
        }
        stage('gradle compile') {
            steps {
               sh './gradlew compileJava'
            }
        }
        stage('sonarqube Analysis') {
            steps {
               sh './gradlew compileJava'
            }
        }
        stage('gradle build') {
            steps {
               sh './gradlew build'
            }
        }
    }
}