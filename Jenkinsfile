pipeline {
    agent any

    stages {

        stage('Clone') {
            steps {
                echo 'Code Pulled From GitHub'
            }
        }

        stage('Build') {
            steps {
                sh 'echo Building Application'
            }
        }

        stage('Test') {
            steps {
                sh 'echo Testing Successful'
            }
        }
    }
}
