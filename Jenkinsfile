pipeline {
    agent any
    tools {
        gradle 'gradle'
        jdk "jdk17"
    }
    environment{
        VERSION = "${env.BUILD_ID}"
        NEXUS_URL = "192.168.1.100:8083"
        NEXUS_REPO = "docker-repo"
        IMAGE_NAME = "java-app"
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
        /*stage("Quality Gate") {
            steps {
                 timeout(time: 1, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline failed due to Quality Gate failure: ${qg.status}"
                        }
                    }
            }
        }*/
        stage('Login to Nexus') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'Nexus-user-pass', passwordVariable: 'Nexus_pass', usernameVariable: 'Nexus_credential')]) {
                    sh """
                    echo \$NEXUS_PASSWORD | docker login -u $Nexus_credential --password-stdin $NEXUS_URL 
                    """ 
                 }
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                sh "docker build -t $NEXUS_URL/$NEXUS_REPO/$IMAGE_NAME:$VERSION ."
            }
        }
        stage('Push to Nexus') {
            steps {
                sh "docker push $NEXUS_URL/$NEXUS_REPO/$IMAGE_NAME:$VERSION"
            }
        }
    }
}