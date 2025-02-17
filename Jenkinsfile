pipeline {
    agent any
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
        stage('Build & Run Tests') {
            steps {
                sh './gradlew clean build jacocoTestReport'
            }
        }
        stage('sonarqube Analysis') {
            steps {
               withSonarQubeEnv(installationName: 'sonarserver',credentialsId: 'sonar-token') {
                 sh "./gradlew sonar"
            }
        }
        }
        stage("Quality Gate") {
            steps {
                 timeout(time: 1, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline failed due to Quality Gate failure: ${qg.status}"
                        }
                    }
            }
        }
        stage('gradle build') {
            steps {
               sh './gradlew build'
            }
        }
    }
}