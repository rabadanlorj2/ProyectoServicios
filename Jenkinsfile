pipeline {
    agent any
    tools {
        maven 'M3_8_2'
    }
    stages {
        stage('Build') {
            steps {
                dir('microservicio-service/'){
                    echo 'Execute Maven'
                    sh 'mvn clean package'
                }
            }
        }
    }
}