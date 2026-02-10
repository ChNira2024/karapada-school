pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'karapada-school'
        DOCKER_TAG = 'latest'
        DOCKER_REGISTRY = '' // Optional if pushing to Docker Hub: e.g., 'username'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/ChNira2024/karapada-school.git'
            }
        }

        stage('Build') {
            steps {
                // Build the Spring Boot app using Maven
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image from Dockerfile in project root
                    sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                }
            }
        }

        stage('Run Docker Container (Optional)') {
            steps {
                script {
                    // Stop and remove old container if it exists
                    sh "docker rm -f karapada-school || true"

                    // Run new container
                    sh "docker run -d -p 2424:2424 --name karapada-school ${DOCKER_IMAGE}:${DOCKER_TAG}"
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
