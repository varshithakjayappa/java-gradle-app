pipeline {
    agent any
    tools{
        gradle 'gradle'
        jdk 'jdk17'
    }
    stages {
        stage('git checkout') {
            steps {
               git branch: 'main', url: 'https://github.com/varshithakjayappa/java-gradle-app.git'
            }
        }
        stage('build gradle') {
            steps {
               sh './gradlew build'
            }
        }
    }
}
